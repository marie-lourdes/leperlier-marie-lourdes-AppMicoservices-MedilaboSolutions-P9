package com.mclient.microserviceclient.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder= new Default();
	
	@Override
	  public Exception decode(String invoqueur, Response reponse) {
		switch(reponse.status()){
		    case 400:  return new PatientBadRequestException(
	                  "Bad Request "  );
		    
			case 404: return new PatientNotFoundException(
		              "Patient not found");
		
			case 403: return new PatientNotAuthorizedException("access forbidden");
			
			case 409:  return new PatientConflictException(
		              "Patient already exist"
		        );
			default: return defaultErrorDecoder.decode(invoqueur, reponse);
		
		}
	    /*  if(reponse.status() == 404) {
	        return new PatientNotFoundException(
	              "Patient not found");
	      }else if(reponse.status() == 409) {
		        return new PatientConflictException(
		              "Patient already exist"
		        );
	      }else if(reponse.status() == 403 ) {
	    	 return new PatientNotAuthorizedException("access forbidden");
	      }
	      return defaultErrorDecoder.decode(invoqueur, reponse);*/
	}
}
