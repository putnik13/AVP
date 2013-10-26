package com.atanor.smanager.client.mvp.views.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.atanor.smanager.client.Client;
import com.atanor.smanager.client.mvp.events.ActivateGridEvent;
import com.atanor.smanager.client.mvp.events.ActivateGridHandler;
import com.atanor.smanager.client.mvp.events.CleanGridActivationEvent;
import com.atanor.smanager.client.mvp.events.CleanWindowSelectionEvent;
import com.atanor.smanager.client.mvp.places.NoPresetSelectedPlace;
import com.atanor.smanager.client.mvp.presenters.EditPresetPresenter;
import com.atanor.smanager.client.mvp.views.EditPresetView;
import com.atanor.smanager.client.ui.GridLabel;
import com.atanor.smanager.client.ui.PresetLabel;
import com.atanor.smanager.client.ui.WindowLabel;
import com.atanor.smanager.client.ui.builder.UiBuilder;
import com.atanor.smanager.client.ui.builder.post.EditPresetPostBuilder;
import com.atanor.smanager.client.ui.builder.post.EditWindowPostBuilder;
import com.atanor.smanager.client.ui.style.PanelsDisplayStyleApplier;
import com.atanor.smanager.rpc.dto.DisplayDto;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PanelLayoutDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DragRepositionMoveEvent;
import com.smartgwt.client.widgets.events.DragRepositionMoveHandler;
import com.smartgwt.client.widgets.events.DragRepositionStopEvent;
import com.smartgwt.client.widgets.events.DragRepositionStopHandler;
import com.smartgwt.client.widgets.events.DragResizeMoveEvent;
import com.smartgwt.client.widgets.events.DragResizeMoveHandler;
import com.smartgwt.client.widgets.events.DragResizeStopEvent;
import com.smartgwt.client.widgets.events.DragResizeStopHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditPresetViewImpl extends VLayout implements EditPresetView, ActivateGridHandler {

	private static final int HEADER_SIZE = 60;

	private EditPresetPresenter presenter;

	private final Label panelDisplay;
	private final Label gridDisplay;
	private final Label editorDisplay;

	private final Map<Long, PresetLabel> layouts = Maps.newHashMap();
	private final Map<Integer, List<GridLabel>> rowGridPanels = Maps.newLinkedHashMap();
	private final Map<Integer, List<GridLabel>> columnGridPanels = Maps.newLinkedHashMap();
	private final LinkedHashMap<String, String> sourcesMap = Maps.newLinkedHashMap();
	private final LinkedHashMap<Integer, String> zIndexesMap = Maps.newLinkedHashMap();

	private final SelectItem selectDepth;
	private final SelectItem selectSource;
	private final IButton applyButton;
	private final IButton saveButton;
	private final IButton cancelButton;

	private Double scaleFactor;

	private PresetLabel currentPreset;

	public EditPresetViewImpl() {
		setHeight100();
		setWidth("85%");
		setOverflow(Overflow.HIDDEN);
		setShowResizeBar(true);

		panelDisplay = new Label();
		panelDisplay.setTop(40);

		gridDisplay = new Label();
		gridDisplay.setWidth100();
		gridDisplay.setHeight100();
		
		editorDisplay = new Label();
		editorDisplay.setWidth100();
		editorDisplay.setHeight100();

		applyButton = new IButton("Apply Source");
		applyButton.setWidth(90);
		applyButton.setDisabled(true);

		applyButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (currentPreset != null) {
					updateWindowDtos(currentPreset);
					presenter.applyPreset(currentPreset.getDto());
				}
			}
		});

		saveButton = new IButton("Save Layout");
		saveButton.setWidth(90);
		saveButton.setDisabled(true);

		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (currentPreset != null) {
					updateWindowDtos(currentPreset);
					presenter.savePreset(currentPreset.getDto());
				}
			}
		});

		cancelButton = new IButton("Cancel Layout");
		cancelButton.setWidth(90);
		cancelButton.setDisabled(true);

		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Client.goTo(new NoPresetSelectedPlace());
			}
		});

		final DynamicForm sources = new DynamicForm();
		sources.setAlign(Alignment.RIGHT);
		selectSource = new SelectItem();
		selectSource.setTitle("Source");
		selectSource.setClipTitle(true);
		selectSource.setAlign(Alignment.RIGHT);
		selectSource.setDisabled(true);
		sources.setFields(selectSource);
		sources.setWidth(200);
		sources.setMinWidth(200);

		selectSource.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				final String newValue = (String) event.getValue();
				final WindowLabel window = getSelectedWindow();
				if (window != null && !newValue.equals(window.getContents())) {
					window.setContents(newValue);
					window.getDto().setSource(newValue);
					onWindowChanged(window);
				}
			}
		});

		final DynamicForm depth = new DynamicForm();
		depth.setAlign(Alignment.RIGHT);
		selectDepth = new SelectItem();
		selectDepth.setTitle("Depth");
		selectDepth.setClipTitle(true);
		selectDepth.setAlign(Alignment.RIGHT);
		selectDepth.setDisabled(true);
		depth.setFields(selectDepth);
		depth.setWidth(200);
		depth.setMinWidth(200);

		zIndexesMap.put(0, "Top");
		zIndexesMap.put(1, "Top-1");
		zIndexesMap.put(2, "Top-2");
		zIndexesMap.put(3, "Top-3");
		zIndexesMap.put(4, "Top-4");
		zIndexesMap.put(5, "Top-5");
		zIndexesMap.put(6, "Top-6");
		zIndexesMap.put(7, "Top-7");
		zIndexesMap.put(8, "Top-8");
		zIndexesMap.put(9, "Top-9");
		zIndexesMap.put(10, "Top-10");
		selectDepth.setValueMap(zIndexesMap);

		selectDepth.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				final Integer newValue = Integer.parseInt((String) event.getValue());
				final WindowLabel window = getSelectedWindow();
				if (window != null && newValue != window.getZIndex()) {
					window.setZIndex(newValue);
					window.getDto().setZIndex(newValue);
					onWindowChanged(window);
				}
			}
		});

		HLayout hButtonLayout = new HLayout(15);
		hButtonLayout.setAlign(Alignment.CENTER);
		hButtonLayout.addMember(depth);
		hButtonLayout.addMember(sources);
		hButtonLayout.addMember(applyButton);
		hButtonLayout.addMember(saveButton);
		hButtonLayout.addMember(cancelButton);
		hButtonLayout.setBackgroundColor("lightgrey");
		hButtonLayout.setHeight(10);

		VLayout vButtonLayout = new VLayout();
		vButtonLayout.setAlign(VerticalAlignment.CENTER);
		vButtonLayout.setHeight(HEADER_SIZE);
		vButtonLayout.setBackgroundColor("lightgrey");
		vButtonLayout.setShowEdges(true);
		vButtonLayout.addMember(hButtonLayout);

		VLayout vDesktopLayout = new VLayout();
		vDesktopLayout.addChild(panelDisplay);
		vDesktopLayout.addChild(gridDisplay);
		vDesktopLayout.addChild(editorDisplay);

		addMember(vButtonLayout);
		addMember(vDesktopLayout);
	}

	private void updateWindowDtos(final PresetLabel preset) {
		for (Canvas child : preset.getChildren()) {
			if (child instanceof WindowLabel) {
				WindowLabel window = (WindowLabel) child;
				window.updateDto();
			}
		}
	}

	@Override
	public void setConfiguration(HardwareDto config) {
		setSources(config.getSources());
		setDisplay(config.getDisplay());
		createPresetLayouts(config.getPresets());
	}

	private void setSources(List<String> sources) {
		sourcesMap.clear();
		for (String source : sources) {
			sourcesMap.put(source, source);
		}
		selectSource.setValueMap(sourcesMap);
	}

	private void setDisplay(DisplayDto display) {

		Long displayWidth = new Long(display.getWidth());
		Long displayHeight = new Long(display.getHigh());
		System.out.println("HARDWARE DISPLAY -> " + "width: " + displayWidth + ", height: " + displayHeight);
		Long hwPanelWidth = Math.round(displayWidth.doubleValue() / display.getLayout().getColumnPanelQuantity());
		Long hwPanelHeight = Math.round(displayHeight.doubleValue() / display.getLayout().getRowPanelQuantity());
		System.out.println("HARDWARE PANEL -> " + "width: " + hwPanelWidth + ", height: " + hwPanelHeight);

		Double padding = adjustPadding(displayWidth, displayHeight);

		Long panelDisplayWidth = Math.round(getElement().getClientWidth() * padding);
		panelDisplayWidth = makeDivisibleOn(panelDisplayWidth, display.getLayout().getColumnPanelQuantity());

		scaleFactor = panelDisplayWidth.doubleValue() / displayWidth.doubleValue();
		Long panelDisplayHeight = Math.round(scaleFactor * displayHeight.doubleValue());
		System.out.println("Panel Display -> " + "width: " + panelDisplayWidth + ", height: " + panelDisplayHeight);

		createDisplayWindow(display.getLayout(), panelDisplayWidth, panelDisplayHeight, scaleFactor);
		createGridWindow(display.getLayout(), panelDisplayWidth, panelDisplayHeight, scaleFactor);
	}

	private Double adjustPadding(Long displayWidth, Long displayHeight) {
		Double padding = 0.7d;

		while (Boolean.TRUE) {
			final Long panelDisplayWidth = Math.round(getElement().getClientWidth() * padding);
			final Double scaleFactor = panelDisplayWidth.doubleValue() / displayWidth.doubleValue();
			final Long panelDisplayHeight = Math.round(scaleFactor * displayHeight.doubleValue());

			if (panelDisplayWidth + 80 <= getElement().getClientWidth()
					&& panelDisplayHeight + 80 + HEADER_SIZE <= getElement().getClientHeight()) {
				return padding;
			}
			padding -= 0.01d;
		}

		return padding;
	}

	private void createDisplayWindow(PanelLayoutDto layout, Long panelDisplayWidth, Long panelDisplayHeight,
			Double scaleFactor) {

		panelDisplay.setWidth(Ints.checkedCast(panelDisplayWidth));
		panelDisplay.setHeight(Ints.checkedCast(panelDisplayHeight));
		alignInDesktop(panelDisplay);

		Long panelWidth = Math.round(panelDisplayWidth.doubleValue() / layout.getColumnPanelQuantity());
		Long panelHeight = Math.round(panelDisplayHeight.doubleValue() / layout.getRowPanelQuantity());
		System.out.println("Panel -> " + "width: " + panelWidth + ", height: " + panelWidth);

		createDisplayPanels(layout, panelWidth, panelHeight);
	}

	private void alignInDesktop(Label panel) {
		Long leftOffset = Math.round((getElement().getClientWidth() - panel.getWidth()) / 2d);
		leftOffset = makeDivisibleOn(leftOffset, 2);
		panel.setLeft(Ints.checkedCast(leftOffset));
	}

	private void createDisplayPanels(PanelLayoutDto layout, Long panelWidth, Long panelHeight) {
		for (int row = 0, top = 0, left = 0; row < layout.getRowPanelQuantity(); row++) {
			for (int col = 0; col < layout.getColumnPanelQuantity(); col++) {
				createDisplayPanel(top, left, panelWidth, panelHeight);
				left += panelWidth;
			}
			left = 0;
			top += panelHeight;
		}
	}

	private void createDisplayPanel(int top, int left, Long panelWidth, Long panelHeight) {
		final Label panel = new Label();

		panel.setTop(top);
		panel.setLeft(left);
		panel.setWidth(Ints.checkedCast(panelWidth));
		panel.setHeight(Ints.checkedCast(panelHeight));

		new PanelsDisplayStyleApplier().applyStyle(panel);

		panelDisplay.addChild(panel);
	}

	private void createGridWindow(PanelLayoutDto layout, Long panelDisplayWidth, Long panelDisplayHeight,
			Double scaleFactor) {

		gridDisplay.setWidth(Ints.checkedCast(panelDisplayWidth));
		gridDisplay.setHeight(Ints.checkedCast(panelDisplayHeight));
		// align as panel display
		//gridDisplay.setLeft(panelDisplay.getLeft());
		
		Long panelWidth = Math.round(panelDisplayWidth.doubleValue() / layout.getColumnPanelQuantity());
		Long panelHeight = Math.round(panelDisplayHeight.doubleValue() / layout.getRowPanelQuantity());

		createGridPanels(layout, panelWidth, panelHeight);
		Client.getEventBus().addHandler(ActivateGridEvent.getType(), this);
	}

	private void createGridPanels(PanelLayoutDto layout, Long panelWidth, Long panelHeight) {
		for (int row = 0, top = 0, left = 0; row < layout.getRowPanelQuantity(); row++) {
			for (int col = 0; col < layout.getColumnPanelQuantity(); col++) {
				createGridPanel(row, col, top, left, panelWidth, panelHeight);
				left += panelWidth;
			}
			left = 0;
			top += panelHeight;
		}
	}

	private void createGridPanel(int row, int col, int top, int left, Long panelWidth, Long panelHeight) {
		final GridLabel panel = new GridLabel(row, col);
		addRowGridPanel(row, panel);
		addColumnGridPanel(col, panel);
		
		final Integer leftOffset = panelDisplay.getLeft();
		final Integer topOffset = panelDisplay.getTop();
		
		panel.setTop(top + topOffset.intValue() );
		panel.setLeft(left + leftOffset.intValue());
		panel.setWidth(Ints.checkedCast(panelWidth));
		panel.setHeight(Ints.checkedCast(panelHeight));
		panel.init();
		panel.setBorder("1px inset black");

		Client.getEventBus().addHandler(ActivateGridEvent.getType(), panel);
		Client.getEventBus().addHandler(CleanGridActivationEvent.getType(), panel);

		gridDisplay.addChild(panel);
	}

	private void addRowGridPanel(final Integer row, final GridLabel panel) {
		if (rowGridPanels.get(row) == null) {
			rowGridPanels.put(row, new ArrayList<GridLabel>());
		}
		rowGridPanels.get(row).add(panel);
	}

	private void addColumnGridPanel(final Integer col, final GridLabel panel) {
		if (columnGridPanels.get(col) == null) {
			columnGridPanels.put(col, new ArrayList<GridLabel>());
		}
		columnGridPanels.get(col).add(panel);
	}

	private void createPresetLayouts(List<PresetDto> presets) {
		Long panelDisplayWidth = new Long(panelDisplay.getWidth());
		Long panelDisplayHeight = new Long(panelDisplay.getHeight());
		Long leftOffset = new Long(panelDisplay.getLeft());
		Long topOffset = new Long(panelDisplay.getTop());

		layouts.clear();
		for (PresetDto preset : presets) {
			createPresetLayout(preset, panelDisplayWidth, panelDisplayHeight, leftOffset, topOffset);
		}
	}

	private void createPresetLayout(PresetDto preset, Long presetWidth, Long presetHeight, Long leftOffset,
			Long topOffset) {

		PresetLabel lab = UiBuilder.buildPresetLayout(preset, presetWidth, presetHeight, scaleFactor,
				new EditPresetPostBuilder(), new EditWindowPostBuilder(leftOffset, topOffset));
		layouts.put(lab.getId(), lab);
	}

	@Override
	public void setPreset(final Long presetId) {
		cleanState();
		if (layouts.containsKey(presetId)) {
			cancelButton.enable();

			currentPreset = layouts.get(presetId).clone();
			addWindowsHandlers(currentPreset);

			editorDisplay.addChild(currentPreset);
		}
	}

	private void addWindowsHandlers(final PresetLabel layout) {
		ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				if (!window.isSelected()) {
					Client.getEventBus().fireEvent(new CleanWindowSelectionEvent());
					selectWindow(window);
					selectSource.enable();
					selectSource.setValue(window.getDto().getSource());
					selectDepth.enable();
					selectDepth.setValue(window.getDto().getZIndex());
				}
			}

		};

		DragRepositionStopHandler dragRepositionStopHandler = new DragRepositionStopHandler() {

			@Override
			public void onDragRepositionStop(DragRepositionStopEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				onWindowChanged(window);
				resizeToAciveGrid(window);
				Client.getEventBus().fireEvent(new CleanGridActivationEvent());
			}
		};

		DragResizeStopHandler dragResizeStopHandler = new DragResizeStopHandler() {

			@Override
			public void onDragResizeStop(DragResizeStopEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				onWindowChanged(window);
				resizeToAciveGrid(window);
				Client.getEventBus().fireEvent(new CleanGridActivationEvent());
			}
		};

		DragRepositionMoveHandler dragRepositionMoveHandler = new DragRepositionMoveHandler() {

			@Override
			public void onDragRepositionMove(DragRepositionMoveEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				activateGridPanel(window.getLeft(), window.getTop(), window.getWidth(), window.getHeight(), window);
			}
		};

		DragResizeMoveHandler dragResizeMoveHandler = new DragResizeMoveHandler() {

			@Override
			public void onDragResizeMove(DragResizeMoveEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				activateGridPanel(window.getLeft(), window.getTop(), window.getWidth(), window.getHeight(), window);
			}
		};

		for (Canvas child : layout.getChildren()) {
			if (child instanceof WindowLabel) {
				child.addClickHandler(clickHandler);
				child.addDragRepositionStopHandler(dragRepositionStopHandler);
				child.addDragResizeStopHandler(dragResizeStopHandler);
				child.addDragRepositionMoveHandler(dragRepositionMoveHandler);
				child.addDragResizeMoveHandler(dragResizeMoveHandler);
			}
		}
	}

	private void activateGridPanel(Integer left, Integer top, Integer width, Integer height, WindowLabel w) {
		Client.getEventBus().fireEvent(new ActivateGridEvent(left, top, width, height, w));
	}

	private void onWindowChanged(WindowLabel window) {
		window.setDirty(true);
		applyButton.enable();
		saveButton.enable();
	}

	private void selectWindow(WindowLabel window) {
		window.bringToFront();
		window.setSelected(true);
		window.setOpacity(100);
		window.setCanDragResize(true);
		window.setCanDragReposition(true);
	}

	private void cleanPresetLayouts() {
		if (currentPreset != null) {
			currentPreset.destroy();
			currentPreset = null;
		}
	}

	private void cleanHeaderWidgets() {
		selectDepth.setValue("");
		selectSource.setValue("");
		selectDepth.disable();
		selectSource.disable();
		applyButton.disable();
		saveButton.disable();
		cancelButton.disable();
	}

	private WindowLabel getSelectedWindow() {
		for (Canvas preset : editorDisplay.getChildren()) {
			if (preset instanceof PresetLabel) {
				for (Canvas child : preset.getChildren()) {
					if (child instanceof WindowLabel) {
						WindowLabel window = (WindowLabel) child;
						if (window.isSelected()) {
							return window;
						}
					}
				}
			}
		}

		return null;
	}

	@Override
	public void cleanState() {
		cleanPresetLayouts();
		cleanHeaderWidgets();
	}

	@Override
	public void setPresenter(EditPresetPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setPresetConfiguration(final PresetDto preset) {
		Long panelDisplayWidth = new Long(panelDisplay.getWidth());
		Long panelDisplayHeight = new Long(panelDisplay.getHeight());
		Long leftOffset = new Long(panelDisplay.getLeft());
		Long topOffset = new Long(panelDisplay.getTop());

		createPresetLayout(preset, panelDisplayWidth, panelDisplayHeight, leftOffset, topOffset);
	}

	@Override
	public void onPresetApplied() {
		applyButton.disable();
	}

	@Override
	public void onActivateGrid(ActivateGridEvent event) {
		// fill gaps between activated grid panels

		// check rows
		for (Map.Entry<Integer, List<GridLabel>> entry : rowGridPanels.entrySet()) {
			activateGapPanels(entry.getValue());
		}

		// check columns
		for (Map.Entry<Integer, List<GridLabel>> entry : columnGridPanels.entrySet()) {
			activateGapPanels(entry.getValue());
		}
	}

	private void activateGapPanels(List<GridLabel> panels) {
		if (hasAtLeastXActivePanels(panels, 2)) {
			boolean activate = false;
			for (GridLabel panel : panels) {
				if (panel.isActive()) {
					activate = !activate;
				}

				if (activate) {
					panel.activate();
				}
			}
		}
	}

	private boolean hasAtLeastXActivePanels(List<GridLabel> panels, Integer x) {
		int activeCount = 0;
		for (GridLabel panel : panels) {
			if (panel.isActive()) {
				activeCount++;
			}
		}
		return activeCount >= x;
	}

	private void resizeToAciveGrid(WindowLabel window) {
		// get active area first
		final GridLabel startActive = getFirstActivePanelInFirstActiveRow();
		final GridLabel endActive = getLastActivePanelInLastActiveColumn();

		final Integer newLeft = startActive.getLeft();
		final Integer newTop = startActive.getTop();
		final Integer bottomRightLeft = endActive.getLeft() + endActive.getWidth();
		final Integer bottomRightTop = endActive.getTop() + endActive.getHeight();
		final Integer newWidth = bottomRightLeft - newLeft;
		final Integer newHeight = bottomRightTop - newTop;

		window.animateRect(newLeft, newTop, newWidth, newHeight);
	}

	private GridLabel getFirstActivePanelInFirstActiveRow() {
		Set<Integer> keys = rowGridPanels.keySet();
		for (Integer key : keys) {
			List<GridLabel> rowElements = rowGridPanels.get(key);
			if (hasAtLeastXActivePanels(rowElements, 1)) {
				for (GridLabel gridPanel : rowElements) {
					if (gridPanel.isActive()) {
						return gridPanel;
					}
				}
			}
		}

		throw new IllegalStateException("Error. No active row found!");
	}

	private GridLabel getLastActivePanelInLastActiveColumn() {
		Set<Integer> keys = columnGridPanels.keySet();
		List<Integer> reverseKeys = Lists.reverse(Lists.newArrayList(keys));
		for (Integer key : reverseKeys) {
			List<GridLabel> columnElements = columnGridPanels.get(key);
			List<GridLabel> reverseColumnElements = Lists.reverse(columnElements);
			if (hasAtLeastXActivePanels(reverseColumnElements, 1)) {
				for (GridLabel gridPanel : reverseColumnElements) {
					if (gridPanel.isActive()) {
						return gridPanel;
					}
				}
			}
		}

		throw new IllegalStateException("Error. No active column found!");
	}

	private Long makeDivisibleOn(Long value, Integer divisibleOn) {
		while (true) {
			if ((value.intValue() % divisibleOn) == 0) {
				return value;
			}
			value++;
		}
	}

}
