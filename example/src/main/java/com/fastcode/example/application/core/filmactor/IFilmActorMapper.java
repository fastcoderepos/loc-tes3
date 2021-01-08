package com.fastcode.example.application.core.filmactor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.fastcode.example.domain.core.actor.ActorEntity;
import com.fastcode.example.domain.core.film.FilmEntity;
import com.fastcode.example.application.core.filmactor.dto.*;
import com.fastcode.example.domain.core.filmactor.FilmActorEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface IFilmActorMapper {

   FilmActorEntity createFilmActorInputToFilmActorEntity(CreateFilmActorInput filmactorDto);
   
   
   @Mappings({ 
   @Mapping(source = "entity.actor.firstName", target = "actorDescriptiveField"),                    
   @Mapping(source = "entity.film.filmId", target = "filmDescriptiveField"),                    
   }) 
   CreateFilmActorOutput filmActorEntityToCreateFilmActorOutput(FilmActorEntity entity);
   
    FilmActorEntity updateFilmActorInputToFilmActorEntity(UpdateFilmActorInput filmActorDto);
    
    @Mappings({ 
    @Mapping(source = "entity.actor.firstName", target = "actorDescriptiveField"),                    
    @Mapping(source = "entity.film.filmId", target = "filmDescriptiveField"),                    
   	}) 
   	UpdateFilmActorOutput filmActorEntityToUpdateFilmActorOutput(FilmActorEntity entity);

   	@Mappings({ 
   	@Mapping(source = "entity.actor.firstName", target = "actorDescriptiveField"),                    
   	@Mapping(source = "entity.film.filmId", target = "filmDescriptiveField"),                    
   	}) 
   	FindFilmActorByIdOutput filmActorEntityToFindFilmActorByIdOutput(FilmActorEntity entity);


   @Mappings({
   @Mapping(source = "film.filmId", target = "filmId"),                  
   @Mapping(source = "film.lastUpdate", target = "lastUpdate"),                  
   @Mapping(source = "foundFilmActor.actorId", target = "filmActorActorId"),
   @Mapping(source = "foundFilmActor.filmId", target = "filmActorFilmId"),
   })
   GetFilmOutput filmEntityToGetFilmOutput(FilmEntity film, FilmActorEntity foundFilmActor);
   
   @Mappings({
   @Mapping(source = "actor.actorId", target = "actorId"),                  
   @Mapping(source = "actor.lastUpdate", target = "lastUpdate"),                  
   @Mapping(source = "foundFilmActor.actorId", target = "filmActorActorId"),
   @Mapping(source = "foundFilmActor.filmId", target = "filmActorFilmId"),
   })
   GetActorOutput actorEntityToGetActorOutput(ActorEntity actor, FilmActorEntity foundFilmActor);
   
}

