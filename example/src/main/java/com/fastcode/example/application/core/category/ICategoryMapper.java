package com.fastcode.example.application.core.category;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.category.dto.*;
import com.fastcode.example.domain.core.category.CategoryEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

   CategoryEntity createCategoryInputToCategoryEntity(CreateCategoryInput categoryDto);
   
   
   CreateCategoryOutput categoryEntityToCreateCategoryOutput(CategoryEntity entity);
   
    CategoryEntity updateCategoryInputToCategoryEntity(UpdateCategoryInput categoryDto);
    
   	UpdateCategoryOutput categoryEntityToUpdateCategoryOutput(CategoryEntity entity);

   	FindCategoryByIdOutput categoryEntityToFindCategoryByIdOutput(CategoryEntity entity);


}

