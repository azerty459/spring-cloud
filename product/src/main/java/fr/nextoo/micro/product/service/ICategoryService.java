package fr.nextoo.micro.product.service;

import fr.nextoo.micro.common.dto.product.CategoryDto;

import java.util.Collection;

public interface ICategoryService {

    Collection<CategoryDto> findAll();

}
