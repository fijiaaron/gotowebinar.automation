package citrix.gotowebinar.automation;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyLoader {

    protected static Logger log = Logger.getLogger(PropertyLoader.class.getSimpleName());

    public static Properties LoadProperties(String propertiesFile) {
        Properties properties = new Properties();

        try {
            properties.load(PropertyLoader.class.getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            log.warning("unable to load properties file: " + propertiesFile);
            e.printStackTrace();
        }

        log.info("getting test properties");

        for (Object propertyName : properties.keySet()) {
            log.info("property: " + propertyName + "=" + properties.get(propertyName));
        }

        return properties;
    }
}
