package fr.nextoo.micro.common.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {

    private Integer id;
    private String label;
    private String desc;
    private Integer categoryId;

}
