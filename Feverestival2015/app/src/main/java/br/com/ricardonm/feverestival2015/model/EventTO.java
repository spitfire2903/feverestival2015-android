package br.com.ricardonm.feverestival2015.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class EventTO extends SugarRecord<EventTO> implements Serializable {

   
    private long eventId;
    private String name;
    boolean favorited;
    private String date;
    private String time;
    private String place;
    private String summary;
    private String sheet;
    private String duration;
    private String rating;
    private String vacancy;
    private String target;
    private String requirement;
    private EventType eventType;
    //private EventCategory eventCategory;
    private String category;
    private String promotion;
    private String owner;

    @Ignore
    Date dateObj;

    @Ignore
    Date timeObj;


    public EventTO() {
    }

    public static enum EventType{
        EVENT_TYPE_EXHIBITION,
        EVENT_TYPE_WORKSHOP,
        EVENT_TYPE_SPECIAL,
        EVENT_TYPE_STORYTELLING,
        EVENT_TYPE_MEETING_POINT
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;

        if ((o instanceof EventTO) && this.getEventId() == ((EventTO)o).getEventId()){
            result = true;
        } else if (super.equals(o)){
            result = true;
        }

        return result;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getDateObj() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            if (dateObj == null)
                dateObj = sdf.parse(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateObj;
    }

    public Date getTimeObj() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            if (timeObj == null)
                timeObj = sdf.parse(this.time);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return timeObj;
    }

}
