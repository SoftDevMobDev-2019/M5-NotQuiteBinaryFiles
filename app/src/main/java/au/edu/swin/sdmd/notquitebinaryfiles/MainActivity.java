package au.edu.swin.sdmd.notquitebinaryfiles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * This app shows how files can be used. We are reusing the binary converter we saw in earlier
 * examples to demonstrate:
 * a. reading from read-only files included in the /res/raw directory;
 * b. using shared preference files -- this app uses a named file;
 * c. reading and writing to files on internal storage.
 *
 * For a. we read an HTML-formatted file with
 *
 * To make things more complex, bottom navigation is used to move between different screens, which
 * means everything is done in fragments.
 */

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    /**
     * Handling clicks on the bottom navigation menu. For each item, a fragment is created and
     * put into the frame for the fragment.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_conversion:
                    fragment = new ConversionFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_process:
                    fragment = new ProcessFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_history:
                    fragment = new HistoryFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initialiseUI();
        /**
         * If you need to delete the file on internal storage, say for testing without deleting
         * your app, then uncomment this line.
         */
        //HistoryFile.deleteFile(this);
    }

    void initialiseUI() {
        // Let's start on the process screen.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, new ProcessFragment());
        transaction.commit();
    }

}
