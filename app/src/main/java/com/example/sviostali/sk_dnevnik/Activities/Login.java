package com.example.sviostali.sk_dnevnik.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sviostali.sk_dnevnik.GetUsersFromJSON;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText etLUsername, etLPassword;
    Button btnLBack, btnLLogin, btnLRegister;
    public String username, password;
    public int id;
    public GetUsersFromJSON g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_login);

        etLUsername = findViewById(R.id.etLUsername);
        etLPassword = findViewById(R.id.etLPassword);
        btnLBack = findViewById(R.id.btnLBack);
        btnLLogin = findViewById(R.id.btnLLogin);
        btnLRegister = findViewById(R.id.btnLRegister);

        g = new GetUsersFromJSON(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String store = preferences.getString("stored", "");
        if(store.equalsIgnoreCase(""))
        {
            g.getData();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("stored","Data is stored");
            editor.commit();
        }

        /**
         * 3 onclicklistenera, login posalje samo username pa sam mislio napravit da userinfo prihvati username,
         * pretrazi bazu i zatim ispise podatke ovisno o tome da li se radi o profesoru ili uceniku
         * **/

        btnLBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

        btnLLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !etLPassword.getText().toString().equals("") && !etLUsername.getText().toString().equals("")){ //Lozinka i korisnicko moraju biti popunjeni
                    if(checkLogin()==true){
                        Intent i = new Intent(Login.this, UserInfo.class);
                        i.putExtra("id", id);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this, "Korisničko ime/lozinka ne postoji/netocno.", Toast.LENGTH_SHORT).show();
                        etLPassword.setText("");
                        etLUsername.setText("");
                    }
                }else{
                    Toast.makeText(Login.this, "Niste unijeli korsnicko ime/lozinku.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    public boolean checkLogin(){

        List<usersugar> allUsers = usersugar.listAll(usersugar.class);

        boolean rtrn = false;
        if(allUsers.isEmpty()){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 0;i<allUsers.size();i++) {
                String a = allUsers.get(i).getLogin();
                String b = allUsers.get(i).getPassword();

                if ((a.equals(etLUsername.getText().toString())) && (b.equals(etLPassword.getText().toString()))) {
                    username = a;
                    password = b;
                    id = Integer.parseInt(String.valueOf(allUsers.get(i).getId()));
                    rtrn = true;
                    Toast.makeText(this, "Uspjesno ulogirani!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return rtrn;
    }
}
