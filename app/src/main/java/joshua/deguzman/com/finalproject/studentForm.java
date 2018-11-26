package joshua.deguzman.com.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class studentForm extends AppCompatActivity {
    DatabaseHelper myDb;
    private EditText snum, fname, lname, cnum, email, pass, cpass;
    private  String snum1, fname1, lname1, cnum1, email1, pass1, cpass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Button cancelStudent = findViewById(R.id.cancelStudent);
        Button submitStudent = findViewById(R.id.submitStudent);
        snum = (EditText)findViewById(R.id.snum);
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        cnum = (EditText)findViewById(R.id.cnum);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        cpass = (EditText)findViewById(R.id.cpass);

        myDb = new DatabaseHelper(this);


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
                snum1 = snum.getText().toString();
                fname1 = fname.getText().toString();
                lname1 = lname.getText().toString();
                cnum1 = cnum.getText().toString();
                email1 = email.getText().toString();
                pass1 = pass.getText().toString();
                cpass1 = cpass.getText().toString();

                if(snum1.length() != 0 && fname1.length() != 0 && lname1.length() != 0 && cnum1.length() != 0 && email1.length() != 0 && pass1.length() != 0 && cpass1.length() != 0)
                {
                    if(pass1.equals(cpass1)){
                        AddData1(snum1, fname1, lname1, cnum1, email1, pass1);
                        Intent submit = new Intent(getApplicationContext(), MainActivity.class);
                        submit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(submit);
                    }
                    else{
                        toastMessage("Password and Confirm Password not match");
                    }
                }
                else{
                    toastMessage("Please complete the form!");
                }

            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void AddData1(String item, String item2, String item3, String item4, String item5, String item6) {
        boolean insertData = myDb.addData(item, item2, item3, item4, item5, item6);

        if (insertData) {
            toastMessage("Account Created Successfully!");
        } else {
            toastMessage("Account Creation Failed! Try Again");
        }
    }
}
