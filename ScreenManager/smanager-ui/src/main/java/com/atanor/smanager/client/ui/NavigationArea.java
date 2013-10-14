package com.atanor.smanager.client.ui;

import com.atanor.smanager.client.ScreenManager;
import com.atanor.smanager.client.event.AnimateEvent;
import com.atanor.smanager.client.event.Client;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.PresetDto;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class NavigationArea extends HLayout implements DoubleClickHandler{

	private final ConfigServiceAsync configService = GWT
			.create(ConfigService.class);
	private String namePreset = "name", windowN = "window";
	private int presActiv = 0;
	private SectionStackSection leftHead;
	private SectionStack sectionStack;

	public NavigationArea(final SimpleEventBus bus) {

		super();

		this.setMembersMargin(0);
		this.setOverflow(Overflow.HIDDEN);
		this.setShowResizeBar(true);

		sectionStack = new SectionStack();
		sectionStack.setShowExpandControls(true);
		sectionStack.setAnimateSections(true);
		sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
		sectionStack.setOverflow(Overflow.HIDDEN);
		sectionStack.setMembersMargin(20);
		sectionStack.setTitle("PRESETS");

		 final Label leftHead = new Label();
		 leftHead.setTop(0);
		 leftHead.setWidth100();
		 leftHead.setHeight(60);
		 leftHead.setTitle("PRESETS");
		 
		 leftHead.setAlign(Alignment.CENTER);
		 leftHead.setContents("<H2>Presets</H2>");
		 leftHead.setMaxHeight(62);
		 leftHead.setBackgroundColor("lightgrey");
		 leftHead.setShowEdges(true);
		 leftHead.draw();
		 sectionStack.addChild(leftHead);

		configService
				.getHardwareConfiguration(new AsyncCallback<HardwareDto>() {

					@Override
					public void onSuccess(final HardwareDto result) {
						int win = 0, pres = 1;

						for (PresetDto preset : result.getPresets()) {
//							namePreset = namePreset + pres;
							createPresWind(pres, preset);
							pres++;
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Configurations are not available!");
						caught.printStackTrace();
					}
				});

		this.addMember(sectionStack);

	}
	
	private void createPresWind(int pres, PresetDto preset){
		
		Label lab = new Label();
		lab.setTop((pres * 94) + 2);
		lab.setLeft(7);
		lab.setWidth(164);
		lab.setHeight(62);
		
		lab.setShowEdges(true);
		lab.setBorder("2px solid black");
		lab.setMaxWidth(164);
		lab.setMaxHeight(62);
		lab.addDoubleClickHandler(this);
				
		for (WindowDto window : preset.getWindows()) {
			
			final Label $windowN = new Label();
			$windowN.setTop(window.getYTopLeft() / 20);
			$windowN.setLeft(window.getXTopLeft() / 10);
			$windowN.setWidth((window.getXBottomRight() - window
					.getXTopLeft()) / 10);
			$windowN.setHeight((window.getYBottomRight() - window
					.getYTopLeft()) / 20);
			$windowN.setBorder("1px solid black");
			$windowN.setBackgroundColor("darkgrey");
			$windowN.draw();
			
			
			lab.addChild($windowN);
			lab.draw();
			};
			sectionStack.addChild(lab);
			 
	};
	

	@Override
	public void onDoubleClick(DoubleClickEvent event) {
		Client.getBus().fireEvent(new AnimateEvent(0));
//		for(Canvas ll : leftHead.getItems()){
//			ll.animateFade(100, null,1000);
//		}
		((Canvas)event.getSource()).animateFade(30, null,
				1000);
		}
}
