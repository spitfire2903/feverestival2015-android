package br.com.ricardonm.feverestival2015.manager;

import com.google.common.collect.Lists;

import java.util.List;

import br.com.ricardonm.feverestival2015.model.EventTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class EventManager {

    public static List<EventTO> getEventsByType(EventTO.EventType eventType){
        List<EventTO> list = null;

        if (eventType != null) {
            list = EventTO.find(EventTO.class, "EVENT_TYPE = ?", eventType.toString());
        } else{
            list = Lists.newArrayList(EventTO.findAll(EventTO.class));
            //list = Lists.as null;//IteratorUtils.toList(EventTO.findAll(EventTO.class));
        }

        return list;
    }

    public  static List<EventTO> getMyCalendarEvents(){
        List<EventTO> list = null;

        list = EventTO.find(EventTO.class, "favorited = ?", "1");

        return list;
    }

    public static void deleteAllEvents(){
        EventTO.deleteAll(EventTO.class);
    }
}
