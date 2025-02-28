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
    private String name;
    private String address;

}
