package au.edu.swin.sdmd.notquitebinaryfiles;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A fragment to handle a conversion from decimal to binary.
 */
public class ConversionFragment extends Fragment {


    public ConversionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_conversion, container, false);
        Button bConvert = v.findViewById(R.id.bConvert);
        bConvert.setOnClickListener(convertDecimal);
        return v;
    }

    View.OnClickListener convertDecimal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText etDecimal = getView().findViewById(R.id.etDecimal);
            String sDecimal = etDecimal.getText().toString();
            if(!sDecimal.equals("")) {
                int iDecimal = Integer.parseInt(sDecimal);
                String sBinary = Integer.toBinaryString(iDecimal);
                TextView tvBinary = getView().findViewById(R.id.tvBinary);
                tvBinary.setText(sBinary);

                /**
                 * This is where we update our files.
                 */
                updateSharedPrefs(sDecimal);
                updateHistory(sDecimal, sBinary);


            } else {
                TextView tvBinary = getView().findViewById(R.id.tvBinary);
                tvBinary.setText("No number entered");
            }

        }
    };

    void updateSharedPrefs(String sDecimal) {
        /**
         * For writing to a shared preference file, we need to name the file first.
         */
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        /**
         * Using an Editor to add/update values in the file before committing.
         */
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.last_input), sDecimal);
        editor.commit();
    }


    /**
     * For our internal storage file, add the decimal input and binary result of the conversion
     * so we can refer to it later.
     * @param sDecimal
     * @param sBinary
     */
    void updateHistory(String sDecimal, String sBinary) {
        HistoryFile.appendInput(getContext(), sDecimal+" = "+sBinary);
    }

}
