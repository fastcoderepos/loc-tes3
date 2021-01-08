package com.fastcode.example.application.extended.country;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.country.ICountryMapper;

@Mapper(componentModel = "spring")
public interface ICountryMapperExtended extends ICountryMapper {

}

