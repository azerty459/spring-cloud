package fr.nextoo.micro.order.controller;

import fr.nextoo.micro.common.dto.product.ProductDto;
import fr.nextoo.micro.order.feign.ProductServiceFeign;
import fr.nextoo.micro.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;
    private final ProductServiceFeign productServiceFeign;

    @Autowired
    public OrderController(IOrderService orderService, ProductServiceFeign productServiceFeign) {
        this.orderService = orderService;
        this.productServiceFeign = productServiceFeign;
    }

    @GetMapping
    private ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/product")
    private ResponseEntity<Collection<ProductDto>> products() {
        System.out.println(productServiceFeign.findAll());
        return ResponseEntity.ok(productServiceFeign.findAll());
    }

}
