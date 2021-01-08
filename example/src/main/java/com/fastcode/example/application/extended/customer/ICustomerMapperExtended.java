package com.fastcode.example.application.extended.customer;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.customer.ICustomerMapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapperExtended extends ICustomerMapper {

}

