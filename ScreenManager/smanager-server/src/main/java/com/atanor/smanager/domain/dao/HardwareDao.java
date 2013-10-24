package com.atanor.smanager.domain.dao;

import com.atanor.smanager.domain.entity.Hardware;


public class HardwareDao extends GenericDaoImpl<Hardware, Long> {

	@Override
	public Class<Hardware> getEntityClass() {
		return Hardware.class;
	}

}
