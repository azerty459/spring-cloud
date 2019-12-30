package fr.nextoo.micro.order.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceFeign {
}
