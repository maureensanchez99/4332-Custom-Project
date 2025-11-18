package com.example.scheduler;

public class Course {
    private String name;
    private String time;

    public Course() {}

    public Course(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() { return name; }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return name + " (" + time + ")";
    }
}
