package com.massessment.microserviceassessment.service;

import java.time.LocalDate;
import java.time.Period;

import lombok.Data;

@Data
public class CalculatorAgeImpl implements ICalculatorAge{
	private int ageCalculated;
	
	private CalculatorAgeImpl(){}
	
	@Override
	public  int calculateAge( LocalDate birthDate,LocalDate currentDate) {
		 return this.ageCalculated=Period.between(birthDate, currentDate).getYears();
	}

}
