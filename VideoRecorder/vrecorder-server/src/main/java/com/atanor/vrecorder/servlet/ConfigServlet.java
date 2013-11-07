package com.atanor.vrecorder.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.io.FileSystemUtils;

import com.atanor.vrecorder.rpc.services.ConfigService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements ConfigService {

	private final String diskName;

	@Inject
	public ConfigServlet(@Named("output.disk") final String diskName) {
		this.diskName = diskName;
	}

	@Override
	public Long getAvailableSpaceSize() {
		try {
			final double freeDiskSpace = FileSystemUtils.freeSpaceKb(diskName);
			return Math.round(freeDiskSpace / 1024);
		} catch (IOException e) {
			throw new IllegalStateException("Can not calculate disk free space", e);
		}
	}

}
