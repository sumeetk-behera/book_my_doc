package com.tyss.bookmydoctor.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {

	@JsonIgnore
	private int id;

	@NotBlank
	private LocalDate fromDate;

	@NotBlank
	private LocalDate toDate;

	@NotBlank
	private String reason;

	@JsonIgnore
	private String status;

	@NotBlank
	private String doctorName;
}
