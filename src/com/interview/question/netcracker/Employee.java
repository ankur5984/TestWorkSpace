package com.interview.question.netcracker;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    int id;
    Double salary;
    String gender;
    String department;

    public Employee(int id, Double salary, String gender, String department) {
        this.id = id;
        this.salary = salary;
        this.gender = gender;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(salary, employee.salary) && Objects.equals(gender, employee.gender) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salary, gender, department);
    }

    public static void main(String[] args) {
        //1. question character count
        Map<Character, Integer> map = countAndGenerateMap("abbbbbbbbbbbccccccceeeedddknlnljnskjn");
        System.out.println(map);

        //2. employee filter with java 8 how many female employee in organization based on department
        //-- use of Collectors.groupingBy

        List<Employee> employees = getEmployeeList();
        Map<String, Object> patchRequestBody = new HashMap<>();
        patchRequestBody.put("department", "QA");
        patchRequestBody.put("salary", 5000.0);

        System.out.println(handlePatchRequest(1L, patchRequestBody));

        Map<String, Long> count = employees.stream()
                .filter(employee -> "FEMALE".equalsIgnoreCase(employee.getGender()))  // Filter for female employees
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));  // Group by department and count

        System.out.println(count);
        for(Map.Entry entry : count.entrySet()) {
            System.out.println(entry.getKey() + "::" + entry.getValue());
        }

    }

    private static Map<Character, Integer> countAndGenerateMap(String word) {
        char[] chars = word.toCharArray();
        Map<Character, Integer> magicShow = new HashMap<>();
        int count = 1;
        for (char c : chars) {
            if (magicShow.get(c) == null) {
                magicShow.put((c), count);
            } else {
                magicShow.put(c, magicShow.get(c) + 1);
            }
        }
        return magicShow;
    }


    public static List<Employee> getEmployeeList(){
        Employee emp1 = new Employee(1, 2000.2, "MALE", "HR");
        Employee emp2 = new Employee(2, 3000.2, "MALE", "HR");
        Employee emp3 = new Employee(3, 4000.2, "FEMALE", "ENGINEERING");
        Employee emp4 = new Employee(4, 5000.2, "MALE", "HR");
        Employee emp5 = new Employee(5, 6000.2, "FEMALE", "ENGINEERING");
        Employee emp6 = new Employee(6, 7000.2, "MALE", "HR");

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);

        return employees;
    }


    public static List<Employee> handlePatchRequest(Long id, Map<String, Object> fieldsMap) {
        Employee existingEmployee = Employee.getEmployeeList().stream().filter(employee1 -> employee1.getId() == (id)).findFirst().orElse(null);
        List<Employee> employees = getEmployeeList();
        int index = employees.indexOf(existingEmployee);
        fieldsMap.forEach((key,value) -> {
            try {
                Field field = Employee.class.getDeclaredField(key);
                field.setAccessible(Boolean.TRUE);
                if(field.getType().isAssignableFrom(value.getClass())) {
                   field.set(existingEmployee, value);
                } else {
                    System.out.println("wrong type -> "+ key+" : "+value);
                }
                field.setAccessible(Boolean.FALSE);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        employees.set(index, existingEmployee);
       return employees;

    }
// LIST OF QUESTIONS:
    // hash map life cycle equals()
    // spring life cycle
    // Multithreading dead lock how to resolve it
    // Arraylist default size 10
    // 2 above questions
    // project questions
    // architecture
    // jwt body structure HEADER, PAYLOAD, SIGNATURE
}
