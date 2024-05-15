package com.mclient.microserviceclient.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mclient.microserviceclient.bean.MedicalReportBean;

@FeignClient(name="microservice-API-Gateway")
public interface IMicroserviceMedicalReportsProxy {
	@GetMapping("/rapport-medical/{patId}")
    List<MedicalReportBean> getPatientByPatId(@PathVariable Integer patId);
}
