package com.atanor.smanager.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "presets")
@NamedQuery(name = "Preset.getAll", query = "SELECT p FROM Preset p")
public class Preset extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "panel_layout", length = 32)
	@Enumerated(EnumType.STRING)
	private PanelLayout layout;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "harware_id")
	private Hardware hardware;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "preset")
	private List<Window> windows;

	public Preset() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public Preset(final PanelLayout layout) {
		this.layout = layout;
	}

	public PanelLayout getLayout() {
		return layout;
	}

	public void setLayout(final PanelLayout layout) {
		this.layout = layout;
	}

	public List<Window> getWindows() {
		return windows;
	}

	public void setWindows(final List<Window> windows) {
		this.windows = windows;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(final Hardware hardware) {
		this.hardware = hardware;
	}

}
