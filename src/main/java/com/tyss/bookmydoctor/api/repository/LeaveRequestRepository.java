package com.tyss.bookmydoctor.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.bookmydoctor.api.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

	Optional<LeaveRequest> findByIdAndStatus(Integer id, String status);

}
