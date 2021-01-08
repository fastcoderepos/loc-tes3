package com.fastcode.example.application.extended.film;

import org.mapstruct.Mapper;
import com.fastcode.example.application.core.film.IFilmMapper;

@Mapper(componentModel = "spring")
public interface IFilmMapperExtended extends IFilmMapper {

}

