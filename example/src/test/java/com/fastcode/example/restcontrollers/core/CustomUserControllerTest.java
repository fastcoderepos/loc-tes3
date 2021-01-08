package com.fastcode.example.restcontrollers.core;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import java.time.*;
import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.core.env.Environment;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.fastcode.example.commons.search.SearchUtils;
import com.fastcode.example.application.core.customuser.CustomUserAppService;
import com.fastcode.example.application.core.customuser.dto.*;
import com.fastcode.example.domain.core.customuser.ICustomUserRepository;
import com.fastcode.example.domain.core.customuser.CustomUserEntity;
import com.fastcode.example.domain.core.address.IAddressRepository;
import com.fastcode.example.domain.core.address.AddressEntity;
import com.fastcode.example.domain.core.rental.IRentalRepository;
import com.fastcode.example.domain.core.rental.RentalEntity;
import com.fastcode.example.domain.core.language.ILanguageRepository;
import com.fastcode.example.domain.core.language.LanguageEntity;
import com.fastcode.example.domain.core.customer.ICustomerRepository;
import com.fastcode.example.domain.core.customer.CustomerEntity;
import com.fastcode.example.domain.core.film.IFilmRepository;
import com.fastcode.example.domain.core.film.FilmEntity;
import com.fastcode.example.domain.core.staff.IStaffRepository;
import com.fastcode.example.domain.core.staff.StaffEntity;
import com.fastcode.example.domain.core.country.ICountryRepository;
import com.fastcode.example.domain.core.country.CountryEntity;
import com.fastcode.example.domain.core.city.ICityRepository;
import com.fastcode.example.domain.core.city.CityEntity;
import com.fastcode.example.domain.core.inventory.IInventoryRepository;
import com.fastcode.example.domain.core.inventory.InventoryEntity;
import com.fastcode.example.application.core.address.AddressAppService;    
import com.fastcode.example.domain.core.customuser.CustomUserId;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
				properties = "spring.profiles.active=test")
public class CustomUserControllerTest {
	
	@Autowired
	protected SortHandlerMethodArgumentResolver sortArgumentResolver;

	@Autowired
	@Qualifier("customUserRepository") 
	protected ICustomUserRepository customUser_repository;
	
	@Autowired
	@Qualifier("addressRepository") 
	protected IAddressRepository addressRepository;
	
	@Autowired
	@Qualifier("rentalRepository") 
	protected IRentalRepository rentalRepository;
	
	@Autowired
	@Qualifier("languageRepository") 
	protected ILanguageRepository languageRepository;
	
	@Autowired
	@Qualifier("customerRepository") 
	protected ICustomerRepository customerRepository;
	
	@Autowired
	@Qualifier("filmRepository") 
	protected IFilmRepository filmRepository;
	
	@Autowired
	@Qualifier("staffRepository") 
	protected IStaffRepository staffRepository;
	
	@Autowired
	@Qualifier("countryRepository") 
	protected ICountryRepository countryRepository;
	
	@Autowired
	@Qualifier("cityRepository") 
	protected ICityRepository cityRepository;
	
	@Autowired
	@Qualifier("inventoryRepository") 
	protected IInventoryRepository inventoryRepository;
	
	@SpyBean
	@Qualifier("customUserAppService")
	protected CustomUserAppService customUserAppService;
	
    @SpyBean
    @Qualifier("addressAppService")
	protected AddressAppService  addressAppService;
	
	@SpyBean
	protected LoggingHelper logHelper;

	@SpyBean
	protected Environment env;

	@Mock
	protected Logger loggerMock;

	protected CustomUserEntity customUser;

	protected MockMvc mvc;
	
	@Autowired
	EntityManagerFactory emf;
	
    static EntityManagerFactory emfs;
    
    static int relationCount = 10;
    
	int countAddress = 10;
	
	int countRental = 10;
	
	int countLanguage = 10;
	
	int countCustomer = 10;
	
	int countFilm = 10;
	
	int countStaff = 10;
	
	int countCountry = 10;
	
	int countCity = 10;
	
	int countInventory = 10;
	
	@PostConstruct
	public void init() {
	emfs = emf;
	}

	@AfterClass
	public static void cleanup() {
		EntityManager em = emfs.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
		em.createNativeQuery("truncate table public.custom_user").executeUpdate();
		em.createNativeQuery("truncate table public.address").executeUpdate();
		em.createNativeQuery("truncate table public.rental").executeUpdate();
		em.createNativeQuery("truncate table public.language").executeUpdate();
		em.createNativeQuery("truncate table public.customer").executeUpdate();
		em.createNativeQuery("truncate table public.film").executeUpdate();
		em.createNativeQuery("truncate table public.staff").executeUpdate();
		em.createNativeQuery("truncate table public.country").executeUpdate();
		em.createNativeQuery("truncate table public.city").executeUpdate();
		em.createNativeQuery("truncate table public.inventory").executeUpdate();
	 	em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
		em.getTransaction().commit();
	}
	
	public AddressEntity createAddressEntity() {
	
		if(countAddress>60) {
			countAddress = 10;
		}
		
		AddressEntity addressEntity = new AddressEntity();
  		addressEntity.setAddress(String.valueOf(relationCount));
  		addressEntity.setAddress2(String.valueOf(relationCount));
		addressEntity.setAddressId(relationCount);
  		addressEntity.setDistrict(String.valueOf(relationCount));
		addressEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countAddress+"-09-01 05:25:22"));
  		addressEntity.setPhone(String.valueOf(relationCount));
  		addressEntity.setPostalCode(String.valueOf(relationCount));
		addressEntity.setVersiono(0L);
		relationCount++;
		CityEntity city= createCityEntity();
		addressEntity.setCity(city);
		if(!addressRepository.findAll().contains(addressEntity))
		{
			 addressEntity = addressRepository.save(addressEntity);
		}
		countAddress++;
	    return addressEntity;
	}
	public RentalEntity createRentalEntity() {
	
		if(countRental>60) {
			countRental = 10;
		}
		
		RentalEntity rentalEntity = new RentalEntity();
		rentalEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countRental+"-09-01 05:25:22"));
		rentalEntity.setRentalDate(SearchUtils.stringToLocalDateTime("19"+countRental+"-09-01 05:25:22"));
		rentalEntity.setRentalId(relationCount);
		rentalEntity.setReturnDate(SearchUtils.stringToLocalDateTime("19"+countRental+"-09-01 05:25:22"));
		rentalEntity.setVersiono(0L);
		relationCount++;
		StaffEntity staff= createStaffEntity();
		rentalEntity.setStaff(staff);
		InventoryEntity inventory= createInventoryEntity();
		rentalEntity.setInventory(inventory);
		CustomerEntity customer= createCustomerEntity();
		rentalEntity.setCustomer(customer);
		if(!rentalRepository.findAll().contains(rentalEntity))
		{
			 rentalEntity = rentalRepository.save(rentalEntity);
		}
		countRental++;
	    return rentalEntity;
	}
	public LanguageEntity createLanguageEntity() {
	
		if(countLanguage>60) {
			countLanguage = 10;
		}
		
		LanguageEntity languageEntity = new LanguageEntity();
		languageEntity.setLanguageId(relationCount);
		languageEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countLanguage+"-09-01 05:25:22"));
  		languageEntity.setName(String.valueOf(relationCount));
		languageEntity.setVersiono(0L);
		relationCount++;
		if(!languageRepository.findAll().contains(languageEntity))
		{
			 languageEntity = languageRepository.save(languageEntity);
		}
		countLanguage++;
	    return languageEntity;
	}
	public CustomerEntity createCustomerEntity() {
	
		if(countCustomer>60) {
			countCustomer = 10;
		}
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setActive(relationCount);
		customerEntity.setActivebool(false);
		customerEntity.setCreateDate(SearchUtils.stringToLocalDate("19"+countCustomer+"-09-01"));
		customerEntity.setCustomerId(relationCount);
  		customerEntity.setEmail(String.valueOf(relationCount));
  		customerEntity.setFirstName(String.valueOf(relationCount));
  		customerEntity.setLastName(String.valueOf(relationCount));
		customerEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countCustomer+"-09-01 05:25:22"));
		customerEntity.setStoreId((short)relationCount);
		customerEntity.setVersiono(0L);
		relationCount++;
		AddressEntity address= createAddressEntity();
		customerEntity.setAddress(address);
		if(!customerRepository.findAll().contains(customerEntity))
		{
			 customerEntity = customerRepository.save(customerEntity);
		}
		countCustomer++;
	    return customerEntity;
	}
	public FilmEntity createFilmEntity() {
	
		if(countFilm>60) {
			countFilm = 10;
		}
		
		FilmEntity filmEntity = new FilmEntity();
  		filmEntity.setDescription(String.valueOf(relationCount));
		filmEntity.setFilmId(relationCount);
		filmEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countFilm+"-09-01 05:25:22"));
		filmEntity.setLength((short)relationCount);
  		filmEntity.setRating(String.valueOf(relationCount));
		filmEntity.setRentalDuration((short)relationCount);
		filmEntity.setRentalRate(BigDecimal.valueOf(relationCount));
		filmEntity.setReplacementCost(BigDecimal.valueOf(relationCount));
  		filmEntity.setTitle(String.valueOf(relationCount));
		filmEntity.setVersiono(0L);
		relationCount++;
		LanguageEntity language= createLanguageEntity();
		filmEntity.setLanguage(language);
		if(!filmRepository.findAll().contains(filmEntity))
		{
			 filmEntity = filmRepository.save(filmEntity);
		}
		countFilm++;
	    return filmEntity;
	}
	public StaffEntity createStaffEntity() {
	
		if(countStaff>60) {
			countStaff = 10;
		}
		
		StaffEntity staffEntity = new StaffEntity();
		staffEntity.setActive(false);
  		staffEntity.setEmail(String.valueOf(relationCount));
  		staffEntity.setFirstName(String.valueOf(relationCount));
  		staffEntity.setLastName(String.valueOf(relationCount));
		staffEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countStaff+"-09-01 05:25:22"));
  		staffEntity.setPassword(String.valueOf(relationCount));
		staffEntity.setStaffId(relationCount);
		staffEntity.setStoreId((short)relationCount);
  		staffEntity.setUsername(String.valueOf(relationCount));
		staffEntity.setVersiono(0L);
		relationCount++;
		AddressEntity address= createAddressEntity();
		staffEntity.setAddress(address);
		if(!staffRepository.findAll().contains(staffEntity))
		{
			 staffEntity = staffRepository.save(staffEntity);
		}
		countStaff++;
	    return staffEntity;
	}
	public CountryEntity createCountryEntity() {
	
		if(countCountry>60) {
			countCountry = 10;
		}
		
		CountryEntity countryEntity = new CountryEntity();
  		countryEntity.setCountry(String.valueOf(relationCount));
		countryEntity.setCountryId(relationCount);
		countryEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countCountry+"-09-01 05:25:22"));
		countryEntity.setVersiono(0L);
		relationCount++;
		if(!countryRepository.findAll().contains(countryEntity))
		{
			 countryEntity = countryRepository.save(countryEntity);
		}
		countCountry++;
	    return countryEntity;
	}
	public CityEntity createCityEntity() {
	
		if(countCity>60) {
			countCity = 10;
		}
		
		CityEntity cityEntity = new CityEntity();
  		cityEntity.setCity(String.valueOf(relationCount));
		cityEntity.setCityId(relationCount);
		cityEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countCity+"-09-01 05:25:22"));
		cityEntity.setVersiono(0L);
		relationCount++;
		CountryEntity country= createCountryEntity();
		cityEntity.setCountry(country);
		if(!cityRepository.findAll().contains(cityEntity))
		{
			 cityEntity = cityRepository.save(cityEntity);
		}
		countCity++;
	    return cityEntity;
	}
	public InventoryEntity createInventoryEntity() {
	
		if(countInventory>60) {
			countInventory = 10;
		}
		
		InventoryEntity inventoryEntity = new InventoryEntity();
		inventoryEntity.setInventoryId(relationCount);
		inventoryEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19"+countInventory+"-09-01 05:25:22"));
		inventoryEntity.setStoreId((short)relationCount);
		inventoryEntity.setVersiono(0L);
		relationCount++;
		FilmEntity film= createFilmEntity();
		inventoryEntity.setFilm(film);
		if(!inventoryRepository.findAll().contains(inventoryEntity))
		{
			 inventoryEntity = inventoryRepository.save(inventoryEntity);
		}
		countInventory++;
	    return inventoryEntity;
	}

	public CustomUserEntity createEntity() {
		AddressEntity address = createAddressEntity();
	
		CustomUserEntity customUserEntity = new CustomUserEntity();
  		customUserEntity.setEmailAdd("1");
		customUserEntity.setEnabled(false);
  		customUserEntity.setFirstName("1");
		customUserEntity.setIsEmailcon(false);
  		customUserEntity.setLastName("1");
  		customUserEntity.setPhone("1");
  		customUserEntity.setPwd("1");
  		customUserEntity.setUname("1");
		customUserEntity.setVersion(1L);
		customUserEntity.setVersiono(0L);
		customUserEntity.setAddress(address);
		
		return customUserEntity;
	}

	public CreateCustomUserInput createCustomUserInput() {
	
	    CreateCustomUserInput customUserInput = new CreateCustomUserInput();
  		customUserInput.setEmailAdd("5");
		customUserInput.setEnabled(false);
  		customUserInput.setFirstName("5");
		customUserInput.setIsEmailcon(false);
  		customUserInput.setLastName("5");
  		customUserInput.setPhone("5");
  		customUserInput.setPwd("5");
  		customUserInput.setUname("5");
		customUserInput.setVersion(5L);
		
		return customUserInput;
	}

	public CustomUserEntity createNewEntity() {
		CustomUserEntity customUser = new CustomUserEntity();
		customUser.setEmailAdd("3");
		customUser.setEnabled(false);
		customUser.setFirstName("3");
		customUser.setIsEmailcon(false);
		customUser.setLastName("3");
		customUser.setPhone("3");
		customUser.setPwd("3");
		customUser.setUname("3");
		customUser.setVersion(3L);
		
		return customUser;
	}
	
	public CustomUserEntity createUpdateEntity() {
		CustomUserEntity customUser = new CustomUserEntity();
		customUser.setEmailAdd("4");
		customUser.setEnabled(false);
		customUser.setFirstName("4");
		customUser.setIsEmailcon(false);
		customUser.setLastName("4");
		customUser.setPhone("4");
		customUser.setPwd("4");
		customUser.setUname("4");
		customUser.setVersion(4L);
		
		return customUser;
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
    
		final CustomUserController customUserController = new CustomUserController(customUserAppService, addressAppService,
	logHelper,env);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());

		this.mvc = MockMvcBuilders.standaloneSetup(customUserController)
				.setCustomArgumentResolvers(sortArgumentResolver)
				.setControllerAdvice()
				.build();
	}

	@Before
	public void initTest() {

		customUser= createEntity();
		List<CustomUserEntity> list= customUser_repository.findAll();
		if(!list.contains(customUser)) {
			customUser=customUser_repository.save(customUser);
		}

	}

	@Test
	public void FindById_IdIsValid_ReturnStatusOk() throws Exception {
	
		mvc.perform(get("/customUser/firstName=" + customUser.getFirstName()+ ",uname=" + customUser.getUname()+"/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}  

	@Test
	public void FindById_IdIsNotValid_ReturnStatusNotFound() {

		 org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(get("/customUser/firstName=999,uname=999")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Not found"));

	}
	@Test
	public void CreateCustomUser_CustomUserDoesNotExist_ReturnStatusOk() throws Exception {
		CreateCustomUserInput customUserInput = createCustomUserInput();	
			
	    
		AddressEntity address =  createAddressEntity();

		customUserInput.setAddressId(Integer.parseInt(address.getAddressId().toString()));

		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
	
		String json = ow.writeValueAsString(customUserInput);

		mvc.perform(post("/customUser").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

	}     
	

	@Test
	public void DeleteCustomUser_IdIsNotValid_ThrowEntityNotFoundException() {

        doReturn(null).when(customUserAppService).findById(new CustomUserId("999", "999"));
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(delete("/customUser/firstName=999,uname=999")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new EntityNotFoundException("There does not exist a customUser with a id=firstName=999,uname=999"));

	}  

	@Test
	public void Delete_IdIsValid_ReturnStatusNoContent() throws Exception {
	
	 	CustomUserEntity entity =  createNewEntity();
	 	entity.setVersiono(0L);
		AddressEntity address = createAddressEntity();
		entity.setAddress(address);
		entity = customUser_repository.save(entity);
		

		FindCustomUserByIdOutput output= new FindCustomUserByIdOutput();
		output.setEmailAdd(entity.getEmailAdd());
		output.setEnabled(entity.getEnabled());
		output.setFirstName(entity.getFirstName());
		output.setIsEmailcon(entity.getIsEmailcon());
		output.setLastName(entity.getLastName());
		output.setPwd(entity.getPwd());
		output.setUname(entity.getUname());
		output.setVersion(entity.getVersion());
		
	//    Mockito.when(customUserAppService.findById(new CustomUserId(entity.getFirstName(), entity.getUname()))).thenReturn(output);
        Mockito.doReturn(output).when(customUserAppService).findById(new CustomUserId(entity.getFirstName(), entity.getUname()));
        
		mvc.perform(delete("/customUser/firstName="+ entity.getFirstName()+ ",uname="+ entity.getUname()+"/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}  


	@Test
	public void UpdateCustomUser_CustomUserDoesNotExist_ReturnStatusNotFound() throws Exception {
   
        doReturn(null).when(customUserAppService).findById(new CustomUserId("999", "999"));
        
        UpdateCustomUserInput customUser = new UpdateCustomUserInput();
  		customUser.setEmailAdd("999");
		customUser.setEnabled(false);
  		customUser.setFirstName("999");
		customUser.setIsEmailcon(false);
  		customUser.setLastName("999");
  		customUser.setPhone("999");
  		customUser.setPwd("999");
  		customUser.setUname("999");
		customUser.setVersion(999L);

		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(customUser);

		 org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(put("/customUser/firstName=999,uname=999").contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Unable to update. CustomUser with id=firstName=999,uname=999 not found."));
	}    

	@Test
	public void UpdateCustomUser_CustomUserExists_ReturnStatusOk() throws Exception {
		CustomUserEntity entity =  createUpdateEntity();
		entity.setVersiono(0L);
		
		AddressEntity address = createAddressEntity();
		entity.setAddress(address);
		entity = customUser_repository.save(entity);
		FindCustomUserByIdOutput output= new FindCustomUserByIdOutput();
		output.setEmailAdd(entity.getEmailAdd());
		output.setEnabled(entity.getEnabled());
		output.setFirstName(entity.getFirstName());
		output.setIsEmailcon(entity.getIsEmailcon());
		output.setLastName(entity.getLastName());
		output.setPhone(entity.getPhone());
		output.setPwd(entity.getPwd());
		output.setUname(entity.getUname());
		output.setVersion(entity.getVersion());
		output.setVersiono(entity.getVersiono());
		
	    Mockito.when(customUserAppService.findById(new CustomUserId(entity.getFirstName(), entity.getUname()))).thenReturn(output);
        
		UpdateCustomUserInput customUserInput = new UpdateCustomUserInput();
		customUserInput.setEmailAdd(entity.getEmailAdd());
		customUserInput.setEnabled(entity.getEnabled());
		customUserInput.setFirstName(entity.getFirstName());
		customUserInput.setIsEmailcon(entity.getIsEmailcon());
		customUserInput.setLastName(entity.getLastName());
		customUserInput.setPwd(entity.getPwd());
		customUserInput.setUname(entity.getUname());
		customUserInput.setVersion(entity.getVersion());
		
		customUserInput.setAddressId(Integer.parseInt(address.getAddressId().toString()));
		
		
		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(customUserInput);
	
		mvc.perform(put("/customUser/firstName=" + entity.getFirstName()+ ",uname=" + entity.getUname()+"/").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

		CustomUserEntity de = createUpdateEntity();
		de.setFirstName(entity.getFirstName());
		de.setUname(entity.getUname());
		customUser_repository.delete(de);
		

	}    
	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsValid_ReturnStatusOk() throws Exception {

		mvc.perform(get("/customUser?search=firstName[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}    

	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsNotValid_ThrowException() throws Exception {

		org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/customUser?search=customUserfirstName[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new Exception("Wrong URL Format: Property customUserfirstName not found!"));

	} 
	
	
	@Test
	public void GetAddress_IdIsNotEmptyAndIdIsNotValid_ThrowException() {
		
		org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/customUser/firstName999/address")
				.contentType(MediaType.APPLICATION_JSON))
	    		  .andExpect(status().isOk())).hasCause(new EntityNotFoundException("Invalid id=firstName999"));
	
	}    
	@Test
	public void GetAddress_IdIsNotEmptyAndIdDoesNotExist_ReturnNotFound() {
  
	   org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/customUser/firstName=999,uname=999/address")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Not found"));
	
	}    
	
	@Test
	public void GetAddress_searchIsNotEmptyAndPropertyIsValid_ReturnList() throws Exception {
	
	   mvc.perform(get("/customUser/firstName=" + customUser.getFirstName()+ ",uname=" + customUser.getUname()+ "/address")
				.contentType(MediaType.APPLICATION_JSON))
	    		  .andExpect(status().isOk());
	}  
	
    
}

