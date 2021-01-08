package com.fastcode.example.application.core.customuser.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCustomUserInput {

  	@NotNull(message = "emailAdd Should not be null")
 	@Length(max = 255, message = "emailAdd must be less than 255 characters")
  	private String emailAdd;
  	
  	@NotNull(message = "enabled Should not be null")
  	private Boolean enabled;
  	
  	@NotNull(message = "firstName Should not be null")
 	@Length(max = 255, message = "firstName must be less than 255 characters")
  	private String firstName;
  	
  	@NotNull(message = "isEmailcon Should not be null")
  	private Boolean isEmailcon;
  	
  	@NotNull(message = "lastName Should not be null")
 	@Length(max = 255, message = "lastName must be less than 255 characters")
  	private String lastName;
  	
  	private String phone;
  	
  	@NotNull(message = "pwd Should not be null")
 	@Length(max = 255, message = "pwd must be less than 255 characters")
  	private String pwd;
  	
  	@NotNull(message = "uname Should not be null")
  	private String uname;
  	
  	@NotNull(message = "version Should not be null")
  	private Long version;
  	
  	private Integer addressId;
  	private Long versiono;
  
}

