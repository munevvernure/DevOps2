package com.example.awsproject.repository;

import com.example.awsproject.model.Withdrawals;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawalRepository extends CrudRepository<Withdrawals, Integer> {
}
