package com.paralucent.viewmodel;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paralucent.utils.CustomDateSerializer;
import com.paralucent.utils.CustomDateTimeSerializer;


/**
 * The persistent class for the history_view database table.
 * 
 */
public class HistoryViewBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String beaconID;

	private String contactAddress;

	private String contactArea;

	private String contactCity;

	private String contactLocalName;

	private String contactMail;

	private String contactMobile;

	private String contactPhone;

	private int contactZipCode;

	private double endX;

	private double endY;

	private double gpsLat;

	private double gpsLng;

	private int id;

	private String iotHubTime;

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

	private String locatLV0;

	private String locatLV1;

	private String locatLV2;

	private double posX;

	private double posY;

	private String readerID;

	private String repeaterID;

	private String residentBirthday;

	private String residentLocalName;

	private String residentPhotoPath;

	private String residentSex;

	private int rssi;

	private String sosClearTime;

	private double startX;

	private double startY;

	private String tagCode;

	private String tagDesc;

	private String tagID;

	private String tagTime;
	
	private String eventType;

	private String userCreated;

	private String userDateCreated;

	private String userLastDateModify;

	private String userLastModify;

	public HistoryViewBO() {
	}

	public String getBeaconID() {
		return this.beaconID;
	}

	public void setBeaconID(String beaconID) {
		this.beaconID = beaconID;
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIotHubTime() {
		return this.iotHubTime;
	}

	public void setIotHubTime(String iotHubTime) {
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

	public String getResidentBirthday() {
		return this.residentBirthday;
	}

	public void setResidentBirthday(String residentBirthday) {
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

	public int getRssi() {
		return this.rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public String getSosClearTime() {
		return this.sosClearTime;
	}

	public void setSosClearTime(String sosClearTime) {
		this.sosClearTime = sosClearTime;
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

	public String getTagCode() {
		return this.tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public String getTagDesc() {
		return this.tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	public String getTagID() {
		return this.tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public String getTagTime() {
		return this.tagTime;
	}

	public void setTagTime(String tagTime) {
		this.tagTime = tagTime;
	}
	
	public String getEventType() {
		return eventType;
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

}