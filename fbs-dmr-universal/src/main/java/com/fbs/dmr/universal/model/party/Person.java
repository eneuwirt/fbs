package com.fbs.dmr.universal.model.party;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PERSONS")
public class Person extends Party
{
	private static final long serialVersionUID = 1L;
	@Column(name = "first_name")
	private String firstName = "";
	@Column(name = "middle_name")
	private String middleName = "";
	@Column(name = "last_name")
	private String lastName = "";
	private String title = "";
	private String suffix = "";
	@Column(name = "nick_name")
	private String nickName = "";
	private String gender = "";
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate = new Date();
	@Column(name = "mothers_maiden_name")
	private String mothersMaidenname = "";
	@Column(name = "marital_status")
	private String maritalStaus = "";
	private String comment = "";

	public Person()
	{

	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getLastName()
	{
		return lastName;
	}

	@Override
	public String getName()
	{
		return this.firstName + " " + this.lastName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSuffix()
	{
		return suffix;
	}

	public void setSuffix(String suffix)
	{
		this.suffix = suffix;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getMothersMaidenname()
	{
		return mothersMaidenname;
	}

	public void setMothersMaidenname(String mothersMaidenname)
	{
		this.mothersMaidenname = mothersMaidenname;
	}

	public String getMaritalStaus()
	{
		return maritalStaus;
	}

	public void setMaritalStaus(String maritalStaus)
	{
		this.maritalStaus = maritalStaus;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof Person))
		{
			return false;
		}

		Person other = (Person) obj;

		if ((this.getId() == null) ? (other.getId() != null) : !this.getId().equals(other.getId()))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result;

		result = super.hashCode();

		return result;
	}

	public String toString()
	{
		return String.format("PersonCoreValues [id=%s, name=%s]", this.getId(), this.firstName + " " + this.lastName);
	}
}
