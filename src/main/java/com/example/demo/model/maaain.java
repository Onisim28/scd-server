package com.example.demo.model;

public class maaain {
    public static void main(String[] args) {

        String text = "John.Davidson/Belgrade Michael.Barton/Krakow Ivan.Perkinson/Moscow";
        String[] array = text.split("[ ./]");

        Person[] persons = new Person[array.length / 3];

        for (int i = 0; i < array.length; i += 3) {
            persons[i / 3] = new Person(array[i], array[i + 1], array[i + 2]);
        }

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i].firstName + " " + persons[i].lastName + " " + persons[i].birthPlace);
        }


    }
}
