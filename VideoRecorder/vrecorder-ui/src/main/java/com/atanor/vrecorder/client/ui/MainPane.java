package com.atanor.vrecorder.client.ui;

import java.util.Date;
import java.util.List;

import com.atanor.vrecorder.rpc.dto.RecordingDto;
import com.atanor.vrecorder.shared.Constants;
import com.google.common.collect.Lists;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainPane extends HLayout {

	private static final String DURATION_GRID_ATTR = "duration";
	private static final String START_TIME_GRID_ATTR = "startTime";
	private static final String END_TIME_GRID_ATTR = "endTime";
	private static final String FILE_NAME_GRID_ATTR = "fileName";
	private static final String ENCODED_IMAGE_ATTR = "encodeImage";
	private static final DateTimeFormat df = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
	private static final Integer TOOLBAR_SIZE = 25;

	private MainPanePresenter presenter;

	private final IButton startRecord;
	private final IButton stopRecord;
	private final Canvas snapshotBox;
	private final ListGrid listGrid;
	private final Label freeSpace;

	private Img snapshot;

	public MainPane() {
		setWidth100();
		setHeight100();
		setBackgroundColor("lightgrey");

		snapshotBox = createSnapshotBox();

		startRecord = new IButton("Start Recording");
		startRecord.setWidth(90);
		startRecord.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.startRecording();
			}
		});

		stopRecord = new IButton("Stop Recording");
		stopRecord.setWidth(90);
		stopRecord.setDisabled(true);
		stopRecord.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.stopRecording();
			}
		});

		final HLayout spacer = new HLayout();
		spacer.setHeight(20);

		final HLayout headerPane = new HLayout();
		headerPane.addMembers(snapshotBox, startRecord, stopRecord);
		headerPane.setMembersMargin(10);

		final HLayout gridToolbar = new HLayout();
		gridToolbar.setAlign(Alignment.RIGHT);
		gridToolbar.setHeight(TOOLBAR_SIZE);
		final Img synchronizeImg = createToolbarImage("synchronize.png", "Synchronize Recordings");
		final Img removeImg = createToolbarImage("remove.png", "Remove Recordings");
		removeImg.setDisabled(true);
		
		gridToolbar.addMembers(synchronizeImg, removeImg);

		listGrid = new ListGrid() {
			@Override
			protected Canvas getCellHoverComponent(Record record, Integer rowNum, Integer colNum) {
				final String encodedImage = record.getAttribute(ENCODED_IMAGE_ATTR);

				final Canvas canvas = createSnapshotBox();
				final Canvas canvasImg = encodedImage == null ? createSnapshotMissed()
						: createSnapshotImage(encodedImage);
				canvas.addChild(canvasImg);

				return canvas;
			}
		};
		listGrid.setCanHover(true);
		listGrid.setShowHover(true);
		listGrid.setShowHoverComponents(true);
		listGrid.setSelectionType(SelectionStyle.NONE);
		listGrid.setShowRowNumbers(true);

		final ListGridField fileName = new ListGridField(FILE_NAME_GRID_ATTR, "File Name");
		final ListGridField startTime = new ListGridField(START_TIME_GRID_ATTR, "Start Time");
		startTime.setCellFormatter(new CellFormatter() {

			@Override
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null) {
					return null;
				}
				return df.format((Date) value);
			}
		});
		final ListGridField endTime = new ListGridField(END_TIME_GRID_ATTR, "End Time");
		endTime.setCellFormatter(new CellFormatter() {

			@Override
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null) {
					return null;
				}
				return df.format((Date) value);
			}
		});
		final ListGridField duration = new ListGridField(DURATION_GRID_ATTR, "Duration");
		listGrid.setFields(fileName, startTime, endTime, duration);

		final VLayout gridLayout = new VLayout();
		gridLayout.addMembers(gridToolbar, listGrid);

		freeSpace = new Label();
		freeSpace.setContents(createSpaceSizeContent(null));
		freeSpace.setBottom(80);

		final VLayout vLayout = new VLayout();
		vLayout.setWidth("80%");
		vLayout.setHeight100();
		vLayout.setMembersMargin(20);
		vLayout.addMembers(spacer, headerPane, gridLayout, freeSpace);

		addMembers(new LayoutSpacer(), vLayout, new LayoutSpacer());
	}

	public void onRecordingStarted() {
		startRecord.disable();
		stopRecord.enable();
	}

	public void onRecordingStopped() {
		startRecord.enable();
		stopRecord.disable();
	}

	public void setRecordings(final List<RecordingDto> recordings) {
		List<ListGridRecord> records = createGridRecords(recordings);
		listGrid.setData(records.toArray(new ListGridRecord[] {}));
	}

	public void setSnapshot(final String encodedSnapshot) {
		cleanSnapshot();

		final String source = "data:image/png;base64," + encodedSnapshot;
		final Img img = new Img();
		img.setSrc(source);
		img.setWidth(Constants.SNAPSHOT_WIDTH);
		img.setHeight(Constants.SNAPSHOT_HEIGHT);

		snapshotBox.addChild(img);
	}

	private void cleanSnapshot() {
		if (snapshot != null) {
			snapshot.destroy();
			snapshot = null;
		}
	}

	private List<ListGridRecord> createGridRecords(final List<RecordingDto> recordings) {
		List<ListGridRecord> records = Lists.newArrayList();
		for (RecordingDto dto : recordings) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute(FILE_NAME_GRID_ATTR, dto.getName());
			record.setAttribute(START_TIME_GRID_ATTR, dto.getStartTime());
			record.setAttribute(END_TIME_GRID_ATTR, dto.getEndTime());
			record.setAttribute(DURATION_GRID_ATTR, dto.getDuration());
			record.setAttribute(ENCODED_IMAGE_ATTR, dto.getEncodedImage());

			records.add(record);
		}

		return records;
	}

	private Canvas createSnapshotBox() {
		final Canvas canvas = new Canvas();
		canvas.setWidth(Constants.SNAPSHOT_WIDTH);
		canvas.setHeight(Constants.SNAPSHOT_HEIGHT);
		canvas.setShowEdges(true);
		canvas.setBackgroundColor("black");
		return canvas;
	}

	private Canvas createSnapshotMissed() {
		final Label lbl = new Label();
		lbl.setWidth100();
		lbl.setHeight100();
		lbl.setContents("<font color='white'>Snapshot Missed</font>");
		lbl.setAlign(Alignment.CENTER);
		lbl.setValign(VerticalAlignment.CENTER);
		return lbl;
	}

	private Canvas createSnapshotImage(final String encodedImage) {
		final Img img = new Img();
		final String source = "data:image/png;base64," + encodedImage;
		img.setSrc(source);
		img.setWidth(Constants.SNAPSHOT_WIDTH);
		img.setHeight(Constants.SNAPSHOT_HEIGHT);
		return img;
	}

	private Img createToolbarImage(final String imgSource, final String tooltip) {
		final Img img = new Img();
		img.setSrc(imgSource);
		img.setTooltip(tooltip);
		img.setWidth(TOOLBAR_SIZE);
		img.setHeight(TOOLBAR_SIZE);

		return img;
	}

	private String createSpaceSizeContent(final Long spaceSize) {
		final String size = spaceSize == null ? "..." : String.valueOf(spaceSize);
		return "<b>Free Space on disk:</b> " + size + " Mb is available";
	}

	public void setPresenter(final MainPanePresenter presenter) {
		this.presenter = presenter;
	}

	public void setAvailableSpaceSize(final Long size) {
		freeSpace.setContents(createSpaceSizeContent(size));
	}
}
