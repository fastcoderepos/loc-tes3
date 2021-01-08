package com.fastcode.example.domain.core.country;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "country")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CountryEntity extends AbstractEntity {

    @Basic
    @Column(name = "country", nullable = false,length =50)
    private String country;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Integer countryId;
    
    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;


}



