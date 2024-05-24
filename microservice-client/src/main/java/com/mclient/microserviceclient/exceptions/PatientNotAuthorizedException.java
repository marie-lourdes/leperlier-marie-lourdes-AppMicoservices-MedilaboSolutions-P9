package com.mclient.microserviceclient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PatientNotAuthorizedException  extends RuntimeException{
	
	public PatientNotAuthorizedException(String message) {
        super(message);
    }
}
