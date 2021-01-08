package com.fastcode.example.domain.core.filmcategory;

import java.time.*;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
public class FilmCategoryId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Short categoryId;
    private Short filmId;
    
    public FilmCategoryId(Short categoryId,Short filmId) {
 	this.categoryId = categoryId;
 	this.filmId = filmId;
    }
    
}
