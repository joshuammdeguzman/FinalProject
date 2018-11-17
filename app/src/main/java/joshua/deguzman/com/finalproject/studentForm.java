package joshua.deguzman.com.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class studentForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Button cancelStudent = findViewById(R.id.cancelStudent);
        Button submitStudent = findViewById(R.id.submitStudent);

        cancelStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getApplicationContext(), MainActivity.class);
                cancel.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(cancel);
            }
        });

        submitStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submit = new Intent(getApplicationContext(), shopHome.class);
                submit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(submit);
            }
        });
    }
}
