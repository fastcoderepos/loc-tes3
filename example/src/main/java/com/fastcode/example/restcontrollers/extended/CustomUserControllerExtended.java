package com.fastcode.example.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.example.restcontrollers.core.CustomUserController;
import com.fastcode.example.application.extended.customuser.ICustomUserAppServiceExtended;
import com.fastcode.example.application.extended.address.IAddressAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.example.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/customUser/extended")
public class CustomUserControllerExtended extends CustomUserController {

		public CustomUserControllerExtended(ICustomUserAppServiceExtended customUserAppServiceExtended, IAddressAppServiceExtended addressAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		customUserAppServiceExtended,
    	addressAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

