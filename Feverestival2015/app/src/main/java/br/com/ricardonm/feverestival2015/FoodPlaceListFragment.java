package br.com.ricardonm.feverestival2015;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.ricardonm.feverestival2015.adapter.FoodPlaceListAdapter;
import br.com.ricardonm.feverestival2015.model.FoodPlaceTO;
import br.com.ricardonm.feverestival2015.task.LoadFoodPlaceDataTask;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class FoodPlaceListFragment extends Fragment {
    private ListView listView;
    private List<FoodPlaceTO> foodPlaceList;

    public FoodPlaceListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food_place_list, container, false);

        this.listView = (ListView) rootView.findViewById(R.id.foodPlaceList);

        LoadFoodPlaceDataTask task = new LoadFoodPlaceDataTask(this);
        task.execute();

        return rootView;
    }

    public void setFoodPlaceList(List<FoodPlaceTO> foodPlaceList){

        listView.setAdapter(new FoodPlaceListAdapter(this.getActivity(), foodPlaceList));
    }
}
