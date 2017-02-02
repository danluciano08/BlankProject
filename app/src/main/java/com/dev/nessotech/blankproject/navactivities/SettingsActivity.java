package com.dev.nessotech.blankproject.navactivities;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dev.nessotech.blankproject.BaseCompatActivity;

/**
 * Created by DanLuciano on 12/21/2016.
 */

public class SettingsActivity extends BaseCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this, getSupportActionBar().getTitle(), Toast.LENGTH_SHORT).show();
    }

}
