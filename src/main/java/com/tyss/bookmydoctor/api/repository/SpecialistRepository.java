package com.tyss.bookmydoctor.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.bookmydoctor.api.entity.Specialist;

public interface SpecialistRepository extends JpaRepository<Specialist, Integer> {

	Optional<Specialist> findBySpeciality(String speciality);
}
