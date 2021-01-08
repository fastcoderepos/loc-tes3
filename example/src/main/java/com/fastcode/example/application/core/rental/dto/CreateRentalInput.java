package com.fastcode.example.application.core.rental.dto;

import java.time.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateRentalInput {

  	private LocalDateTime lastUpdate;
  
  	@NotNull(message = "rentalDate Should not be null")
  	private LocalDateTime rentalDate;
  
  	private LocalDateTime returnDate;
  
  	private Short customerId;
  	private Integer inventoryId;
  	private Short staffId;

}

