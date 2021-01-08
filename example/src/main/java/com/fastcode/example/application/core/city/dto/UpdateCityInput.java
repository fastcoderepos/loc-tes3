package com.fastcode.example.application.core.city.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCityInput {

  	@NotNull(message = "city Should not be null")
 	@Length(max = 50, message = "city must be less than 50 characters")
  	private String city;
  	
  	@NotNull(message = "cityId Should not be null")
  	private Integer cityId;
  	
  	private LocalDateTime lastUpdate;
  	
  	private Short countryId;
  	private Long versiono;
  
}

