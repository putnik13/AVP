package com.atanor.smanager.client.mvp.views.impl;

import java.util.List;

import com.atanor.smanager.client.Client;
import com.atanor.smanager.client.mvp.places.PresetSelectedPlace;
import com.atanor.smanager.client.mvp.views.NavigatePresetView;
import com.atanor.smanager.client.ui.PresetLabel;
import com.atanor.smanager.client.ui.builder.UiBuilder;
import com.atanor.smanager.client.ui.builder.post.NavigatePresetPostBuilder;
import com.atanor.smanager.client.ui.builder.post.NavigateWindowPostBuilder;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.google.common.collect.Lists;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class NavigatePresetViewImpl extends VLayout implements NavigatePresetView, ClickHandler {

	private final List<PresetLabel> layouts = Lists.newArrayList();

	private Double scaleFactor;
	private Long presetWidth;
	private Long presetHeight;

	public NavigatePresetViewImpl() {
		setHeight100();
		setWidth("15%");
		setOverflow(Overflow.HIDDEN);
		setShowResizeBar(true);
		setMembersMargin(40);
		setBackgroundColor("lightgrey");

		final Label headerLabel = new Label();
		headerLabel.setTop(0);
		headerLabel.setHeight(60);
		headerLabel.setAlign(Alignment.CENTER);
		headerLabel.setContents("<H3>Presets</H3>");
		headerLabel.setMaxHeight(60);
		headerLabel.setBackgroundColor("lightgrey");
		headerLabel.setShowEdges(true);

		addMember(headerLabel);
	}

	private void createPresetWindow(PresetDto preset, Long presetWidth, Long presetHeight, Double scaleFactor) {

		PresetLabel lab = UiBuilder.buildPresetLayout(preset, presetWidth, presetHeight, scaleFactor,
				new NavigatePresetPostBuilder(), new NavigateWindowPostBuilder());
		lab.addClickHandler(this);
		layouts.add(lab);

		HLayout labelLayout = new HLayout();
		labelLayout.setAlign(Alignment.CENTER);
		labelLayout.addMember(lab);
		labelLayout.setHeight(10);

		addMember(labelLayout);
	}

	@Override
	public void setConfiguration(final HardwareDto config) {
		Long displayWidth = new Long(config.getDisplay().getWidth());
		Long displayHeight = new Long(config.getDisplay().getHigh());

		presetWidth = Math.round(getElement().getClientWidth() * 0.8d);

		scaleFactor = presetWidth.doubleValue() / displayWidth.doubleValue();
		presetHeight = Math.round(scaleFactor * displayHeight.doubleValue());

		for (PresetDto preset : config.getPresets()) {
			createPresetWindow(preset, presetWidth, presetHeight, scaleFactor);
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		PresetLabel preset = (PresetLabel) event.getSource();

		if (!preset.isSelected()) {
			Client.goTo(new PresetSelectedPlace(preset.getDto().getId()));
		}
	}

	private void selectPreset(final PresetLabel preset) {
		preset.setSelected(true);
		preset.setOpacity(20);
	}

	private void resetPresetLayouts() {
		for (PresetLabel layout : layouts) {
			layout.setSelected(false);
			layout.setOpacity(100);
		}
	}

	@Override
	public void cleanState() {
		resetPresetLayouts();
	}

	@Override
	public void setPreset(final Long presetId) {
		resetPresetLayouts();
		selectPreset(getPreset(presetId));
	}

	private PresetLabel getPreset(final Long presetId) {
		for (PresetLabel preset : layouts) {
			if (presetId == preset.getId()) {
				return preset;
			}
		}
		throw new IllegalStateException("Preset with following id: " + id + " not found");
	}

	@Override
	public void setPresetConfiguration(final PresetDto newPreset) {
		List<PresetDto> presets = Lists.newArrayList();
		for (PresetLabel playout : layouts) {
			presets.add(playout.getDto().getId() != newPreset.getId() ? playout.getDto() : newPreset);
		}
		
		layouts.clear();
		cleanPresetLayouts();
		
		for (PresetDto preset : presets) {
			createPresetWindow(preset, presetWidth, presetHeight, scaleFactor);
		}
	}

	private void cleanPresetLayouts() {
		for (Canvas child : getChildren()) {
			if (child instanceof HLayout) {
				child.destroy();
			}
		}
	}
}
