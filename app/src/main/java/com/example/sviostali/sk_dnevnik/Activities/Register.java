package com.example.sviostali.sk_dnevnik.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;

public class Register extends AppCompatActivity {

    EditText etRFName, etRLName, etRUsername, etRPassword1, etRPassword2, etRDateOfBirth;
    Button btnRRegister, btnRReset;

    public String firstName, lastName, username, password, dateofbirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_register);

        etRFName = findViewById(R.id.etRFName);
        etRLName = findViewById(R.id.etRLName);
        etRUsername = findViewById(R.id.etRUsername);
        etRPassword1 = findViewById(R.id.etRPassword1);
        etRPassword2 = findViewById(R.id.etRPassword2);
        etRDateOfBirth = findViewById(R.id.etRDateOfBirth);
        btnRRegister = findViewById(R.id.btnRRegister);
        btnRReset = findViewById(R.id.btnRReset);

        btnRRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIfEmpty()==false) {
                    if(validPass() == true) {
                            insertData();
                    }
                }
                else{
                    Toast.makeText(Register.this, "Nepotpuna registracijska forma!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnRReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearETs();
            }
        });
    }

    private void clearETs() {
        etRFName.setText("");
        etRLName.setText("");
        etRUsername.setText("");
        etRPassword1.setText("");
        etRPassword2.setText("");
        etRDateOfBirth.setText("");
    }

    private boolean passwordsMatch() {
        if(etRPassword1.getText().toString().equals(etRPassword2.getText().toString())) {
            return true;
        }
        else{
            Toast.makeText(Register.this, "Lozinke nisu jednake.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void insertData() {
        firstName = etRFName.getText().toString();
        lastName = etRLName.getText().toString();
        username = etRUsername.getText().toString();
        password = etRPassword1.getText().toString();
        dateofbirth = etRDateOfBirth.getText().toString();

        String avatar = "https://www.iconfinder.com/data/icons/rcons-user-action/32/boy-512.png";

        usersugar user = new usersugar(username,password,avatar,firstName,lastName,dateofbirth,1);
        user.save();

        Toast.makeText(this, "Uspješno ste se registrirali! Sada se možete prijaviti.", Toast.LENGTH_LONG).show();
        finish();
    }

    private boolean checkIfEmpty() {
        boolean cie = false;
        if(etRFName.getText().toString().equals(""))
            cie=true;
        if(etRLName.getText().toString().equals(""))
            cie=true;
        if(etRUsername.getText().toString().equals(""))
            cie=true;
        if(etRDateOfBirth.getText().toString().equals(""))
            cie=true;
        if(etRPassword1.getText().toString().equals(""))
            cie=true;
        if(etRPassword2.getText().toString().equals(""))
            cie=true;
        return cie;
    }

    private boolean validPass() {
        if(passwordsMatch() == true){
            password = etRPassword1.getText().toString();
            if(password.length()>=4){
                return true;
            }
            else{
                Toast.makeText(this, "Lozinka bi trebala imati najmanje 4 znaka.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            return false;
        }
    }
}
