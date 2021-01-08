package com.fastcode.example.domain.core.filmcategory;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.category.CategoryEntity;
import com.fastcode.example.domain.core.film.FilmEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film_category")
@IdClass(FilmCategoryId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FilmCategoryEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "category_id", nullable = false)
    private Short categoryId;
    
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "film_id", nullable = false)
    private Short filmId;
    
    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "film_id", insertable=false, updatable=false)
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable=false, updatable=false)
    private CategoryEntity category;


}



