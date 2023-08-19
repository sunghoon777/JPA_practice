package model;

import javax.persistence.*;

@Entity
@SecondaryTable(
        name = "sight_detail",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "sight_id", referencedColumnName = "id")
)
public class Sight {

    @Id
    private Long id;
    private String name;
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",column = @Column(name = "kr_zipcode")),
            @AttributeOverride(name = "address1",column = @Column(name = "kr_address1")),
            @AttributeOverride(name = "address2",column = @Column(name = "kr_address2"))
    })
    private Address address;
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode",column = @Column(name = "eng_zipcode")),
            @AttributeOverride(name = "address1",column = @Column(name = "eng_address1")),
            @AttributeOverride(name = "address2",column = @Column(name = "eng_address2"))
    })
    private Address engAddress;
    private SightDetail sightDetail;

    public Sight() {
    }

    public Sight(Long id, String name, Address address, Address engAddress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.engAddress = engAddress;
    }

    public void setSightDetail(SightDetail sightDetail) {
        this.sightDetail = sightDetail;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Address getEngAddress() {
        return engAddress;
    }

    public SightDetail getSightDetail() {
        return sightDetail;
    }
}
