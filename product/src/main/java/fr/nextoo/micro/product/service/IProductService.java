package fr.nextoo.micro.product.service;

import fr.nextoo.micro.common.dto.product.ProductDto;

import java.util.Collection;

public interface IProductService {

    Collection<ProductDto> findAll();

}
