package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tracker_history database table.
 * 
 */
@Entity
@Table(name="tracker_history")
@NamedQuery(name="TrackerHistory.findAll", query="SELECT t FROM TrackerHistory t")
public class TrackerHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String beaconID;

	private String dataUuid;

	private double gpsLat;

	private double gpsLng;

	@Temporal(TemporalType.TIMESTAMP)
	private Date iotHubTime;

	private boolean isBeacon;

	private boolean isFreezed1;

	private boolean isFreezed2;

	private boolean isGPS;

	private boolean isLinked;

	private boolean isLost;

	private boolean isLowBattery;

	private boolean isSOS;

	private boolean isTriggerSOS;

	private String linkedName;

	private String readerID;

	private String repeaterID;

	private int rssi;

	@Temporal(TemporalType.TIMESTAMP)
	private Date sosClearTime;

	private String tagID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tagTime;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	public TrackerHistory() {
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

	public String getDataUuid() {
		return this.dataUuid;
	}

	public void setDataUuid(String dataUuid) {
		this.dataUuid = dataUuid;
	}

	public double getGpsLat() {
		return this.gpsLat;
	}

	public void setGpsLat(double gpsLat) {
		this.gpsLat = gpsLat;
	}

	public double getGpsLng() {
		return this.gpsLng;
	}

	public void setGpsLng(double gpsLng) {
		this.gpsLng = gpsLng;
	}

	public Date getIotHubTime() {
		return this.iotHubTime;
	}

	public void setIotHubTime(Date iotHubTime) {
		this.iotHubTime = iotHubTime;
	}

	public boolean getIsBeacon() {
		return this.isBeacon;
	}

	public void setIsBeacon(boolean isBeacon) {
		this.isBeacon = isBeacon;
	}

	public boolean getIsFreezed1() {
		return this.isFreezed1;
	}

	public void setIsFreezed1(boolean isFreezed1) {
		this.isFreezed1 = isFreezed1;
	}

	public boolean getIsFreezed2() {
		return this.isFreezed2;
	}

	public void setIsFreezed2(boolean isFreezed2) {
		this.isFreezed2 = isFreezed2;
	}

	public boolean getIsGPS() {
		return this.isGPS;
	}

	public void setIsGPS(boolean isGPS) {
		this.isGPS = isGPS;
	}

	public boolean getIsLinked() {
		return this.isLinked;
	}

	public void setIsLinked(boolean isLinked) {
		this.isLinked = isLinked;
	}

	public boolean getIsLost() {
		return this.isLost;
	}

	public void setIsLost(boolean isLost) {
		this.isLost = isLost;
	}

	public boolean getIsLowBattery() {
		return this.isLowBattery;
	}

	public void setIsLowBattery(boolean isLowBattery) {
		this.isLowBattery = isLowBattery;
	}

	public boolean getIsSOS() {
		return this.isSOS;
	}

	public void setIsSOS(boolean isSOS) {
		this.isSOS = isSOS;
	}

	public boolean getIsTriggerSOS() {
		return this.isTriggerSOS;
	}

	public void setIsTriggerSOS(boolean isTriggerSOS) {
		this.isTriggerSOS = isTriggerSOS;
	}

	public String getLinkedName() {
		return this.linkedName;
	}

	public void setLinkedName(String linkedName) {
		this.linkedName = linkedName;
	}

	public String getReaderID() {
		return this.readerID;
	}

	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}

	public String getRepeaterID() {
		return this.repeaterID;
	}

	public void setRepeaterID(String repeaterID) {
		this.repeaterID = repeaterID;
	}

	public int getRssi() {
		return this.rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public Date getSosClearTime() {
		return this.sosClearTime;
	}

	public void setSosClearTime(Date sosClearTime) {
		this.sosClearTime = sosClearTime;
	}

	public String getTagID() {
		return this.tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public Date getTagTime() {
		return this.tagTime;
	}

	public void setTagTime(Date tagTime) {
		this.tagTime = tagTime;
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