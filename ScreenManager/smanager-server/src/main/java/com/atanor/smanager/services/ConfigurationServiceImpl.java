package com.atanor.smanager.services;

import java.util.List;

import javax.inject.Inject;

import com.atanor.smanager.domain.dao.HardwareDao;
import com.atanor.smanager.domain.entity.Hardware;
import com.google.common.collect.Lists;

public class ConfigurationServiceImpl implements ConfigurationService {

	@Inject
	private HardwareDao hardwareDao;

	@Override
	public Hardware getActiveHardware() {
		final List<Hardware> allHardware = hardwareDao.findAll();
		final List<Hardware> allActiveHardware = getActive(allHardware);

		if (allActiveHardware.isEmpty()) {
			throw new IllegalStateException("Configuration error. No active hardware found");
		}
		if (allActiveHardware.size() > 1) {
			throw new IllegalStateException("Configuration error. More than one active hardware found");
		}

		return allActiveHardware.get(0);
	}

	private List<Hardware> getActive(final List<Hardware> allHardware) {
		final List<Hardware> result = Lists.newArrayList();
		for (final Hardware hardware : allHardware) {
			if (hardware.isActive()) {
				result.add(hardware);
			}
		}
		return result;
	}

}
