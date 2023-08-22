package model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Performance {

    @Id
    private String id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "perf_person",
            joinColumns = @JoinColumn(name = "performance_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> cast;

    public Performance() {
    }

    public Performance(String id, String name, Set<Person> cast) {
        this.id = id;
        this.name = name;
        this.cast = cast;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Person> getCast() {
        return cast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Performance)) return false;
        Performance that = (Performance) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
