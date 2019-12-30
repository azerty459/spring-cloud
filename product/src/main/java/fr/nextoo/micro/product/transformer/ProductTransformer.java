package fr.nextoo.micro.product.transformer;

import fr.nextoo.micro.common.dto.product.ProductDto;
import fr.nextoo.micro.common.transformer.Transformer;
import fr.nextoo.micro.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer extends Transformer<Product, ProductDto, Integer> {

    private final CategoryTransformer categoryTransformer;

    @Autowired
    public ProductTransformer(CategoryTransformer categoryTransformer) {
        this.categoryTransformer = categoryTransformer;
    }

    @Override
    public ProductDto transformToDto(Product entity) {
        return entity == null ? null :
                ProductDto.builder()
                        .id(entity.getId())
                        .label(entity.getLabel())
                        .desc(entity.getDesc())
                        .categoryId(categoryTransformer.extractKey(entity.getCategory()))
                        .build();
    }

    @Override
    public Integer extractKey(Product entity) {
        return entity == null ? null : entity.getId();
    }

    @Override
    public Product transformToEntity(ProductDto dto) {
        return dto == null ? null :
                Product.builder()
                        .id(dto.getId())
                        .label(dto.getLabel())
                        .desc(dto.getDesc())
                        .category(categoryTransformer.holdKey(dto.getCategoryId()))
                        .build();
    }

    @Override
    public Product holdKey(Integer key) {
        return key == null ? null : Product.builder().id(key).build();
    }
}
