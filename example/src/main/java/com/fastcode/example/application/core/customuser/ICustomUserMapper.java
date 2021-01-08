package com.fastcode.example.application.core.customuser;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.fastcode.example.domain.core.address.AddressEntity;
import com.fastcode.example.application.core.customuser.dto.*;
import com.fastcode.example.domain.core.customuser.CustomUserEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface ICustomUserMapper {

   CustomUserEntity createCustomUserInputToCustomUserEntity(CreateCustomUserInput customuserDto);
   
   
   @Mappings({ 
   @Mapping(source = "entity.address.addressId", target = "addressId"),                   
   @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),                    
   }) 
   CreateCustomUserOutput customUserEntityToCreateCustomUserOutput(CustomUserEntity entity);
   
    CustomUserEntity updateCustomUserInputToCustomUserEntity(UpdateCustomUserInput customUserDto);
    
    @Mappings({ 
    @Mapping(source = "entity.address.addressId", target = "addressId"),                   
    @Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),                    
   	}) 
   	UpdateCustomUserOutput customUserEntityToUpdateCustomUserOutput(CustomUserEntity entity);

   	@Mappings({ 
   	@Mapping(source = "entity.address.addressId", target = "addressId"),                   
   	@Mapping(source = "entity.address.addressId", target = "addressDescriptiveField"),                    
   	}) 
   	FindCustomUserByIdOutput customUserEntityToFindCustomUserByIdOutput(CustomUserEntity entity);


   @Mappings({
   @Mapping(source = "address.address", target = "address"),                  
   @Mapping(source = "address.phone", target = "phone"),                  
   @Mapping(source = "foundCustomUser.firstName", target = "customUserFirstName"),
   @Mapping(source = "foundCustomUser.uname", target = "customUserUname"),
   })
   GetAddressOutput addressEntityToGetAddressOutput(AddressEntity address, CustomUserEntity foundCustomUser);
   
}

