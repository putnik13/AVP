package com.atanor.hdconnect.client;

import com.atanor.hdconnect.client.ui.MainPane;

public class Client {

	private static MainPane view;

	public static MainPane getView() {
		if (view == null) {
			view = new MainPane();
		}
		return view;
	}

}
