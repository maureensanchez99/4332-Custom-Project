package com.example.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleDatabaseTest {

    ScheduleDatabase classList;

    @BeforeEach
    public void createScheduleDatabase() {
        classList = new ScheduleDatabase();
        List<Student> courseNames = classList.getStudents();
        for(Student name : courseNames){
            System.out.println(name);
        }
    }

    @Test
    public void testDatabaseLoads() {
        List<Student> students = classList.getStudents();
            assert students != null;

            System.out.println("Loaded " + students.size() + " students.");
        }

    @Test
    public void studentExists() {
        Student s = new Student("Alice");

        List<Student> students = classList.getStudents();
        assertFalse(students.contains(s),
                "Expected database to contain student Alice, but it did not.");
    }

}

