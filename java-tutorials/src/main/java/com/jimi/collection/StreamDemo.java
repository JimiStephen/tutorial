package com.jimi.collection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/3/28 13:49
 */
public class StreamDemo {
    private final static String NAMECHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        List<Person> persons = getData(10);
        Stream2List(persons);
        System.out.println(persons);
        Stream2PartList(persons);
        Stream2Map(persons);
        Stream2Sort(persons);
    }

    public static void Stream2List(List<Person> persons) {
        Stream<Person> stream = persons.stream();
//        stream = Stream.of();
        stream.collect(Collectors.toList());
    }

    public static void Stream2PartList(List<Person> persons) {
       List<Person> partPersons =  persons.stream().filter(person -> {
            if(person.getAge() > 50) {
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        System.out.println(partPersons);
    }

    public static void Stream2Sort(List<Person> persons) {
       List<Person> partPersons =  persons.stream()
               .sorted(Comparator.comparing(person -> person,(personA,personB) -> {
                   int ageA = personA.getAge();
                   int ageB = personB.getAge();
                   if(ageA > ageB){
                       return 1;
                   }else{
                       return -1;
                   }
               }))
               .collect(Collectors.toList());
        System.out.println(partPersons);
    }

    public static void Stream2Map(List<Person> persons) {
        Map<String,Person> mapPersons =  persons.stream().collect(Collectors.toMap(person -> person.getName(), person -> person));
        System.out.println(mapPersons);
    }

    public static List<Person> getData(int num) {
        int count = 10;
        if (num > 0) {
            count = num;
        }
        List<Person> personList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int start = random.nextInt(NAMECHARS.length());
            int end = start + 5 >= NAMECHARS.length() ? NAMECHARS.length() : start + 5;
            int age = random.nextInt(100);
            String name = NAMECHARS.substring(start, end);
            Person person = new Person(name, age);
            personList.add(person);

        }

        return personList;
    }
}


class Person {
    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("name[%s]age[%s]", name, age);
    }
}
