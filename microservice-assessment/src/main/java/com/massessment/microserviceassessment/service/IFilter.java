package com.massessment.microserviceassessment.service;

import java.util.List;

import com.massessment.microserviceassessment.beans.PatientBean;

public interface IFilter {
     PatientBean filterAgePatient(List<PatientBean> AllPatients);
}
 