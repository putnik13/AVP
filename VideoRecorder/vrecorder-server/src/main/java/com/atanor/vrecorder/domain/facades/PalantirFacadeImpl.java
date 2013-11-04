package com.atanor.vrecorder.domain.facades;

public class PalantirFacadeImpl implements PalantirFacade {

	@Override
	public void signal(Signal signal) {
		System.out.println("Send to Palantir: " + signal.name());
	}

}
