package joshua.deguzman.com.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class shopHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_home);

        Button shirtOrderButton = findViewById(R.id.shirtOrderButton);
        Button jacketOrderButton = findViewById(R.id.jacketOrderButton);
        Button lanyardOrderButton = findViewById(R.id.lanyardOrderButton);
        Button capOrderButton = findViewById(R.id.capOrderButton);

        shirtOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveShirt = new Intent(getApplicationContext(), shirtOrder.class);
                moveShirt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveShirt);
            }
        });

        jacketOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveShirt = new Intent(getApplicationContext(), jacketOrder.class);
                moveShirt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveShirt);
            }
        });

        lanyardOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveShirt = new Intent(getApplicationContext(), lanyardOrder.class);
                moveShirt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveShirt);
            }
        });

        capOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveShirt = new Intent(getApplicationContext(), capOrder.class);
                moveShirt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveShirt);
            }
        });

    }
}
