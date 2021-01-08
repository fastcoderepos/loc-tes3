package com.fastcode.example.application.core.country;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.country.dto.*;
import com.fastcode.example.domain.core.country.CountryEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface ICountryMapper {

   CountryEntity createCountryInputToCountryEntity(CreateCountryInput countryDto);
   
   
   CreateCountryOutput countryEntityToCreateCountryOutput(CountryEntity entity);
   
    CountryEntity updateCountryInputToCountryEntity(UpdateCountryInput countryDto);
    
   	UpdateCountryOutput countryEntityToUpdateCountryOutput(CountryEntity entity);

   	FindCountryByIdOutput countryEntityToFindCountryByIdOutput(CountryEntity entity);


}

