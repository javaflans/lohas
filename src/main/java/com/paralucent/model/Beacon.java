package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the beacon database table.
 * 
 */
@Entity
@Table(name="beacon")
@NamedQuery(name="Beacon.findAll", query="SELECT b FROM Beacon b")
public class Beacon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String beaconID;

	private boolean canbeEvent;

	private double endX;

	private double endY;

	private String locatLV0;

	private String locatLV1;

	private String locatLV2;

	private double posX;

	private double posY;

	private double startX;

	private double startY;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	public Beacon() {
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