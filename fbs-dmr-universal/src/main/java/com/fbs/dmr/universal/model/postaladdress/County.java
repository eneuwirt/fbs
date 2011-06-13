package com.fbs.dmr.universal.model.postaladdress;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTIES")
public class County extends GeographicBoundary
{
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "state_id")
    @ManyToOne(optional=true)
    private State state;

    public County()
    {
    	
    }

	public void setState(State state)
    {
	    this.state = state;
    }

	public State getState()
    {
	    return state;
    }
}
