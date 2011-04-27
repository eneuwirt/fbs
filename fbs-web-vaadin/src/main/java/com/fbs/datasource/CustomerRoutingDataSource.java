package com.fbs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CustomerRoutingDataSource extends AbstractRoutingDataSource {

   @Override
   protected Object determineCurrentLookupKey() {
      Object result = CustomerContextHolder.getCustomerType();
      
      return result;
   }
}
