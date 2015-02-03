package br.com.ricardonm.feverestival2015.task;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardonm.feverestival2015.EventListFragment;
import br.com.ricardonm.feverestival2015.MainActivity;
import br.com.ricardonm.feverestival2015.manager.EventManager;
import br.com.ricardonm.feverestival2015.model.EventTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class LoadEventDataTask extends AsyncTask<Void, Void, Void> {
    private EventListFragment parent;
    private List<EventTO> eventList;
    private EventTO.EventType eventType = null;
    private Boolean isFavorited = Boolean.FALSE;

    public LoadEventDataTask(EventListFragment fragment){
        super();
        this.parent = fragment;
    }

    public LoadEventDataTask(EventListFragment fragment, EventTO.EventType type){
        super();
        this.parent = fragment;
        this.eventType = type;
    }

    public LoadEventDataTask(EventListFragment fragment, EventTO.EventType type,
                             Boolean isFavorited){
        super();
        this.parent = fragment;
        this.eventType = type;
        this.isFavorited = isFavorited;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ((MainActivity)this.parent.getActivity()).showThrobber();

        eventList = new ArrayList<EventTO>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (this.isFavorited == null || !this.isFavorited)
            this.eventList = EventManager.getEventsByType(this.eventType);
        else
            this.eventList = EventManager.getMyCalendarEvents();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        ((MainActivity)this.parent.getActivity()).hideThrobber();

        parent.setEventList(this.eventList);
    }
}
