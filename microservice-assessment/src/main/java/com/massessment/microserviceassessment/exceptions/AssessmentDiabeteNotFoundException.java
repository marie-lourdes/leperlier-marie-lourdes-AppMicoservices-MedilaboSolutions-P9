package com.massessment.microserviceassessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssessmentDiabeteNotFoundException extends RuntimeException{
	public AssessmentDiabeteNotFoundException(String message) {
        super(message);
    }
}
