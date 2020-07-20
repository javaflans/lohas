package com.paralucent.viewmodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


public class HistoryBO implements Serializable {
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

	private double gpsLat;

	private double gpsLng;

	private int id;

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

	private String locatLV0;

	private String locatLV1;

	private String locatLV2;

	private double posX;

	private double posY;

	private String rangeEnd;

	private String rangeStart;

	private String readerID;

	private String repeaterID;

	private Date residentBirthday;

	private String residentPhotoPath;

	private String residentSex;

	private Date sosClearTime;

	private String tagCode;

	private String tagDesc;

	private String tagID;

	private Date tagTime;
	
	private Date startDate;
	
	private Date endDate;

	public HistoryBO() {
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

	public String getRangeEnd() {
		return this.rangeEnd;
	}

	public void setRangeEnd(String rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public String getRangeStart() {
		return this.rangeStart;
	}

	public void setRangeStart(String rangeStart) {
		this.rangeStart = rangeStart;
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

	public Date getResidentBirthday() {
		return this.residentBirthday;
	}

	public void setResidentBirthday(Date residentBirthday) {
		this.residentBirthday = residentBirthday;
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

	public Date getSosClearTime() {
		return this.sosClearTime;
	}

	public void setSosClearTime(Date sosClearTime) {
		this.sosClearTime = sosClearTime;
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

	public Date getTagTime() {
		return this.tagTime;
	}

	public void setTagTime(Date tagTime) {
		this.tagTime = tagTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}