package br.com.ricardonm.feverestival2015.task;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardonm.feverestival2015.FoodPlaceListFragment;
import br.com.ricardonm.feverestival2015.MainActivity;
import br.com.ricardonm.feverestival2015.manager.FoodPlaceManager;
import br.com.ricardonm.feverestival2015.model.FoodPlaceTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class LoadFoodPlaceDataTask extends AsyncTask<Void, Void, Void> {
    private FoodPlaceListFragment parent;
    private List<FoodPlaceTO> foodPlaceList;

    public LoadFoodPlaceDataTask(FoodPlaceListFragment fragment){
        super();
        this.parent = fragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ((MainActivity)this.parent.getActivity()).showThrobber();

        foodPlaceList = new ArrayList<FoodPlaceTO>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        this.foodPlaceList = FoodPlaceManager.getFoodPlaces();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        ((MainActivity)this.parent.getActivity()).hideThrobber();

        parent.setFoodPlaceList(this.foodPlaceList);
    }
}
