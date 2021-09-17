package sample.java.Model;

public class Course {

    private String courseName;
    private String courseId;
    private String term;
    private String professorId;
    private String taId;

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseid) {
        this.courseId = courseid;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getProfessorId() {
        return professorId;
    }
    public void setProfessorId(String professorid) {
        this.professorId = professorid;
    }
    public String getTaId() {
        return taId;
    }
    public void setTaId(String taid) {
        this.taId = taid;
    }
}
