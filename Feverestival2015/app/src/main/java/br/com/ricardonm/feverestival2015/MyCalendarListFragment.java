package br.com.ricardonm.feverestival2015;

import java.util.List;

import br.com.ricardonm.feverestival2015.adapter.MyCalendarListAdapter;
import br.com.ricardonm.feverestival2015.model.EventTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class MyCalendarListFragment extends EventListFragment {
    public MyCalendarListFragment(){
        super();
        this.isFavorited = true;
    }

    @Override
    public void setEventList(List<EventTO> list) {
        super.setEventList(list);

        this.listView.setAdapter(new MyCalendarListAdapter(this.getActivity(), list));
    }

}
