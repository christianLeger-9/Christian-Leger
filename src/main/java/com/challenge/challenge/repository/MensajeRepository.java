package com.challenge.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.challenge.entity.Mensajes;

public interface MensajeRepository extends JpaRepository<Mensajes, Long> {

}
