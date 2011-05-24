package com.fbs.dmr.universal.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface SeedService
{
	public void defaultFill();
}
