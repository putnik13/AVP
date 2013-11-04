package com.atanor.vrecorder.servlet;

import java.util.List;

import javax.inject.Singleton;

import com.atanor.vrecorder.rpc.dto.RecordingDto;
import com.atanor.vrecorder.rpc.services.RecordingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class RecordingServlet extends RemoteServiceServlet implements RecordingService {

	@Override
	public Boolean startRecording() {
		System.out.println("Recording started ..");
		return Boolean.TRUE;
	}

	@Override
	public Boolean stopRecording() {
		System.out.println("Recording stopped ..");
		return Boolean.TRUE;
	}

	@Override
	public List<RecordingDto> getRecordings() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
