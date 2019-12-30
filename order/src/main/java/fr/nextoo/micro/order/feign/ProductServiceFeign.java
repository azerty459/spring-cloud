package fr.nextoo.micro.order.feign;

import fr.nextoo.micro.common.dto.product.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceFeign {

    @GetMapping("/product")
    Collection<ProductDto> findAll();
    
}
