package com.myReservations.MyCrud.Repository;

import com.myReservations.MyCrud.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository {

    List<Customer> findByName(String name, long id);

}
