package com.employeeApp.EmployeeAPP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.employeedao.EmployeeDAO.Employee;
import com.employeeservice.EmployeeService.EmployeeNotFoundException;
import com.employeeservice.EmployeeService.EmployeeOperations;
import com.employeeservice.EmployeeService.InvalidEmployeeData;
import com.employeeservice.EmployeeService.JDBCEmployeeOperationsImpl;
import static com.employeeservice.EmployeeService.EmployeeUtil.*;
import com.employeeservice.EmployeeService.Sorting;
import com.employeeservice.EmployeeService.EmployeeOp;


public class JDBCEmployeeOperationsApp {
	EmployeeOperations employeeOperations;
	static Connection con;
	public static void main(String[] args) throws ClassNotFoundException {
		JDBCEmployeeOperationsApp jdbcEmployeeOperationsApp = new JDBCEmployeeOperationsApp();
		jdbcEmployeeOperationsApp.initializingTotalNumberOfEmployees();
		jdbcEmployeeOperationsApp.loadEmployeesToDataBase();
		while(true){
			EmployeeOp selectedEmpColumn = EmployeeOp.getselectedEmployeeColoumn();
			switch (selectedEmpColumn) {
			case ADD:
				jdbcEmployeeOperationsApp.addingEmployees();
				break;
			case DELETE:
				jdbcEmployeeOperationsApp.deletingEmployees();
				break;
			case UPDATE :
				jdbcEmployeeOperationsApp.updatingEmployees();			
				break;
			case DISPLAYBYID :
				jdbcEmployeeOperationsApp.displayingEmployeesByID();	
				break;		
			case DISPLAYALL :
				jdbcEmployeeOperationsApp.displayingEmployeesAll();
				break;
			case SORT :
				Sorting selectedSortColumn = Sorting.getSelectedSortedColumn();
				jdbcEmployeeOperationsApp.sortingByColumn(selectedSortColumn);
				break;
			case DISPLAYUNIQUENAMES:
				jdbcEmployeeOperationsApp.displayUniqueNames();
				break;
			case AGEWISECOUNT:
				jdbcEmployeeOperationsApp.ageWiseCount();
				break;
			case EMPLOYEESSERVINGMORETHAN5YEARS:
				jdbcEmployeeOperationsApp.employeesServingMoreThan5Years();
				break;
			case EXIT:
				jdbcEmployeeOperationsApp.exitDataToFile();
				break;
			}
		
		}
		}

	private void initializingTotalNumberOfEmployees() throws ClassNotFoundException{
		employeeOperations = new JDBCEmployeeOperationsImpl();
	
	}
	private void loadEmployeesToDataBase(){
		
		ArrayList<Employee> employees= loadingEmployeesToArray();
		employeeOperations.loadEmployeesToDataBase(employees);
	}


	private void addingEmployees() {
		Employee employee = readingEmployee("insert");
		try{
			employeeOperations.createEmployee(employee);
			System.out.println("Employee Details are added into the DataBase");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

	private void deletingEmployees() {
		int id = detailsForDeletingEmployee();
		boolean x=false;
		try{
		x=employeeOperations.deleteEmployee(id);
		}
		
		catch(EmployeeNotFoundException e){
			System.out.println(e);
		}
		if(x==true){
		System.out.println("Employee Details are Deleted");
		}
		
	}
	private void updatingEmployees() {
		boolean x=false;
		try{
			int empId = detailsForUpdatingEmployee();
			Employee employee=employeeOperations.findEmployee(empId);
			x =employeeOperations.updateEmployee(empId,employee);
		}
		catch(InvalidEmployeeData e){
				System.out.println(e);
			}
		catch(EmployeeNotFoundException e){
				System.out.println("Employee ID is not found for Updation");
			}
		if(x==true){
			System.out.println("Employee Details are Updated");
		}
	}
	private void displayingEmployeesByID() {
		int displayId = detailsForDisplayEmployeeById();
		Employee employeeDisplayById = null;
		try{
			 employeeDisplayById = employeeOperations.findEmployee(displayId);
		}
		catch(EmployeeNotFoundException e){
			System.out.println(e);
		}
		if(employeeDisplayById!=null){
		System.out.println(employeeDisplayById);
		}
		
	}
	private void displayingEmployeesAll() {
	
		List<Employee> employeeDisplayByName = null;
		try{
			employeeDisplayByName = employeeOperations.diaplayAllEmployees();
		}
		catch(Exception e){
			System.out.println(e);
		}
		if(employeeDisplayByName.size()!=0){
			System.out.println("EmployeeNumber EmployeeName EmployeeAge EmployeeSalary");
			for(Employee employee:employeeDisplayByName){
				System.out.println(employee);
			}
		}
		
	}
	private void displayUniqueNames(){
		HashSet<String> employeeNames=employeeOperations.displayUniqueNames();
		System.out.println("UNIQUE NAMES");
		Iterator it = employeeNames.iterator();
		while(it.hasNext()){
			System.out.println(it.next());			
		}
	}	
	private void ageWiseCount() {
		try{
			HashMap<Integer, Integer> hashMap=employeeOperations.ageWiseCount();
			for(Integer i: hashMap.keySet()){
				System.out.println("Age: "+i+" number: "+hashMap.get(i));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void sortingByColumn(Sorting selectedSortColumn){
		try{
		List<Employee> employeeList=employeeOperations.sort(selectedSortColumn);
		for(Employee employee:employeeList){
			System.out.println(employee);
		}
		}catch(Exception e){
		e.printStackTrace();
	}
	}
	private void employeesServingMoreThan5Years(){
		try {
			List<Employee> employeeList=employeeOperations.employeesServingMoreThan5Years();
			for(Employee employee:employeeList){
				System.out.println(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void exitDataToFile(){
		
			System.out.println("Employee Data Copied to the file");
			System.out.println("Good Bye");
			System.exit(0);
		
		
	}

}