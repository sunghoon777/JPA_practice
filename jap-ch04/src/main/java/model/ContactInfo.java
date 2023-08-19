package model;

import javax.persistence.*;

@Embeddable
public class ContactInfo {

    @Column(name = "city_phone")
    private String phone;
    @Column(name = "city_email")
    private String email;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",column = @Column(name = "city_zipcode")),
            @AttributeOverride(name = "address1",column = @Column(name = "city_address1")),
            @AttributeOverride(name = "address2",column = @Column(name = "city_address2"))
    })
    private Address address;

}
