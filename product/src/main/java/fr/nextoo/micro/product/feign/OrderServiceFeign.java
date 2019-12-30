package fr.nextoo.micro.product.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceFeign {
}
