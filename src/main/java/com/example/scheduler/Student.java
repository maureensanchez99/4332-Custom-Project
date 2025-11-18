package com.example.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Student() {}

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<Course> getCourses() { return courses; }

    public void addCourse(Course c) {
        courses.add(c);
    }

    @Override
    public String toString() {
        return name + " -> " + courses.toString();
    }
}
