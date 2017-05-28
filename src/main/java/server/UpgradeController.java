package server;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bobcowher on 5/26/17.
 */

@RestController
public class UpgradeController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getupgradefile")
    public String getUpgradeFile(@RequestParam(value="licensekey") String licensekey, @RequestParam(value="url") String url, @RequestParam(value="version") String version, HttpServletResponse response) {
        try {

            License license = new License(counter.incrementAndGet(), licensekey, url, version, new java.sql.Date(Calendar.getInstance().getTime().getTime()));

            if(license.isCurrent() &&  license.isValid()) {
                // get your file as InputStream
                InputStream is = new FileInputStream(new File("upgrades/latest.zip"));

                response.addHeader("Content-disposition", "attachment;filename=latest.zip");
                response.setContentType("txt/plain");

                IOUtils.copy(is, response.getOutputStream());

                response.flushBuffer();

                return "License successfully validated.";

            } else {
                return "License validation unsuccessful. Unable to download upgrade files";
            }

        } catch (Exception e) {
            System.out.println(e);
            return "License validation unsuccessful. Unable to download upgrade files";
        }
    }

}
