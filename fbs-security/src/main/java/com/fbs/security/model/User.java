package com.fbs.security.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Comparable<User>, Serializable
{
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String salt;
	private Integer tenantId;
	private Date validFrom = new Date();
	private Date validTo = new Date();
	
	
	@Override
    public int compareTo(User o)
    {
	   
	    return 0;
    }

}
