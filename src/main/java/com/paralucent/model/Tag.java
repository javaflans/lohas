package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String tagID;

	private Timestamp linkDate;

	private String linkResident;

	private String tagCode;

	private String tagCompany;

	private String tagDesc;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	public Tag() {
	}

	public String getTagID() {
		return this.tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public Timestamp getLinkDate() {
		return this.linkDate;
	}

	public void setLinkDate(Timestamp linkDate) {
		this.linkDate = linkDate;
	}

	public String getLinkResident() {
		return this.linkResident;
	}

	public void setLinkResident(String linkResident) {
		this.linkResident = linkResident;
	}

	public String getTagCode() {
		return this.tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public String getTagCompany() {
		return this.tagCompany;
	}

	public void setTagCompany(String tagCompany) {
		this.tagCompany = tagCompany;
	}

	public String getTagDesc() {
		return this.tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public Date getUserDateCreated() {
		return this.userDateCreated;
	}

	public void setUserDateCreated(Date userDateCreated) {
		this.userDateCreated = userDateCreated;
	}

	public Date getUserLastDateModify() {
		return this.userLastDateModify;
	}

	public void setUserLastDateModify(Date userLastDateModify) {
		this.userLastDateModify = userLastDateModify;
	}

	public String getUserLastModify() {
		return this.userLastModify;
	}

	public void setUserLastModify(String userLastModify) {
		this.userLastModify = userLastModify;
	}

}