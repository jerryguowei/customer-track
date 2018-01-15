package com.jerryguowei.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jerryguowei.springdemo.dao.CustomerDAO;
import com.jerryguowei.springdemo.entity.Customer;
import com.jerryguowei.springdemo.service.CustomerService;



@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

   @GetMapping("/list")
   public String listCustomers(Model theModel){
		List<Customer> theCustomers=customerService.getCustomers();
		theModel.addAttribute("customers",theCustomers);
	    return "list-customers";
   }
   @GetMapping("/showFormForAdd")
   public String showFormForAdd(Model theModel){
	   theModel.addAttribute("customer",new Customer());
	   return "customer-form";
   }
   @RequestMapping(value="/saveCustomer", method = RequestMethod.POST)
   public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
	   customerService.saveCustomer(theCustomer);
	   return "redirect:/customer/list";
   }
   
   @RequestMapping(value="/showFormForUpdate")
   public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel){
	   Customer theCustomer= customerService.getCustomer(theId);
	   theModel.addAttribute("customer",theCustomer);
	   return "customer-form";
   }
   @GetMapping("deleteCustomer")
   public String deleteCustomer(@RequestParam("customerId") int theId){
	   customerService.deleteCustomer(theId);
	   return "redirect:/customer/list";
   }
   
}
