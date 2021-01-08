package com.fastcode.example.application.extended.payment;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.payment.IPaymentMapper;

@Mapper(componentModel = "spring")
public interface IPaymentMapperExtended extends IPaymentMapper {

}

