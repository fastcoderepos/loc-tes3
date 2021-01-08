package com.fastcode.example.application.core.filmcategory.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FindFilmCategoryByIdOutput {

  	private Short categoryId;
  	private Short filmId;
  	private LocalDateTime lastUpdate;
  	private String categoryDescriptiveField;
  	private Integer filmDescriptiveField;
	private Long versiono;
 
}

