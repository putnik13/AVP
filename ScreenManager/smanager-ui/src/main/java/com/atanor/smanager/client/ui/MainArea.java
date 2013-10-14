package com.atanor.smanager.client.ui;

import java.util.LinkedHashMap;

import com.atanor.smanager.client.event.AnimateEvent;
import com.atanor.smanager.client.event.AnimateEventHandler;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.dto.WindowDto;
import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class MainArea extends HLayout implements ClickHandler {

	final TabSet topTabSet = new TabSet();
	private final ConfigServiceAsync configService = GWT
			.create(ConfigService.class);
	// panels at all
	private int nHorizontal = 5, nVertical = 3; // number of panels
	private int layoutN;
	private String name = "drawRect", selectedInput = null;
	private Label mainLayout;
	private int scaleY = 4, scaleX = 2;

	// private ClickHandler handler = new ClickHandler() {
	// @Override
	// public void onClick(ClickEvent event) {
	// (Label)event.getSource();
	//
	// }
	// };

	@SuppressWarnings("deprecation")
	public MainArea(final SimpleEventBus bus) {

		super();

		this.setOverflow(Overflow.HIDDEN);

		topTabSet.setTabBarPosition(Side.BOTTOM);
		topTabSet.setTabBarAlign(Side.RIGHT);
		topTabSet.setAlign(Alignment.LEFT);

		VLayout layout = new VLayout(5);
		layout.setAlign(Alignment.RIGHT);

		final Label mainLayout = new Label();
		mainLayout.setWidth(809);
		mainLayout.setHeight(281);
		mainLayout.setShowEdges(true);
		// mainLayout.setLayoutMargin(0);
		mainLayout.setBorder("1px solid black");
		mainLayout.setMaxWidth(808);
		mainLayout.setMaxHeight(282);
		mainLayout.setOverflow(Overflow.HIDDEN);

		final Label member1 = new Label();
		final Label member2 = new Label();

		member1.setDragAppearance(DragAppearance.TARGET);
		member1.setCanDragResize(true);
		member1.setResizeFrom("T", "B", "L", "R", "RB");
		member1.setRect(0, 0, 300, 270);
		// member1.setCanDragReposition(true);
		member1.setBackgroundColor("DARKBLUE");
		member1.setTop(0);
		member1.setOpacity(30);
		member1.setBorder("2px solid black");
		member1.setProperty("labName", "layoutWindow");
		member1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				member1.setOpacity(40);
				member1.setBorder("3px solid brown");
				member1.setCanDragReposition(true);
				member2.setCanDragReposition(false);
				member2.setBorder("2px solid black");
				member2.setOpacity(30);
			}
		});

		member2.setDragAppearance(DragAppearance.TARGET);
		member2.setCanDragResize(true);
		member2.setResizeFrom("T", "B", "L", "R", "RB");
		member2.setRect(300, 0, 490, 240);
		// member2.setCanDragReposition(true);
		member2.setBackgroundColor("DARKBLUE");
		member2.setTop(0);
		member2.setOpacity(30);
		member2.setBorder("2px solid black");
		member2.setProperty("labName", "layoutWindow");
		member2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				member2.setOpacity(40);
				member2.setBorder("3px solid brown");
				member2.setCanDragReposition(true);
				member1.setCanDragReposition(false);
				member1.setBorder("2px solid black");
				member1.setOpacity(30);
			}
		});

		HLayout buttonLayout = new HLayout(15);
		// buttonLayout.setLeft("7%");
		// buttonLayout.setTop(30);

		final IButton applyLayout = new IButton("Apply Source");
		applyLayout.setWidth(90);
		applyLayout.setDisabled(true);
		final IButton saveLayout = new IButton("Save Layout");
		saveLayout.setDisabled(true);
		saveLayout.setWidth(90);
		final IButton cancelLayout = new IButton("Cancel Layout");
		cancelLayout.setWidth(90);

		applyLayout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				member1.setContents("<div align = center><H1>" + selectedInput
						+ "</H1></div>");
				member1.redraw();
				applyLayout.disable();
				cancelLayout.enable();
			}
		});

		saveLayout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainLayout.removeChild(member1);
				saveLayout.disable();
				applyLayout.enable();
				cancelLayout.enable();
			}
		});

		cancelLayout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Canvas[] children = mainLayout.getChildren();
				int childCount = 0;
				for (Canvas child : children) {
					childCount++;
					System.out.println("child is -- " + child.toString());
					if (childCount > (nHorizontal * nVertical)) {
						mainLayout.removeChild(child);
					}
					;
				}
				applyLayout.disable();
				cancelLayout.disable();
				saveLayout.disable();
			}
		});

		for (int ii = 0; ii < nVertical; ii++) {
			final HLayout $nLayout = new HLayout();
			for (int k = 0; k < nHorizontal; k++) {
				name = name + k + ii;
				final Label $name = new Label();
				$name.setHeight(90);
				$name.setWidth(160);
				$name.setLeft(160 * k);
				$name.setTop(90 * ii);
				$name.setBorder("1px solid black");
				$name.setBackgroundColor("DARKGREY");
				$name.setCanDragResize(false);
				$name.setCanDragReposition(false);
				$nLayout.addMember($name);
				mainLayout.addChild($name);
			}
			mainLayout.redraw();
		}

		final DynamicForm form = new DynamicForm();
		form.setWidth(220);
		form.setAlign(Alignment.LEFT);

		final SelectItem selectInput = new SelectItem();
		selectInput.setTitle("Select Input");
		selectInput.setAlign(Alignment.LEFT);
		form.setFields(selectInput);
		final LinkedHashMap sourcesListMap = new LinkedHashMap<Integer, String>();

		configService
				.getHardwareConfiguration(new AsyncCallback<HardwareDto>() {

					@Override
					public void onSuccess(HardwareDto result) {
						int i = 0;
						for (String source : result.getSources()) {
							sourcesListMap.put(i, source);
							i++;
						}
						selectInput.setValueMap(sourcesListMap);
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Configurations are not available!");
						caught.printStackTrace();
					}
				});

		selectInput.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				selectedInput = event.getItem().getDisplayValue();
			}
		});

		layout.addMember(mainLayout);

		buttonLayout.addMember(form);
		buttonLayout.setAlign(Alignment.CENTER);
		buttonLayout.addMember(applyLayout);
		buttonLayout.addMember(saveLayout);
		buttonLayout.addMember(cancelLayout);

		layout.addMember(buttonLayout);
		layout.draw();

		bus.addHandler(AnimateEvent.TYPE, new AnimateEventHandler() {
			@Override
			public void animate(AnimateEvent event) {

				setLayoutN(event.getLayoutNumber());

				configService
						.getHardwareConfiguration(new AsyncCallback<HardwareDto>() {

							@Override
							public void onSuccess(final HardwareDto result) {
								int win = 0;
								System.out.println("Tray to DRAW!!!");

								for (WindowDto window : result.getPresets()
										.get(getLayoutN()).getWindows()) {

									mainLayout.addChild(createWind(
											window.getXTopLeft(),
											window.getYTopLeft(),
											window.getXBottomRight(),
											window.getYBottomRight()));
									win++;
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								SC.say("Configurations are not available!");
								caught.printStackTrace();
							}
						});
				mainLayout.redraw();
				applyLayout.enable();
				cancelLayout.enable();
			}
		});

		final VLayout hlayout = new VLayout();

		// hlayout.addMember(toolStrip);
		hlayout.addMember(layout);

		addTabToTopTabset("Main", hlayout, true);
		this.setLayoutAlign(Alignment.CENTER);
		this.addMember(topTabSet);
	}

	private void addTabToTopTabset(String title, Canvas pane, boolean closable) {
		Tab tab = createTab(title, pane, closable);
		topTabSet.addTab(tab);
		topTabSet.selectTab(tab);
	}

	private Tab createTab(String title, Canvas pane, boolean closable) {
		Tab tab = new Tab(title);
		tab.setCanClose(closable);
		tab.setPane(pane);
		return tab;
	}

	private Label createWind(int topXLeft, int topYLeft, int botXRight,
			int botYRight) {
		Label win = new Label();
		win.setBorder("2px solid black");
		win.setBackgroundColor("blue");
		win.setOpacity(21);
		win.setCanDragResize(true);
		win.setTop(topYLeft / scaleY);
		win.setLeft(topXLeft / scaleX);
		win.setWidth((botXRight - topXLeft) / scaleX);
		win.setHeight((botYRight - topYLeft) / scaleY);
		win.addClickHandler(this);
		win.draw();
		return win;
	};

	public int getLayoutN() {
		return layoutN;
	}

	public void setLayoutN(int layoutN) {
		this.layoutN = layoutN;
	}

	@Override
	public void onClick(ClickEvent event) {
		System.out.println("ClickEvent --- " + event.getSource());

//		if (event.getSource() == "Label") {
			((Label) event.getSource()).setOpacity(10);
			((Label) event.getSource()).setCanDragReposition(true);
			((Label) event.getSource()).setShowOverCanvas(true);
//		}
	}
}