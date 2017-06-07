package com.employeeservice.EmployeeService;

public class EmployeeNotFoundException extends RuntimeException{

	public EmployeeNotFoundException(String message){
		super(message);
	}
	public EmployeeNotFoundException(){
		super();
	}
	public EmployeeNotFoundException(String message, Throwable arg1){
		super(message,arg1);
	}
}
