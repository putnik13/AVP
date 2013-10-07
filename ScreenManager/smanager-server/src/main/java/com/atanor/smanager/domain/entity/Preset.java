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

	@Column(name = "display_layout", length = 32)
	@Enumerated(EnumType.STRING)
	private DisplayLayout layout;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "preset")
	private List<ActiveScreen> activeScreens;

	public Preset() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public Preset(final DisplayLayout layout) {
		this.layout = layout;
	}

	public DisplayLayout getLayout() {
		return layout;
	}

	public void setLayout(final DisplayLayout layout) {
		this.layout = layout;
	}

	public List<ActiveScreen> getActiveScreens() {
		return activeScreens;
	}

	public void setActiveScreens(final List<ActiveScreen> activeScreens) {
		this.activeScreens = activeScreens;
	}

}
