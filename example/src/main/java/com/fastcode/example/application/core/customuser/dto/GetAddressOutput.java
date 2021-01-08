package com.fastcode.example.application.core.customuser.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAddressOutput {

 	private String address;
 	private String address2;
 	private Integer addressId;
 	private String district;
 	private LocalDateTime lastUpdate;
 	private String phone;
 	private String postalCode;
  	private String customUserFirstName;
  	private String customUserUname;

}

