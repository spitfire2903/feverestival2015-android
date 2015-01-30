package br.com.ricardonm.feverestival2015.task;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardonm.feverestival2015.EventListFragment;
import br.com.ricardonm.feverestival2015.manager.EventManager;
import br.com.ricardonm.feverestival2015.model.EventTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class LoadEventDataTask extends AsyncTask<Void, Void, Void> {
    private EventListFragment parent;
    private List<EventTO> eventList;
    private EventTO.EventType eventType = null;

    public LoadEventDataTask(EventListFragment fragment){
        super();
        this.parent = fragment;
    }

    public LoadEventDataTask(EventListFragment fragment, EventTO.EventType type){
        super();
        this.parent = fragment;
        this.eventType = type;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        eventList = new ArrayList<EventTO>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        this.eventList = EventManager.getEventsByType(this.eventType);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        parent.setEventList(this.eventList);
    }
}
