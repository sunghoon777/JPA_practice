package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Test {
    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    protected Test() {
        System.out.println("test create");
    }

    public Test(Grade grade) {
        this.grade = grade;
    }


    public int getId() {
        return id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
