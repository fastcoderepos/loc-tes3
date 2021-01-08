package com.fastcode.example.application.core.customuser.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCustomUserOutput {

  	private String emailAdd;
  	private Boolean enabled;
  	private String firstName;
  	private Boolean isEmailcon;
  	private String lastName;
  	private String phone;
  	private String pwd;
  	private String uname;
  	private Long version;
  	private Integer addressId;
	private Integer addressDescriptiveField;

}
