package com.fastcode.example.application.core.payment.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class UpdatePaymentOutput {

  	private BigDecimal amount;
  	private LocalDateTime paymentDate;
  	private Integer paymentId;
  	private Short customerId;
	private Integer customerDescriptiveField;
  	private Integer rentalId;
	private Integer rentalDescriptiveField;
  	private Short staffId;
	private Integer staffDescriptiveField;

}
