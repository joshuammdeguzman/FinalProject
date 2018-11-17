package joshua.deguzman.com.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class guestForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);

        Button cancelGuest = findViewById(R.id.cancelGuest);
        Button submitGuest = findViewById(R.id.submitGuest);

        cancelGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getApplicationContext(), MainActivity.class);
                cancel.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(cancel);
            }
        });

        submitGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submit = new Intent(getApplicationContext(), shopHome.class);
                submit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(submit);
            }
        });
    }
}
