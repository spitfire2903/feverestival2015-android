package br.com.ricardonm.feverestival2015;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.ricardonm.feverestival2015.task.LoadEventTask;

import static br.com.ricardonm.feverestival2015.model.EventTO.EventType.EVENT_TYPE_EXHIBITION;
import static br.com.ricardonm.feverestival2015.model.EventTO.EventType.EVENT_TYPE_SPECIAL;
import static br.com.ricardonm.feverestival2015.model.EventTO.EventType.EVENT_TYPE_STORYTELLING;
import static br.com.ricardonm.feverestival2015.model.EventTO.EventType.EVENT_TYPE_WORKSHOP;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    protected ProgressDialog progressDialog = null;

    public void showThrobber(){
        this.hideThrobber();

        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle("Carregando...");
        this.progressDialog.setMessage("Aguarde um instante");
        this.progressDialog.show();

    }

    public void hideThrobber(){
        if (this.progressDialog != null){
            if (this.progressDialog.isShowing())
                this.progressDialog.dismiss();

            this.progressDialog = null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(108,91,168)));

        LoadEventTask task = new LoadEventTask(this);
        task.execute();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Bundle args = new Bundle();
        EventListFragment eventFragment = null;

        switch (position){
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MyCalendarListFragment())
                        .commit();
                break;
            case 1:
                args.putInt("eventType", EVENT_TYPE_EXHIBITION.ordinal());
                eventFragment = new EventListFragment();
                eventFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, eventFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                args.putInt("eventType", EVENT_TYPE_WORKSHOP.ordinal());
                eventFragment = new EventListFragment();
                eventFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, eventFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 3:
                args.putInt("eventType", EVENT_TYPE_SPECIAL.ordinal());
                eventFragment = new EventListFragment();
                eventFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, eventFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 4:
                args.putInt("eventType", EVENT_TYPE_STORYTELLING.ordinal());
                eventFragment = new EventListFragment();
                eventFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, eventFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 5:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MeetingPointListFragment())
                        .commit();
                break;
            case 6:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AddressFragment())
                        .commit();
                break;
            case 7:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PartnersFragment())
                        .commit();
                break;
            case 8:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new FoodPlaceListFragment())
                        .commit();
                break;
            case 9:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new SheetFragment())
                        .commit();
                break;
        }

        this.onSectionAttached(position+1);

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = "Meu Calendário";
                break;
            case 2:
                mTitle = "Programação";
                break;
            case 3:
                mTitle = "Oficinas";
                break;
            case 4:
                mTitle = "Eventos Especiais";
                break;
            case 5:
                mTitle = "Contação de Histórias";
                break;
            case 6:
                mTitle = "Pontos de Encontro";
                break;
            case 7:
                mTitle = "Endereços";
                break;
            case 8:
                mTitle = "Parceiros";
                break;
            case 9:
                mTitle = "Onde Comer";
                break;
            case 10:
                mTitle = "Ficha Técnica";
                break;

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PartnersFragment extends Fragment {


        public PartnersFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_partners, container, false);
            return rootView;
        }

    }

    public static class AddressFragment extends Fragment {


        public AddressFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_address, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.textView);

            tv.setText(Html.fromHtml(getString(R.string.address_text)));

            return rootView;
        }

    }

    public static class SheetFragment extends Fragment {


        public SheetFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sheet, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.textView);

            tv.setText(Html.fromHtml(getString(R.string.sheet_text)));

            return rootView;
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//    }

}
