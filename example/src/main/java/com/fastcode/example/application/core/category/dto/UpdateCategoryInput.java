package com.fastcode.example.application.core.category.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCategoryInput {

  	@NotNull(message = "categoryId Should not be null")
  	private Integer categoryId;
  	
  	private LocalDateTime lastUpdate;
  	
  	@NotNull(message = "name Should not be null")
 	@Length(max = 25, message = "name must be less than 25 characters")
  	private String name;
  	
  	private Long versiono;
  
}

