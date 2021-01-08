package com.fastcode.example.domain.core.customuser;

import java.time.*;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
public class CustomUserId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String firstName;
    private String uname;
    
    public CustomUserId(String firstName,String uname) {
 	this.firstName = firstName;
 	this.uname = uname;
    }
    
}
