package com.lecturerappointmentsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author GustTech
 */
@Entity
@Table(name = "time_slots")
public class TimeSlots implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Size(max = 50)
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@NotNull
	@Column(name = "end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	public TimeSlots() {
	}

	public TimeSlots(Integer id) {
		this.id = id;
	}

	public TimeSlots(Integer id, Date startTime, Date endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public static TimeSlots fromCommand(TimeSlots request) {

		if (request == null) {
			return null;
		}

		TimeSlots timeSlot = new TimeSlots();
		timeSlot.setName(request.getName());
		timeSlot.setStartTime(request.getStartTime());
		timeSlot.setEndTime(request.getEndTime());

		return timeSlot;
	}

	public void update(TimeSlots updateRequest) {
		this.setName(updateRequest.getName());
		this.setStartTime(updateRequest.getStartTime());
		this.setEndTime(updateRequest.getEndTime());
	}

	@Override
	public String toString() {
		return "TimeSlots{" +
				"id=" + id +
				", name='" + name + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				'}';
	}
}
