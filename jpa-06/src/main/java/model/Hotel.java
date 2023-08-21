package model;

import javax.persistence.*;

@Entity
@SecondaryTable(
        name = "hotel_detail",
        pkJoinColumns =
        @PrimaryKeyJoinColumn(name = "hotelId", referencedColumnName = "id")
)
public class Hotel {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",column = @Column(table = "hotel_detail")),
            @AttributeOverride(name = "address1",column = @Column(table = "hotel_detail")),
            @AttributeOverride(name = "address2",column = @Column(table = "hotel_detail"))
    })
    private Address korAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",column = @Column(name = "eng_zipcode", table = "hotel_detail")),
            @AttributeOverride(name = "address1",column = @Column(name = "eng_address1", table = "hotel_detail")),
            @AttributeOverride(name = "address2",column = @Column(name = "eng_address2", table = "hotel_detail"))
    })
    private Address engAddress;


    protected Hotel() {
    }

    public Hotel(String id, String name, Grade grade, Address korAddress, Address engAddress) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.korAddress = korAddress;
        this.engAddress = engAddress;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public Address getKorAddress() {
        return korAddress;
    }

    public Address getEngAddress() {
        return engAddress;
    }
}
