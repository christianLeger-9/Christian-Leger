package com.challenge.challenge.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.challenge.entity.Contacto;
import com.challenge.challenge.entity.Mensajes;
import com.challenge.challenge.repository.ContactoRepository;
import com.challenge.challenge.service.ContactoService;
import com.challenge.challenge.service.MensajeService;


@RestController
@RequestMapping("/WhatsApp")
public class WhatsAppController {

	@Autowired
    private ContactoService contactoService;
	
	@Autowired
    private MensajeService mensajeService;
	
	@Autowired
	ContactoRepository contactoRepository;
	
	@RequestMapping("/newMessage")
    public ResponseEntity<?> newMessage(@RequestBody Mensajes m) {
		Mensajes mensaje = new Mensajes();
		try {
			Optional<Contacto> c = contactoService.findById(m.getIdContacto());
			if (c.isPresent()) {
				//Se guarda un nuevo mensaje y luego se envia la notificacion a cada contacto excluyendose el que lo envia.
				mensaje = mensajeService.newMessage(m);
			} else {
				throw new Exception("El idContacto no existe en la Base de Datos.");
			}
		}
		catch( Exception e) { 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((e.getMessage()));
		 }
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}
}
