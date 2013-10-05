package com.atanor.smanager.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "display")
@NamedQuery(name = "Display.getAll", query = "SELECT c from Display c")
public class Display extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "physhigh", length = 20)
	private Integer physicalHigh;
	
	@Column(name = "physwidth", length = 20)
	private Integer physicalWidth;
	
	@Column(name = "reshigh", length = 20)
	private Integer resolutionHigh;
	
	@Column(name = "reswidth", length = 20)
	private Integer resolutionWidth;
	
	private String layout;

	public Display() {
	}

	public Display(final int physicalHigh, final int physicalWidth) {
		this.physicalHigh = physicalHigh;
		this.physicalWidth = physicalWidth;
	}

	@Override
	public Long getId() {
		return id;
	}

	public int getPhysicalHigh() {
		return physicalHigh;
	}

	public void setPhysicalHigh(final int physicalHigh) {
		this.physicalHigh = physicalHigh;
	}

	public int getPhysicalWidth() {
		return physicalWidth;
	}

	public void setPhysicalWidth(final int physicalWidth) {
		this.physicalWidth = physicalWidth;
	}

	public int getResolutionHigh() {
		return resolutionHigh;
	}

	public void setResolutionHigh(final int resolutionHigh) {
		this.resolutionHigh = resolutionHigh;
	}

	public int getResolutionWidth() {
		return resolutionWidth;
	}

	public void setResolutionWidth(final int resolutionWidth) {
		this.resolutionWidth = resolutionWidth;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(final String layout) {
		this.layout = layout;
	}

	@Override
	public String toString() {
		return "Display [physicalHigh=" + physicalHigh + ", physicalWidth="
				+ physicalWidth + ", resolutionHigh=" + resolutionHigh
				+ ", resolutionWidth=" + resolutionWidth + ", layout=" + layout
				+ "]";
	}

}
