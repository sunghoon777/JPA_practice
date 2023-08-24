package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")

@NamedQueries({
        @NamedQuery(name = "User.all", query = "select u from User u"),
        @NamedQuery(name = "User.findByName", query = "select u from User u where u.name = :name")
})

public class User {

    @Id
    private String email;
    private String name;
    @Column(name = "create_date")
    private Date createDate;
    @OneToOne(mappedBy = "owner")
    private MembershipCard membershipCard;

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

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public String getNickName() {
        return nickName;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public void issue(MembershipCard membershipCard){
        membershipCard.assignTo(this);
        this.membershipCard = membershipCard;
    }
}
