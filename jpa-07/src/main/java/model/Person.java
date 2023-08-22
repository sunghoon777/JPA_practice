package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Person {

    @Id
    private String id;
    private String name;
    @ManyToMany(mappedBy = "cast")
    private Set<Performance> perfs;

    public Person() {
    }

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Performance> getPerfs() {
        return perfs;
    }

    public void setPerfs(Set<Performance> perfs) {
        this.perfs = perfs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId().equals(person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
