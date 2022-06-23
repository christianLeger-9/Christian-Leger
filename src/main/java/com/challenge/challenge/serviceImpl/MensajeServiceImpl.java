package com.challenge.challenge.serviceImpl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.challenge.entity.Contacto;
import com.challenge.challenge.entity.Mensajes;
import com.challenge.challenge.repository.ContactoRepository;
import com.challenge.challenge.repository.MensajeRepository;
import com.challenge.challenge.service.MensajeService;

import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class MensajeServiceImpl implements MensajeService {
	
	 // Create a Logger
    Logger logger
        = Logger.getLogger(
        		MensajeServiceImpl.class.getName());
	
	@Autowired
	ContactoRepository contactoRepository;
	
	@Autowired
	MensajeRepository mensajeRepository;
	
	@Override
	public Mensajes newMessage(Mensajes m) {
		Mensajes mensaje = mensajeRepository.save(m);
		sendNotifications(m);
		return mensaje;
	}
	
    private void sendNotifications(Mensajes mensaje) {
    	
	    Iterable<Contacto> contactos = contactoRepository.findAll();
		for (Iterator<Contacto> iterator = contactos.iterator(); iterator.hasNext();) {
			Contacto c = (Contacto) iterator.next();
			//Envio la notificacion a todos los contactos del grupo excluyendo al que lo envio
			if (!c.getId().equals(mensaje.getIdContacto())) {
				 logger.log(Level.INFO, c.getNombre() + " " + c.getApellido() + " " + "tiene un mensaje nuevo en el grupo");
			}
		}
    }
}
