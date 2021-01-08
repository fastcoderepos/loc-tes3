package com.fastcode.example.domain.core.inventory;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.film.FilmEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class InventoryEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", nullable = false)
    private Integer inventoryId;
    
    @Basic
    @Column(name = "store_id", nullable = false)
    private Short storeId;
    
    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;


}



