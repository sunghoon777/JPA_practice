package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "membership_card")
public class MembershipCard {
    @Id
    @Column(name = "card_number")
    private String number;
    @OneToOne
    @JoinColumn(name = "user_email")
    private User owner;
    @Column(name = "expiry_date")
    private Date expiryDate;
    private boolean enabled;

    public MembershipCard() {
    }

    public MembershipCard(String number, User owner, Date expiryDate) {
        this.number = number;
        this.owner = owner;
        this.expiryDate = expiryDate;
        this.enabled = true;
    }

    public String getNumber() {
        return number;
    }

    public User getOwner() {
        return owner;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void assignTo(User owner){
        if(this.owner != null){
            throw new RuntimeException();
        }
        this.owner = owner;
    }

    public void disable(){
        this.enabled = false;
    }
}
