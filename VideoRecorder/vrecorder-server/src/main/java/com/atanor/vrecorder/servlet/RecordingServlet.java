package com.atanor.vrecorder.servlet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.vrecorder.domain.converter.RecordingConverter;
import com.atanor.vrecorder.facades.PalantirFacade;
import com.atanor.vrecorder.facades.PlayerFacade;
import com.atanor.vrecorder.facades.palantir.Signal;
import com.atanor.vrecorder.rpc.dto.RecordingDto;
import com.atanor.vrecorder.rpc.services.RecordingService;
import com.atanor.vrecorder.services.RecordingDataService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class RecordingServlet extends RemoteServiceServlet implements RecordingService {

	@Inject
	private RecordingDataService recordingsService;

	@Inject
	private RecordingConverter converter;

	@Inject
	private PalantirFacade palantir;

	@Inject
	private PlayerFacade player;

	@Override
	public Boolean startRecording() {
		palantir.signal(Signal.START_RECORDING);
		player.startRecording();
		return Boolean.TRUE;
	}

	@Override
	public Boolean stopRecording() {
		palantir.signal(Signal.STOP_RECORDING);
		player.stopRecording();
		return Boolean.TRUE;
	}

	@Override
	public List<RecordingDto> getRecordings() {
		return converter.toListDto(recordingsService.getRecordings());
	}

	@Override
	public Boolean removeRecordings(final List<RecordingDto> recordings) {
		recordingsService.removeRecordings(converter.toListEntities(recordings));
		return Boolean.TRUE;
	}

	@Override
	public List<RecordingDto> getSynchronizationInfo() {
		return converter.toListDto(recordingsService.getSynchronizationInfo());
	}

}
