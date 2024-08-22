package com.tyss.bookmydoctor.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tyss.bookmydoctor.api.entity.Doctor;
import com.tyss.bookmydoctor.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

	@JsonIgnore
	private int appointmentId;

	@NotBlank
	private String patientName;

	@NotBlank
	private int age;

	@NotBlank
	@Size(min = 10)
	private long phoneNumber;

	@NotBlank
	private String email;

	@NotBlank
	@Pattern(regexp = "Dr")
	private String doctorName;

	@NotBlank
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@CreatedDate
	private LocalDate createdDate;

	@NotBlank
//	@Value("SUNDAY WE ARE CLOSED")
	private String appointmentDate;

	private String time;

	@JsonIgnore
	private Doctor doctor;

	@JsonIgnore
	private User user;
}
