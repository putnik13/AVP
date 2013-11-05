package com.atanor.vrecorder.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "recordings")
@NamedQuery(name = "Recording.GetAll", query = "SELECT r FROM Recording r")
public class Recording extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 32)
	private String name;

	@Column(name = "starttime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@Column(name = "endtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	@Column(name = "duration", length = 32)
	private String duration;

	public Recording() {
	}

	public Recording(Long id) {
		this.id = id;
	}

	public Recording(final String name) {
		this.name = name;
	}

	@Override
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

}
