package com.fastcode.example.application.extended.address;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.address.IAddressMapper;

@Mapper(componentModel = "spring")
public interface IAddressMapperExtended extends IAddressMapper {

}

