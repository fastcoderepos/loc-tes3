package com.fastcode.example.domain.core.payment;

import javax.persistence.*;
import java.time.*;
import java.math.BigDecimal;
import com.fastcode.example.domain.core.customer.CustomerEntity;
import com.fastcode.example.domain.core.rental.RentalEntity;
import com.fastcode.example.domain.core.staff.StaffEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PaymentEntity extends AbstractEntity {

    @Basic
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    
    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer paymentId;
    
    @Basic
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private RentalEntity rental;


}



