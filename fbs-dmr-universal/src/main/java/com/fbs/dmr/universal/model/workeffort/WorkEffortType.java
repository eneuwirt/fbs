package com.fbs.dmr.universal.model.workeffort;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.fbs.dmr.universal.model.type.Type;

@Entity
@Table(name = "WORK_EFFORT_TYPES")
public class WorkEffortType extends Type
{
    private static final long serialVersionUID = 1L;
	@Column(name="standard_work_minutes")
    private Integer standardWorkMinutes = 0;
	
	public WorkEffortType()
	{
		
	}

	public void setStandardWorkMinutes(Integer standardWorkMinutes)
    {
	    this.standardWorkMinutes = standardWorkMinutes;
    }

	public Integer getStandardWorkMinutes()
    {
	    return standardWorkMinutes;
    }
}
