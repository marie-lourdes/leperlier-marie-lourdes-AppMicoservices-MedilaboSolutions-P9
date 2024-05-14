package com.mmedicalreport.microservicemedicalReports.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;

@Repository
public interface IMedicalReportRepository  extends MongoRepository<MedicalReport,String>{
     Optional< MedicalReport> findByPatient(String namePatient);
     
     @Query("{ 'patid' : ?0 }")
     Optional< MedicalReport> getByPatId(String patId);
}
