package com.fastcode.example.application.core.filmactor.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FindFilmActorByIdOutput {

  	private Short actorId;
  	private Short filmId;
  	private LocalDateTime lastUpdate;
  	private String actorDescriptiveField;
  	private Integer filmDescriptiveField;
	private Long versiono;
 
}

