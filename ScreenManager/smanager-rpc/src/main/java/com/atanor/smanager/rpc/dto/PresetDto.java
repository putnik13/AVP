package com.atanor.smanager.rpc.dto;

import java.util.List;

@SuppressWarnings("serial")
public class PresetDto extends AbstractDto {

	private Long id;
	private LayoutDto layout;
	private List<ActiveScreenDto> activeScreens;

	public PresetDto(final Long id) {
		this.id = id;
	}

	public LayoutDto getLayout() {
		return layout;
	}

	public void setLayout(LayoutDto layout) {
		this.layout = layout;
	}

	public List<ActiveScreenDto> getActiveScreens() {
		return activeScreens;
	}

	public void setActiveScreens(List<ActiveScreenDto> activeScreens) {
		this.activeScreens = activeScreens;
	}

	public Long getId() {
		return id;
	}

}
