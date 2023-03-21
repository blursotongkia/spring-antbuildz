package com.smu.antisocial.Email;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import com.smu.antisocial.Partner.Partner;
import com.smu.antisocial.Partner.PartnerRepository;

import com.smu.antisocial.User.User;
import com.smu.antisocial.User.UserRepository;

@RestController
@RequestMapping(path = "api/v1/email")
@AllArgsConstructor
public class EmailController {
    
    @Autowired
    private final PartnerRepository partnerRepository;

    @Autowired
    private final UserRepository userRepository;

    @PostMapping
    private void sendmail(@RequestParam String email, @RequestParam Integer userid, @RequestParam String token, @RequestParam Integer type) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("projectavocados@gmail.com", "12345678a!");
           }
        });

        List<Partner> partnerList = partnerRepository.findAll();

        List<String> emailPartnerList = new ArrayList<>();

        for (Partner partner : partnerList) {
            User user = userRepository.getOne(partner.getUserid());
            emailPartnerList.add(user.getEmail());
        }

        String emailList = emailPartnerList.stream().collect(Collectors.joining(","));

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("projectavocados@gmail.com", true));
     
        if(type == 0){
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Antisocial - Verification Token");
            msg.setContent("Please verify your account by clicking this link " + "http://localhost:8080/activate_user.html" + "?token=" + token + "&userid=" + userid, "text/html");
            msg.setSentDate(new Date());
        }
        else if(type == 1){
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailList));
            msg.setSubject("Rental Request");
            msg.setContent("There is a new Rental Request. If interested, please login at http://localhost:8080/login.html for more information.", "text/html");
            msg.setSentDate(new Date());
        }
        else if(type == 2){
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Rental Confirmation");
            msg.setContent("Hi the rental ID " + userid + " has been duly paid in time. Please make the necessary arrangements" , "text/html");
            msg.setSentDate(new Date());
        }
        else if(type == 4){
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Rental Confirmation");
            msg.setContent("Thank you for your payment please download the confirmation at http://localhost:8080/files/po/" + userid + ".pdf ", "text/html");
            msg.setSentDate(new Date());
        }
        else{
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Rental Reservation");
            msg.setContent("Congraulations your bid has been selected!, please download and see your reservation at http://localhost:8080/files/do/" + userid + ".pdf" , "text/html");
            msg.setSentDate(new Date());
        }
        
        Transport.send(msg);
    }

    public void bidResults(String email) throws AddressException, MessagingException, IOException{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("projectavocados@gmail.com", "12345678a!");
           }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("projectavocados@gmail.com", true));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Bidding Results");
        msg.setContent("Hi there! Your results for a recent bid is ready. Please login at http://localhost:8080/login.html to view the bids now!", "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }
}
