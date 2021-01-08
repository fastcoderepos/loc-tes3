package com.fastcode.example.application.core.city;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.fastcode.example.domain.core.country.CountryEntity;
import com.fastcode.example.application.core.city.dto.*;
import com.fastcode.example.domain.core.city.CityEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface ICityMapper {

   CityEntity createCityInputToCityEntity(CreateCityInput cityDto);
   
   
   @Mappings({ 
   @Mapping(source = "entity.country.countryId", target = "countryId"),                   
   @Mapping(source = "entity.country.countryId", target = "countryDescriptiveField"),                    
   }) 
   CreateCityOutput cityEntityToCreateCityOutput(CityEntity entity);
   
    CityEntity updateCityInputToCityEntity(UpdateCityInput cityDto);
    
    @Mappings({ 
    @Mapping(source = "entity.country.countryId", target = "countryId"),                   
    @Mapping(source = "entity.country.countryId", target = "countryDescriptiveField"),                    
   	}) 
   	UpdateCityOutput cityEntityToUpdateCityOutput(CityEntity entity);

   	@Mappings({ 
   	@Mapping(source = "entity.country.countryId", target = "countryId"),                   
   	@Mapping(source = "entity.country.countryId", target = "countryDescriptiveField"),                    
   	}) 
   	FindCityByIdOutput cityEntityToFindCityByIdOutput(CityEntity entity);


   @Mappings({
   @Mapping(source = "country.country", target = "country"),                  
   @Mapping(source = "country.lastUpdate", target = "lastUpdate"),                  
   @Mapping(source = "foundCity.cityId", target = "cityCityId"),
   })
   GetCountryOutput countryEntityToGetCountryOutput(CountryEntity country, CityEntity foundCity);
   
}

