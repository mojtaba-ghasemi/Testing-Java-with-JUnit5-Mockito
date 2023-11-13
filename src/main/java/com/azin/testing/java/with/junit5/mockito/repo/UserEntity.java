package com.azin.testing.java.with.junit5.mockito.repo;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String userId;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=120)
    private String email;

    @Column(nullable=false)
    private String encryptedPassword;
}
