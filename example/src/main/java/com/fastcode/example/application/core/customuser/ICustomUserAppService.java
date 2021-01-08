package com.fastcode.example.application.core.customuser;

import com.fastcode.example.domain.core.customuser.CustomUserId;
import org.springframework.data.domain.Pageable;
import com.fastcode.example.application.core.customuser.dto.*;
import com.fastcode.example.commons.search.SearchCriteria;

import java.util.*;

public interface ICustomUserAppService {
	
	//CRUD Operations
	
	CreateCustomUserOutput create(CreateCustomUserInput customuser);

    void delete(CustomUserId customUserId);

    UpdateCustomUserOutput update(CustomUserId customUserId, UpdateCustomUserInput input);

    FindCustomUserByIdOutput findById(CustomUserId customUserId);

    List<FindCustomUserByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
	//Relationship Operations
    
    GetAddressOutput getAddress(CustomUserId customUserId);
    
    //Join Column Parsers
    
	CustomUserId parseCustomUserKey(String keysString);
}

