package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;


public class Program2 {
	public static void main(String[] args) {
		
		DepartmentDAO depDao = DAOFactory.createDepartmentDao(); 
		
		System.out.println("=== TEST 1: department findById ===");
		Department department = depDao.findById(3);
		System.out.println(department);
		
		System.out.println("\n=== TEST 2: department findAll ===");
		List<Department> list = depDao.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: seller insert ===");
		Department newDepartment = new Department(null, "Magalu");
		depDao.insert(newDepartment);
		System.out.println("Inserted! New ID = " + newDepartment.getId());
		
		System.out.println("\n=== TEST 4: department update ===");
		department = depDao.findById(3);
		department.setName("RedeCompras");
		depDao.update(department);
		System.out.println("Update complete");
	}
}
