package model;

import javax.persistence.*;

@Entity
public class Test {
    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private Address address;


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

}
