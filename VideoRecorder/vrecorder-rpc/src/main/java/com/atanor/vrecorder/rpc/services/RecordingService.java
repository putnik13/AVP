package com.atanor.vrecorder.rpc.services;

import java.util.List;

import com.atanor.vrecorder.rpc.dto.RecordingDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Recording Service.
 */
@RemoteServiceRelativePath("recording")
public interface RecordingService extends RemoteService {

	Boolean startRecording();

	Boolean stopRecording();

	Boolean removeRecordings(List<RecordingDto> recordings);
	
	List<RecordingDto> getRecordings();
	
	List<RecordingDto> getSynchronizationInfo();
}
