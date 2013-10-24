package com.atanor.smanager.client.mvp.views.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.atanor.smanager.client.Client;
import com.atanor.smanager.client.mvp.places.NoPresetSelectedPlace;
import com.atanor.smanager.client.mvp.presenters.EditPresetPresenter;
import com.atanor.smanager.client.mvp.views.EditPresetView;
import com.atanor.smanager.client.ui.PresetLabel;
import com.atanor.smanager.client.ui.WindowLabel;
import com.atanor.smanager.client.ui.builder.UiBuilder;
import com.atanor.smanager.client.ui.builder.post.EditPresetPostBuilder;
import com.atanor.smanager.client.ui.builder.post.EditWindowPostBuilder;
import com.atanor.smanager.client.ui.style.EditWindowStyleApplier;
import com.atanor.smanager.rpc.dto.DisplayDto;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PanelLayoutDto;
import com.atanor.smanager.rpc.dto.PresetDto;
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
import com.smartgwt.client.widgets.events.DragRepositionStopEvent;
import com.smartgwt.client.widgets.events.DragRepositionStopHandler;
import com.smartgwt.client.widgets.events.DragResizeStopEvent;
import com.smartgwt.client.widgets.events.DragResizeStopHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditPresetViewImpl extends VLayout implements EditPresetView {

	private static final int HEADER_SIZE = 60;

	private EditPresetPresenter presenter;

	private final Label panelDisplay;
	private final HLayout panelDisplayLayout;
	private final Map<Long, PresetLabel> layouts = Maps.newHashMap();
	private final LinkedHashMap<String, String> sourcesMap = Maps.newLinkedHashMap();

	private final SelectItem selectInput;
	private final IButton applyButton;
	private final IButton saveButton;
	private final IButton cancelButton;

	private Double scaleFactor;

	public EditPresetViewImpl() {
		setHeight100();
		setWidth("85%");
		setOverflow(Overflow.HIDDEN);
		setShowResizeBar(true);
		setMembersMargin(40);

		panelDisplay = new Label();
		panelDisplay.setShowEdges(true);
		panelDisplay.setBorder("1px solid black");
		panelDisplay.setOverflow(Overflow.HIDDEN);

		panelDisplayLayout = new HLayout();
		panelDisplayLayout.setAlign(Alignment.CENTER);
		panelDisplayLayout.addMember(panelDisplay);

		applyButton = new IButton("Apply Source");
		applyButton.setWidth(90);
		applyButton.setDisabled(true);

		applyButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

			}
		});

		saveButton = new IButton("Save Layout");
		saveButton.setWidth(90);
		saveButton.setDisabled(true);

		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final PresetLabel preset = getCurrentPreset();
				if (preset != null) {
					updateWindowDtos(preset);
					presenter.savePreset(preset.getDto());
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

		final DynamicForm form = new DynamicForm();
		form.setAlign(Alignment.RIGHT);
		selectInput = new SelectItem();
		selectInput.setTitle("Select Source");
		selectInput.setClipTitle(true);
		selectInput.setAlign(Alignment.RIGHT);
		selectInput.setDisabled(true);
		form.setFields(selectInput);
		form.setWidth(250);
		form.setMinWidth(250);

		selectInput.addChangedHandler(new ChangedHandler() {

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

		HLayout hButtonLayout = new HLayout(15);
		hButtonLayout.setAlign(Alignment.CENTER);
		hButtonLayout.addMember(form);
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

		addMember(vButtonLayout);
		addMember(panelDisplayLayout);
	}

	private void updateWindowDtos(final PresetLabel preset) {
		for (Canvas child : preset.getChildren()) {
			if (child instanceof WindowLabel) {
				WindowLabel window = (WindowLabel) child;
				window.updateDto(scaleFactor);
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
		selectInput.setValueMap(sourcesMap);
	}

	private void setDisplay(DisplayDto display) {

		Long displayWidth = new Long(display.getWidth());
		Long displayHeight = new Long(display.getHigh());

		Double padding = adjustPadding(displayWidth, displayHeight);

		Long panelDisplayWidth = Math.round(getElement().getClientWidth() * padding);
		scaleFactor = panelDisplayWidth.doubleValue() / displayWidth.doubleValue();
		Long panelDisplayHeight = Math.round(scaleFactor * displayHeight.doubleValue());

		createDisplayWindow(display.getLayout(), panelDisplayWidth, panelDisplayHeight, scaleFactor);
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

		Long panelWidth = Math.round(panelDisplayWidth.doubleValue() / layout.getColumnPanelQuantity());
		Long panelHeight = Math.round(panelDisplayHeight.doubleValue() / layout.getRowPanelQuantity());

		createDisplayPanels(layout, panelWidth, panelHeight);
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
		panel.setBorder("1px solid black");
		panel.setBackgroundColor("darkgrey");
		panel.setCanDragResize(false);
		panel.setCanDragReposition(false);

		panelDisplay.addChild(panel);
	}

	private void createPresetLayouts(List<PresetDto> presets) {
		Long panelDisplayWidth = new Long(panelDisplay.getWidth());
		Long panelDisplayHeight = new Long(panelDisplay.getHeight());

		layouts.clear();
		for (PresetDto preset : presets) {
			createPresetLayout(preset, panelDisplayWidth, panelDisplayHeight, scaleFactor);
		}
	}

	private void createPresetLayout(PresetDto preset, Long presetWidth, Long presetHeight, Double scaleFactor) {

		PresetLabel lab = UiBuilder.buildPresetLayout(preset, presetWidth, presetHeight, scaleFactor,
				new EditPresetPostBuilder(), new EditWindowPostBuilder());
		layouts.put(lab.getId(), lab);
	}

	@Override
	public void setPreset(final Long presetId) {
		cleanState();
		if (layouts.containsKey(presetId)) {
			cancelButton.enable();

			PresetLabel layout = layouts.get(presetId);
			layout = layout.clone();
			addWindowsHandlers(layout);

			panelDisplay.addChild(layout);
		}
	}

	private void addWindowsHandlers(final PresetLabel layout) {
		ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				if (!window.isSelected()) {
					cleanWindows(layout);
					selectWindow(window);
					selectInput.enable();
					selectInput.setValue(window.getDto().getSource());
				}
			}

		};

		DragRepositionStopHandler dragRepositionHandler = new DragRepositionStopHandler() {

			@Override
			public void onDragRepositionStop(DragRepositionStopEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				onWindowChanged(window);
			}
		};

		DragResizeStopHandler dragResizeHandler = new DragResizeStopHandler() {

			@Override
			public void onDragResizeStop(DragResizeStopEvent event) {
				WindowLabel window = (WindowLabel) event.getSource();
				onWindowChanged(window);
			}
		};

		for (Canvas child : layout.getChildren()) {
			if (child instanceof WindowLabel) {
				child.addClickHandler(clickHandler);
				child.addDragRepositionStopHandler(dragRepositionHandler);
				child.addDragResizeStopHandler(dragResizeHandler);
			}
		}
	}

	private void onWindowChanged(WindowLabel window) {
		window.setDirty(true);
		applyButton.enable();
		saveButton.enable();
	}

	private void cleanWindows(PresetLabel layout) {
		for (Canvas child : layout.getChildren()) {
			if (child instanceof WindowLabel) {
				WindowLabel window = (WindowLabel) child;
				new EditWindowStyleApplier().applyStyle(window);
				window.setSelected(false);
			}
		}
	}

	private void selectWindow(WindowLabel window) {
		window.setSelected(true);
		window.setOpacity(100);
		window.setCanDragResize(true);
		window.setCanDragReposition(true);
	}

	private void cleanPresetLayouts() {
		for (Canvas child : panelDisplay.getChildren()) {
			if (child instanceof PresetLabel) {
				panelDisplay.removeChild(child);
			}
		}
	}

	private void cleanHeaderWidgets() {
		selectInput.setValue("");
		selectInput.disable();
		applyButton.disable();
		saveButton.disable();
		cancelButton.disable();
	}

	private WindowLabel getSelectedWindow() {
		for (Canvas preset : panelDisplay.getChildren()) {
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

	private PresetLabel getCurrentPreset() {
		for (Canvas child : panelDisplay.getChildren()) {
			if (child instanceof PresetLabel) {
				return (PresetLabel) child;
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
		createPresetLayout(preset, panelDisplayWidth, panelDisplayHeight, scaleFactor);
	}

}
