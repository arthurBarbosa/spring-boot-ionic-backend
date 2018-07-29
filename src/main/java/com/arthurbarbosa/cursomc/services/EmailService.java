package com.arthurbarbosa.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.arthurbarbosa.cursomc.domain.Cliente;
import com.arthurbarbosa.cursomc.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
    
    void sendNewPasswordEmail(Cliente cliente,String newPass);
}
