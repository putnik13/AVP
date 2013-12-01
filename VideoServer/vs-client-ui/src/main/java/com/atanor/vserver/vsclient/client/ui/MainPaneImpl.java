package com.atanor.vserver.vsclient.client.ui;

import com.atanor.vserver.common.entity.Snapshot;
import com.atanor.vserver.vsclient.client.presenter.MainPanePresenter;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;

public class MainPaneImpl extends HLayout implements MainPane {

	private final Canvas snapshotBox;
	private MainPanePresenter presenter;
	private Img currentImg;

	public MainPaneImpl() {
		setWidth100();
		setHeight100();

		snapshotBox = new Canvas();
		snapshotBox.setWidth100();
		snapshotBox.setHeight100();
		snapshotBox.setShowEdges(false);
		snapshotBox.setBackgroundColor("lightgrey");

		addMembers(snapshotBox);
	}

	@Override
	public void setPresenter(final MainPanePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void addSnapshot(final Snapshot snapshot) {
		cleanScreen();
		final String source = "data:image/png;base64," + snapshot.getEncodedImage();
		currentImg = new Img();
		currentImg.setSrc(source);
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
