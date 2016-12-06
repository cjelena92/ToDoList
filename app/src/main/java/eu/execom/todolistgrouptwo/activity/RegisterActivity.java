package eu.execom.todolistgrouptwo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.api.RestApi;
import eu.execom.todolistgrouptwo.model.User;
import eu.execom.todolistgrouptwo.model.dto.RegisterDTO;
import eu.execom.todolistgrouptwo.model.dto.TokenContainerDTO;
import eu.execom.todolistgrouptwo.preference.UserPreferences_;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();


    @RestService
    RestApi restApi;

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @ViewById
    EditText confirmPassword;

    @Pref
    UserPreferences_ userPreferences;

    @EditorAction(R.id.password)
    @Click
    void register() {
        final String email = this.email.getText().toString();
        final String password = this.password.getText().toString();
        final String confirmPassword = this.confirmPassword.getText().toString();
        final User user = new User(email, password,confirmPassword);
        registerUser(user);
    }



    @Background
    void registerUser(User user) {
        RegisterDTO registerDTO = new RegisterDTO(user.getEmail(),user.getPassword(),
                user.getConfirmPassword());

        try {
            restApi.register(registerDTO);
        } catch(RestClientException e) {
            Log.e(TAG, e.toString());
            showRegisterError();
            return;
        }
           /* final TokenContainerDTO tokenContainerDTO = restApi.login(accountInfo(user));
            userPreferences.accessToken().put(tokenContainerDTO.getAccessToken());*/
            login(user);
        }


    public LinkedMultiValueMap<String, String> accountInfo(User user) {
        final LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("grant_type", "password");
        map.set("username", user.getEmail());
        map.set("password", user.getPassword());
        return map;
    }
    @UiThread
    void login(User user) {
        final Intent intent = new Intent();
        intent.putExtra("user_id", user.getId());

        setResult(RESULT_OK, intent);
        finish();
    }

    @UiThread
    void showRegisterError() {
        Toast.makeText(this,
                "Email already exists.",
                Toast.LENGTH_SHORT)
                .show();
        //email.setError("Email already exists.");
    }

}
