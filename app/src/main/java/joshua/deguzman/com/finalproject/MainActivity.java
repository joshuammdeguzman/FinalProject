package joshua.deguzman.com.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button icsButton = findViewById(R.id.icsButton);

        icsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveStudent = new Intent(getApplicationContext(), studentForm.class);
                moveStudent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveStudent);
            }
        });

        Button guestButton = findViewById(R.id.guestButton);

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveGuest = new Intent(getApplicationContext(), guestForm.class);
                moveGuest.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveGuest);
            }
        });

    }
}
