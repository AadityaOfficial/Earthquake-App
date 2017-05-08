package com.example.android.quakereport;

        import android.app.Activity;
        import android.content.Context;
        import android.support.annotation.LayoutRes;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.content.ContextCompat;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import java.text.DecimalFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

        import android.graphics.drawable.GradientDrawable;

        import static android.R.attr.resource;

/**
 * Created by aadit on 4/8/2017.
 */

public class CustomAdapter extends ArrayAdapter<Earthquake> {

    public CustomAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        TextView earthquakeDate = (TextView) listItemView.findViewById(R.id.date);
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        String formattedDate = formatDate(dateObject);
        earthquakeDate.setText(formattedDate);

        //Splitting location String
        String loc = currentEarthquake.getLocationt();
        String location[] = loc.split("of");
        String offset = "", primaryLocation = "";
        if (location.length < 2) {
            offset = "Near the";
            primaryLocation = location[0];
        } else {
            offset = location[0] + " " + "of";
            primaryLocation = location[1];
        }
        TextView earthquakeOffsetLocation = (TextView) listItemView.findViewById(R.id.offset_location);
        earthquakeOffsetLocation.setText(offset);

        TextView earthquakeprimaryLocation = (TextView) listItemView.findViewById(R.id.primary_location);
        earthquakeprimaryLocation.setText(primaryLocation);

        //Setting up the magnitude icon
        TextView earthquakeMagitude = (TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        earthquakeMagitude.setText(formattedMagnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) earthquakeMagitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        //Setting up earthquake time
        TextView earthquakeTime = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        earthquakeTime.setText(formattedTime);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}