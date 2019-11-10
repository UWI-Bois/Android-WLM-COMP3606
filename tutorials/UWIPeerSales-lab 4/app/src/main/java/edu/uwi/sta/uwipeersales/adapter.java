package edu.uwi.sta.uwipeersales;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

//Custom Adapter to display Item information in list view
public class adapter extends ArrayAdapter<Map> {
    SharedPreferences sp;
    public adapter(Context context, ArrayList<Map> items,final SharedPreferences pref){
        super(context, 0, items);
        sp=pref;
    }

    public View getView(final int position, View v, ViewGroup p){
        //Retrieves the view item position and View group
        v = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        final View V = v; // Copies the view into a final variable to be used by button function

        //Retrieves position, title, description and image information from the map
        Map map = getItem(position);
        final int id=(Integer)map.get("id");
        String title = (String)map.get("name");
        String descr = (String)map.get("desc");
        int icon = (Integer)map.get("image");

        //Displays the information in the relevant areas in the view
        ((TextView)v.findViewById(R.id.title)).setText(title);
        ((TextView)v.findViewById(R.id.desc)).setText(descr);
        final ImageView iv=(ImageView)v.findViewById(R.id.img);
        iv.setImageResource(icon);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder confirm = new AlertDialog.Builder(getContext());
                confirm.setMessage("Delete This Item From Cart?");
                confirm.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size;
                        SharedPreferences.Editor editor = sp.edit();
                        size = sp.getInt("cartSize", 0);
                        editor.remove("cartItem"+id);
                        editor.putInt("cartSize", size - 1);
                        editor.apply();

                        Toast.makeText(getContext(), "Item Deleted, Go back to refresh cart", Toast.LENGTH_SHORT).show();
                    }
                });

                confirm.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                confirm.show();
            }
        });

        //Retrieves  the button from the view and sets the function to it
        final Button showDesc = (Button) v.findViewById(R.id.showDesc);
        showDesc.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TextView tv = (TextView) V.findViewById(R.id.desc);
                if (tv.getVisibility() == View.VISIBLE) {
                    tv.setVisibility(View.INVISIBLE);
                    showDesc.setText("Show Description");
                } else {
                    tv.setVisibility(View.VISIBLE);
                    showDesc.setText("Hide Description");
                }
            }
        });

        return v; //Returns the view to be displayed as a list view item
    }
}

