package com.massessment.microserviceassessment.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CalculatorAgeImpl implements ICalculatorAge{
	
	@Override
	public  int calculateAge( LocalDate birthDate,LocalDate currentDate) {
		 return Period.between(birthDate, currentDate).getYears();
	}

}
