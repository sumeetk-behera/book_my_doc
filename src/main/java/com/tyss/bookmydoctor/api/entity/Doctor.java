package com.tyss.bookmydoctor.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Doctor.class)
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private String email;
	private String password;
	private String sex;
	private long phoneNumber;
	private String ratings;
	private String location;
	private String speciality;
	private String roleName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
	private List<Appointment> appointmentsList;

	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;

	@ManyToOne(cascade = CascadeType.ALL)
	private Specialist specialist;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
	private List<LeaveRequest> leaveRequests;

}
