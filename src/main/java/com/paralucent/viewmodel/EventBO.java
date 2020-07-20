package com.paralucent.viewmodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the event database table.
 * 
 */
public class EventBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String beaconID;
	
	private String locatLV0;

	private String locatLV1;
	
	private String locatLV2;

	private String eventDesc;
	
	private String eventStartDate;
	
	private String eventEndDate;

	private Date eventDate;

	private Date eventStartTime;

	private Date eventEndTime;

	private String eventType;

	private String userCreated;

	private String userDateCreated;

	private String userLastDateModify;

	private String userLastModify;

	public EventBO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeaconID() {
		return this.beaconID;
	}

	public void setBeaconID(String beaconID) {
		this.beaconID = beaconID;
	}

	public String getEventDesc() {
		return this.eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(Date eventStartTime) {
		this.eventStartTime = eventStartTime;
	}

	public Date getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(Date eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public String getUserDateCreated() {
		return this.userDateCreated;
	}

	public void setUserDateCreated(String userDateCreated) {
		this.userDateCreated = userDateCreated;
	}

	public String getUserLastDateModify() {
		return this.userLastDateModify;
	}

	public void setUserLastDateModify(String userLastDateModify) {
		this.userLastDateModify = userLastDateModify;
	}

	public String getUserLastModify() {
		return this.userLastModify;
	}

	public void setUserLastModify(String userLastModify) {
		this.userLastModify = userLastModify;
	}

	public String getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public String getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public String getLocatLV0() {
		return locatLV0;
	}

	public void setLocatLV0(String locatLV0) {
		this.locatLV0 = locatLV0;
	}

	public String getLocatLV1() {
		return locatLV1;
	}

	public void setLocatLV1(String locatLV1) {
		this.locatLV1 = locatLV1;
	}

	public String getLocatLV2() {
		return locatLV2;
	}

	public void setLocatLV2(String locatLV2) {
		this.locatLV2 = locatLV2;
	}
	
	

}