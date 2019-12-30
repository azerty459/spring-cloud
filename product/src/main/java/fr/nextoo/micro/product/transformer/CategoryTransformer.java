package fr.nextoo.micro.product.transformer;

import fr.nextoo.micro.common.dto.product.CategoryDto;
import fr.nextoo.micro.common.transformer.Transformer;
import fr.nextoo.micro.product.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryTransformer extends Transformer<Category, CategoryDto, Integer> {

    @Override
    public CategoryDto transformToDto(Category entity) {
        return entity == null ? null :
                CategoryDto.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .parentId(extractKey(entity.getParent()))
                        .childrenIds(extractKey(entity.getChildren()))
                        .build();
    }

    @Override
    public Integer extractKey(Category entity) {
        return entity == null ? null : entity.getId();
    }

    @Override
    public Category transformToEntity(CategoryDto dto) {
        return dto == null ? null :
                Category.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .parent(holdKey(dto.getParentId()))
                        .children(holdKey(dto.getChildrenIds()))
                        .build();
    }

    @Override
    public Category holdKey(Integer key) {
        return key == null ? null : Category.builder().id(key).build();
    }
}
