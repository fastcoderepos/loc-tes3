package com.fastcode.example.application.core.filmcategory.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateFilmCategoryOutput {

  	private Short categoryId;
  	private Short filmId;
  	private LocalDateTime lastUpdate;
	private String categoryDescriptiveField;
	private Integer filmDescriptiveField;

}
