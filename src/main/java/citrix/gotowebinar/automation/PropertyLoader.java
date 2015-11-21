package citrix.gotowebinar.automation;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyLoader {

    protected static Logger log = Logger.getLogger(PropertyLoader.class.getSimpleName());

    public Properties loadProperties(String filename) {
        Properties properties = new Properties();
        try {
            //This depends on a filename being in the class path
            properties.load(getClass().getResourceAsStream(filename));
        } catch (IOException e) {
            log.warning("can't load properties file: " + filename);
            e.printStackTrace();
        }

        return properties;
    }
}
