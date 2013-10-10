package com.atanor.smanager.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hardware")
@NamedQuery(name = "Hardware.getAll", query = "SELECT h from Hardware h")
public class Hardware extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "model_name", length = 32)
	private String modelName;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hardware")
	private List<Preset> presets;

	@ElementCollection
	private List<String> sources;
	
	@Column(name = "active")
	private Boolean active;
	
	private Display display;

	public Hardware() {
	}

	public Hardware(final String modelName) {
		this.modelName = modelName;
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

	public List<Preset> getPresets() {
		return presets;
	}

	public void setPresets(final List<Preset> presets) {
		this.presets = presets;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(final List<String> sources) {
		this.sources = sources;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(final Display display) {
		this.display = display;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}
	
}
