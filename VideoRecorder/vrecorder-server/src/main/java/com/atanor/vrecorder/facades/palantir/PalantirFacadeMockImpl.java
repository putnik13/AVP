package com.atanor.vrecorder.facades.palantir;

import com.atanor.vrecorder.facades.PalantirFacade;

public class PalantirFacadeMockImpl implements PalantirFacade {

	@Override
	public void signal(Signal signal) {
		System.out.println("Send to Palantir: " + signal.name());
	}

}
