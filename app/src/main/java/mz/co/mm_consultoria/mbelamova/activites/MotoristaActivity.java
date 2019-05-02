package mz.co.mm_consultoria.mbelamova.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class MotoristaActivity extends FragmentedActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferencesManager manager;
    private TextView nome_completo;
    private TextView saldo_corrente;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);
        createViews();
    }

    private void createViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = new SharedPreferencesManager(getApplicationContext());
        databaseManager = new DatabaseManager(getApplicationContext());
        setupNavDrawer(toolbar);
    }

    private void setupNavDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_motorista);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Passageiro passageiro = manager.getPassageiro();//Buscar motorista do SharedPrefs
        View headerView =  navigationView.getHeaderView(0);
        nome_completo = headerView.findViewById(R.id.nav_header_nome_completo_motorista);
        nome_completo.setText(passageiro.getNome()+" "+passageiro.getApelido());
        saldo_corrente = headerView.findViewById(R.id.nav_header_saldo_corrente_motorista);
        saldo_corrente.setText("Saldo: "+passageiro.getSaldo_corrente()+" MT");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_motorista);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.motorista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_pagina_inicial_motorista:
                break;
            case R.id.nav_historico_motorista:
                break;
            case R.id.nav_recarregar_mpesa_motorista:
                break;
            case R.id.nav_promocao_motorista:
                break;
            case R.id.nav_configuracao_motorista:
                break;
            case R.id.nav_pedir_boleia:
                databaseManager.changeMotoristaEstado();
                startActivityByClass(MainActivity.class);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_motorista);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
