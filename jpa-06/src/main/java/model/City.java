package model;

import javax.persistence.*;

@Entity
@SecondaryTable(
        name = "",
        pkJoinColumns = @PrimaryKeyJoinColumn()
)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private ContactInfo contactInfo;


}
