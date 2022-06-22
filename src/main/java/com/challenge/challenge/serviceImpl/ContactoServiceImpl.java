package com.challenge.challenge.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.challenge.entity.Contacto;
import com.challenge.challenge.repository.ContactoRepository;
import com.challenge.challenge.service.ContactoService;

@Service
public class ContactoServiceImpl implements ContactoService {

	@Autowired
	ContactoRepository contactoRepository;
	
	@Override
	public Optional<Contacto> findById(Long idContacto) {
		return contactoRepository.findById(idContacto);
	}
}
