package com.challenge.challenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.challenge.entity.Mensajes;
import com.challenge.challenge.repository.ContactoRepository;
import com.challenge.challenge.repository.MensajeRepository;
import com.challenge.challenge.serviceImpl.MensajeServiceImpl;

@SpringBootTest
public class WhatsAppControllerTest {

	@InjectMocks
    private MensajeServiceImpl mensajeServiceMock;
	
	@Mock
    private ContactoRepository contactoRepositoryMock;
	
	@Mock
	private MensajeRepository mensajeRepositoryMock;
	
	@Autowired
	WhatsAppController wsp = new WhatsAppController();
	
	@Test
	void newMessageTest() {
		
		Mensajes m = new Mensajes(1L,"prueba unitaria del metodo del controller",4L);
		Mockito.when(mensajeRepositoryMock.save(m)).thenReturn(m);
		assertEquals(m, mensajeServiceMock.newMessage(m));
		
	}
	
}
