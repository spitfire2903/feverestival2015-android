package br.com.ricardonm.feverestival2015;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.ricardonm.feverestival2015.adapter.EventListAdapter;
import br.com.ricardonm.feverestival2015.model.EventTO;
import br.com.ricardonm.feverestival2015.task.LoadEventDataTask;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class EventListFragment extends Fragment {
    protected List<EventTO> eventList;
    protected EventTO.EventType eventType = null;
    protected ListView listView;
    protected Boolean isFavorited = null;

    public EventListFragment(){
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);

        if (getArguments() != null) {
            int eventTypeInt = getArguments().getInt("eventType");

            if (eventTypeInt == EventTO.EventType.EVENT_TYPE_WORKSHOP.ordinal()) {
                this.eventType = EventTO.EventType.EVENT_TYPE_WORKSHOP;
            } else if (eventTypeInt == EventTO.EventType.EVENT_TYPE_EXHIBITION.ordinal()) {
                this.eventType = EventTO.EventType.EVENT_TYPE_EXHIBITION;
            } else if (eventTypeInt == EventTO.EventType.EVENT_TYPE_SPECIAL.ordinal()) {
                this.eventType = EventTO.EventType.EVENT_TYPE_SPECIAL;
            } else if (eventTypeInt == EventTO.EventType.EVENT_TYPE_STORYTELLING.ordinal()) {
                this.eventType = EventTO.EventType.EVENT_TYPE_STORYTELLING;
            }
        }

        this.listView = (ListView) rootView.findViewById(R.id.eventList);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Activity activity = EventListFragment.this.getActivity();
                FragmentManager fragmentManager = activity.getFragmentManager();
                EventInfoFragment eventFragment = new EventInfoFragment();
                Bundle args = new Bundle();
                EventTO event = EventListFragment.this
                        .eventList.get(i);
                args.putSerializable("event", event);
                eventFragment.setArguments(args);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, eventFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        LoadEventDataTask task = new LoadEventDataTask(this, this.eventType, this.isFavorited);
        task.execute();

        return rootView;
    }

    public void setEventList(List<EventTO> list){
        this.eventList = list;
        this.listView.setAdapter(new EventListAdapter(this.getActivity(), list));
    }

    @Override
    public void onResume() {
        super.onResume();

        String title = null;

        if(isFavorited != null && isFavorited){
            title = "Meu Calendário";
        } else{
            if (eventType != null){
                switch (eventType){
                    case EVENT_TYPE_EXHIBITION:
                        title = "Programação";

                        break;
                    case EVENT_TYPE_WORKSHOP:
                        title = "Oficinas";

                        break;
                    case EVENT_TYPE_SPECIAL:
                        title = "Eventos Especiais";

                        break;
                    case EVENT_TYPE_STORYTELLING:
                        title = "Contação de Histórias";

                        break;
                    case EVENT_TYPE_MEETING_POINT:
                        title = "Pontos de Encontros";

                        break;
                }
            }

        }

        getActivity().getActionBar().setTitle(title);
    }
}
