package fr.nextoo.micro.common.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Builder
public class CategoryDto {

    private Integer id;
    private String name;
    private Integer parentId;
    private Collection<Integer> childrenIds;

}
