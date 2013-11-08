package com.atanor.vrecorder.rpc.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class RecordingDto extends AbstractDto {

	private Long id;
	private String name;
	private Date startTime;
	private Date endTime;
	private String duration;
	private String encodedImage;
	private Boolean outdated;
	
	public RecordingDto() {
	}

	public RecordingDto(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

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

	public String getDuration() {
		return duration;
	}

	public void setDuration(final String duration) {
		this.duration = duration;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(final String encodedImage) {
		this.encodedImage = encodedImage;
	}

	public Boolean isOutdated() {
		return outdated;
	}

	public void setOutdated(final Boolean outdated) {
		this.outdated = outdated;
	}

}
