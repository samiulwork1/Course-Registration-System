package entity;

public class Course {
    private String courseCode;
    private String courseTitle;
    private int credit;

    public Course() {
        this.courseCode = " ";
        this.courseTitle = " ";
        this.credit = 0;
    }

    public Course(String courseCode, String courseTitle, int credit) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.credit = credit;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public String getCourseDetails() {
        return "Course Code: " + courseCode + "\n" +
                "Course Title: " + courseTitle + "\n" +
                "Credit: " + credit + "\n";
    }
}
