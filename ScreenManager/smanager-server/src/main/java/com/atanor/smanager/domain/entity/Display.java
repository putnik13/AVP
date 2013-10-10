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
@Table(name = "display")
@NamedQuery(name = "Display.getAll", query = "SELECT d from Display d")
public class Display extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "width")
	private Integer width;

	@Column(name = "high")
	private Integer high;

	@Column(name = "panel_layout", length = 32)
	@Enumerated(EnumType.STRING)
	private PanelLayout layout;

	public Display(final PanelLayout layout, final Integer width, final Integer high) {
		this.layout = layout;
		this.width = width;
		this.high = high;
	}

	@Override
	public Long getId() {
		return id;
	}

	public PanelLayout getLayout() {
		return layout;
	}

	public void setLayout(final PanelLayout layout) {
		this.layout = layout;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(final Integer width) {
		this.width = width;
	}

	public Integer getHigh() {
		return high;
	}

	public void setHigh(final Integer high) {
		this.high = high;
	}

}
