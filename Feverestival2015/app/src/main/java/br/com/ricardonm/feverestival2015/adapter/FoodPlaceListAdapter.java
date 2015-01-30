package br.com.ricardonm.feverestival2015.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.ricardonm.feverestival2015.R;
import br.com.ricardonm.feverestival2015.model.FoodPlaceTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class FoodPlaceListAdapter extends ArrayAdapter<FoodPlaceTO> {
    private Context context;
    private List<FoodPlaceTO> items;

    public FoodPlaceListAdapter(Context ctx, List<FoodPlaceTO> list){
        super(ctx, R.layout.food_place_item, list);

        this.context = ctx;
        this.items = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.food_place_item, null);
        } else{
            view = convertView;
        }

        FoodPlaceTO place = this.items.get(position);

        TextView name = (TextView) view.findViewById(R.id.food_name);
        TextView phone = (TextView) view.findViewById(R.id.food_phone);
        TextView address = (TextView) view.findViewById(R.id.food_address);
        TextView site = (TextView) view.findViewById(R.id.food_site);

        name.setText(place.getName());
        phone.setText(place.getPhone());
        address.setText(place.getAddress());
        site.setText(place.getSite());

        return view;
    }
}
