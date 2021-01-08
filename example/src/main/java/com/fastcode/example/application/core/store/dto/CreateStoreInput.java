package com.fastcode.example.application.core.store.dto;

import java.time.*;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateStoreInput {

  	private LocalDateTime lastUpdate;
  
  	private Short addressId;
  	private Short managerStaffId;

}

