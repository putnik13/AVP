package com.atanor.smanager.services.hardware;

import com.atanor.smanager.domain.entity.Preset;

public class HardwareFacadeImpl implements HardwareFacade {

	@Override
	public Boolean sendPresetConfiguration(Preset preset) {
		// TODO Sergey, please add telnet calls to real HARDWARE here 
		System.out.println("sendPresetConfiguration() called..");
		return Boolean.TRUE;
	}

}
