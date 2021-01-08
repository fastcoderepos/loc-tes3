package com.fastcode.example.application.extended.customuser;

import org.springframework.stereotype.Service;
import com.fastcode.example.application.core.customuser.CustomUserAppService;

import com.fastcode.example.domain.extended.customuser.ICustomUserRepositoryExtended;
import com.fastcode.example.domain.extended.address.IAddressRepositoryExtended;
import com.fastcode.example.commons.logging.LoggingHelper;

@Service("customUserAppServiceExtended")
public class CustomUserAppServiceExtended extends CustomUserAppService implements ICustomUserAppServiceExtended {

	public CustomUserAppServiceExtended(ICustomUserRepositoryExtended customUserRepositoryExtended,
				IAddressRepositoryExtended addressRepositoryExtended,ICustomUserMapperExtended mapper,LoggingHelper logHelper) {

		super(customUserRepositoryExtended,
		addressRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}

