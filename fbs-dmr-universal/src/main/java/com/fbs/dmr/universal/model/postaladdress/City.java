package com.fbs.dmr.universal.model.postaladdress;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CITIES")
public class City extends GeographicBoundary
{
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public City()
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
