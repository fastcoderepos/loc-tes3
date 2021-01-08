package com.fastcode.example.application.core.customuser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.fastcode.example.application.core.customuser.dto.*;
import com.fastcode.example.domain.core.customuser.ICustomUserRepository;
import com.fastcode.example.domain.core.customuser.QCustomUserEntity;
import com.fastcode.example.domain.core.customuser.CustomUserEntity;
import com.fastcode.example.domain.core.customuser.CustomUserId;
import com.fastcode.example.domain.core.address.IAddressRepository;
import com.fastcode.example.domain.core.address.AddressEntity;
import com.fastcode.example.commons.search.*;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.querydsl.core.BooleanBuilder;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

@Service("customUserAppService")
@RequiredArgsConstructor
public class CustomUserAppService implements ICustomUserAppService {

	@Qualifier("customUserRepository")
	@NonNull protected final ICustomUserRepository _customUserRepository;

    @Qualifier("addressRepository")
	@NonNull protected final IAddressRepository _addressRepository;

	@Qualifier("ICustomUserMapperImpl")
	@NonNull protected final ICustomUserMapper mapper;

	@NonNull protected final LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
	public CreateCustomUserOutput create(CreateCustomUserInput input) {

		CustomUserEntity customUser = mapper.createCustomUserInputToCustomUserEntity(input);
		AddressEntity foundAddress = null;
	  	if(input.getAddressId()!=null) {
			foundAddress = _addressRepository.findById(input.getAddressId()).orElse(null);
			
			if(foundAddress!=null) {
				customUser.setAddress(foundAddress);
			}
		}

		CustomUserEntity createdCustomUser = _customUserRepository.save(customUser);
		return mapper.customUserEntityToCreateCustomUserOutput(createdCustomUser);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpdateCustomUserOutput update(CustomUserId customUserId, UpdateCustomUserInput input) {

		CustomUserEntity customUser = mapper.updateCustomUserInputToCustomUserEntity(input);
		AddressEntity foundAddress = null;
        
	  	if(input.getAddressId()!=null) { 
			foundAddress = _addressRepository.findById(input.getAddressId()).orElse(null);
		
			if(foundAddress!=null) {
				customUser.setAddress(foundAddress);
			}
		}
		
		CustomUserEntity updatedCustomUser = _customUserRepository.save(customUser);
		return mapper.customUserEntityToUpdateCustomUserOutput(updatedCustomUser);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(CustomUserId customUserId) {

		CustomUserEntity existing = _customUserRepository.findById(customUserId).orElse(null); 
	 	_customUserRepository.delete(existing);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public FindCustomUserByIdOutput findById(CustomUserId customUserId) {

		CustomUserEntity foundCustomUser = _customUserRepository.findById(customUserId).orElse(null);
		if (foundCustomUser == null)  
			return null; 
 	   

 	    return mapper.customUserEntityToFindCustomUserByIdOutput(foundCustomUser);
	}
	
    //Address
	// ReST API Call - GET /customUser/1/address
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
	public GetAddressOutput getAddress(CustomUserId customUserId) {

		CustomUserEntity foundCustomUser = _customUserRepository.findById(customUserId).orElse(null);
		if (foundCustomUser == null) {
			logHelper.getLogger().error("There does not exist a customUser wth a id=%s", customUserId);
			return null;
		}
		AddressEntity re = foundCustomUser.getAddress();
		return mapper.addressEntityToGetAddressOutput(re, foundCustomUser);
	}
	
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<FindCustomUserByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception  {

		Page<CustomUserEntity> foundCustomUser = _customUserRepository.findAll(search(search), pageable);
		List<CustomUserEntity> customUserList = foundCustomUser.getContent();
		Iterator<CustomUserEntity> customUserIterator = customUserList.iterator(); 
		List<FindCustomUserByIdOutput> output = new ArrayList<>();

		while (customUserIterator.hasNext()) {
		CustomUserEntity customUser = customUserIterator.next();
 	    output.add(mapper.customUserEntityToFindCustomUserByIdOutput(customUser));
		}
		return output;
	}
	
	protected BooleanBuilder search(SearchCriteria search) throws Exception {

		QCustomUserEntity customUser= QCustomUserEntity.customUserEntity;
		if(search != null) {
			Map<String,SearchFields> map = new HashMap<>();
			for(SearchFields fieldDetails: search.getFields())
			{
				map.put(fieldDetails.getFieldName(),fieldDetails);
			}
			List<String> keysList = new ArrayList<String>(map.keySet());
			checkProperties(keysList);
			return searchKeyValuePair(customUser, map,search.getJoinColumns());
		}
		return null;
	}
	
	protected void checkProperties(List<String> list) throws Exception  {
		for (int i = 0; i < list.size(); i++) {
			if(!(
				list.get(i).replace("%20","").trim().equals("addressId") ||
				list.get(i).replace("%20","").trim().equals("emailAdd") ||
				list.get(i).replace("%20","").trim().equals("enabled") ||
				list.get(i).replace("%20","").trim().equals("firstName") ||
				list.get(i).replace("%20","").trim().equals("isEmailcon") ||
				list.get(i).replace("%20","").trim().equals("lastName") ||
				list.get(i).replace("%20","").trim().equals("phone") ||
				list.get(i).replace("%20","").trim().equals("pwd") ||
				list.get(i).replace("%20","").trim().equals("uname") ||
				list.get(i).replace("%20","").trim().equals("version")
			)) 
			{
			 throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!" );
			}
		}
	}
	
	protected BooleanBuilder searchKeyValuePair(QCustomUserEntity customUser, Map<String,SearchFields> map,Map<String,String> joinColumns) {
		BooleanBuilder builder = new BooleanBuilder();
        
		for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if(details.getKey().replace("%20","").trim().equals("emailAdd")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(customUser.emailAdd.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(customUser.emailAdd.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(customUser.emailAdd.ne(details.getValue().getSearchValue()));
			}
			if(details.getKey().replace("%20","").trim().equals("enabled")) {
				if(details.getValue().getOperator().equals("equals") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(customUser.enabled.eq(Boolean.parseBoolean(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("notEqual") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(customUser.enabled.ne(Boolean.parseBoolean(details.getValue().getSearchValue())));
			}
            if(details.getKey().replace("%20","").trim().equals("firstName")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(customUser.firstName.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(customUser.firstName.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(customUser.firstName.ne(details.getValue().getSearchValue()));
			}
			if(details.getKey().replace("%20","").trim().equals("isEmailcon")) {
				if(details.getValue().getOperator().equals("equals") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(customUser.isEmailcon.eq(Boolean.parseBoolean(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("notEqual") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(customUser.isEmailcon.ne(Boolean.parseBoolean(details.getValue().getSearchValue())));
			}
            if(details.getKey().replace("%20","").trim().equals("lastName")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(customUser.lastName.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(customUser.lastName.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(customUser.lastName.ne(details.getValue().getSearchValue()));
			}
            if(details.getKey().replace("%20","").trim().equals("phone")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(customUser.phone.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(customUser.phone.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(customUser.phone.ne(details.getValue().getSearchValue()));
			}
            if(details.getKey().replace("%20","").trim().equals("pwd")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(customUser.pwd.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(customUser.pwd.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(customUser.pwd.ne(details.getValue().getSearchValue()));
			}
            if(details.getKey().replace("%20","").trim().equals("uname")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(customUser.uname.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(customUser.uname.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(customUser.uname.ne(details.getValue().getSearchValue()));
			}
			if(details.getKey().replace("%20","").trim().equals("version")) {
				if(details.getValue().getOperator().equals("equals") && StringUtils.isNumeric(details.getValue().getSearchValue()))
					builder.and(customUser.version.eq(Long.valueOf(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("notEqual") && StringUtils.isNumeric(details.getValue().getSearchValue()))
					builder.and(customUser.version.ne(Long.valueOf(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("range"))
				{
				   if(StringUtils.isNumeric(details.getValue().getStartingValue()) && StringUtils.isNumeric(details.getValue().getEndingValue()))
                	   builder.and(customUser.version.between(Long.valueOf(details.getValue().getStartingValue()), Long.valueOf(details.getValue().getEndingValue())));
                   else if(StringUtils.isNumeric(details.getValue().getStartingValue()))
                	   builder.and(customUser.version.goe(Long.valueOf(details.getValue().getStartingValue())));
                   else if(StringUtils.isNumeric(details.getValue().getEndingValue()))
                	   builder.and(customUser.version.loe(Long.valueOf(details.getValue().getEndingValue())));
				}
			}
	    
		}
		
		for (Map.Entry<String, String> joinCol : joinColumns.entrySet()) {
		if(joinCol != null && joinCol.getKey().equals("addressId")) {
		    builder.and(customUser.address.addressId.eq(Integer.parseInt(joinCol.getValue())));
		}
        
        }
		return builder;
	}
	
	public CustomUserId parseCustomUserKey(String keysString) {
		
		String[] keyEntries = keysString.split(",");
		CustomUserId customUserId = new CustomUserId();
		
		Map<String,String> keyMap = new HashMap<String,String>();
		if(keyEntries.length > 1) {
			for(String keyEntry: keyEntries)
			{
				String[] keyEntryArr = keyEntry.split("=");
				if(keyEntryArr.length > 1) {
					keyMap.put(keyEntryArr[0], keyEntryArr[1]);					
				}
				else {
					return null;
				}
			}
		}
		else {
			String[] keyEntryArr = keysString.split("=");
			if(keyEntryArr.length > 1) {
				keyMap.put(keyEntryArr[0], keyEntryArr[1]);					
			}
			else 
			return null;
		}
		
		customUserId.setFirstName(keyMap.get("firstName"));
		customUserId.setUname(keyMap.get("uname"));
		return customUserId;
		
	}	

	
}



