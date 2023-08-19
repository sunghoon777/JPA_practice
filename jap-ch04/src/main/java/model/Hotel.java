package model;

import javax.persistence.*;

@Entity
public class Hotel {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",column = @Column(name = "kr_zipcode")),
            @AttributeOverride(name = "address1",column = @Column(name = "kr_address1")),
            @AttributeOverride(name = "address2",column = @Column(name = "kr_address2"))
    })
    private Address address;
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",column = @Column(name = "eng_zipcode")),
            @AttributeOverride(name = "address1",column = @Column(name = "eng_address1")),
            @AttributeOverride(name = "address2",column = @Column(name = "eng_address2"))
    })
    private Address engAddress;

    public Hotel() {
    }

    public Hotel(String id, String name, Grade grade, Address address, Address engAddress) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.address = address;
        this.engAddress = engAddress;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public Address getAddress() {
        return address;
    }

    public Address getEngAddress() {
        return engAddress;
    }
}
