package eu.execom.todolistgrouptwo.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.model.Task;
import eu.execom.todolistgrouptwo.preference.UserPreferences_;

/**
 * {@link AppCompatActivity Activity} for creating a new task.
 */
@EActivity(R.layout.activity_add_task)
@OptionsMenu(R.menu.menu_items)
public class AddTaskActivity extends AppCompatActivity {

    protected static final int LOGIN_REQUEST_CODE = 420;

    /**
     * Title input {@link TextInputEditText TextInputEditText}.
     */
    @ViewById
    TextInputEditText title;

    /**
     * Description input {@link TextInputEditText TextInputEditText}.
     */
    @ViewById
    TextInputEditText description;

    @OptionsMenuItem
    MenuItem menuLogout;

    @Pref
    UserPreferences_ userPreferences;
    /**
     * Called when the Save Task {@link android.widget.Button Button} is clicked.
     * Sets the result (the new task) so that the {@link HomeActivity HomeActivity} can read it.
     */
    @Click
    void saveTask() {
        final Task task = new Task(title.getText().toString(),
                description.getText().toString());
        final Intent intent = new Intent();
        final Gson gson = new Gson();
        intent.putExtra("task", gson.toJson(task));
        setResult(RESULT_OK, intent);
        finish();
    }

    @OptionsItem
    boolean menuLogout(){
        userPreferences.accessToken().remove();
        LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
        return true;
    }
}
