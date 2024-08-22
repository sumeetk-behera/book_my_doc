package com.tyss.bookmydoctor.api.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Appointment.class)
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String patientName;

	private int age;

	private long phoneNumber;

	private String email;

	private String doctorName;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate createdDate;

	private String appointmentDate;

	private String time;

	@ManyToOne(cascade = CascadeType.ALL)
	private Doctor doctor;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
}
