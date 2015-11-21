package citrix.gotowebinar.automation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public static Properties LoadProperties(String filename) {
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
