package com.fastcode.example.domain.core.language;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "language")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LanguageEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", nullable = false)
    private Integer languageId;
    
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;


}



