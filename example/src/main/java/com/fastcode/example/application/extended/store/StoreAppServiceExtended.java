package com.fastcode.example.application.extended.store;

import org.springframework.stereotype.Service;
import com.fastcode.example.application.core.store.StoreAppService;

import com.fastcode.example.domain.extended.store.IStoreRepositoryExtended;
import com.fastcode.example.domain.extended.address.IAddressRepositoryExtended;
import com.fastcode.example.domain.extended.staff.IStaffRepositoryExtended;
import com.fastcode.example.commons.logging.LoggingHelper;

@Service("storeAppServiceExtended")
public class StoreAppServiceExtended extends StoreAppService implements IStoreAppServiceExtended {

	public StoreAppServiceExtended(IStoreRepositoryExtended storeRepositoryExtended,
				IAddressRepositoryExtended addressRepositoryExtended,IStaffRepositoryExtended staffRepositoryExtended,IStoreMapperExtended mapper,LoggingHelper logHelper) {

		super(storeRepositoryExtended,
		addressRepositoryExtended,staffRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}

