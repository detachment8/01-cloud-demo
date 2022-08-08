package com.xn2001.order.service;

import com.sqs.feign.clients.UserClient;
import com.sqs.feign.pojo.User;
import com.xn2001.order.mapper.OrderMapper;
import com.xn2001.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient UserClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用feign发送http请求
        User user = UserClient.findById(order.getUserId());

        // 4.返回
        order.setUser(user);
        return order;
    }

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        // 2.利用restTemplate发送http请求
//        String url = "http://userservice/user/"+order.getUserId();
//        // 3.封装user到order
//        User user = restTemplate.getForObject(url,User.class);
//
//        // 4.返回
//        order.setUser(user);
//        return order;
//    }
}
