package com.example.scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static ScheduleDatabase db = new ScheduleDatabase();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Schedule Database ---");
            System.out.println("1. Load schedules from file");
            System.out.println("2. Add student manually");
            System.out.println("3. Search by course or time");
            System.out.println("4. List all students");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            switch (sc.nextLine()) {
                case "1" -> loadFromFile(sc);
                case "2" -> addManual(sc);
                case "3" -> search(sc);
                case "4" -> listAll();
                case "5" -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private static void loadFromFile(Scanner sc) {
        System.out.print("Enter filename: ");
        String file = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length < 3) continue;

                String name = p[0];
                Student s = new Student(name);
                for (int i = 1; i < p.length; i += 2) {
                    if (i + 1 >= p.length) break;
                    s.addCourse(new Course(p[i], p[i+1]));
                }
                db.addStudent(s);
            }
            System.out.println("Loaded from file.");
        } catch (IOException e) {
            System.out.println("Cannot read file.");
        }
    }

    private static void addManual(Scanner sc) {
        System.out.print("Student name: ");
        Student s = new Student(sc.nextLine());

        while (true) {
            System.out.print("Course name (or blank to stop): ");
            String name = sc.nextLine();
            if (name.isEmpty()) break;

            System.out.print("Time: ");
            String time = sc.nextLine();

            s.addCourse(new Course(name, time));
        }

        db.addStudent(s);
        System.out.println("Saved.");
    }

    private static void search(Scanner sc) {
        System.out.print("Search keyword (course or time): ");
        db.searchByCourse(sc.nextLine()).forEach(System.out::println);
    }

    private static void listAll() {
        db.getStudents().forEach(System.out::println);
    }
}
