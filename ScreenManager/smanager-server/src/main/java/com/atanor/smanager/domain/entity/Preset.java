package com.atanor.smanager.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "preset")
@NamedQuery(name = "Preset.getAll", query = "SELECT p FROM Preset p")
public class Preset extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "display_layout")
	@Enumerated(EnumType.STRING)
	private DisplayLayout layout;

	public Preset() {
	}

	public Preset(final DisplayLayout layout) {
		this.layout = layout;
	}

	@Override
	public Long getId() {
		return id;
	}

	public DisplayLayout getLayout() {
		return layout;
	}

	public void setLayout(DisplayLayout layout) {
		this.layout = layout;
	}

}
