package model;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class SiteInfo {
    private String site;
    private Date time;

    public SiteInfo() {
    }

    public SiteInfo(String site, Date time) {
        this.site = site;
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public Date getTime() {
        return time;
    }
}
