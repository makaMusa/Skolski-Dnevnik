package com.example.sviostali.sk_dnevnik.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import com.google.android.material.navigation.NavigationView;
import com.orm.SugarContext;

public class UserInfo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // tvui1,itd radi lakseg gledanja, avatar isto tako
    Button btnUI1, btnUI2;
    TextView tvUI1, tvUI2, tvUI3, tvUI4, tvDrawer;
    ImageView avatar;
    public String avatarurl;
    public int id;
    ImageView imgvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_user_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvUI1 = findViewById(R.id.tvUIUsername);
        tvUI2 = findViewById(R.id.tvUIProsjek);
        tvUI3 = findViewById(R.id.tvUIRedovitost);
        tvUI4 = findViewById(R.id.tvUIRodjendan);
        avatar = findViewById(R.id.iv_uiavatar);
        btnUI1 = findViewById(R.id.btnUI1);
        btnUI2 = findViewById(R.id.btnUI2);


        /** Iz bundlea uzme username, i u showdata provjeri username i pokupi ostale podatke da ih moze koristiti*/
        Bundle s = getIntent().getExtras();

        id = s.getInt("id");
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        tvDrawer = headerView.findViewById(R.id.textView);
        imgvw = headerView.findViewById(R.id.imageView);
        tvDrawer = headerView.findViewById(R.id.textView);
        tvDrawer.setText("AHSHASFHASFHASFHHASF");
        tvDrawer.setVisibility(View.VISIBLE);
        tvDrawer.setTextColor(Color.BLACK);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        showData(id);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;
        switch (id) {
            case R.id.change_info:
                i = new Intent(UserInfo.this, ChangeInfo.class);
                i.putExtra("id", this.id);
                startActivity(i);
                item.setChecked(true);
                break;
            case R.id.change_pass:
                i = new Intent(UserInfo.this, ChangePassword.class);
                i.putExtra("id", this.id);
                startActivity(i);
                item.setChecked(true);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    public void showData(final int id) {

        usersugar user = usersugar.findById(usersugar.class, id);

        tvUI1.setText(user.getLogin());
        tvUI1.setGravity(Gravity.CENTER);
        tvUI2.setText("Ime: " + user.getFirstname());
        tvUI3.setText("Prezime: " + user.getLastname());
        avatarurl = user.getAvatar();
        Glide.with(this)
                .load(avatarurl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(avatar);
        Glide.with(this)
                .load(avatarurl)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgvw);
        tvDrawer.setText(user.getFirstname() + " " + user.getLastname());
        avatarurl = user.getAvatar();

        tvUI4.setText(user.getBirthdate());
        if (user.getProfessor() == 1) { //Gornje za profesore, donje za studente

            btnUI1.setText("Vaši predmeti");// dodat onclicklistenere za sva 4 kad napravimo predmete i ocjene itd
            btnUI2.setText("Dodavanje predmeta");

            btnUI1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(UserInfo.this, SelectedSubjectsActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
            });

            btnUI2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(UserInfo.this, SubjectListActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
            });
        } else {
            btnUI1.setText("Popis predmeta");
            btnUI2.setText("Ocjene");

            btnUI2.setVisibility(View.GONE);

            btnUI1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(UserInfo.this, StudentsSubjects.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
            });
        }
    }
}