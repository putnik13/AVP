package com.atanor.smanager.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "active_screens")
@NamedQuery(name = "ActiveScreen.getAll", query = "SELECT scr FROM ActiveScreen scr")
public class ActiveScreen extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "source", length = 32)
	private String source;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "preset_id", nullable = false)
	private Preset preset;

	@Column(name = "start_section_hz")
	private Integer startSectionHz;
	
	@Column(name = "width_sections")
	private Integer widthInSections;
	
	@Column(name = "start_section_vt")
	private Integer startSectionVt;
	
	@Column(name = "high_sections")
	private Integer highInSections;

	public ActiveScreen() {
	}

	public ActiveScreen(final Integer startSectionHz,
			final Integer widthInSections, final Integer startSectionVt,
			final Integer highInSections, final String source) {
		this.startSectionHz = startSectionHz;
		this.widthInSections = widthInSections;
		this.startSectionVt = startSectionVt;
		this.highInSections = highInSections;
		this.source = source;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Preset getPreset() {
		return preset;
	}

	public void setPreset(final Preset preset) {
		this.preset = preset;
	}

	public Integer getStartSectionHz() {
		return startSectionHz;
	}

	public void setStartSectionHz(final Integer startSectionHz) {
		this.startSectionHz = startSectionHz;
	}

	public Integer getWidthInSections() {
		return widthInSections;
	}

	public void setWidthInSections(final Integer widthInSections) {
		this.widthInSections = widthInSections;
	}

	public Integer getStartSectionVt() {
		return startSectionVt;
	}

	public void setStartSectionVt(final Integer startSectionVt) {
		this.startSectionVt = startSectionVt;
	}

	public Integer getHighInSections() {
		return highInSections;
	}

	public void setHighInSections(final Integer highInSections) {
		this.highInSections = highInSections;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

}
