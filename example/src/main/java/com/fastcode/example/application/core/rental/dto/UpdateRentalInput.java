package com.fastcode.example.application.core.rental.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRentalInput {

  	private LocalDateTime lastUpdate;
  	
  	@NotNull(message = "rentalDate Should not be null")
  	private LocalDateTime rentalDate;
  	
  	@NotNull(message = "rentalId Should not be null")
  	private Integer rentalId;
  	
  	private LocalDateTime returnDate;
  	
  	private Short customerId;
  	private Integer inventoryId;
  	private Short staffId;
  	private Long versiono;
  
}

