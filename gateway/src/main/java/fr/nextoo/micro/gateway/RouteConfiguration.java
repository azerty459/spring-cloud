package fr.nextoo.micro.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/**").uri("lb://USER-SERVICE"))
                .route(r -> r.path("/product/**").uri("lb://PRODUCT-SERVICE"))
                .route(r -> r.path("/category/**").uri("lb://PRODUCT-SERVICE"))
                .route(r -> r.path("/order/**").uri("lb://ORDER-SERVICE"))
                .build();
    }
}
