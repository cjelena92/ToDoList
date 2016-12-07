package eu.execom.todolistgrouptwo.activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.RestClientException;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.adapter.TaskAdapter;
import eu.execom.todolistgrouptwo.api.RestApi;
import eu.execom.todolistgrouptwo.model.Task;
import eu.execom.todolistgrouptwo.preference.UserPreferences_;

@EActivity(R.layout.activity_details)
@OptionsMenu(R.menu.menu_items)
public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    protected static final int LOGIN_REQUEST_CODE = 420;

    @ViewById
    TextInputEditText title;

    @ViewById
    TextInputEditText description;

    @ViewById
    Button save;

    @ViewById
    Button done;

    @OptionsMenuItem
    MenuItem menuLogout;

    @Extra
    String detail;

    @RestService
    RestApi restApi;

    @Pref
    UserPreferences_ userPreferences;

    Task task;

    public static final int HOME_RESULT = 1;

    @AfterViews
    void fillField(){
        final Gson gson = new Gson();
        task = gson.fromJson(detail,Task.class);
        title.setText(task.getTitle());
        description.setText(task.getDescription());
    }

    @Background
    @Click
    void save(){
        String newTitle = title.getText().toString();
        String newDescription = description.getText().toString();
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        try{
            task = restApi.updataTask(task.getId(),task);
            Toast.makeText(this, R.string.update, Toast.LENGTH_SHORT).show();
        }catch(RestClientException e){
            Log.e(TAG, e.getMessage(), e);
        }
        HomeActivity_.intent(this).startForResult(HOME_RESULT);

    }

    @Background
    @Click
    void done(){
        String newTitle = title.getText().toString();
        String newDescription = description.getText().toString();
        boolean finished =true;
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        task.setFinished(finished);
        try{
            task = restApi.updataTask(task.getId(),task);
            Toast.makeText(this, R.string.task_done, Toast.LENGTH_SHORT).show();
        }catch(RestClientException e){
            Log.e(TAG, e.getMessage(), e);
        }
        HomeActivity_.intent(this).startForResult(HOME_RESULT);
    }

    @OptionsItem
    boolean menuLogout(){
        userPreferences.accessToken().remove();
        LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
        return true;
    }

}