import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int employeeId;
    private String name;
    private String designation;
    private double salary;

    public Employee(int employeeId, String name, String designation, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name +
               ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_NAME = "employees.ser";
    private List<Employee> employees;

    public EmployeeManagementSystem() {
        employees = loadEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployees();
        System.out.println("Employee added successfully!");
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    private void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Employee> loadEmployees() {
        List<Employee> empList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            empList = (List<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found; returning empty list
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }
        return empList;
    }

    public static void menubased(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    Employee employee = new Employee(id, name, designation, salary);
                    ems.addEmployee(employee);
                    break;

                case 2:
                    ems.displayAllEmployees();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
