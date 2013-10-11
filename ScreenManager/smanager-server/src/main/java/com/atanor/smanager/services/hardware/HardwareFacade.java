package com.atanor.smanager.services.hardware;

import com.atanor.smanager.domain.entity.Preset;

public interface HardwareFacade {

	Boolean sendPresetConfiguration(Preset preset);
}
