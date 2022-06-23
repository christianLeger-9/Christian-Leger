package com.challenge.challenge;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.challenge.challenge.entity.Contacto;
import com.challenge.challenge.entity.Mensajes;
import com.challenge.challenge.repository.ContactoRepository;
import com.challenge.challenge.repository.MensajeRepository;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
	
	//metodo para cargar la base de datos cuando se inicia la app.
	@Bean
	public CommandLineRunner loadData(ContactoRepository cr, MensajeRepository mr) {
	    return (args) -> {
	    	cr.save(new Contacto(null,"chris","Leger","2213149477"));
	    	cr.save(new Contacto(null,"Laura","Perez","2932430225"));
	    	cr.save(new Contacto(null,"Juan","Simon","2932426120"));
	    	cr.save(new Contacto(null,"Adriana","Lopez","2932424670"));
	    	
	    	mr.save(new Mensajes(null,"Mensaje1", 1L));
	    	mr.save(new Mensajes(null,"Mensaje2", 2L));
	    };
	}
}
