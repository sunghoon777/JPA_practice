package jpa.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    private String email;
    private String name;
    @Column(name = "create_date")
    private Date createDate;
    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    private MembershipCard membershipCard;

    protected User(){

    }

    public User(String email, String name, Date createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }
}
