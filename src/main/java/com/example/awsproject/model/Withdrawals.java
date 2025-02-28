package com.example.awsproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "withdrawals")
public class Withdrawals {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},targetEntity = Customer.class)
    private Customer customer;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Account.class)
    private Account account;


    private String date;

    private Float amount;


}
