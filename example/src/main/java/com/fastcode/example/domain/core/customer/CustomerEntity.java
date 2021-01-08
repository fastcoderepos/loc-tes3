package com.fastcode.example.domain.core.customer;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.address.AddressEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CustomerEntity extends AbstractEntity {

    @Basic
    @Column(name = "last_name", nullable = false,length =45)
    private String lastName;

    @Basic
    @Column(name = "active", nullable = true)
    private Integer active;
    
    @Basic
    @Column(name = "activebool", nullable = false)
    private Boolean activebool;
    
    @Basic
    @Column(name = "store_id", nullable = false)
    private Short storeId;
    
    @Basic
    @Column(name = "first_name", nullable = false,length =45)
    private String firstName;

    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;
    
    @Basic
    @Column(name = "email", nullable = true,length =50)
    private String email;

    @Basic
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;


}



