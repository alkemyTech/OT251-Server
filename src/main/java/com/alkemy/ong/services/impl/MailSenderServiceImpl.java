package com.alkemy.ong.services.impl;

import com.alkemy.ong.services.IMailSenderService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements IMailSenderService{
    
    private String email_sender = "";
    private String key = "";  //set a string with your sendgrid key value 
    
    @Override
    public void sendMailRegister(String email) {
        Email from = new Email(email_sender);
        Email toEmail = new Email(email);
        
        String subject = "Bienvenido a Somos Más";
        Content content = new Content();
        Mail mail = new Mail(from, subject, toEmail, content);
        sendMail(mail);
    }
    
    public void sendMail(Mail mail){
        SendGrid sendGrid = new SendGrid(key);
        Request request = new Request();
        
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException e) {
            System.out.println("Error trying to send email");
        }
    }

}
