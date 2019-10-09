package com.example.sviostali.sk_dnevnik.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;

public class ChangePassword extends AppCompatActivity {

    public Button btnCP1;
    public EditText etCP1, etCP2, etCP3;
    public usersugar user;
    public String oldpassword, newpassword1, newpassword2;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_change_password);

        Bundle s = getIntent().getExtras();
        id = s.getInt("id");
        user=usersugar.findById(usersugar.class, id);

        btnCP1 = findViewById(R.id.btnCP1);
        etCP1 = findViewById(R.id.etCP1);
        etCP2 = findViewById(R.id.etCP2);
        etCP3 = findViewById(R.id.etCP3);

        btnCP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldpassword = etCP1.getText().toString();
                newpassword1 = etCP2.getText().toString();
                newpassword2 = etCP3.getText().toString();

                if(oldpassword.equals("") && etCP2.equals("") && etCP3.equals("")){
                    Toast.makeText(ChangePassword.this, "Unesite sve podatke", Toast.LENGTH_SHORT).show();
                }else {
                    if (newpassword1.length() >= 4) {
                        if (user.getPassword().equals(oldpassword)) {
                            if (newpassword1.equals(newpassword2)) {
                                user.setPassword(newpassword1);
                                user.save();
                                Intent i = new Intent(ChangePassword.this, UserInfo.class);
                                i.putExtra("id", id);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(ChangePassword.this, "Lozinke se ne podudaraju.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ChangePassword.this, "Stara lozinka je netocna.", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ChangePassword.this, "Lozinka bi trebala imati najmanje 4 znaka.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}