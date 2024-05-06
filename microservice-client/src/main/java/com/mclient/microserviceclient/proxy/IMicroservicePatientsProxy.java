package com.mclient.microserviceclient.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name ="microservice-API-Gateway")
public interface IMicroservicePatientsProxy {

}
