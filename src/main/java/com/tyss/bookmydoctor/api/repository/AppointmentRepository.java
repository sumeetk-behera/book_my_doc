package com.tyss.bookmydoctor.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.bookmydoctor.api.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> findByDoctorId(Integer id);

}
