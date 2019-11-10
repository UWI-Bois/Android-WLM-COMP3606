package dcit.uwi.simplelocationtracker;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

class LocationService extends IntentService implements CloseListener {

    private static final String NAME = "DCIT-LocationService";
    protected LocationManager manager;
    protected LocationListener listener;
    private boolean hasPermission = false;

    public LocationService(){super(NAME);}

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new PrefLocationListener(this, this);

        if(
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
        ){
            Log.d(NAME, "unable to access GPS");
        }
        else{
            hasPermission = true;
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, listener);
        }
    }

    @SuppressWarnings("MissingPermissions")
    @Override
    public void deRegisterListener(){
        if(manager != null && listener != null){
            if(hasPermission) manager.removeUpdates(listener);
        }
    }
}
