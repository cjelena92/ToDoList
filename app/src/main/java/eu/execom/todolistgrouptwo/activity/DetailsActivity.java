package eu.execom.todolistgrouptwo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.adapter.TaskAdapter;

@EActivity(R.layout.activity_details)
public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    @ViewById
    EditText title;

    @ViewById
    EditText description;

    @ViewById
    Button save;

    @ViewById
    Button delete;

    @ViewById
    Button done;




}