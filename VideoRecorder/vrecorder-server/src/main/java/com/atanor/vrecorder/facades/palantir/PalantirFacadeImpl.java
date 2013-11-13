package com.atanor.vrecorder.facades.palantir;

import javax.inject.Inject;

import com.atanor.vrecorder.facades.PalantirFacade;

public class PalantirFacadeImpl implements PalantirFacade {

	@Inject
	private PalantirClient client;

	@Override
	public void signal(Signal signal) {
		client.send(signal.value());
	}

}
