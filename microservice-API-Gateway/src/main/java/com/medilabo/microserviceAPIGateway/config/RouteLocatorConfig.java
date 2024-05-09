package com.medilabo.microserviceAPIGateway.config;

import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@RefreshScope
@Configuration
public class RouteLocatorConfig {
	@Bean
	public  DiscoveryClientRouteDefinitionLocator locator(ReactiveDiscoveryClient dc, DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(dc,dlp);
	}

	/*@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder ) {
		return routeLocatorBuilder.routes().route(r->r.path("/patient/**").filters(f->f.filter((exchange,chain)->{
			 ServerHttpRequest req = exchange.getRequest();
			 return chain.filter(exchange.mutate().request(req).build());
		})).uri("http://localhost:9001/")).build();
	}*/
	
}
