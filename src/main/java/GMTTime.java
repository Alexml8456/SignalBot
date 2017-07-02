import java.util.Calendar;
import java.util.TimeZone;

public class GMTTime {

    public static long getDateGMT() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        return cal.getTimeInMillis();
    }
}