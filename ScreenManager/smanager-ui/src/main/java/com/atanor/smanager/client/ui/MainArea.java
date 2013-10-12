package com.atanor.smanager.client.ui;

import java.util.LinkedHashMap;

import com.atanor.smanager.client.event.AnimateEvent;
import com.atanor.smanager.client.event.AnimateEventHandler;
import com.atanor.smanager.rpc.dto.HardwareDto;
import com.atanor.smanager.rpc.services.ConfigService;
import com.atanor.smanager.rpc.services.ConfigServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
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
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.FocusEvent;
import com.smartgwt.client.widgets.form.fields.events.FocusHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class MainArea extends HLayout {

	final TabSet topTabSet = new TabSet();
	private final ConfigServiceAsync configService = GWT
			.create(ConfigService.class);
	// panels at all
	private int nHorizontal = 5, nVertical = 3; // number of panels
	private String name = "drawRect", selectedInput = null;

	@SuppressWarnings("deprecation")
	public MainArea(final SimpleEventBus bus) {

		super();

		this.setOverflow(Overflow.HIDDEN);

		topTabSet.setTabBarPosition(Side.BOTTOM);
		topTabSet.setTabBarAlign(Side.RIGHT);
		topTabSet.setAlign(Alignment.LEFT);

		// ToolStrip toolStrip = new ToolStrip();
		// toolStrip.setWidth100();
		//
		// ToolStripButton iconButton = new ToolStripButton();
		// iconButton.setTitle(" Get Data! ");
		//
		// iconButton.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// getConfigurations();
		// }
		//
		// });
		//
		// toolStrip.addButton(iconButton);

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
		// mainLayout.setAlign(Alignment.CENTER);

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
				member1.setContents("<div align = center><H1>"+selectedInput+"</H1></div>");
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
				System.out.println("Selected --"
						+ event.getItem().getDisplayValue());

			}
		});

		layout.addMember(mainLayout);

		buttonLayout.addMember(form);
		buttonLayout.setAlign(Alignment.CENTER);
		// buttonLayout.addChild(selectInputsList);
		buttonLayout.addMember(applyLayout);
		buttonLayout.addMember(saveLayout);
		buttonLayout.addMember(cancelLayout);

		layout.addMember(buttonLayout);

		layout.draw();

		bus.addHandler(AnimateEvent.TYPE, new AnimateEventHandler() {
			@Override
			public void animate(AnimateEvent event) {

				int layoutN = event.getLayoutNumber();

				mainLayout.setZIndex(0);
				mainLayout.addChild(member1);
				mainLayout.setZIndex(1);
				mainLayout.addChild(member2);
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

	private void getConfigurations() {
		configService
				.getHardwareConfiguration(new AsyncCallback<HardwareDto>() {

					@Override
					public void onSuccess(HardwareDto result) {
						SC.say("Fetched configuration for following hardware: "
								+ result.getSources());
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Configurations are not available!");
						caught.printStackTrace();
					}
				});
	}

	public class DragRect extends DrawRect {
		public DragRect() {
			setPadding(4);
			setMinWidth(10);
			setMinHeight(10);
			setMaxWidth(1000);
			setMaxHeight(700);
			setKeepInParentRect(false);
			setCanDragReposition(true);
			setDragAppearance(DragAppearance.OUTLINE);
			setCanDragResize(true);
			setCanFocus(true);
		}
	}
}
