package com.fastcode.example.application.core.film.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class UpdateFilmInput {

  	private String description;
  	
  	@NotNull(message = "filmId Should not be null")
  	private Integer filmId;
  	
  	private LocalDateTime lastUpdate;
  	
  	private Short length;
  	
 	@Length(max = 256, message = "rating must be less than 256 characters")
  	private String rating;
  	
  	@NotNull(message = "rentalDuration Should not be null")
  	private Short rentalDuration;
  	
  	@NotNull(message = "rentalRate Should not be null")
  	private BigDecimal rentalRate;
  	
  	@NotNull(message = "replacementCost Should not be null")
  	private BigDecimal replacementCost;
  	
  	@NotNull(message = "title Should not be null")
 	@Length(max = 255, message = "title must be less than 255 characters")
  	private String title;
  	
  	private Short languageId;
  	private Long versiono;
  
}

