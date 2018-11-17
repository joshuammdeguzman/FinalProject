package joshua.deguzman.com.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class jacketOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jacket_order);

        Button cancelJacketButton = findViewById(R.id.cancelJacketButton);

        cancelJacketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(getApplicationContext(), shopHome.class);
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });
    }
}