package com.sqs.gateway;

import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledGlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        //2.获取参数中的authorization参数
         String authorization = params.getFirst("authorization");
         //3.判断参数是否等于admin
        if ("admin".equals(authorization)){
            //放行
           return chain.filter(exchange);
        }

        //5.否，拦截
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED) ;
        return exchange.getResponse().setComplete();
    }

    //设置过滤器优先级
    @Override
    public int getOrder() {
        return -1;
    }
}