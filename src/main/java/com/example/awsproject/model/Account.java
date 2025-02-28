package com.example.awsproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="accounts")
public class Account {
    @Id
    private int accid;
    private String branch;
    private Float balance;

}

