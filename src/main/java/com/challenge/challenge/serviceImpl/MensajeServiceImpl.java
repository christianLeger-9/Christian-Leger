package com.challenge.challenge.serviceImpl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.challenge.entity.Contacto;
import com.challenge.challenge.entity.Mensajes;
import com.challenge.challenge.repository.ContactoRepository;
import com.challenge.challenge.repository.MensajeRepository;
import com.challenge.challenge.service.MensajeService;

@Service
public class MensajeServiceImpl implements MensajeService {
	
	@Autowired
	ContactoRepository contactoRepository;
	
	@Autowired
	MensajeRepository mensajeRepository;
	
	@Override
	public Mensajes newMessage(Mensajes m) {
		Mensajes mensaje = mensajeRepository.save(m);
		sendNotifications(m.getIdContacto());
		return mensaje;
	}
	
    private void sendNotifications(Long id) {
	    System.out.println("Enviando notificación a todos los contactos del grupo");
	    
	    Iterable<Contacto> contactos = contactoRepository.findAll();
		for (Iterator<Contacto> iterator = contactos.iterator(); iterator.hasNext();) {
			Contacto c = (Contacto) iterator.next();
			//Envio la notificacion a todos los contactos del grupo excluyendo al que lo envio
			if (!c.getId().equals(id)) {
				System.out.println(c.getNombre() + " " + c.getApellido() + " " + "Usted tiene un nuevo mensaje");
			}
		}
    }
}
