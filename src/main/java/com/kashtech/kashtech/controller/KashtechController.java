package com.kashtech.kashtech.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kashtech.kashtech.model.Employee;



@RestController
public class KashtechController {
	
   private static Map<String, Employee> employeeRepo = new HashMap<>();
   
   static {
	  Employee emp1 = new Employee();
	  emp1.setId("1");
	  emp1.setName("Name 1");
	  employeeRepo.put(emp1.getId(), emp1);
      
      Employee emp2 = new Employee();
      emp2.setId("2");
      emp2.setName("Name 2");
      employeeRepo.put(emp2.getId(), emp2);
   }
   
   @RequestMapping(value = "/employees")
   public ResponseEntity<Object> get() {
      return new ResponseEntity<>(employeeRepo.values(), HttpStatus.OK);
   }
   
   @RequestMapping(value = "/employees", method = RequestMethod.POST)
   public ResponseEntity<Object> create(@RequestBody Employee employee) {
	   employeeRepo.put(employee.getId(), employee);
      return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
   }
  
   @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Employee employee) { 
	   employeeRepo.remove(id);
	   employee.setId(id);
      employeeRepo.put(id, employee);
      return new ResponseEntity<>("Employee details updated successfully", HttpStatus.OK);
   }
   
   
   
   @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
	   employeeRepo.remove(id);
      return new ResponseEntity<>("Employee details deleted successsfully", HttpStatus.OK);
   }
   
  
   
   
   
}