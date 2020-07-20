package com.paralucent.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paralucent.utils.CustomDateTimeSerializer;


/**
 * The persistent class for the event_view database table.
 * 
 */
@Entity
@Table(name="event_view")
@NamedQuery(name="EventView.findAll", query="SELECT e FROM EventView e")
public class EventView implements Serializable {
	private static final long serialVersionUID = 1L;

	private String beaconID;

	private boolean canbeEvent;

	private double endX;

	private double endY;

	private String eventDesc;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	private Date eventEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	private Date eventStartDate;

	private String eventType;

	@Id
	private int id;

	private String locatLV0;

	private String locatLV1;

	private String locatLV2;

	private double posX;

	private double posY;

	private double startX;

	private double startY;

	private String userCreated;

	private String userDateCreated;

	private String userLastDateModify;

	private String userLastModify;

	public EventView() {
	}

	public String getBeaconID() {
		return this.beaconID;
	}

	public void setBeaconID(String beaconID) {
		this.beaconID = beaconID;
	}

	public boolean getCanbeEvent() {
		return this.canbeEvent;
	}

	public void setCanbeEvent(boolean canbeEvent) {
		this.canbeEvent = canbeEvent;
	}

	public double getEndX() {
		return this.endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return this.endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocatLV0() {
		return this.locatLV0;
	}

	public void setLocatLV0(String locatLV0) {
		this.locatLV0 = locatLV0;
	}

	public String getLocatLV1() {
		return this.locatLV1;
	}

	public void setLocatLV1(String locatLV1) {
		this.locatLV1 = locatLV1;
	}

	public String getLocatLV2() {
		return this.locatLV2;
	}

	public void setLocatLV2(String locatLV2) {
		this.locatLV2 = locatLV2;
	}

	public double getPosX() {
		return this.posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return this.posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getStartX() {
		return this.startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return this.startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
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

}