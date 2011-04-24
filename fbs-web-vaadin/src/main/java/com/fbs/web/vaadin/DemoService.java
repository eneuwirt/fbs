package com.fbs.web.vaadin;

import org.apache.shiro.authz.annotation.RequiresRoles;

public interface DemoService {
	@RequiresRoles("user_role_1")
	public void doSomething();
}
