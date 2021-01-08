package com.fastcode.example.domain.core.rental;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.staff.StaffEntity;
import com.fastcode.example.domain.core.inventory.InventoryEntity;
import com.fastcode.example.domain.core.customer.CustomerEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rental")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class RentalEntity extends AbstractEntity {

    @Basic
    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate;

    @Basic
    @Column(name = "return_date", nullable = true)
    private LocalDateTime returnDate;

    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id", nullable = false)
    private Integer rentalId;
    
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;


}



