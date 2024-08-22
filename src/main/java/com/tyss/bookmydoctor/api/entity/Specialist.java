package com.tyss.bookmydoctor.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Specialist.class)
public class Specialist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String speciality;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "specialist")
	private List<Doctor> doctorsList;

}
