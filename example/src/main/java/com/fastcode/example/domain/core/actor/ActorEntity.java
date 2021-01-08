package com.fastcode.example.domain.core.actor;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ActorEntity extends AbstractEntity {

    @Basic
    @Column(name = "first_name", nullable = false,length =45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false,length =45)
    private String lastName;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false)
    private Integer actorId;
    
    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;


}



