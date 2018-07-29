package com.arthurbarbosa.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.arthurbarbosa.cursomc.domain.Cliente;
import com.arthurbarbosa.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj){
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! código: " +obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;

    }
    
    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPass) {
    	SimpleMailMessage mailMessage = prepareNewPasswordEmail(cliente, newPass);
    	sendEmail(mailMessage);
    }
    
    protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente , String newPass){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitaçãp nova senha. " );
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;

    }

}
