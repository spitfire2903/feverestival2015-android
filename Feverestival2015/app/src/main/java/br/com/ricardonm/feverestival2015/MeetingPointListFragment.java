package br.com.ricardonm.feverestival2015;

import java.util.List;

import br.com.ricardonm.feverestival2015.adapter.MeetingPointListAdapter;
import br.com.ricardonm.feverestival2015.model.EventTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class MeetingPointListFragment extends EventListFragment {
    public MeetingPointListFragment(){
        this.eventType = EventTO.EventType.EVENT_TYPE_MEETING_POINT;
    }

    @Override
    public void setEventList(List<EventTO> list) {
        super.setEventList(list);

        this.listView.setAdapter(new MeetingPointListAdapter(this.getActivity(), list));
        this.listView.setOnItemClickListener(null);
    }
}
