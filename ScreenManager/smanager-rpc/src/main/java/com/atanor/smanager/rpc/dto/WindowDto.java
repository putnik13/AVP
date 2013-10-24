package com.atanor.smanager.rpc.dto;

@SuppressWarnings("serial")
public class WindowDto extends AbstractDto {

	private Long id;
	private String name;
	private String source;
	private Integer xTopLeft;
	private Integer yTopLeft;
	private Integer xBottomRight;
	private Integer yBottomRight;
	private Integer zIndex;

	public WindowDto() {
	}

	public WindowDto(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

}
