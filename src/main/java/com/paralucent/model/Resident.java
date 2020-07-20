package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the resident database table.
 * 
 */
@Entity
@Table(name="resident")
@NamedQuery(name="Resident.findAll", query="SELECT r FROM Resident r")
public class Resident implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date residentBirthday;

	private String residentLocalName;

	private String residentPhotoPath;

	private String residentSex;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	public Resident() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getResidentBirthday() {
		return this.residentBirthday;
	}

	public void setResidentBirthday(Date residentBirthday) {
		this.residentBirthday = residentBirthday;
	}

	public String getResidentLocalName() {
		return this.residentLocalName;
	}

	public void setResidentLocalName(String residentLocalName) {
		this.residentLocalName = residentLocalName;
	}

	public String getResidentPhotoPath() {
		return this.residentPhotoPath;
	}

	public void setResidentPhotoPath(String residentPhotoPath) {
		this.residentPhotoPath = residentPhotoPath;
	}

	public String getResidentSex() {
		return this.residentSex;
	}

	public void setResidentSex(String residentSex) {
		this.residentSex = residentSex;
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