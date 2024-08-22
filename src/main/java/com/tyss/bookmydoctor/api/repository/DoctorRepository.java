package com.tyss.bookmydoctor.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.bookmydoctor.api.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	Optional<Doctor> findByName(String name);

	Optional<List<Doctor>> findByLocationOrSpeciality(String location, String speciality);

	Optional<Doctor> findByEmail(String email);

	Optional<Doctor> findByEmailAndPassword(String email, String password);
}
