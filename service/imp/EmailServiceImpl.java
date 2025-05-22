package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import co.edu.unbosque.service.api.EmailServiceAPI;
@Service
public class EmailServiceImpl  implements EmailServiceAPI{
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void EnviarCorreo(String destinatario, String asunto, String mensaje) {
		// TODO Auto-generated method stub
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(asunto);
        email.setText(mensaje);
        email.setFrom("tuscompras.market@gmail.com");
        mailSender.send(email);
		
        
	} 
	
	
	

}
