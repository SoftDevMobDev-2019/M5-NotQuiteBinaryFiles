package au.edu.swin.sdmd.notquitebinaryfiles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * An example of reading from a read-only file. The file is stored in /res/raw.
 */
public class ProcessFragment extends Fragment {


    public ProcessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_process, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /**
         * Use openRawResource() to open the file. From there it can be used like a regular Java
         * input stream. In this case we use a BufferedReader to read one line at a time and add
         * to a TextView. The lines could also be added to a list.
         */
        InputStream inputStream = getResources().openRawResource(R.raw.binary_process);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        TextView tvProcess = getView().findViewById(R.id.process);
        String process = "";
        try {
            String line = br.readLine();
            while (line != null) {
                process += line;
                line = br.readLine();
            }
            tvProcess.setText(Html.fromHtml(process, Html.FROM_HTML_MODE_COMPACT));
        } catch (IOException e) {
            tvProcess.setText(R.string.file_error);

        }
    }

}
