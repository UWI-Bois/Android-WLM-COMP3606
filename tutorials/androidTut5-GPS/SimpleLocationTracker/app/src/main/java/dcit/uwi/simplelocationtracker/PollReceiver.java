package dcit.uwi.simplelocationtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.nio.channels.InterruptedByTimeoutException;

class PollReceiver extends BroadcastReceiver {

    public static final int INTERVAL = 5000; // for testing
    public static final int REQUEST_CODE = 342; // used to uniquely identify the service

    public static boolean setUpService(final Context context) {
        // the alarm manager gives us the ability to schedule repeated calls of teh service
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // create the intent that will launch the service
        Intent i = new Intent(context, LocationService.class);
        PendingIntent pi = PendingIntent.getService(context, REQUEST_CODE, i, 0);
        // set the alarm to go off immediately (0) and every 5s( INTERVAL)
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, 0, INTERVAL, pi);
        return true;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        setUpService(context);
    }
}
