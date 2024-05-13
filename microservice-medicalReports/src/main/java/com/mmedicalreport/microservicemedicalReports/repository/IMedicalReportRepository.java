package com.mmedicalreport.microservicemedicalReports.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;

public interface IMedicalReportRepository  extends MongoRepository<MedicalReport,String>{

}
