package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tags_copy database table.
 * 
 */
@Entity
@Table(name="tags_copy")
@NamedQuery(name="TagsCopy.findAll", query="SELECT t FROM TagsCopy t")
public class TagsCopy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String tagID;

	private String tagCode;

	private String tagCompany;

	private String tagDesc;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	public TagsCopy() {
	}

	public String getTagID() {
		return this.tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
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