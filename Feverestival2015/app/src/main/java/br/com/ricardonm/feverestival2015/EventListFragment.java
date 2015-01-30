package br.com.ricardonm.feverestival2015;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.ricardonm.feverestival2015.model.EventTO;
import br.com.ricardonm.feverestival2015.task.LoadEventDataTask;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class EventListFragment extends Fragment {
    protected List<EventTO> eventList;
    protected EventTO.EventType eventType = null;
    protected ListView listView;

    public EventListFragment(){
        super();
    }

    public EventListFragment(EventTO.EventType type){
        super();
        eventType = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);

        this.listView = (ListView) rootView.findViewById(R.id.eventList);
        // FIXME: implement one generic this.listView.setOnItemClickListener();
        LoadEventDataTask task = new LoadEventDataTask(this, eventType);
        task.execute();

        return rootView;
    }

    public void setEventList(List<EventTO> list){
        this.eventList = list;
    }
}
