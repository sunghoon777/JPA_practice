package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {

    @Id
    @TableGenerator(
            name = "review_table_gen",
            table = "id_gen",
            pkColumnName = "entity",
            pkColumnValue = "review",
            valueColumnName = "nextid",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(generator = "review_table_gen")
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Review() {
    }

    public Review(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
