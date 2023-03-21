package com.smu.antisocial.Bid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.lowagie.text.DocumentException;
import com.smu.antisocial.Equipment.Equipment;
import com.smu.antisocial.Equipment.EquipmentRepository;
import com.smu.antisocial.Request.Request;
import com.smu.antisocial.Request.RequestRepository;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BidService {
    @Autowired
    private final BidRepository bidRepository;

    @Autowired
    private final RequestRepository requestRepository;

    @Autowired
    private final EquipmentRepository equipmentRepository;


    public List<Bid> getBids(){
        return bidRepository.findAll();
	}

    public Bid createBid(Bid bid){
		return bidRepository.save(bid);
	}

    public void deleteBid(Integer bidID){
		bidRepository.deleteById(bidID);
	}

    public List<Bid> getLowestBids(Integer requestID){
        List<Bid> bidsList = bidRepository.findTop3ByRequestIDOrderByTotalPriceAsc(requestID);
        return bidsList;
    }

    @Transactional
    public void updateStatus(Integer bidID){
        Bid bid = bidRepository.findById(bidID).orElseThrow(
			() -> new IllegalStateException("Bid with ID " + bidID + " does not exist"));

        Integer requestID = bid.getRequestID();
        
        List<Bid> bidList = bidRepository.findAllByRequestID(requestID);
        for(Bid b : bidList){
            b.setStatus("unsuccessful");
        }
        bid.setStatus("success");
	}

    // @Scheduled(cron = "0/5 * * * * ?")
    @Transactional
    @Scheduled(cron = "0 0 12 * * *")
    public void updateMinBids(){
        List<Equipment> equipments = equipmentRepository.findAll();
        for(Equipment equipment : equipments){
            ArrayList<Double> result = getSuggestedBids(equipment.getEquipmentID());
            if(result.size() > 0){
                if(result.get(0) != 0){
                    equipment.setPerTripPrice(result.get(0));
                }

                if(result.get(1) != 0){
                    equipment.setPerHrPrice(result.get(1));
                }

                if(result.get(2) != 0){
                    equipment.setFullDayPrice(result.get(2));
                }

                if(result.get(3) != 0){
                    equipment.setRateAfter5Price(result.get(3));
                }

                if(result.get(4) != 0){ 
                    equipment.setRateAfter10Price(result.get(4));
                }

                if(result.get(5) != 0){
                    equipment.setSundayPHPrice(result.get(5));
                }
            }
        }
    }

    public ArrayList<Double> getSuggestedBids(Integer equipmentID){

        int count = 0;
        ArrayList<Double> result = new ArrayList<>();
        List<Bid> successfulBids = bidRepository.getSuccessfulBids();
        
        for(Bid bid : successfulBids){
            Integer requestID = bid.getRequestID();
            Request request = requestRepository.getOne(requestID);
            if(request.getEquipmentID() == equipmentID){

                try{
                    result.get(0);
                    result.set(0, result.get(0)+bid.getPerTripPrice());
                }catch (IndexOutOfBoundsException e) {
                    try{
                        result.add(0, bid.getPerTripPrice());
                    }
                    catch (NullPointerException npe){
                        continue;
                    }
                }

                try{
                    result.get(1);
                    result.set(1, result.get(0)+bid.getPerHrPrice());
                }catch (IndexOutOfBoundsException e) {
                    try{
                        result.add(1, bid.getPerHrPrice());
                    }
                    catch (NullPointerException npe){
                        continue;
                    }
                }

                try{
                    result.get(2);
                    result.add(2, result.get(2)+bid.getFullDayPrice());
                }catch (IndexOutOfBoundsException e) {
                    try{
                        result.add(2, bid.getFullDayPrice());
                    }
                    catch (NullPointerException npe){
                        continue;
                    }
                }

                try{
                    result.get(3);
                    result.add(3, result.get(3)+bid.getRateAfter5Price());
                }catch (IndexOutOfBoundsException e) {
                    try{
                        result.add(3, bid.getRateAfter5Price());
                    }
                    catch (NullPointerException npe){
                        continue;
                    }
                }

                try{
                    result.get(4);
                    result.add(4, result.get(4)+bid.getRateAfter10Price());
                }catch (IndexOutOfBoundsException e) {
                    try{
                        result.add(4, bid.getRateAfter10Price());
                    }
                    catch (NullPointerException npe){
                        continue;
                    }
                }

                try{
                    result.get(5);
                    result.add(5, result.get(5)+bid.getSundayPHPrice());
                }catch (IndexOutOfBoundsException e) {
                    try{
                        result.add(5, bid.getSundayPHPrice());
                    }
                    catch (NullPointerException npe){
                        continue;
                    }
                }
                count++;
            }
        }

        for(int i = 0; i < result.size(); i++){
            try{
                result.get(i);
                result.set(i, (result.get(i)/count));
            }catch(IndexOutOfBoundsException e){
                continue;
            }
        }

        return result;
    }

    private String parseInvoice(Integer bidID, String type) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
    
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Bid bid = bidRepository.getOne(bidID);
        Request request = requestRepository.getOne(bid.getRequestID());
        Integer quantity = request.getQuantity();
        String equipment = equipmentRepository.getOne(request.getEquipmentID()).getEquipmentName();
        double totalPrice = bid.getTotalPrice();
        
        Context context = new Context();
        context.setVariable("quantity", quantity);
        context.setVariable("equipment", equipment);
        context.setVariable("invoice_no", "#100" + bid.getRequestID());
        context.setVariable("price", "$" + totalPrice);
        context.setVariable("total_price", "$" + totalPrice);

        if(type.equals("do")){
            return templateEngine.process("delivery_order", context);
        }
    
        return templateEngine.process("purchase_order", context);
    }

    public void generatePdfFromHtml(Integer bidID, String type) throws UnsupportedEncodingException {
        String html = parseInvoice(bidID, type);
        String output = "";
        if(type.equals("do")){
            output = System.getProperty("user.dir") + File.separator + "src/main/resources/static/files/do/" + bidID + ".pdf"; 
        }
        else{
            output = System.getProperty("user.dir") + File.separator + "src/main/resources/static/files/po/" + bidID + ".pdf"; 
        }
        try(OutputStream outputStream = new FileOutputStream(output)){
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html, new ClassPathResource("/static/").getURL().toExternalForm());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(DocumentException e){
            System.out.println(e.getMessage());
        }
    }
}
