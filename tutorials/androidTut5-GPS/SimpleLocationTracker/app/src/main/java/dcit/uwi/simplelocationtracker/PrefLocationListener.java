package dcit.uwi.simplelocationtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

class PrefLocationListener implements LocationListener {

    private final Context context;
    private final CloseListener closer;

    public PrefLocationListener(Context context, CloseListener closer) {
        this.context = context;
        this.closer = closer;
    }

    @Override
    public void onLocationChanged(Location location){
        Log.i("PrefLocationListener", "Accessed the GPS Successfully");
        // check if location exist before
        SharedPreferences pref = context.getSharedPreferences("DCIT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String rep = pref.getString("last_loc", null);
        if(rep != null){
            Location oldLoc = convertFromRep(rep);
            float dist = location.distanceTo(oldLoc);
            dist = dist + pref.getFloat("total_dist", 0);

            // store distance
            editor.putFloat("total_dist", dist);
        }
        //store location
        editor.putString("last_loc", convert2Rep(location));

        if(editor.commit()){
            Log.i("PrefLocationListener", "Information Stored Successfully");
        }

        // after reading location close and deregister listener
        if(closer != null){
            closer.deRegisterListener();
        }
    }

    private String convert2Rep(Location location) {
        return location.getLatitude() + ", " + location.getLongitude() + ", " + location.getAltitude();
    }

    private Location convertFromRep(String rep) {
        String[] res = rep.split(", ");
        double lat = Double.parseDouble(res[0]);
        double log = Double.parseDouble(res[1]);
        double alt = Double.parseDouble(res[2]);

        Location location = new Location("");
        location.setLatitude(lat);
        location.setAltitude(alt);
        location.setLongitude(log);

        return location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){

    }

    @Override
    public void onProviderEnabled(String provider){

    }

    @Override
    public void onProviderDisabled(String provider){

    }
}
