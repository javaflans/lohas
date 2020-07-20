package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the resident_view database table.
 * 
 */
@Entity
@Table(name="resident_view")
@NamedQuery(name="ResidentView.findAll", query="SELECT r FROM ResidentView r")
public class ResidentView implements Serializable {
	private static final long serialVersionUID = 1L;

	private String contactAddress;

	private String contactArea;

	private String contactCity;

	private String contactLocalName;

	private String contactMail;

	private String contactMobile;

	private String contactPhone;

	private int contactZipCode;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date residentBirthday;

	private String residentLocalName;

	private String residentPhotoPath;

	private String residentSex;

	public ResidentView() {
	}

	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactArea() {
		return this.contactArea;
	}

	public void setContactArea(String contactArea) {
		this.contactArea = contactArea;
	}

	public String getContactCity() {
		return this.contactCity;
	}

	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactLocalName() {
		return this.contactLocalName;
	}

	public void setContactLocalName(String contactLocalName) {
		this.contactLocalName = contactLocalName;
	}

	public String getContactMail() {
		return this.contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getContactMobile() {
		return this.contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public int getContactZipCode() {
		return this.contactZipCode;
	}

	public void setContactZipCode(int contactZipCode) {
		this.contactZipCode = contactZipCode;
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

}