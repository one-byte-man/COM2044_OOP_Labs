package Lab5;

import java.io.*;
import java.util.*;


// NOTE (To the teacher): Eclipse doesn't accept classes in the same file, so I had to upload them separately.

public class StudentAnalyzer {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java Lab5.StudentAnalyzer input.csv output.csv");
            return;
        }

        String inputPath = args[0];
        String outputPath = args[1];


        List<Student> students = readStudentsFromCSV(inputPath);
        if (students.isEmpty()) {
            System.err.println("No data to process. Exiting.");
            return;
        }


        writeResultsToCSV(outputPath, students);


        printSummary(students);


        promptPerformanceSummary(students);
    }

    public static List<Student> readStudentsFromCSV(String path) {
        List<Student> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isHeader = true;
            
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; 
                }
                
                String[] values = line.split(",");
                if (values.length == 4) {
                    try {
                        int id = Integer.parseInt(values[0].trim());
                        String name = values[1].trim();
                        double midterm = Double.parseDouble(values[2].trim());
                        double finalExam = Double.parseDouble(values[3].trim());
                        list.add(new Student(id, name, midterm, finalExam));
                    } catch (NumberFormatException e) {
                        System.err.println("Parsing error on line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return list;
    }

    public static void writeResultsToCSV(String path, List<Student> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println("ID,Name,Average,Status");
            for (Student s : list) {
                pw.printf(Locale.US, "%d,%s,%.1f,%s%n", s.getId(), s.getName(), s.computeAverage(), s.determineStatus());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void printSummary(List<Student> list) {
        int passCount = 0;
        Student topPerformer = null;

        for (Student s : list) {
            if (s.determineStatus().equals("PASS")) passCount++;
            
            if (topPerformer == null || s.computeAverage() > topPerformer.computeAverage()) {
                topPerformer = s;
            }
        }

        System.out.println("--- PERFORMANCE SUMMARY ---");
        System.out.println("Total students processed: " + list.size());
        System.out.println("Number of students passed: " + passCount);
        if (topPerformer != null) {
            System.out.printf("Top performer: %s (Score: %.1f)%n", topPerformer.getName(), topPerformer.computeAverage());
        }
        System.out.println("---------------------------\n");
    }

    public static void promptPerformanceSummary(List<Student> list) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Would you like to see the best performing or worst performing students?");
        String response = scanner.nextLine().trim();
        
        if (!response.equalsIgnoreCase("yes")) {
            System.out.println("Exiting program.");
            return;
        }

        System.out.println("How many of those would you like to see? You may also provide a percentage (e.g., 25%).");
        String limitInput = scanner.nextLine().trim();
        
        int count = 0;
        try {
            if (limitInput.endsWith("%")) {
                double percentage = Double.parseDouble(limitInput.replace("%", "")) / 100.0;
                count = (int) Math.ceil(list.size() * percentage);
            } else {
                count = Integer.parseInt(limitInput);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format. Showing 1 student by default.");
            count = 1;
        }

        count = Math.max(1, Math.min(count, list.size()));

        System.out.println("Show worst or best? (Type 'worst' or 'best')");
        String type = scanner.nextLine().trim().toLowerCase();

        List<Student> sortedList = new ArrayList<>(list);
        if (type.equals("best")) {
            sortedList.sort(Comparator.comparingDouble(Student::computeAverage).reversed());
        } else if (type.equals("worst")) {
            sortedList.sort(Comparator.comparingDouble(Student::computeAverage));
        } else {
            System.out.println("Invalid selection. Exiting.");
            return;
        }

        System.out.println("\n--- " + type.toUpperCase() + " " + count + " STUDENTS ---");
        for (int i = 0; i < count; i++) {
            Student s = sortedList.get(i);
            System.out.printf("ID: %d | Name: %s | Average: %.1f | Status: %s%n", 
                    s.getId(), s.getName(), s.computeAverage(), s.determineStatus());
        }
    }
}