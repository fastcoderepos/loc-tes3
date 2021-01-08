package com.fastcode.example.application.core.customuser;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcode.example.domain.core.customuser.*;
import com.fastcode.example.commons.search.*;
import com.fastcode.example.application.core.customuser.dto.*;
import com.fastcode.example.domain.core.customuser.QCustomUserEntity;
import com.fastcode.example.domain.core.customuser.CustomUserEntity;
import com.fastcode.example.domain.core.customuser.CustomUserId;
import com.fastcode.example.domain.core.address.AddressEntity;
import com.fastcode.example.domain.core.address.IAddressRepository;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.time.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomUserAppServiceTest {

	@InjectMocks
	@Spy
	protected CustomUserAppService _appService;

	@Mock
	protected ICustomUserRepository _customUserRepository;
	
    @Mock
	protected IAddressRepository _addressRepository;

	@Mock
	protected ICustomUserMapper _mapper;

	@Mock
	protected Logger loggerMock;

	@Mock
	protected LoggingHelper logHelper;
	
    @Mock
    protected CustomUserId customUserId;
    
    private static final Long ID = 15L;
	 
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(_appService);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());
	}
	
	@Test
	public void findCustomUserById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {
		Optional<CustomUserEntity> nullOptional = Optional.ofNullable(null);;
		Mockito.when(_customUserRepository.findById(any(CustomUserId.class))).thenReturn(nullOptional);
		Assertions.assertThat(_appService.findById(customUserId)).isEqualTo(null);
	}
	
	@Test
	public void findCustomUserById_IdIsNotNullAndIdExists_ReturnCustomUser() {

		CustomUserEntity customUser = mock(CustomUserEntity.class);
		Optional<CustomUserEntity> customUserOptional = Optional.of((CustomUserEntity) customUser);
		Mockito.when(_customUserRepository.findById(any(CustomUserId.class))).thenReturn(customUserOptional);
		
	    Assertions.assertThat(_appService.findById(customUserId)).isEqualTo(_mapper.customUserEntityToFindCustomUserByIdOutput(customUser));
	}
	
	
	@Test 
    public void createCustomUser_CustomUserIsNotNullAndCustomUserDoesNotExist_StoreCustomUser() { 
 
        CustomUserEntity customUserEntity = mock(CustomUserEntity.class); 
    	CreateCustomUserInput customUserInput = new CreateCustomUserInput();
		
        AddressEntity address = mock(AddressEntity.class);
		Optional<AddressEntity> addressOptional = Optional.of((AddressEntity) address);
        customUserInput.setAddressId(15);
		
		Mockito.when(_addressRepository.findById(any(Integer.class))).thenReturn(addressOptional);
		
        Mockito.when(_mapper.createCustomUserInputToCustomUserEntity(any(CreateCustomUserInput.class))).thenReturn(customUserEntity); 
        Mockito.when(_customUserRepository.save(any(CustomUserEntity.class))).thenReturn(customUserEntity);

	   	Assertions.assertThat(_appService.create(customUserInput)).isEqualTo(_mapper.customUserEntityToCreateCustomUserOutput(customUserEntity));

    } 
    @Test
	public void createCustomUser_CustomUserIsNotNullAndCustomUserDoesNotExistAndChildIsNullAndChildIsNotMandatory_StoreCustomUser() {

		CustomUserEntity customUserEntity = mock(CustomUserEntity.class);
		CreateCustomUserInput customUser = mock(CreateCustomUserInput.class);
		
		
		Mockito.when(_mapper.createCustomUserInputToCustomUserEntity(any(CreateCustomUserInput.class))).thenReturn(customUserEntity);
		Mockito.when(_customUserRepository.save(any(CustomUserEntity.class))).thenReturn(customUserEntity);
	    Assertions.assertThat(_appService.create(customUser)).isEqualTo(_mapper.customUserEntityToCreateCustomUserOutput(customUserEntity)); 
	}
	
    @Test
	public void updateCustomUser_CustomUserIsNotNullAndCustomUserDoesNotExistAndChildIsNullAndChildIsNotMandatory_ReturnUpdatedCustomUser() {

		CustomUserEntity customUserEntity = mock(CustomUserEntity.class);
		UpdateCustomUserInput customUser = mock(UpdateCustomUserInput.class);
		
		Mockito.when(_mapper.updateCustomUserInputToCustomUserEntity(any(UpdateCustomUserInput.class))).thenReturn(customUserEntity);
		Mockito.when(_customUserRepository.save(any(CustomUserEntity.class))).thenReturn(customUserEntity);
		Assertions.assertThat(_appService.update(customUserId,customUser)).isEqualTo(_mapper.customUserEntityToUpdateCustomUserOutput(customUserEntity));
	}
	
		
	@Test
	public void updateCustomUser_CustomUserIdIsNotNullAndIdExists_ReturnUpdatedCustomUser() {

		CustomUserEntity customUserEntity = mock(CustomUserEntity.class);
		UpdateCustomUserInput customUser= mock(UpdateCustomUserInput.class);
	 		
		Mockito.when(_mapper.updateCustomUserInputToCustomUserEntity(any(UpdateCustomUserInput.class))).thenReturn(customUserEntity);
		Mockito.when(_customUserRepository.save(any(CustomUserEntity.class))).thenReturn(customUserEntity);
		Assertions.assertThat(_appService.update(customUserId,customUser)).isEqualTo(_mapper.customUserEntityToUpdateCustomUserOutput(customUserEntity));
	}
    
	@Test
	public void deleteCustomUser_CustomUserIsNotNullAndCustomUserExists_CustomUserRemoved() {

		CustomUserEntity customUser = mock(CustomUserEntity.class);
		Optional<CustomUserEntity> customUserOptional = Optional.of((CustomUserEntity) customUser);
		Mockito.when(_customUserRepository.findById(any(CustomUserId.class))).thenReturn(customUserOptional);
 		
		_appService.delete(customUserId); 
		verify(_customUserRepository).delete(customUser);
	}
	
	@Test
	public void find_ListIsEmpty_ReturnList() throws Exception {

		List<CustomUserEntity> list = new ArrayList<>();
		Page<CustomUserEntity> foundPage = new PageImpl(list);
		Pageable pageable = mock(Pageable.class);
		List<FindCustomUserByIdOutput> output = new ArrayList<>();
		SearchCriteria search= new SearchCriteria();
//		search.setType(1);
//		search.setValue("xyz");
//		search.setOperator("equals");

		Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
		Mockito.when(_customUserRepository.findAll(any(Predicate.class),any(Pageable.class))).thenReturn(foundPage);
		Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
	}
	
	@Test
	public void find_ListIsNotEmpty_ReturnList() throws Exception {

		List<CustomUserEntity> list = new ArrayList<>();
		CustomUserEntity customUser = mock(CustomUserEntity.class);
		list.add(customUser);
    	Page<CustomUserEntity> foundPage = new PageImpl(list);
		Pageable pageable = mock(Pageable.class);
		List<FindCustomUserByIdOutput> output = new ArrayList<>();
        SearchCriteria search= new SearchCriteria();
//		search.setType(1);
//		search.setValue("xyz");
//		search.setOperator("equals");
		output.add(_mapper.customUserEntityToFindCustomUserByIdOutput(customUser));
		
		Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
    	Mockito.when(_customUserRepository.findAll(any(Predicate.class),any(Pageable.class))).thenReturn(foundPage);
		Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
	}
	
	@Test
	public void searchKeyValuePair_PropertyExists_ReturnBooleanBuilder() {
		QCustomUserEntity customUser = QCustomUserEntity.customUserEntity;
	    SearchFields searchFields = new SearchFields();
		searchFields.setOperator("equals");
		searchFields.setSearchValue("xyz");
	    Map<String,SearchFields> map = new HashMap<>();
        map.put("emailAdd",searchFields);
		 Map<String,String> searchMap = new HashMap<>();
        searchMap.put("xyz",String.valueOf(ID));
		BooleanBuilder builder = new BooleanBuilder();
         builder.and(customUser.emailAdd.eq("xyz"));
		Assertions.assertThat(_appService.searchKeyValuePair(customUser,map,searchMap)).isEqualTo(builder);
	}
	
	@Test (expected = Exception.class)
	public void checkProperties_PropertyDoesNotExist_ThrowException() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("xyz");
		_appService.checkProperties(list);
	}
	
	@Test
	public void checkProperties_PropertyExists_ReturnNothing() throws Exception {
		List<String> list = new ArrayList<>();
        list.add("emailAdd");
        list.add("lastName");
        list.add("phone");
        list.add("pwd");
		_appService.checkProperties(list);
	}
	
	@Test
	public void  search_SearchIsNotNullAndSearchContainsCaseThree_ReturnBooleanBuilder() throws Exception {
	
		Map<String,SearchFields> map = new HashMap<>();
		QCustomUserEntity customUser = QCustomUserEntity.customUserEntity;
		List<SearchFields> fieldsList= new ArrayList<>();
		SearchFields fields=new SearchFields();
		SearchCriteria search= new SearchCriteria();
		search.setType(3);
		search.setValue("xyz");
		search.setOperator("equals");
        fields.setFieldName("emailAdd");
        fields.setOperator("equals");
		fields.setSearchValue("xyz");
        fieldsList.add(fields);
        search.setFields(fieldsList);
		BooleanBuilder builder = new BooleanBuilder();
        builder.or(customUser.emailAdd.eq("xyz"));
        Mockito.doNothing().when(_appService).checkProperties(any(List.class));
		Mockito.doReturn(builder).when(_appService).searchKeyValuePair(any(QCustomUserEntity.class), any(HashMap.class), any(HashMap.class));
        
		Assertions.assertThat(_appService.search(search)).isEqualTo(builder);
	}
	
	@Test
	public void  search_StringIsNull_ReturnNull() throws Exception {

		Assertions.assertThat(_appService.search(null)).isEqualTo(null);
	}
   
   //Address
	@Test
	public void GetAddress_IfCustomUserIdAndAddressIdIsNotNullAndCustomUserExists_ReturnAddress() {
		CustomUserEntity customUser = mock(CustomUserEntity.class);
		Optional<CustomUserEntity> customUserOptional = Optional.of((CustomUserEntity) customUser);
		AddressEntity addressEntity = mock(AddressEntity.class);

		Mockito.when(_customUserRepository.findById(any(CustomUserId.class))).thenReturn(customUserOptional);
		Mockito.when(customUser.getAddress()).thenReturn(addressEntity);
		Assertions.assertThat(_appService.getAddress(customUserId)).isEqualTo(_mapper.addressEntityToGetAddressOutput(addressEntity, customUser));
	}

	@Test 
	public void GetAddress_IfCustomUserIdAndAddressIdIsNotNullAndCustomUserDoesNotExist_ReturnNull() {
		Optional<CustomUserEntity> nullOptional = Optional.ofNullable(null);;
		Mockito.when(_customUserRepository.findById(any(CustomUserId.class))).thenReturn(nullOptional);
		Assertions.assertThat(_appService.getAddress(customUserId)).isEqualTo(null);
	}
  
	@Test
	public void ParseCustomUserKey_KeysStringIsNotEmptyAndKeyValuePairExists_ReturnCustomUserId()
	{
		String keyString= "firstName=15,uname=15";
	
		CustomUserId customUserId = new CustomUserId();
		customUserId.setFirstName(String.valueOf(ID));
		customUserId.setUname(String.valueOf(ID));

		Assertions.assertThat(_appService.parseCustomUserKey(keyString)).isEqualToComparingFieldByField(customUserId);
	}
	
	@Test
	public void ParseCustomUserKey_KeysStringIsEmpty_ReturnNull()
	{
		String keyString= "";
		Assertions.assertThat(_appService.parseCustomUserKey(keyString)).isEqualTo(null);
	}
	
	@Test
	public void ParseCustomUserKey_KeysStringIsNotEmptyAndKeyValuePairDoesNotExist_ReturnNull()
	{
		String keyString= "firstName";

		Assertions.assertThat(_appService.parseCustomUserKey(keyString)).isEqualTo(null);
	}
	
}


