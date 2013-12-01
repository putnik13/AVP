package com.atanor.vserver.vsclient.client.ui;

import com.atanor.vserver.common.entity.Snapshot;
import com.atanor.vserver.vsclient.client.presenter.MainPanePresenter;
import com.google.common.primitives.Ints;
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

		adjustSize(currentImg, Long.valueOf(snapshot.getWidth()), Long.valueOf(snapshot.getHeight()));
		adjustPosition(currentImg);
		
		snapshotBox.addChild(currentImg);
	}

	private void cleanScreen() {
		if (currentImg != null) {
			currentImg.destroy();
			currentImg = null;
		}
	}

	private void adjustSize(final Img image, final Long originWidth, final Long originHeight) {

		Double vPadding = 1.0d;
		Long imageHeight = null;
		Long imageWidth = null;

		do {
			imageHeight = Math.round(getElement().getClientHeight() * vPadding);
			final Double scaleFactor = imageHeight.doubleValue() / originHeight.doubleValue();
			imageWidth = Math.round(scaleFactor * originWidth.doubleValue());

			vPadding -= 0.01d;
		} while (imageWidth > getElement().getClientWidth());

		image.setWidth(Ints.checkedCast(imageWidth));
		image.setHeight(Ints.checkedCast(imageHeight));
	}

	private void adjustPosition(final Img image) {
		final Long leftOffset = Math.round((getElement().getClientWidth() - image.getWidth()) / 2d);
		image.setLeft(Ints.checkedCast(leftOffset));
	}
	
}
