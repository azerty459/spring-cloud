package fr.nextoo.micro.order.service.impl;


import fr.nextoo.micro.common.dto.order.OrderDto;
import fr.nextoo.micro.order.repository.OrderRepository;
import fr.nextoo.micro.order.service.IOrderService;
import fr.nextoo.micro.order.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderTransformer orderTransformer;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderTransformer orderTransformer) {
        this.orderRepository = orderRepository;
        this.orderTransformer = orderTransformer;
    }

    public Collection<OrderDto> getAll() {
        return orderRepository.findAll().stream().map(orderTransformer::transformToDto).collect(Collectors.toList());
    }
}
