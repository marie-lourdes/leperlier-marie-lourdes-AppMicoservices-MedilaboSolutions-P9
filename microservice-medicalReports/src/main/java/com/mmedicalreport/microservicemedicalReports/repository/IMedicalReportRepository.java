package com.mmedicalreport.microservicemedicalReports.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;

public interface IMedicalReportRepository  extends MongoRepository<MedicalReport,String>{
     Optional< MedicalReport> findByPatient(String namePatient);
}
