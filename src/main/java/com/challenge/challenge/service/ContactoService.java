package com.challenge.challenge.service;

import java.util.Optional;

import com.challenge.challenge.entity.Contacto;

public interface ContactoService {
	
	public Optional<Contacto> findById(Long idContacto);

}
