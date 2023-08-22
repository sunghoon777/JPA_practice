package model;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Hotel {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private Address address;
    @ElementCollection
    @CollectionTable(
            name = "hotel_property",
            joinColumns = @JoinColumn(name = "hotel_id")
    )
    @MapKeyColumn(name = "prop_name")
    @Column(name = "prop_value")
    private Map<String,String> properties;

    public Hotel(String id, String name, Grade grade, Address address) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.address = address;
    }

    public Hotel() {
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

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
