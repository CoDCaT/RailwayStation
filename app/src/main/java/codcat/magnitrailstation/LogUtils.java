package codcat.magnitrailstation;

import android.util.Log;

public class LogUtils {

    public static void E(String message) {
        String[] log_Tags = Thread.currentThread().getStackTrace()[3].getClassName().split("\\.");
        Log.e(log_Tags[log_Tags.length - 1], message);
    }

    public static void D(String message) {
        String[] log_Tags = Thread.currentThread().getStackTrace()[3].getClassName().split("\\.");
        Log.d(log_Tags[log_Tags.length - 1], message);
    }

}
