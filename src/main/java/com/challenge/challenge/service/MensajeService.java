package com.challenge.challenge.service;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;

import com.challenge.challenge.entity.Mensajes;

public interface MensajeService {

	public boolean newMessage(Mensajes m) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, GeneralSecurityException, JoseException, ExecutionException, InterruptedException;
}
