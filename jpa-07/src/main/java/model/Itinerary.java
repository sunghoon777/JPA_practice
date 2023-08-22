package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Itinerary {
    @Id
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    @CollectionTable(
            name = "itinerary_site",
            joinColumns = @JoinColumn(name = "itinerary_id")
    )
    @OrderColumn(name = "list_index")
    private List<SiteInfo> siteInfos;
    transient private List<String> sites;

    public Itinerary() {
    }

    public Itinerary(Long id, String name, String description, List<String> sites) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sites = sites;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSites() {
        return sites;
    }

    public List<SiteInfo> getSiteInfos() {
        return siteInfos;
    }

    public void setSites(List<String> sites) {
        this.sites = sites;
    }
}
