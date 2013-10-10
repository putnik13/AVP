package com.atanor.smanager.rpc.dto;

@SuppressWarnings("serial")
public class DisplayDto extends AbstractDto {

	private Long id;

	private Integer width;
	private Integer high;
	private PanelLayoutDto layout;

	public DisplayDto(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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

	public PanelLayoutDto getLayout() {
		return layout;
	}

	public void setLayout(final PanelLayoutDto layout) {
		this.layout = layout;
	}

}
