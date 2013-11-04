package com.atanor.vrecorder.rpc.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class RecordingDto extends AbstractDto {

	private String name;
	private Date startTime;
	private Date endTime;
	private Long duration;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

}
