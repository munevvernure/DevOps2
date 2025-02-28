package com.example.awsproject.controller;

import com.example.awsproject.model.Customer;
import com.example.awsproject.model.Withdrawals;
import com.example.awsproject.repository.WithdrawalRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;

@RestController
@RequestMapping("/with")
public class WithdrawalController {
    private static final Logger logger = LoggerFactory.getLogger(WithdrawalController.class);
    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @GetMapping(value = "",produces = "application/json")
    public ResponseEntity<Iterable<Withdrawals>> getAllWithdrawals() {
        Iterable<Withdrawals> custList=withdrawalRepository.findAll();
        return new ResponseEntity<>(custList, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<Withdrawals> getWithdrawalsById(@PathVariable int id)
    {
        Withdrawals withdrawals= withdrawalRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found with id: " + id));
        logger.info("Get Withdrawal with id: " + id);
        return new ResponseEntity<>(withdrawals,HttpStatus.OK);
    }
    @PostMapping(value = "/add",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Withdrawals> addWithdrawals(@RequestBody Withdrawals withdrawals) {
        logger.info("Withdrawal created with id: " + withdrawals.getId());
        return new ResponseEntity<>(withdrawalRepository.save(withdrawals),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Withdrawals> updateWithdrawals(@PathVariable Integer id, @RequestBody Withdrawals withdrawals) {
        if(id==null || id==0 || withdrawals== null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Withdrawals w = withdrawalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Withdrawal not found with id: " + id));
        w.setId(id);
        w.setCustomer(withdrawals.getCustomer());
        w.setAccount(withdrawals.getAccount());
        w.setDate(withdrawals.getDate());
        w.setAmount(withdrawals.getAmount());


        Withdrawals updatedWithdrawal = withdrawalRepository.save(w);
        return new ResponseEntity<>(updatedWithdrawal,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteWithdrawals(@PathVariable Integer id){
        if (id==null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Withdrawals w =withdrawalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Withdrawal not found with id: " + id));
        withdrawalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
