package com.fastcode.example.application.core.payment.dto;

import java.time.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePaymentInput {

  	@NotNull(message = "amount Should not be null")
  	private BigDecimal amount;
  
  	@NotNull(message = "paymentDate Should not be null")
  	private LocalDateTime paymentDate;
  
  	private Short customerId;
  	private Integer rentalId;
  	private Short staffId;

}

