package com.tyss.bookmydoctor.api.constant;

public class Constant {

	private Constant() {
		@SuppressWarnings("unused")
		String string = "private constructor to hide implicit public one";
	}

	public static final String USER_SAVE_SUCCESS = "User Registered Successfully";
	public static final String USER_LOGIN_SUCCESS = "User Logged In Successfully";

	public static final String ROLE_SAVE_SUCCESS = "Role Saved Successfully";

	public static final String SPECIALIST_SAVE_SUCCESS = "Specialist added Successfully";
	public static final String SPECIALIST_ASSIGN_SUCCESS = "Specialist added Successfully";

	public static final String DOCTOR_LOGIN_SUCCESS = "Doctor Logged In Successfully";
	public static final String DOCTOR_REGISTER_SUCCESS = "Doctor Registration Successfull";
	public static final String DOCTOR_GETALL_SUCCESS = "Successfully Found All Doctors";
	public static final String DOCTOR_SEARCH_SUCCESSS = "Found Doctor Successfully";
	public static final String DOCTOR_LEAVE_REQUEST_SUCCESS = "Leave Requested Successfully";

	public static final String APPOINTMENT_BOOK_SUCCESS = "Your Appointment Booked Successfully";
	public static final String APPOINTMENT_GET_SUCCESS = "Appointment Found Successfully";
	public static final String APPOINTMENT_GETALL_SUCCESS = "Appointments Found Successfully";

	public static final String ADMIN_LOGIN_SUCCESS = "Admin Logged In Successfully";
	public static final String ADMIN_GETALL_LEAVE_SUCCESS = "leaves found Successfully";
	public static final String LEAVE_REQUEST_STATUS_UPDATED = "Successfully updated status";
	public static final String FETCHED = "Successfully Fetched : {}";

}
