package com.tyss.bookmydoctor.api.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.bookmydoctor.api.exception.AccountNotFoundException;
import com.tyss.bookmydoctor.api.exception.AppointmentNotBookedException;
import com.tyss.bookmydoctor.api.exception.DoctorNotFoundException;
import com.tyss.bookmydoctor.api.exception.DoctorPresentException;
import com.tyss.bookmydoctor.api.exception.LeaveRequestAlreadyApproved;
import com.tyss.bookmydoctor.api.exception.LeaveRequestFailedException;
import com.tyss.bookmydoctor.api.exception.LeaveRequestNotFoundException;
import com.tyss.bookmydoctor.api.exception.LoginFailedException;
import com.tyss.bookmydoctor.api.exception.NoAppointmentsFoundException;
import com.tyss.bookmydoctor.api.exception.RoleFoundException;
import com.tyss.bookmydoctor.api.exception.RoleNotFoundException;
import com.tyss.bookmydoctor.api.exception.SomethingWentWrongException;
import com.tyss.bookmydoctor.api.exception.SpecialistAlreadyPresentException;
import com.tyss.bookmydoctor.api.exception.SpecialistNotAddedException;
import com.tyss.bookmydoctor.api.exception.SpecialityNotFoundException;
import com.tyss.bookmydoctor.api.exception.UserFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AppointmentNotBookedException.class)
	public ResponseEntity<?> appointmentNotBook(AppointmentNotBookedException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(NoAppointmentsFoundException.class)
	public ResponseEntity<?> appointmentNotFound(NoAppointmentsFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<?> doctorNotFound(DoctorNotFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(DoctorPresentException.class)
	public ResponseEntity<?> doctorFound(DoctorPresentException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(RoleFoundException.class)
	public ResponseEntity<?> roleFound(RoleFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<?> roleNotFound(RoleNotFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(SpecialistAlreadyPresentException.class)
	public ResponseEntity<?> specialistFound(SpecialistAlreadyPresentException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(SpecialistNotAddedException.class)
	public ResponseEntity<?> specialistNotAdded(SpecialistNotAddedException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(SpecialityNotFoundException.class)
	public ResponseEntity<?> specialistNotFound(SpecialityNotFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> userFound(UserFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(SomethingWentWrongException.class)
	public ResponseEntity<?> wentWrong(SomethingWentWrongException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<?> loginFailed(LoginFailedException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> noAccount(AccountNotFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(LeaveRequestNotFoundException.class)
	public ResponseEntity<?> noLeave(LeaveRequestNotFoundException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(LeaveRequestFailedException.class)
	public ResponseEntity<?> leaveFailed(LeaveRequestFailedException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

	@ExceptionHandler(LeaveRequestAlreadyApproved.class)
	public ResponseEntity<?> leaveApproved(LeaveRequestAlreadyApproved exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());

	}

}
