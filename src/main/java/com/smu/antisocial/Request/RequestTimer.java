package com.smu.antisocial.Request;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.smu.antisocial.Email.EmailController;
import com.smu.antisocial.User.User;


public class RequestTimer {

    public RequestTimer(Request request, RequestRepository requestRepository, User user, EmailController emailController){
        LocalDateTime oneDayLater = LocalDateTime.now().plusDays(1);
        Date oneDayLaterAsDate = Date.from(oneDayLater.atZone(ZoneId.systemDefault()).toInstant());
        new Timer().schedule(new UpdateTask(request, requestRepository, user, emailController), oneDayLaterAsDate);
    }

    class UpdateTask extends TimerTask {
        private Request request;
        private RequestRepository requestRepository;
        private EmailController emailController;
        private User user;

        public UpdateTask(Request request, RequestRepository requestRepository, User user, EmailController emailController){
            this.request = request;
            this.requestRepository = requestRepository;
            this.user = user;
            this.emailController = emailController;
        }

        public void run() {
            request.setActive(false);
            requestRepository.save(request);
            try{
                emailController.bidResults(user.getEmail());
            }
            catch(AddressException e){
                System.out.println(e.getMessage());
            }
            catch(MessagingException e){
                System.out.println(e.getMessage());
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Bidding Closed, 24 hours is up ...");
        }
    }
}
