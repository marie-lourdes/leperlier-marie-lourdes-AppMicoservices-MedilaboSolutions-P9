package com.massessment.microserviceassessment.service;

public interface IFilter {
	boolean filterAgeLimitPatient(Integer id, Integer ageFiltered);
	String filterSexPatient(Integer id);
}
