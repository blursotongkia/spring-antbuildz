package com.smu.antisocial.Request;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.smu.antisocial.Email.EmailController;
import com.smu.antisocial.Equipment.Equipment;
import com.smu.antisocial.Equipment.EquipmentRepository;
import com.smu.antisocial.PublicHoliday.PublicHolidayService;
import com.smu.antisocial.User.User;
import com.smu.antisocial.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestService {
    @Autowired
    private final RequestRepository requestRepository;

    @Autowired
    private final EquipmentRepository equipmentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PublicHolidayService publicHolidayService;

    private EmailController emailController;

    public List<Request> getRequests(){
        return requestRepository.findAll();
	}

    public Request createRequest(Request request){
        Request r = requestRepository.save(request);
        User user = userRepository.getOne(request.getUserid());
        System.out.println(user);
        new RequestTimer(r, requestRepository, user, emailController);

        return r;
	}

    @Transactional
    public void updateStatus(Request request){
        request.setActive(true);
    }

    public void deleteRequest(Integer requestid){
		requestRepository.deleteById(requestid);
	}

    public List<Request> getRequestByUserId(Integer userid){
        return requestRepository.getRequestByUserId(userid);
    }

    public double getTruncatedHours(Long minutes) {
        double hours = (double)minutes / 60;
        return hours;
    }

    public Map<String, Object> getBreakdown(Integer requestid) {

        Request request = requestRepository.getOne(requestid);
        Integer equipmentId = request.getEquipmentID();
        String pricingType = request.getPricingType();
        Equipment equipment = equipmentRepository.getOne(equipmentId);
        Integer quantity = request.getQuantity();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String startStr = request.getStartDateTime();
        String endStr = request.getEndDateTime();
        String[] startList = startStr.split(" ");
        LocalDateTime startDateTime = LocalDateTime.parse(startStr, formatter);
        String newStart = startList[0];        

        Map<String, Object> breakdown = new HashMap<String, Object>();

        // perTripPrice : no end date
        // fullDayPrice: flat rate/Day
        // SUN / PH : rateAfter10PRice/hr
        // perHrPrice:  less than 5pm, + perTripPrice
        // rateAfter5Price: 1.5 of perHrPrice
        // rateAfter10Price: 2x of perHrPrice

        if(endStr.equals("00/00/0000 00:00")){
            
            double total = equipment.getPerTripPrice();

            breakdown.put("Pricing Type", "Per Trip");
            breakdown.put("startDateTime", startDateTime);
            breakdown.put("Rate", total);
            breakdown.put("Quantity", quantity);
            breakdown.put("Id", requestid);

            return breakdown;

        } else if (pricingType.equals("Full Day")){

            LocalDateTime endDateTime = LocalDateTime.parse(endStr, formatter);
            long days = ChronoUnit.DAYS.between(startDateTime, endDateTime) + 1;
            double total = equipment.getFullDayPrice();

            breakdown.put("Pricing Type", "Full Day");
            breakdown.put("startDateTime", startDateTime);
            breakdown.put("endDateTime", endDateTime);
            breakdown.put("Rate", total);
            breakdown.put("Days", days);
            breakdown.put("Quantity", quantity);
            breakdown.put("Id", requestid);

            return breakdown;

        } else {

            LocalDateTime endDateTime = LocalDateTime.parse(endStr, formatter);
            String[] endList = endStr.split(" ");
            String newEnd = endList[0];

            LocalDateTime after5 = LocalDateTime.parse(newEnd + " 17:00",formatter);
            LocalDateTime after10 = LocalDateTime.parse(newEnd + " 20:00",formatter);
            LocalDateTime endOfDay = LocalDateTime.parse(newStart + " 24:00",formatter);
            LocalDateTime startOfDay = LocalDateTime.parse(newEnd + " 08:00",formatter);
            LocalDate startDate = LocalDate.parse(newStart, dateFormatter);
            LocalDate endDate = LocalDate.parse(newEnd, dateFormatter);

            List<String> phDates = publicHolidayService.getPublicHolidayDates();

            double phCost = equipment.getSundayPHPrice();
            double after5cost = equipment.getRateAfter5Price();
            double after10cost = equipment.getRateAfter10Price();
            double perHrPrice = equipment.getPerHrPrice();

            if (startDate.equals(endDate) & !(phDates.contains(startDate.toString()) & !(startDate.getDayOfWeek() == DayOfWeek.SUNDAY))) { // if only one day & not sunday or ph

                if (endDateTime.isBefore(after5)) { //both before 5
                    double hrBefore5pm = 0.0;
                    long minutes = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
                    hrBefore5pm += getTruncatedHours(minutes);

                    breakdown.put("Time After 3Hrs", hrBefore5pm);

                } else if (endDateTime.isBefore(after10)) { //endDateTime before 10

                    if (startDateTime.isBefore(after5)) { //if startDateTime before 5 and endDateTime before 10

                        double hrBefore5pm = 0.0;
                        double hrBefore10pm = 0.0;

                        long minutesbefore5 = ChronoUnit.MINUTES.between(startDateTime, after5);
                        long minutesafter5before10 = ChronoUnit.MINUTES.between(after5, endDateTime);

                        hrBefore5pm += getTruncatedHours(minutesbefore5);
                        hrBefore10pm += getTruncatedHours(minutesafter5before10);

                        breakdown.put("Time After 3Hrs", hrBefore5pm);
                        breakdown.put("Rate after 5pm (1.5x)", hrBefore10pm);

                    } else { //if startDateTime after 5 and endDateTime before 10

                        double hrBefore10pm = 0.0;
                        long minutesafter5before10 = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
                        hrBefore10pm += getTruncatedHours(minutesafter5before10);

                        breakdown.put("Rate after 5pm (1.5x)", hrBefore10pm);
                    }

                } else {  //endDateTime after 10

                    if (startDateTime.isBefore(after5)) { //if startDateTime before 5 and endDateTime after 10

                        double hrBefore5pm = 0.0;
                        double hrBefore10pm = 0.0;
                        double hrAfter10pm = 0.0;

                        long minutesbefore5 = ChronoUnit.MINUTES.between(startDateTime, after5);
                        long minutesafter5before10 = ChronoUnit.MINUTES.between(after5, after10);
                        long minutesafter10 = ChronoUnit.MINUTES.between(after10, endDateTime);

                        hrBefore5pm += getTruncatedHours(minutesbefore5);
                        hrBefore10pm += getTruncatedHours(minutesafter5before10);
                        hrAfter10pm += getTruncatedHours(minutesafter10);

                        breakdown.put("Time After 3Hrs", hrBefore5pm);
                        breakdown.put("Rate after 5pm (1.5x)", hrBefore10pm);
                        breakdown.put("Rate after 10pm Onwards (2x)", hrAfter10pm);


                    } else if (startDateTime.isBefore(after10)) { //if startDateTime after 5 and endDateTime after 10

                        double hrBefore10pm = 0.0;
                        double hrAfter10pm = 0.0;

                        long minutesafter5before10 = ChronoUnit.MINUTES.between(startDateTime, after10);
                        long minutesafter10 = ChronoUnit.MINUTES.between(after10, endDateTime);

                        hrBefore10pm += getTruncatedHours(minutesafter5before10);
                        hrAfter10pm += getTruncatedHours(minutesafter10);

                        breakdown.put("Rate after 5pm (1.5x)", hrBefore10pm);
                        breakdown.put("Rate after 10pm Onwards (2x)", hrAfter10pm);

                    } else { //if startDateTime after 10 and endDateTime after 10

                        double hrAfter10pm = 0.0;

                        long minutesafter10 = ChronoUnit.MINUTES.between(startDateTime, endDateTime);

                        hrAfter10pm += getTruncatedHours(minutesafter10);

                        breakdown.put("Rate after 10pm Onwards (2x)", hrAfter10pm);
                    }
                
                }
                    
            } else if (startDate.equals(endDate) & phDates.contains(startDate.toString()) | startDate.getDayOfWeek() == DayOfWeek.SUNDAY) { // if only one day & is sunday or ph
                
                double hrSundayOrPH = 0.0;
                
                long minutes = ChronoUnit.MINUTES.between(startDateTime, endDateTime);

                hrSundayOrPH += getTruncatedHours(minutes);

                breakdown.put("Sunday & PH (/Hr)", hrSundayOrPH);

            } else { // multiple days

                double hrSundayOrPH = 0.0;
                double hrBefore5pm = 0.0;
                double hrBefore10pm = 0.0;
                double hrAfter10pm = 0.0;

                for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) { //for multiple days

                    if (date != startDate | date != endDate){ //check inbetween start and end dates (cfm full day)
                        if(date.getDayOfWeek() == DayOfWeek.SUNDAY | phDates.contains(date.toString())){ // if date is sun or PH        
                            hrSundayOrPH += 24.0;
                        } else {
                            hrBefore5pm += 17.0;
                            hrBefore10pm += 5.0;
                            hrAfter10pm += 2.0;
                        }
                    } else {
                        if (date.equals(startDate)) {

                            if (startDateTime.isBefore(after5)) { // before 5 to midnight 
        
                                long minutesbefore5 = ChronoUnit.MINUTES.between(startDateTime, after5);
                                long minutesafter5before10 = ChronoUnit.MINUTES.between(after5, after10);
                                long minutesafter10 = ChronoUnit.MINUTES.between(after10, endOfDay);
        
                                hrBefore5pm += getTruncatedHours(minutesbefore5);
                                hrBefore10pm += getTruncatedHours(minutesafter5before10);
                                hrAfter10pm += getTruncatedHours(minutesafter10);

                            } else if(startDateTime.isAfter(after5)){ // after 5 to midnight 
                                
                                long minutesafter5before10 = ChronoUnit.MINUTES.between(startDateTime, after10);
                                long minutesafter10 = ChronoUnit.MINUTES.between(after10, endOfDay);

                                hrBefore10pm += getTruncatedHours(minutesafter5before10);
                                hrAfter10pm += getTruncatedHours(minutesafter10);

                            } else {

                                long minutesafter10 = ChronoUnit.MINUTES.between(after10, endOfDay);
                                
                                hrAfter10pm += getTruncatedHours(minutesafter10);

                            }
            
                        } else if (date.equals(endDate)) {

                            if(endDateTime.isBefore(after5)){ // before 5 to endDateTime

                                long minutesbefore5 = ChronoUnit.MINUTES.between(startOfDay,endDateTime);

                                hrBefore5pm += getTruncatedHours(minutesbefore5);
    
                            } else if (endDateTime.isAfter(after5) & endDateTime.isBefore(after10)){ // after 5 to endDateTime (before 10)

                                long minutesbefore5 = ChronoUnit.MINUTES.between(startOfDay,after5);
                                long minutesbefore10 = ChronoUnit.MINUTES.between(after5,endDateTime);

                                hrBefore5pm += getTruncatedHours(minutesbefore5);
                                hrBefore10pm += getTruncatedHours(minutesbefore10);

                            } else {
                                long minutesbefore5 = ChronoUnit.MINUTES.between(startOfDay,after5); // after 5 to endDateTime (after 10)
                                long minutesbefore10 = ChronoUnit.MINUTES.between(after5,after5);
                                long minutesafter10 = ChronoUnit.MINUTES.between(after10,endDateTime);

                                hrBefore5pm += getTruncatedHours(minutesbefore5);
                                hrBefore10pm += getTruncatedHours(minutesbefore10);
                                hrAfter10pm += getTruncatedHours(minutesafter10);
    
                            }
                        }
                    }
                }

                breakdown.put("Pricing Type", "Custom");
                breakdown.put("SUN & PH (/Hr)", hrSundayOrPH);
                breakdown.put("Time After 3Hrs", hrBefore5pm);
                breakdown.put("Rate after 5pm (1.5x)", hrBefore10pm);
                breakdown.put("Rate after 10pm Onwards (2x)", hrAfter10pm);
                breakdown.put("Rate1",after5cost);
                breakdown.put("Rate2",after10cost);
                breakdown.put("Rate3",phCost);
                breakdown.put("Rate4",perHrPrice);
                breakdown.put("Quantity", quantity);
                breakdown.put("Id", requestid);

            }
            return breakdown;
        }
    }
}
