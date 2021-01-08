package com.fastcode.example.application.extended.customer;

import org.springframework.stereotype.Service;
import com.fastcode.example.application.core.customer.CustomerAppService;

import com.fastcode.example.domain.extended.customer.ICustomerRepositoryExtended;
import com.fastcode.example.domain.extended.address.IAddressRepositoryExtended;
import com.fastcode.example.commons.logging.LoggingHelper;

@Service("customerAppServiceExtended")
public class CustomerAppServiceExtended extends CustomerAppService implements ICustomerAppServiceExtended {

	public CustomerAppServiceExtended(ICustomerRepositoryExtended customerRepositoryExtended,
				IAddressRepositoryExtended addressRepositoryExtended,ICustomerMapperExtended mapper,LoggingHelper logHelper) {

		super(customerRepositoryExtended,
		addressRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}

