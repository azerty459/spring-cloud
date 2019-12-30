package fr.nextoo.micro.common.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDto {

    private Long id;
    private Double price;
    private Long userId;

}
