package com.jimi.javase.util.Stream;

import com.jimi.javase.util.Stream.po.Department;
import com.jimi.javase.util.Stream.po.Employee;
import com.jimi.javase.util.Stream.po.Person;
import com.jimi.javase.util.Stream.po.Student;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsTest {

    public static List<Person> peoples = new ArrayList<>();
    public static List<String> things = new ArrayList<>();
    public static List<Employee> employees = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();

    private static final Integer PASS_THRESHOLD = 60;

    static {
        Person person1 = new Person("jimi");
        Person person2 = new Person("tom");
        Person person3 = new Person("amoy");
        peoples.add(person1);
        peoples.add(person2);
        peoples.add(person3);

        things.add("A");
        things.add("day");
        things.add("gd");
        things.add("a");
        Department department1 = new Department("dev");
        Department department2 = new Department("test");

        Employee employee1 = new Employee(department1, 100);
        Employee employee2 = new Employee(department1, 200);
        Employee employee3 = new Employee(department2, 900);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Student student1 = new Student(90);
        Student student2 = new Student(100);
        Student student3 = new Student(50);
        students.add(student1);
        students.add(student2);
        students.add(student3);
    }



    public static void testCollectMap() {
        // Accumulate names into a List
        List<String> list = peoples.stream().map(Person::getName).collect(Collectors.toList());

        System.out.println(list);
        // Accumulate names into a TreeSet
        Set<String> set = peoples.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);
        // Convert elements to strings and concatenate them, separated by commas
        String joined = things.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println(joined);

    }

    public static void testCollectPartition() {
        // Partition students into passing and failing
        Map<Boolean, List<Student>> passingFailing =
                students.stream()
                        .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
    }

    public static void testCollectGroup() {
        // Group employees by department
        Map<Department, List<Employee>> byDept
                = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        // Compute sum of salaries by department
        Map<Department, Integer> totalByDept
                = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.summingInt(Employee::getSalary)));
    }

    public static void testCollectSum() {
        // Compute sum of salaries of employee
        int total = employees.stream()
                .collect(Collectors.summingInt(Employee::getSalary));
    }


    public static void main(String[] args) {
        testCollectMap();
        testCollectGroup();
        testCollectSum();
        testCollectPartition();
    }
}
