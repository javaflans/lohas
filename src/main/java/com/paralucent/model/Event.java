package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@Table(name="event")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String beaconID;

	private String eventDesc;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eventEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eventStartDate;

	private String eventType;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	public Event() {
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

	public Date getEventEndDate() {
		return this.eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Date getEventStartDate() {
		return this.eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
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