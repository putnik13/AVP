package com.atanor.smanager.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "preset_windows")
@NamedQuery(name = "Window.GetAll", query = "SELECT w FROM Window w")
public class Window extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 32)
	private String name;

	@Column(name = "source", length = 32)
	private String source;

	@ManyToOne
	@JoinColumn(name = "preset_id", nullable = false)
	private Preset preset;

	@Column(name = "x_top_left")
	private Integer xTopLeft;

	@Column(name = "y_top_left")
	private Integer yTopLeft;

	@Column(name = "x_bottom_right")
	private Integer xBottomRight;

	@Column(name = "y_bottom_right")
	private Integer yBottomRight;

	@Column(name = "z_index")
	private Integer zIndex;

	@Transient
	private Boolean modified;
	
	public Window() {
	}

	public Window(final Long id){
		this.id = id;
	}
	
	public Window(final String name, final String source, final Integer xTopLeft, final Integer yTopLeft,
			final Integer xBottomRight, final Integer yBottomRight, final Integer zIndex) {
		this.name = name;
		this.source = source;
		this.xTopLeft = xTopLeft;
		this.yTopLeft = yTopLeft;
		this.xBottomRight = xBottomRight;
		this.yBottomRight = yBottomRight;
		this.zIndex = zIndex;
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

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public Integer getXTopLeft() {
		return xTopLeft;
	}

	public void setXTopLeft(final Integer xTopLeft) {
		this.xTopLeft = xTopLeft;
	}

	public Integer getYTopLeft() {
		return yTopLeft;
	}

	public void setYTopLeft(final Integer yTopLeft) {
		this.yTopLeft = yTopLeft;
	}

	public Integer getXBottomRight() {
		return xBottomRight;
	}

	public void setXBottomRight(final Integer xBottomRight) {
		this.xBottomRight = xBottomRight;
	}

	public Integer getYBottomRight() {
		return yBottomRight;
	}

	public void setYBottomRight(final Integer yBottomRight) {
		this.yBottomRight = yBottomRight;
	}

	public Integer getZIndex() {
		return zIndex;
	}

	public void setZIndex(final Integer zIndex) {
		this.zIndex = zIndex;
	}

	public Boolean isModified() {
		return modified;
	}

	public void setModified(final Boolean modified) {
		this.modified = modified;
	}

}
