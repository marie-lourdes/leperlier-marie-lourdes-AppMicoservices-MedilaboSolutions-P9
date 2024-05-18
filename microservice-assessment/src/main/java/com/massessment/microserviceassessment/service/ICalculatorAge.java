package com.massessment.microserviceassessment.service;

import java.time.LocalDate;

public interface ICalculatorAge {
 int calculateAge( LocalDate birthDate,LocalDate currentDate); 
}
