package com.mclient.microserviceclient.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder= new Default();
	
	@Override
	  public Exception decode(String invoqueur, Response reponse) {
	      if(reponse.status() == 404) {
	        return new PatientNotFoundException(
	              "Patient not found");
	      }else if(reponse.status() == 409) {
		        return new PatientConflictException(
		              "Patient already exist"
		        );
	      }
	      return defaultErrorDecoder.decode(invoqueur, reponse);
	}
}