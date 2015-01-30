package br.com.ricardonm.feverestival2015.adapter;

import android.content.Context;
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
public class MeetingPointListAdapter extends ArrayAdapter<EventTO> {
    private Context context;
    private List<EventTO> items;

    public MeetingPointListAdapter(Context ctx, List<EventTO> list){
        super(ctx, R.layout.meeting_point_item, list);

        this.context = ctx;
        this.items = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.meeting_point_item, null);
        } else{
            view = convertView;
        }

        EventTO event = this.items.get(position);

        TextView place = (TextView) view.findViewById(R.id.meeting_place);
        TextView name = (TextView) view.findViewById(R.id.meeting_name);
        TextView dateTime = (TextView) view.findViewById(R.id.meeting_date);
        TextView promotion = (TextView) view.findViewById(R.id.meeting_promotion);

        place.setText(event.getPlace());

        if (event.getName() != null && event.getName().length() > 0) {
            name.setText(event.getName());
        } else{
            name.setVisibility(View.GONE);
        }

        dateTime.setText(event.getDate()+" - "+event.getTime());

        promotion.setText(event.getSummary());


        return view;
    }
}
