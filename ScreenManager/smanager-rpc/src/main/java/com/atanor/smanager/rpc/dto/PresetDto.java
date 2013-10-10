package com.atanor.smanager.rpc.dto;

import java.util.List;

@SuppressWarnings("serial")
public class PresetDto extends AbstractDto {

	private Long id;
	private PanelLayoutDto layout;
	private List<WindowDto> windows;

	public PresetDto(final Long id) {
		this.id = id;
	}

	public PanelLayoutDto getLayout() {
		return layout;
	}

	public void setLayout(final PanelLayoutDto layout) {
		this.layout = layout;
	}

	public List<WindowDto> getWindows() {
		return windows;
	}

	public void setWindows(final List<WindowDto> windows) {
		this.windows = windows;
	}

	public Long getId() {
		return id;
	}

}
