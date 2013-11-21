package com.atanor.hdconnect.client.ui;

import com.atanor.hdconnect.client.entity.Snapshot;
import com.atanor.hdconnect.client.presenter.MainPanePresenter;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;

public class MainPaneImpl extends HLayout implements MainPane {

	private final Canvas snapshotBox;
	private MainPanePresenter presenter;
	private Integer left = 0;
	private Integer top = 0;
	private Img currentImg;

	public MainPaneImpl() {
		setWidth100();
		setHeight100();

		snapshotBox = new Canvas();
		snapshotBox.setWidth100();
		snapshotBox.setHeight100();
		snapshotBox.setShowEdges(false);
		snapshotBox.setBackgroundColor("black");

		addMembers(snapshotBox);
	}

	@Override
	public void setPresenter(final MainPanePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void addSnapshot(final Snapshot snapshot) {
		cleanScreen();
//		left += 20;
//		top += 20;
		final String source = "data:image/png;base64," + snapshot.getEncodedImage();
		currentImg = new Img();
		currentImg.setSrc(source);
//		currentImg.setLeft(left);
//		currentImg.setTop(top);
		currentImg.setWidth(Integer.valueOf(snapshot.getWidth()));
		currentImg.setHeight(Integer.valueOf(snapshot.getHeight()));

		snapshotBox.addChild(currentImg);
	}

	private void cleanScreen() {
		if (currentImg != null) {
			currentImg.destroy();
			currentImg = null;
		}
	}

}
