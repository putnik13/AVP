package com.atanor.smanager.rpc.dto;

import java.util.List;

@SuppressWarnings("serial")
public class HardwareDto extends AbstractDto {

	private Long id;
	private String modelName;
	private List<String> sources;
	private List<PresetDto> presets;
	private DisplayDto display;

	public HardwareDto() {
	}

	public HardwareDto(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(final List<String> sources) {
		this.sources = sources;
	}

	public List<PresetDto> getPresets() {
		return presets;
	}

	public void setPresets(final List<PresetDto> presets) {
		this.presets = presets;
	}

	public DisplayDto getDisplay() {
		return display;
	}

	public void setDisplay(final DisplayDto display) {
		this.display = display;
	}

}
