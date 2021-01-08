package com.fastcode.example.domain.core.customuser;

import javax.persistence.*;
import java.time.*;
import com.fastcode.example.domain.core.address.AddressEntity;
import com.fastcode.example.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_user")
@IdClass(CustomUserId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CustomUserEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "first_name", nullable = false,length =255)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false,length =255)
    private String lastName;

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "uname", nullable = false)
    private String uname;

    @Basic
    @Column(name = "is_emailcon", nullable = false)
    private Boolean isEmailcon;
    
    @Basic
    @Column(name = "phone", nullable = true)
    private String phone;

    @Basic
    @Column(name = "email_add", nullable = false,length =255)
    private String emailAdd;

    @Basic
    @Column(name = "pwd", nullable = false,length =255)
    private String pwd;

    @Basic
    @Column(name = "version", nullable = false)
    private Long version;
    
    @Basic
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    
    @ManyToOne
    @JoinColumn(name = "address_id", insertable=false, updatable=false)
    private AddressEntity address;


}



