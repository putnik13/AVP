package com.atanor.smanager.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hardware")
@NamedQuery(name = "Hardware.getAll", query = "SELECT c from Hardware c")
public class Hardware extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "modelname", length = 32)
	private String modelName;

	@Column(name = "manufacturename", length = 32)
	private String manufactureName;

	@OneToOne
    @JoinColumn(name = "display_id", referencedColumnName = "id")
	private Display display;

	public Hardware() {
	}

	public Hardware(final String modelName, final String manufactureName) {
		this.modelName = modelName;
		this.manufactureName = manufactureName;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(final String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(final Display display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return "Hardware [id=" + id + ", modelName=" + modelName
				+ ", manufactureName=" + manufactureName + ", display="
				+ display + "]";
	}
	
}
