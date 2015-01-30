package br.com.ricardonm.feverestival2015.task;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.ricardonm.feverestival2015.MainActivity;
import br.com.ricardonm.feverestival2015.manager.EventManager;
import br.com.ricardonm.feverestival2015.manager.FoodPlaceManager;
import br.com.ricardonm.feverestival2015.model.EventTO;
import br.com.ricardonm.feverestival2015.model.FoodPlaceTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class LoadEventTask extends AsyncTask<Void, Void, Void> {
    private MainActivity parent = null;
    private List<EventTO> events = null;
    private List<FoodPlaceTO> foodPlaces = null;

    public LoadEventTask(MainActivity parent) {
        this.parent = parent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //parent.showThrobber();

        events = new ArrayList<EventTO>();
        foodPlaces = new ArrayList<FoodPlaceTO>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        List list = null;

        //EventManager.deleteAllEvents();

        list = FoodPlaceManager.getFoodPlaces();

        if (list == null || list.size() == 0)
            this.readFoodPlaceJson(this.loadJSONFromAsset("foodPlaces.json"));


        list = EventManager.getEventsByType(EventTO.EventType.EVENT_TYPE_MEETING_POINT);

        if (list == null || list.size() == 0)
            this.readMeetingPointsJson(this.loadJSONFromAsset("meetingPoints.json"));


        list = EventManager.getEventsByType(EventTO.EventType.EVENT_TYPE_WORKSHOP);

        if (list == null || list.size() == 0)
            this.readWorkshopsJson(this.loadJSONFromAsset("workshops.json"));

        list = EventManager.getEventsByType(EventTO.EventType.EVENT_TYPE_STORYTELLING);

        if (list == null || list.size() == 0)
            this.readStorytellingJson(this.loadJSONFromAsset("storytelling.json"));

        list = EventManager.getEventsByType(EventTO.EventType.EVENT_TYPE_SPECIAL);

        if (list == null || list.size() == 0){
            this.readSpecialJson(this.loadJSONFromAsset("specialShow.json"));
            this.readSpecialPocketJson(this.loadJSONFromAsset("pocketShow.json"));
        }

        list = EventManager.getEventsByType(EventTO.EventType.EVENT_TYPE_EXHIBITION);

        if (list == null || list.size() == 0) {
            this.readAdultJson(this.loadJSONFromAsset("adultShow.json"));
            this.readChildrenJson(this.loadJSONFromAsset("childrenShow.json"));
            this.readStreetJson(this.loadJSONFromAsset("streetShow.json"));
            this.readUnicampJson(this.loadJSONFromAsset("unicamp.json"));
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //parent.hideThrobber();
    }

    private String loadJSONFromAsset(String assetFile) {
        String json = null;
        try {

            InputStream is = parent.getAssets().open(assetFile);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    private void readFoodPlaceJson(String json){
        try {

            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("foodPlaces");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                FoodPlaceTO foodPlace = new FoodPlaceTO();

                foodPlace.setName(jo_inside.getString("place"));
                foodPlace.setAddress(jo_inside.getString("address"));
                foodPlace.setPhone(jo_inside.getString("phone"));
                foodPlace.setSite(jo_inside.getString("site"));

                foodPlace.save();

                foodPlaces.add(foodPlace);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readMeetingPointsJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("summary"));
                event.setDate(jo_inside.getString("date"));
                event.setTime(jo_inside.getString("time"));
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("promotion"));

                event.setEventType(EventTO.EventType.EVENT_TYPE_MEETING_POINT);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readWorkshopsJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("summary"));
                event.setOwner(jo_inside.getString("owner"));
                event.setSheet(jo_inside.getString("date") + " - " + jo_inside.getString("time"));
                event.setDuration(jo_inside.getString("duration"));
                event.setVacancy(jo_inside.getString("vacancys"));
                event.setTarget(jo_inside.getString("target"));
                event.setRequirement(jo_inside.getString("requirements"));

                event.setEventType(EventTO.EventType.EVENT_TYPE_WORKSHOP);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readStorytellingJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("summary"));
                event.setSheet(jo_inside.getString("sheet"));
                event.setDate(jo_inside.getString("date"));
                event.setTime(jo_inside.getString("time"));
                event.setDuration(jo_inside.getString("duration"));

                event.setEventType(EventTO.EventType.EVENT_TYPE_STORYTELLING);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readSpecialJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setDate(jo_inside.getString("date"));
                if (jo_inside.getString("time") != null && jo_inside.getString("time").length() > 0) {
                    event.setTime(jo_inside.getString("time"));
                }
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("summary"));
                event.setSheet(jo_inside.getString("sheet"));
                event.setDuration(jo_inside.getString("duration"));
                event.setRating(jo_inside.getString("rating"));
                event.setCategory(jo_inside.getString("category"));

                event.setEventType(EventTO.EventType.EVENT_TYPE_SPECIAL);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readSpecialPocketJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setDate(jo_inside.getString("date"));
                event.setTime(jo_inside.getString("time"));
                event.setPlace(jo_inside.getString("place") + " - " + jo_inside.getString("address"));
                event.setSummary(jo_inside.getString("summary"));
                event.setOwner(jo_inside.getString("owner"));
                //event.setSheet(jo_inside.getString("sheet"));
                //event.setDuration(jo_inside.getString("duration"));
                //event.setRating(jo_inside.getString("rating"));
                event.setCategory("Mostra de Bolso");

                event.setEventType(EventTO.EventType.EVENT_TYPE_SPECIAL);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readAdultJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setDate(jo_inside.getString("date"));
                event.setTime(jo_inside.getString("time"));
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("summary"));
                event.setSheet(jo_inside.getString("sheet"));
                event.setDuration(jo_inside.getString("duration"));
                event.setRating(jo_inside.getString("rating"));
                event.setCategory("Espetáculo Adulto");

                event.setEventType(EventTO.EventType.EVENT_TYPE_EXHIBITION);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readChildrenJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setDate(jo_inside.getString("date"));
                event.setTime(jo_inside.getString("time"));
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("summary"));
                event.setSheet(jo_inside.getString("sheet"));
                event.setDuration(jo_inside.getString("duration"));
                event.setRating(jo_inside.getString("rating"));
                event.setCategory("Espetáculo Infantil");

                event.setEventType(EventTO.EventType.EVENT_TYPE_EXHIBITION);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readStreetJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setDate(jo_inside.getString("date"));
                event.setTime(jo_inside.getString("time"));
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("summary"));
                event.setSheet(jo_inside.getString("sheet"));
                event.setDuration(jo_inside.getString("duration"));
                event.setRating(jo_inside.getString("rating"));
                event.setCategory("Espetáculo de Rua");

                event.setEventType(EventTO.EventType.EVENT_TYPE_EXHIBITION);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void readUnicampJson(String json){
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("events");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                EventTO event = new EventTO();

                event.setName(jo_inside.getString("name"));
                event.setDate(jo_inside.getString("date"));
                event.setTime(jo_inside.getString("time"));
                event.setPlace(jo_inside.getString("place"));
                event.setSummary(jo_inside.getString("summary"));
                event.setSheet(jo_inside.getString("sheet"));
                event.setDuration(jo_inside.getString("duration"));
                event.setRating(jo_inside.getString("rating"));
                event.setCategory("Mostra Unicamp");

                event.setEventType(EventTO.EventType.EVENT_TYPE_EXHIBITION);

                event.save();

                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
