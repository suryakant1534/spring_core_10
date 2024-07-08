import com.console_color.ConsoleColors;
import com.spring.controller.Controller;
import com.spring.dto.EmployeeDto;
import com.spring.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        var applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println(getCommands(ConsoleColors.TEXT_GREEN, ConsoleColors.TEXT_BLUE));
            System.out.print(ConsoleColors.TEXT_YELLOW + "WRITE A COMMAND: " + ConsoleColors.TEXT_RESET);
            input = scanner.nextLine();
            String command = input.trim().toLowerCase();
            switch (command) {
                case "insert":
                    insert(applicationContext, scanner);
                    break;
                case "update":
                    update(applicationContext, scanner);
                    break;
                case "delete":
                    delete(applicationContext, scanner);
                    break;
                case "read":
                    read(applicationContext, scanner);
                    break;
                case "exit":
                    System.out.println(ConsoleColors.TEXT_CYAN + "Thank you for using Spring Boot Application:)");
                    break;
                default:
                    System.err.println('\'' + input + "' command not recognized");
            }
        } while (!input.trim().equalsIgnoreCase("exit"));
        scanner.close();
        applicationContext.close();
    }

    private static String getCommand(String contentColor, String commandColor, String command, boolean isComma) {
        return "'" + commandColor + command + contentColor + "'" + getLast(isComma);
    }

    static private String getLast(boolean isComma) {
        return isComma ? ", " : " ]";
    }

    static String getCommands(String contentColor, String commandColor) {
        return contentColor + "command: [ " + getLisOfCammand(contentColor, commandColor) + ConsoleColors.TEXT_RESET;
    }

    private static String getLisOfCammand(String contentColor, String commandColor) {
        return    getCommand(contentColor, commandColor, "insert", true)
                + getCommand(contentColor, commandColor, "update", true)
                + getCommand(contentColor, commandColor, "delete", true)
                + getCommand(contentColor, commandColor, "read", true)
                + getCommand(contentColor, commandColor, "exit", false);
    }

    static private void insert(ApplicationContext context, Scanner scanner) {
        System.out.print("Enter Employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Employee email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Employee phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Employee sallary: ");
        double sallary = scanner.nextDouble();
        System.out.print("Enter Employee age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        EmployeeDto dto = new EmployeeDto(name, age, address, phone, email, sallary);

        Controller controller = (Controller) context.getBean("controller");
        if (!controller.isConnectionClosed()) controller.insertEmployee(dto);
        else System.err.println("Connection is closed");
    }
    static private void update(ApplicationContext context, Scanner scanner) {
        System.out.print("Enter Employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Controller controller = (Controller) context.getBean("controller");
        Employee employee = controller.getEmployeeById(id);
        System.out.println(ConsoleColors.TEXT_RED + ConsoleColors.TEXT_BG_GREEN + "Help: if you want to update the data then please write 'yes'." + ConsoleColors.TEXT_RESET);
        System.out.print(ConsoleColors.TEXT_YELLOW + "Employee name is " + employee.getName() + " want to change: " + ConsoleColors.TEXT_RESET);
        String choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("yes")) {
            System.out.print("Enter Employee new name: ");
            employee.setName(scanner.nextLine());
        }
        System.out.print(ConsoleColors.TEXT_YELLOW + "Employee age is " + employee.getAge() + " want to change: " + ConsoleColors.TEXT_RESET);
        choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("yes")) {
            System.out.print("Enter Employee new age: ");
            employee.setAge(scanner.nextInt());
            scanner.nextLine();
        }
        System.out.print(ConsoleColors.TEXT_YELLOW + "Employee adress is " + employee.getAddress() + " want to change: " + ConsoleColors.TEXT_RESET);
        choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("yes")) {
            System.out.print("Enter Employee new address: ");
            employee.setAddress(scanner.nextLine());
        }
        System.out.print(ConsoleColors.TEXT_YELLOW + "Employee phone no is " + employee.getPhone() + " want to change: " + ConsoleColors.TEXT_RESET);
        choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("yes")) {
            System.out.print("Enter Employee new phone: ");
            employee.setPhone(scanner.nextLine());
        }
        System.out.print(ConsoleColors.TEXT_YELLOW + "Employee email is " + employee.getEmail() + " want to change: " + ConsoleColors.TEXT_RESET);
        choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("yes")) {
            System.out.print("Enter Employee new email: ");
            employee.setEmail(scanner.nextLine());
        }
        System.out.print(ConsoleColors.TEXT_YELLOW + "Employee sallary is " + employee.getSalary() + " want to change: " + ConsoleColors.TEXT_RESET);
        choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("yes")) {
            System.out.print("Enter Employee new salary: ");
            employee.setSalary(scanner.nextDouble());
        }
        EmployeeDto dto = new EmployeeDto(employee.getName(), employee.getAge(), employee.getAddress(), employee.getPhone(), employee.getEmail(), employee.getSalary());
        dto.setId(employee.getId());
        controller.updateEmployee(dto);
    }

    static private void delete(ApplicationContext context, Scanner scanner) {
        System.out.print("Enter Employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Controller controller = (Controller) context.getBean("controller");
        controller.deleteEmployee(id);
    }
    static private void read(ApplicationContext context, Scanner scanner) {
        System.out.print(ConsoleColors.TEXT_YELLOW + "Do you want to read by id: " + ConsoleColors.TEXT_RESET);
        String choice = scanner.nextLine().trim().toLowerCase();
        Controller controller = (Controller) context.getBean("controller");
        if (choice.equals("yes")) {
            System.out.print("Enter Employee id: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            var emp = controller.getEmployeeById(id);
            System.out.println(emp.toString());
        } else {
            controller.getAllEmployees().forEach(System.out::println);
        }
    }

    static String getMessage(String messageColor, String message) {
        return messageColor + message + ConsoleColors.TEXT_RESET;
    }
}
