package server;

/**
 * Created by bobcowher on 12/31/16.
 */
public class License {

    private final long id;
    private final String licenseKey;
    private final String url;
    private final String version;

    public License(long id, String licenseKey, String url, String version) {
        this.id = id;
        this.licenseKey = licenseKey;
        this.url = url;
        this.version = version;
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
}
