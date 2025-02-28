package com.example.awsproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customers")
public class Customer {
    @Id
    private int custid;
    private String firstname;
    private String address;
    private String city;

    @ManyToOne
    @JoinColumn(name = "accountid", nullable = false)
    private Account account;

}
