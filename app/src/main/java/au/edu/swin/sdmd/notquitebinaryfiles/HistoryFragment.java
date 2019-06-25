package au.edu.swin.sdmd.notquitebinaryfiles;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.List;


/**
 * This fragment shows the last value converted (from the shared preferences file) and also a
 * list of the previous values converted.
 */
public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * check if something is stored in the shared preferences file to show, and update the
         * TextView on screen.
         */
        String sHistory = checkSharedPrefs();
        TextView tvHistory = getView().findViewById(R.id.history);
        tvHistory.setText(String.format("Last value converted was %s", sHistory));


        /**
         * The detailed retrieval of data from the file has moved to another class; this gets a list
         * of the strings in the file and provides them to the list adapter. This is similar to what
         * we saw when we studied lists; an ArrayAdapter is being used as is.
         */
        FileInputStream fis = HistoryFile.getFile(getContext());
        List<String> numbers = HistoryFile.getFileContents(fis);
        ListView lvNumbers = getView().findViewById(R.id.history_list);
        lvNumbers.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, numbers));


    }


    String checkSharedPrefs() {
        /**
         * For reading shared preference files, simply find the correct file and find the value
         * using the key. A default value can also be specified.
         */
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return sharedPref.getString(getString(R.string.last_input), "");
    }

}
