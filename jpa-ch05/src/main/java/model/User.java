package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    private String email;
    private String name;
    @Column(name = "create_date")
    private Date createDate;

    transient private String nickName;

    protected User(){

    }

    public User(String email, String name, Date createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void changeName(String newName){
        this.name = newName;
    }

}
