package citrix.gotowebinar.automation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String generateTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date());
    }
}
