package server;

import core.LicenseLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bobcowher on 12/31/16.
 */

@RestController
public class LicenseController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/validatelicense")
    public License license(@RequestParam(value="licensekey") String licensekey, @RequestParam(value="url") String url, @RequestParam(value="version") String version) {
       License license = new License(counter.incrementAndGet(), licensekey, url, version, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
       LicenseLogger.addLicenseEntry(license);
       return license;
    }

}
