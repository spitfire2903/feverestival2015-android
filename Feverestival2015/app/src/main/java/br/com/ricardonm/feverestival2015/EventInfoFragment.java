package br.com.ricardonm.feverestival2015;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.ricardonm.feverestival2015.model.EventTO;

/**
 * Created by ricardomiranda on 30/01/15.
 */
public class EventInfoFragment extends Fragment {
    protected EventTO event;
    protected ImageButton addCalendar;
    protected TextView eventName;
    protected TextView eventPlace;
    protected TextView eventDateTime;
    protected TextView eventContent;

    public EventInfoFragment(){
        super();
    }
/*
    public EventInfoFragment(EventTO e){
        super();
        this.event = e;
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_info, container, false);

        if (getArguments() != null && getArguments().getSerializable("event") != null){
            this.event = (EventTO) getArguments().getSerializable("event");
        }

        this.eventName = (TextView) rootView.findViewById(R.id.event_name);
        this.eventPlace = (TextView) rootView.findViewById(R.id.event_place);
        this.eventDateTime = (TextView) rootView.findViewById(R.id.event_date_time);
        this.eventContent = (TextView) rootView.findViewById(R.id.event_content);

        this.addCalendar = (ImageButton) rootView.findViewById(R.id.event_add);

        if (this.event != null){
            if (this.event.isFavorited()){
                this.addCalendar.setVisibility(View.GONE);
                //this.rotateAddButton(true);
            }

            if(this.event.getCategory() != null && this.event.getCategory()
                    .length() > 0) {
                ActionBar actionBar = getActivity().getActionBar();
                actionBar.setTitle(this.event.getCategory());
                //actionBar.setDisplayHomeAsUpEnabled(true);
            }

            this.eventName.setText(this.event.getName());
            this.eventPlace.setText(this.event.getPlace());

            if (event.getEventType() == EventTO.EventType.EVENT_TYPE_WORKSHOP) {
                eventDateTime.setText(event.getSheet());
            } else if (TextUtils.isEmpty(event.getTime())){
                eventDateTime.setText(event.getDate());
            } else {
                eventDateTime.setText(event.getDate() + " - " + event.getTime());
            }

            String content = "";

            if (!TextUtils.isEmpty(this.event.getOwner())){
                content = this.event.getOwner()+"\n\n";
            }

            if (!TextUtils.isEmpty(this.event.getSummary())){
                content += this.event.getSummary()+"\n\n";
            }

            if (!TextUtils.isEmpty(this.event.getSheet())){
                content += this.event.getSheet()+"\n\n";
            }

            if (!TextUtils.isEmpty(this.event.getDuration())){
                content += this.event.getDuration()+"\n\n";
            }

            if (!TextUtils.isEmpty(this.event.getRating())){
                content += this.event.getRating()+"\n\n";
            }


            if (!TextUtils.isEmpty(this.event.getTarget())){
                content += this.event.getTarget()+"\n\n";
            }

            if (!TextUtils.isEmpty(this.event.getRequirement())){
                content += this.event.getRequirement()+"\n\n";
            }

            this.eventContent.setText(content);
        }


        this.addCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (EventInfoFragment.this.event.isFavorited()){
//                    //EventInfoFragment.this.rotateAddButton(false);
//                    EventInfoFragment.this.event.setFavorited(false);
//                    EventInfoFragment.this.event.save();
//                } else{
//                    EventInfoFragment.this.rotateAddButton(true);
                    EventInfoFragment.this.event.setFavorited(true);
                    EventInfoFragment.this.event.save();
                    EventInfoFragment.this.addCalendar.setVisibility(View.GONE);
//                }
                //EventInfoFragment.this.event.setFavorited(true);
                //EventInfoFragment.this.event.save();
                //EventInfoFragment.this.addCalendar.setVisibility(View.INVISIBLE);
            }
        });

        return rootView;
    }

    private void rotateAddButton(Boolean toClose){
        if (toClose) {
            RotateAnimation ra = new RotateAnimation(0, 45);
            ra.setFillAfter(true);
            ra.setDuration(1000);

            addCalendar.startAnimation(ra);
        } else{
            RotateAnimation ra = new RotateAnimation(0, -45);

            ra.setFillAfter(true);
            ra.setDuration(1000);

            addCalendar.startAnimation(ra);
        }
    }
}
