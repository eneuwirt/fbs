package com.fbs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

   @Override
   protected Object determineCurrentLookupKey() {
      Object result = TenantContextHolder.getTenant();
      
      return result;
   }
}
