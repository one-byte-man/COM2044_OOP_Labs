package Lab5;

public class Student {
    private int id;
    private String name;
    private double midterm;
    private double finalExam;

    public Student(int id, String name, double midterm, double finalExam) {
        this.id = id;
        this.name = name;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }

    public double computeAverage() {
        return (0.4 * midterm) + (0.6 * finalExam);
    }

    public String determineStatus() {
        return computeAverage() >= 60.0 ? "PASS" : "FAIL";
    }

    public String getName() { return name; }
    public int getId() { return id; }
}