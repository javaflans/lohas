package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the security database table.
 * 
 */
@Entity
@Table(name="security")
@NamedQuery(name="Security.findAll", query="SELECT s FROM Security s")
public class Security implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean linked;

	private String permissionRole;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	//uni-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="memberID")
	private Member member;

	//uni-directional many-to-one association to Menus
	@ManyToOne
	@JoinColumn(name="menuID")
	private Menus menus;

	public Security() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getLinked() {
		return this.linked;
	}

	public void setLinked(boolean linked) {
		this.linked = linked;
	}

	public String getPermissionRole() {
		return this.permissionRole;
	}

	public void setPermissionRole(String permissionRole) {
		this.permissionRole = permissionRole;
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

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Menus getMenus() {
		return this.menus;
	}

	public void setMenus(Menus menus) {
		this.menus = menus;
	}

}