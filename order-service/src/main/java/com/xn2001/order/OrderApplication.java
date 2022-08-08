package com.xn2001.order;

import com.sqs.feign.clients.UserClient;
import com.sqs.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.xn2001.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = UserClient.class,defaultConfiguration = DefaultFeignConfiguration.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     *创建RestTemplate
     * @return
     */
    @LoadBalanced//负载均衡
    @Bean
    public RestTemplate restTemplate(){
       return  new RestTemplate();
    }

//    @Bean
//
//    public IRule randomRule(){
//        return new RandomRule();
//    }

}