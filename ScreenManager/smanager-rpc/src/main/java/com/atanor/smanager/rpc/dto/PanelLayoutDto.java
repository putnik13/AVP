package com.atanor.smanager.rpc.dto;

@SuppressWarnings("serial")
public class PanelLayoutDto extends AbstractDto {

	private String name;
	private Integer rowPanelQuantity;
	private Integer columnPanelQuantity;
	
	public PanelLayoutDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRowPanelQuantity() {
		return rowPanelQuantity;
	}

	public void setRowPanelQuantity(Integer rowPanelQuantity) {
		this.rowPanelQuantity = rowPanelQuantity;
	}

	public Integer getColumnPanelQuantity() {
		return columnPanelQuantity;
	}

	public void setColumnPanelQuantity(Integer columnPanelQuantity) {
		this.columnPanelQuantity = columnPanelQuantity;
	}

}
