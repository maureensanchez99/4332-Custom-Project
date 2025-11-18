package com.example.scheduler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleDatabase {
    private static final String DB_FILE = "src/main/resources/schedules.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Student> students = new ArrayList<>();

    ScheduleDatabase() {
        load();
    }

    public void addStudent(Student s) {
        students.add(s);
        save();
    }

    public List<Student> getStudents() { return students; }

    public List<Student> searchByCourse(String query) {
        return students.stream()
                .filter(s -> s.getCourses().stream().anyMatch(c ->
                        c.getName().equalsIgnoreCase(query) || c.getTime().contains(query)))
                .collect(Collectors.toList());
    }

    private void load() {
        File f = new File(DB_FILE);
        if (!f.exists()) {
            save();
            return;
        }
        try {
            students = mapper.readValue(f, new TypeReference<List<Student>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_FILE), students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
