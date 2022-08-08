package com.ccnu.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.swing.*;

@EnableEurekaServer
@SpringBootApplication
public class eurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(eurekaApplication.class,args);
    }
}