package com.employeeservice.EmployeeService;

import java.util.Scanner;

public enum Sorting{
	SORTBYID(1),SORTBYSALARY(2);
	
	int sortOption;
	Sorting(int sortOption){
		this.sortOption = sortOption;
	}
	public static Sorting getSelectedSortedColumn(){
		Sorting slectedSortedColoumn;
		for(Sorting sortOperations: Sorting.values()){
			System.out.println(sortOperations.sortOption+" : "+sortOperations.name());
		}
		System.out.println("Select the column: ");
		int selectedIndex = new Scanner(System.in).nextInt();

		slectedSortedColoumn = getSortColumn(selectedIndex);
		return slectedSortedColoumn;
	}
	static Sorting getSortColumn(int givenIndex) {
		//Department departmentNumber;

		for(Sorting sortOperations : Sorting.values()) {
			if (sortOperations.sortOption == givenIndex) {
				return sortOperations;
			}
		}

		return null;
	}
	
}
