package com.fastcode.example.domain.core.filmactor;

import java.time.*;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
public class FilmActorId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Short actorId;
    private Short filmId;
    
    public FilmActorId(Short actorId,Short filmId) {
 	this.actorId = actorId;
 	this.filmId = filmId;
    }
    
}
