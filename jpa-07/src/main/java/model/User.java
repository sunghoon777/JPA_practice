package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    private String email;
    private String name;
    @Column(name = "create_date")
    private Date createDate;
    @ElementCollection
    @CollectionTable(
            name = "user_keyword",
            joinColumns = @JoinColumn(name = "user_email")
    )
    @Column(name = "keyword")
    private Set<String> keywords;

    protected User(){

    }

    public User(String email, String name, Date createDate, Set<String> keywords) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
        this.keywords = keywords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
