package com.tyss.bookmydoctor.api.constant;

public class ExceptionConstant {

	private ExceptionConstant() {
	}

	public static final String USER_FOUND = "User Already present, please login";
	public static final String USER_NOT_FOUND = "User Registered Successfully";
	public static final String LOGIN_FAILED = "failed to load user, please check your crendentials and try again";

	public static final String ROLE_NOT_FOUND = "No Role present in the database";
	public static final String ROLE_FOUND = "Role already present in database";

	public static final String DOCTOR_FOUND = "Doctor Already registered, please login";
	public static final String DOCTOR_NOT_FOUND = "Doctor Not Available, Please try again";
//	public static final String DOCTOR_LOGIN_FAILED = "failed to load doctor, please check your crendentials and try again";
	public static final String DOCTOR_LEAVEREQUEST_FAILED = "failed to load Leave Request, please try again";

	public static final String SPECIALITY_FOUND = "Speciality already present in database";
	public static final String SPECIALITY_NOT_FOUND = "No Speciality present in the database";

	public static final String APPOINTMENT_BOOK_FAILED = "failed to book Appoinment, please try again";
	public static final String APPOINTMENT_NOT_FOUND = "No Appointments, please try again";

//	public static final String ADMIN_LOGIN_FAILED = "failed to load AdminAccount, please check your crendentials and try again";
	public static final String NO_LEAVE_REQUEST = "No leave requests, please try again";
	public static final String LEAVE_REQUEST_FAILED = "No leave requests, please try again";
	public static final String LEAVE_REQUEST_STATUS = "Already Updated the status, Expired";

	public static final String SOMETHING_WENT_WRONG = "Please try again";
	public static final String ACCOUNT_NOT_FOUND = "No Account found with this ID, please check and try again";
	public static final String EXCEPTION_RAISED = "Throwed Exception in else block";


}
