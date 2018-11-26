package joshua.deguzman.com.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    DatabaseHelper myDb;
    private EditText username, password;
    public static String username1, password1;
    private CheckBox checkb;
    private SharedPreferences credentials;
    private static final String PREFS_NAME = "prefName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        checkb = (CheckBox)findViewById(R.id.checkBox1);
        credentials = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        getPreferenceData();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username1 = username.getText().toString();
                password1 = password.getText().toString();

                if(username1.equals("")|| password1.equals("")){
                    toastMessage("Missing Fields! Try Again");
                }
                else{
                    Boolean check = myDb.getData1(username1, password1);
                    if(check==true){
                        if(checkb.isChecked()){
                            Boolean isChck = checkb.isChecked();
                            SharedPreferences.Editor editor = credentials.edit();
                            editor.putString("pref_user", username1);
                            editor.putString("pref_pass", password1);
                            editor.putBoolean("pref_check", isChck);
                            editor.apply();
                        }else{
                            credentials.edit().clear().apply();
                        }
                        toastMessage("Login Successful!");
                        Intent moveStudent = new Intent(getApplicationContext(), shopHome.class);
                        moveStudent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveStudent);
                        finish();
                    }
                    else{
                        toastMessage("Login Failed! Try again");
                    }
                }
            }
        });

        Button signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveGuest = new Intent(getApplicationContext(), studentForm.class);
                moveGuest.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveGuest);
            }
        });

    }

    private void getPreferenceData(){
        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if(sp.contains("pref_user")){
            String a = sp.getString("pref_user", "not found");
            username.setText(a);
        }
        if(sp.contains("pref_pass")){
            String b = sp.getString("pref_pass", "not found");
            password.setText(b);
        }
        if(sp.contains("pref_check")){
            Boolean c = sp.getBoolean("pref_check", false);
            checkb.setChecked(c);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
