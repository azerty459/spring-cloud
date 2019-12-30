package fr.nextoo.micro.order.transformer;

import fr.nextoo.micro.common.dto.order.OrderDto;
import fr.nextoo.micro.common.transformer.Transformer;
import fr.nextoo.micro.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderTransformer extends Transformer<Order, OrderDto, Long> {

    @Override
    public OrderDto transformToDto(Order entity) {
        return entity == null ? null :
                OrderDto.builder()
                        .id(entity.getId())
                        .price(entity.getPrice())
                        .userId(entity.getUserId())
                        .build();
    }

    @Override
    public Long extractKey(Order entity) {
        return entity == null ? null : entity.getId();
    }

    @Override
    public Order transformToEntity(OrderDto dto) {
        return dto == null ? null :
                Order.builder()
                        .id(dto.getId())
                        .price(dto.getPrice())
                        .userId(dto.getUserId())
                        .build();
    }

    @Override
    public Order holdKey(Long key) {
        return key == null ? null : Order.builder().id(key).build();
    }
}
