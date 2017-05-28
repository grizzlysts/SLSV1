package server;

import java.sql.Date;

/**
 * Created by bobcowher on 12/31/16.
 */
public class License {

    private final long id;
    private final String licenseKey;
    private final String url;
    private final String version;
    private final Date date;
    private final Boolean valid;
    private final String status;

    public License(long id, String licenseKey, String url, String version, Date date) {
        this.id = id;
        this.licenseKey = licenseKey;
        this.url = url;
        this.version = version;
        this.date = date;
        this.valid = true;
        this.status = "current";
    }

    public long getId() {
        return id;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    public Date getDate() {
        return date;
    }

    public Boolean isValid() {
        return true;
    }

    public Boolean isCurrent() {
        if (status.equals("current")) {
            return true;
        } else {
            return false;
        }
    }

 }
