package com.fastcode.example.application.extended.inventory;

import org.springframework.stereotype.Service;
import com.fastcode.example.application.core.inventory.InventoryAppService;

import com.fastcode.example.domain.extended.inventory.IInventoryRepositoryExtended;
import com.fastcode.example.domain.extended.film.IFilmRepositoryExtended;
import com.fastcode.example.commons.logging.LoggingHelper;

@Service("inventoryAppServiceExtended")
public class InventoryAppServiceExtended extends InventoryAppService implements IInventoryAppServiceExtended {

	public InventoryAppServiceExtended(IInventoryRepositoryExtended inventoryRepositoryExtended,
				IFilmRepositoryExtended filmRepositoryExtended,IInventoryMapperExtended mapper,LoggingHelper logHelper) {

		super(inventoryRepositoryExtended,
		filmRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}

