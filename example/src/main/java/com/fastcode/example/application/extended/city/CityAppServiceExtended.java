package com.fastcode.example.application.extended.city;

import org.springframework.stereotype.Service;
import com.fastcode.example.application.core.city.CityAppService;

import com.fastcode.example.domain.extended.city.ICityRepositoryExtended;
import com.fastcode.example.domain.extended.country.ICountryRepositoryExtended;
import com.fastcode.example.commons.logging.LoggingHelper;

@Service("cityAppServiceExtended")
public class CityAppServiceExtended extends CityAppService implements ICityAppServiceExtended {

	public CityAppServiceExtended(ICityRepositoryExtended cityRepositoryExtended,
				ICountryRepositoryExtended countryRepositoryExtended,ICityMapperExtended mapper,LoggingHelper logHelper) {

		super(cityRepositoryExtended,
		countryRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}

