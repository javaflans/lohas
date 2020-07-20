package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the miscode database table.
 * 
 */
@Embeddable
public class MiscodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String codekind;

	private String code;

	public MiscodePK() {
	}
	public String getCodekind() {
		return this.codekind;
	}
	public void setCodekind(String codekind) {
		this.codekind = codekind;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MiscodePK)) {
			return false;
		}
		MiscodePK castOther = (MiscodePK)other;
		return 
			this.codekind.equals(castOther.codekind)
			&& this.code.equals(castOther.code);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codekind.hashCode();
		hash = hash * prime + this.code.hashCode();
		
		return hash;
	}
}