package com.fastcode.example.domain.core.store;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.staff.StaffEntity;
import com.fastcode.example.domain.core.address.AddressEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class StoreEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Integer storeId;
    
    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;

    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;


}



