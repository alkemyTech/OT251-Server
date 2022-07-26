package com.alkemy.ong.services;

import com.sendgrid.helpers.mail.Mail;

public interface IMailSenderService {
    
    public void sendMailRegister(String email);
    
    public void sendMail(Mail mail);
    
    
}
