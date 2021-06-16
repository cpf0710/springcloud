package com.ribbon.cn.ribbon.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
 
import java.util.HashMap;
import java.util.Map;
 
@Service
public class RibbonService implements IRibbonService {
 
    @Autowired
    RestTemplate restTemplate;
 
    @Autowired
    LoadBalancerClient loadBalancerClient;
 
    public String port() {
        this.loadBalancerClient.choose("SPRING-CLIENT-01");
        String info = restTemplate.getForObject("http://SPRING-CLIENT-01/getInfo/port", String.class);
        return restTemplate.getForObject("http://SPRING-CLIENT-01/getInfo/port", String.class);
    }
 
}
