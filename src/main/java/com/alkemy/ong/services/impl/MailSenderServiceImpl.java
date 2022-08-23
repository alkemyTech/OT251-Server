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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailSenderServiceImpl implements IMailSenderService{

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${sendgrid.email-sender}")
    private String email_sender;

    @Value("${sendgrid.api-key}")
    private String key;
    
    @Override
    public void sendMailRegister(String email) {
        Context context = new Context();
        String content = templateEngine.process("plantilla_email", context);
        String subject = "Bienvenido a Somos Más";
        String contentType = "text/html";
        Mail mail = prepareMail(email, subject, contentType, content);
        sendMail(mail);
    }

    @Override
    public void sendMailContact(String email) {
        String subject = "Gracias por comunicarte";
        String contentType = "text/plain";
        String content = "Muchas gracias por comunicarte con nosotros. Analizaremos tu consulta y te responderemos a la brevedad.";
        Mail mail = prepareMail(email, subject, contentType, content);
        sendMail(mail);
    }

    public Mail prepareMail(String to, String subject, String contentType, String content){
        Email fromEmail = new Email(email_sender);
        Email toEmail = new Email(to);
        Content contentEmail = new Content(contentType, content);
        Mail mail = new Mail(fromEmail, subject, toEmail, contentEmail);
        return mail;
    }

    public void sendMail(Mail mail){
        SendGrid sendGrid = new SendGrid(key);
        Request request = new Request();
        
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println("Email sent successfully");
        } catch (IOException e) {
            System.out.println("Error trying to send email");
        }
    }

}
