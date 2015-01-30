package br.com.ricardonm.feverestival2015.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.ricardonm.feverestival2015.R;
import br.com.ricardonm.feverestival2015.model.EventTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class EventListAdapter extends ArrayAdapter<EventTO> {

    private Context context;
    private List<EventTO> items;

    public EventListAdapter(Context ctx, List<EventTO> list){
        super(ctx, R.layout.event_item, list);

        this.context = ctx;
        this.items = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.event_item, null);
        } else{
            view = convertView;
        }

        EventTO event = this.items.get(position);

        TextView name = (TextView) view.findViewById(R.id.event_name);
        TextView placeCategory = (TextView) view.findViewById(R.id.event_place_category);
        TextView dateTime = (TextView) view.findViewById(R.id.event_date);

        name.setText(event.getName());

        if (event.getCategory() != null && event.getCategory().length() > 0) {
            placeCategory.setText(event.getCategory());
        } else{
            placeCategory.setText(event.getPlace());
        }

        if (event.getEventType() == EventTO.EventType.EVENT_TYPE_WORKSHOP) {
            dateTime.setText(event.getSheet());
        } else if (TextUtils.isEmpty(event.getTime())){
            dateTime.setText(event.getDate());
        } else {
            dateTime.setText(event.getDate() + " - " + event.getTime());
        }

        return view;
    }
}
