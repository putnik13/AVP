package com.atanor.smanager.rpc.dto;

@SuppressWarnings("serial")
public class PanelLayoutDto extends AbstractDto {

	private String name;
	private Integer panelQuantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPanelQuantity() {
		return panelQuantity;
	}

	public void setPanelQuantity(Integer panelQuantity) {
		this.panelQuantity = panelQuantity;
	}

}
