package com.fastcode.example.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.example.restcontrollers.core.CityController;
import com.fastcode.example.application.extended.city.ICityAppServiceExtended;
import com.fastcode.example.application.extended.address.IAddressAppServiceExtended;
import com.fastcode.example.application.extended.country.ICountryAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.example.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/city/extended")
public class CityControllerExtended extends CityController {

		public CityControllerExtended(ICityAppServiceExtended cityAppServiceExtended, IAddressAppServiceExtended addressAppServiceExtended, ICountryAppServiceExtended countryAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		cityAppServiceExtended,
    	addressAppServiceExtended,
    	countryAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

