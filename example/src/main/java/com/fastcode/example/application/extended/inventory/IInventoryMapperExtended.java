package com.fastcode.example.application.extended.inventory;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.inventory.IInventoryMapper;

@Mapper(componentModel = "spring")
public interface IInventoryMapperExtended extends IInventoryMapper {

}

