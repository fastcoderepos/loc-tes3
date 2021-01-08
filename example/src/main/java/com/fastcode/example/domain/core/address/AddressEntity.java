package com.fastcode.example.domain.core.address;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.city.CityEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class AddressEntity extends AbstractEntity {

    @Basic
    @Column(name = "address", nullable = false,length =50)
    private String address;

    @Basic
    @Column(name = "address2", nullable = true,length =50)
    private String address2;

    @Basic
    @Column(name = "postal_code", nullable = true,length =10)
    private String postalCode;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer addressId;
    
    @Basic
    @Column(name = "phone", nullable = false,length =20)
    private String phone;

    @Basic
    @Column(name = "district", nullable = false,length =20)
    private String district;

    @Basic
    @Column(name = "last_update", nullable = true)
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;


}



