package com.fastcode.example.application.extended.customuser;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.customuser.ICustomUserMapper;

@Mapper(componentModel = "spring")
public interface ICustomUserMapperExtended extends ICustomUserMapper {

}

