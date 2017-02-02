package com.dev.nessotech.blankproject.navfragments;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dev.nessotech.blankproject.utils.Delayer;

/**
 * Created by DanLuciano on 12/21/2016.
 */

public class BaseFragment extends Fragment {
    int secondsDelayBeforeReady = 1;

    @Override
    public void onStart() {
        super.onStart();
        Delayer.delay(secondsDelayBeforeReady, new Delayer.DelayCallback() {
            @Override
            public void afterDelay() {
                onReady();
            }
        });
    }

    public void onReady()
    {
        Toast.makeText(getActivity(), "Fragment Attached", Toast.LENGTH_SHORT).show();
    }

}
