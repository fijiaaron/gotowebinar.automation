package citrix.gotowebinar.automation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyLoader {

    protected static Logger log = Logger.getLogger(PropertyLoader.class.getSimpleName());

    public static Properties LoadProperties(String filename) {
        Path foo = Paths.get(filename);
        log.info("loading properties for filename: " + filename);

        Properties properties = new Properties();
        try {
            InputStream in = PropertyLoader.class.getResourceAsStream(filename);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
