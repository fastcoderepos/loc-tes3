package com.fastcode.example.domain.core.film;

import javax.persistence.*;
import java.time.*;
import java.math.BigDecimal;
import com.fastcode.example.domain.core.language.LanguageEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FilmEntity extends AbstractEntity {

    @Basic
    @Column(name = "rental_rate", nullable = false)
    private BigDecimal rentalRate;
    
    @Basic
    @Column(name = "rental_duration", nullable = false)
    private Short rentalDuration;
    
    @Basic
    @Column(name = "length", nullable = true)
    private Short length;
    
    @Basic
    @Column(name = "rating", nullable = true,length =256)
    private String rating;

    @Basic
    @Column(name = "description", nullable = true)
    private String description;

    @Basic
    @Column(name = "replacement_cost", nullable = false)
    private BigDecimal replacementCost;
    
    @Basic
    @Column(name = "title", nullable = false,length =255)
    private String title;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false)
    private Integer filmId;
    
    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity language;


}



