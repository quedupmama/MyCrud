package com.myReservations.MyCrud.Controller;

import com.myReservations.MyCrud.Customer.Customer;
import com.myReservations.MyCrud.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/Customers/")

public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;
    private  Customer customer;

    @GetMapping("showForm")
    public String ShowReservationForm() {
        return "add reservation";

    }

    @GetMapping("list")
    public String Customer(Model model) {
        model.addAttribute("Customer", this.customerRepo.findAll());
        return "index";

    }
    @PostMapping("add")
    public String addCustomer(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add customer";
        }
        this.customerRepo.save(customer);
        return "redirect:list";
    }
    @GetMapping("edit/{id}")
    public String showReservationForm(@PathVariable("id") long id, Model model){
       Optional Customer = this.customerRepo.findById(id);

        model.addAttribute("customer", customer);
        return "update customer";
    }
    @GetMapping("update/{id}")
    public String updateCustomer(@PathVariable("id") long id, @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "update customer";
        }

    this.customerRepo.save(customer);
    model.addAttribute("customer", this.customerRepo.findAll());
    return "index";
    }
    @DeleteMapping("delete/{id}")
    public String deleteCustomer(@PathVariable ("id") long id, Model model){


        try {
            this.customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer id :" + id));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        this.customerRepo.delete(customer);
       model.addAttribute("customer", this.customerRepo.findAll());
        return "index";
    }
}

