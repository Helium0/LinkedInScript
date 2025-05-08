package projectHelper;

import java.util.Date;

public class GenerateDate {


    private static Date myDate () {
        return new Date();
    }

    public static String actuallDate() {
        return myDate().toString().replace("-", "").replace(":", "_");
    }


}
