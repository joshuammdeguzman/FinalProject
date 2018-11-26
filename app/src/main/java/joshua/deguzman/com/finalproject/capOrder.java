package joshua.deguzman.com.finalproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class capOrder extends AppCompatActivity implements View.OnClickListener{
    Button btnOrderCap;
    DatabaseHelper myDb;
    RadioGroup capColor;
    EditText capQuantity;
    RadioButton capColorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_order);

        capColor = (RadioGroup)findViewById(R.id.capColor);
        capQuantity = (EditText)findViewById(R.id.capQuantity);

        btnOrderCap = (Button)findViewById(R.id.btnOrderCap);
        btnOrderCap.setOnClickListener(this);
        myDb = new DatabaseHelper(this);

        Button cancelCapButton = findViewById(R.id.cancelCapButton);
        cancelCapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(getApplicationContext(), shopHome.class);
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });
    }

    private void addItemToSheet() {
        int colorId = capColor.getCheckedRadioButtonId();

        capColorText =(RadioButton)findViewById(colorId);

        final String color = capColorText.getText().toString();
        final String quantity = capQuantity.getText().toString();

        String username=MainActivity.username1;
        Cursor data = myDb.getData2(username);
        final ProgressDialog loading = ProgressDialog.show(this, "Submitting Order", "Please wait");
        final String sNo = data.getString(1);
        final String lName = data.getString(3);
        final String fName = data.getString(2);
        final String cNo = data.getString(4);
        final String email = data.getString(5);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwDJmW02ZruZOYdbV1xeXTiyco9MSPgNDEpyALgPm5fHMuCaKo/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(capOrder.this, response, Toast.LENGTH_LONG).show();
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action", "addItem");
                parmas.put("sNo", sNo);
                parmas.put("lName", lName);
                parmas.put("fName", fName);
                parmas.put("cNo", cNo);
                parmas.put("email", email);
                parmas.put("size", "");
                parmas.put("color", color);
                parmas.put("quantity", quantity);
                parmas.put("order", "Cap");

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if(v==btnOrderCap){
            if(capColor.getCheckedRadioButtonId() == -1 || TextUtils.isEmpty(capQuantity.getText())){
                Toast.makeText(this, "Please fill up form completely.", Toast.LENGTH_SHORT).show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Order", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                addItemToSheet();
                            }
                        })
                        .setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }
}
