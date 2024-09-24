package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.entity.Supplier;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {


   public Supplier setSupplierRelatedWithEvent(){
       Supplier supplier = new Supplier();
       supplier.setAccountId("1");
       supplier.setFirstName("Jan");
       supplier.setLastName("Kowalski");
   return supplier;}


}
