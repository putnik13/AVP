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

	private boolean containsInInterval(Integer value, Integer startInterval, Integer endInterval) {
		return (value > startInterval) && (value < endInterval);
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
		boolean leftTopCorner = containsInInterval(event.getLeft(), leftStart, leftEnd)
				&& containsInInterval(event.getTop(), topStart, topEnd);
		boolean rightTopCorner = containsInInterval(event.getLeft() + event.getWidth(), leftStart, leftEnd)
				&& containsInInterval(event.getTop(), topStart, topEnd);
		boolean leftBottomCorner = containsInInterval(event.getLeft(), leftStart, leftEnd)
				&& containsInInterval(event.getTop() + event.getHeight(), topStart, topEnd);
		boolean rightBottomCorner = containsInInterval(event.getLeft() + event.getWidth(), leftStart, leftEnd)
				&& containsInInterval(event.getTop() + event.getHeight(), topStart, topEnd);

		boolean cornerOverlap = leftTopCorner || rightTopCorner || leftBottomCorner || rightBottomCorner;

		// ==========================================================
		boolean panelWidthBelongsWindow = containsInInterval(leftStart, event.getLeft(),
				event.getLeft() + event.getWidth())
				&& containsInInterval(leftEnd, event.getLeft(), event.getLeft() + event.getWidth());
		boolean bottomCornerThrough = containsInInterval(event.getTop() + event.getHeight(), topStart, topEnd)
				&& panelWidthBelongsWindow;
		boolean topCornerThrough = containsInInterval(event.getTop(), topStart, topEnd) && panelWidthBelongsWindow;

		boolean panelHeightBelongsWindow = containsInInterval(topStart, event.getTop(),
				event.getTop() + event.getHeight())
				&& containsInInterval(topEnd, event.getTop(), event.getTop() + event.getHeight());
		boolean rightCornerThrough = containsInInterval(event.getLeft() + event.getWidth(), leftStart, leftEnd)
				&& panelHeightBelongsWindow;
		boolean leftCornerThrough = containsInInterval(event.getLeft(), leftStart, leftEnd) && panelHeightBelongsWindow;

		boolean throughOverlap = bottomCornerThrough || topCornerThrough || rightCornerThrough || leftCornerThrough;

		// ==========================================================
		boolean fullOverlap = panelWidthBelongsWindow && panelHeightBelongsWindow;

		// ==========================================================
		boolean basicEqualsV = leftStart == event.getLeft() || leftEnd == event.getLeft() + event.getWidth();
		boolean windowCornerV = (containsInInterval(event.getTop(), topStart, topEnd) || containsInInterval(
				event.getTop() + event.getHeight(), topStart, topEnd))
				&& basicEqualsV;
		boolean panelsCornerV = (containsInInterval(topStart, event.getTop(), event.getTop() + event.getHeight()) || containsInInterval(
				topEnd, event.getTop(), event.getTop() + event.getHeight())) && basicEqualsV;

		boolean basicEqualsH = topStart == event.getTop() || topEnd == event.getTop() + event.getHeight();
		boolean windowCornerH = (containsInInterval(event.getLeft(), leftStart, leftEnd) || containsInInterval(
				event.getLeft() + event.getWidth(), leftStart, leftEnd))
				&& basicEqualsH;
		boolean panelsCornerH = (containsInInterval(leftStart, event.getLeft(), event.getLeft() + event.getWidth()) || containsInInterval(
				leftEnd, event.getLeft(), event.getLeft() + event.getWidth())) && basicEqualsH;
		
		boolean fix = (containsInInterval(topStart, event.getTop(), event.getTop() + event.getHeight()) || containsInInterval(
				topEnd, event.getTop(), event.getTop() + event.getHeight())) && (containsInInterval(leftStart, event.getLeft(), event.getLeft() + event.getWidth()) || containsInInterval(
						leftEnd, event.getLeft(), event.getLeft() + event.getWidth()));
		
		boolean equalsOverlap = windowCornerV || panelsCornerV || windowCornerH || panelsCornerH || fix;

		boolean result = cornerOverlap || throughOverlap || fullOverlap || equalsOverlap;

		setActive(result);
	}

	@Override
	public void onCleanGridActivation(CleanGridActivationEvent event) {
		deactivate();
	}

}
