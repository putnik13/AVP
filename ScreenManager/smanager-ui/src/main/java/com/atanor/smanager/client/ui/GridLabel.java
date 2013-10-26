package com.atanor.smanager.client.ui;

import com.atanor.smanager.client.mvp.events.ActivateGridEvent;
import com.atanor.smanager.client.mvp.events.ActivateGridHandler;
import com.atanor.smanager.client.mvp.events.CleanGridActivationEvent;
import com.atanor.smanager.client.mvp.events.CleanGridActivationHandler;
import com.smartgwt.client.widgets.Label;

public class GridLabel extends Label implements ActivateGridHandler, CleanGridActivationHandler {

	private Boolean active = Boolean.FALSE;

	private Integer row;
	private Integer column;

	private Integer leftStart;
	private Integer leftEnd;
	private Integer topStart;
	private Integer topEnd;

	public GridLabel(final Integer row, final Integer column) {
		this.row = row;
		this.column = column;
	}

	public Boolean isActive() {
		return active;
	}

	public Integer getRow() {
		return row;
	}

	public Integer getColumn() {
		return column;
	}

	public void init() {
		leftStart = getLeft();
		leftEnd = getLeft() + getWidth();
		topStart = getTop();
		topEnd = getTop() + getHeight();
	}

	private boolean containsInHorizontal(Integer value) {
		return (value >= leftStart) && (value <= leftEnd);
	}

	private boolean containsInVertical(Integer value) {
		return (value >= topStart) && (value <= topEnd);
	}

	private void setActive(boolean active) {
		if (!isActive() && active) {
			activate();
		}
		if (isActive() && !active) {
			deactivate();
		}
	}

	public void activate() {
		this.active = Boolean.TRUE;
		setBackgroundColor("red");
		markForRedraw();
	}

	public void deactivate() {
		this.active = Boolean.FALSE;
		setBackgroundColor("darkgrey");
		markForRedraw();
	}

	@Override
	public void onActivateGrid(ActivateGridEvent event) {
		boolean leftTopCorner = containsInHorizontal(event.getLeft()) && containsInVertical(event.getTop());
		boolean rightTopCorner = containsInHorizontal(event.getLeft() + event.getWidth())
				&& containsInVertical(event.getTop());
		boolean leftBottomCorner = containsInHorizontal(event.getLeft())
				&& containsInVertical(event.getTop() + event.getHeight());
		boolean rightBottomCorner = containsInHorizontal(event.getLeft() + event.getWidth())
				&& containsInVertical(event.getTop() + event.getHeight());
		boolean active = leftTopCorner || rightTopCorner || leftBottomCorner || rightBottomCorner;
		setActive(active);
		moveBelow(event.getW());
	}

	@Override
	public void onCleanGridActivation(CleanGridActivationEvent event) {
		deactivate();
	}

}
