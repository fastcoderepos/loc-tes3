package com.fastcode.example.application.core.language.dto;

import java.time.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateLanguageInput {

  	private LocalDateTime lastUpdate;
  
  	@NotNull(message = "name Should not be null")
  	private String name;
  

}

