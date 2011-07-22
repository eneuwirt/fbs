package com.fbs.dmr.universal.service.demo;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface DemoDataService
{
    public void demoFill();
}
