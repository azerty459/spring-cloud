package fr.nextoo.micro.order.service;


import fr.nextoo.micro.common.dto.order.OrderDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface IOrderService {

    Collection<OrderDto> getAll();
}
