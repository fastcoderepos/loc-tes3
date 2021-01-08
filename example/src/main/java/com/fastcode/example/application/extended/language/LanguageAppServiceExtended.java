package com.fastcode.example.application.extended.language;

import org.springframework.stereotype.Service;
import com.fastcode.example.application.core.language.LanguageAppService;

import com.fastcode.example.domain.extended.language.ILanguageRepositoryExtended;
import com.fastcode.example.commons.logging.LoggingHelper;

@Service("languageAppServiceExtended")
public class LanguageAppServiceExtended extends LanguageAppService implements ILanguageAppServiceExtended {

	public LanguageAppServiceExtended(ILanguageRepositoryExtended languageRepositoryExtended,
				ILanguageMapperExtended mapper,LoggingHelper logHelper) {

		super(languageRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}

