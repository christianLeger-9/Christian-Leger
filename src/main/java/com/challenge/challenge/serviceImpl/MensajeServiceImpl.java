package com.challenge.challenge.serviceImpl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.challenge.entity.Contacto;
import com.challenge.challenge.entity.Mensajes;
import com.challenge.challenge.repository.ContactoRepository;
import com.challenge.challenge.repository.MensajeRepository;
import com.challenge.challenge.service.MensajeService;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

@Service
public class MensajeServiceImpl implements MensajeService {
	
	@Autowired
	ContactoRepository contactoRepository;
	
	@Autowired
	MensajeRepository mensajeRepository;
	
	private PushService pushService;

	private String publicKey = "BAG1Sd1OjTTuVbSTslZaSRZxJB3lWS8wBBXnWx_TDoDHg8M2HV40Vpv_3Qgkh9kBTiD6SGRj6VTiyBdGLrPNwFI";
	private String privateKey = "V0uGsbT6SF4C86Vb1dtvXJyNv5urvWaSQvZR0J4Su0Q";
	
	@PostConstruct
	private void init() throws GeneralSecurityException {
	    Security.addProvider(new BouncyCastleProvider());
	    pushService = new PushService(publicKey, privateKey);
	}

	public String getPublicKey() {
		return publicKey;
	}

	@Override
	public boolean newMessage(Mensajes m) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, GeneralSecurityException, JoseException, ExecutionException, InterruptedException {
		mensajeRepository.save(m);
		sendNotifications(m.getIdContacto());
		return true;
	}
	
	public void sendNotification(Subscription subscription, String messageJson) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, GeneralSecurityException, JoseException, ExecutionException, InterruptedException {
	    try {
	      pushService.send(new Notification(subscription, messageJson));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

    private void sendNotifications(Long id) 
		  throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, 
		  GeneralSecurityException, JoseException, ExecutionException, InterruptedException {
	    System.out.println("Enviando notificacion a todos los contactos del grupo");
	    
	    Iterable<Contacto> contactos = contactoRepository.findAll();
		for (Iterator<Contacto> iterator = contactos.iterator(); iterator.hasNext();) {
			Contacto c = (Contacto) iterator.next();
			if (!c.getId().equals(id)) {
				Subscription sub = new Subscription();
				sendNotification(sub, "Usted tiene un mensaje nuevo en el grupo");
			}
		}
    }
}
