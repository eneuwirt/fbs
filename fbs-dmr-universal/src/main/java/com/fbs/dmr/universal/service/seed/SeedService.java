package com.fbs.dmr.universal.service.seed;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface SeedService
{
	public void defaultFill();
	
	public void demoFill();
}
