package application;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;


public class Program2 {
	public static void main(String[] args) {
		
		DepartmentDAO depDao = DAOFactory.createDepartmentDao(); 
		
		System.out.println("=== TEST 1: department findById ===");
		Department department = depDao.findById(3);
		System.out.println(department);
	}
}
