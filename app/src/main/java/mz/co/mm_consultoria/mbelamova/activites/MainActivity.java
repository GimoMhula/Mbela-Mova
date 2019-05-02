package mz.co.mm_consultoria.mbelamova.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.fragments.PassageiroMapaFragment;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class MainActivity extends FragmentedActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferencesManager manager;
    private ImageView fotoPerfil;
    private TextView textView;
    private TextView nome_completo;
    private TextView saldo_corrente;
    private DatabaseManager databaseManager;

    public MainActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createViews();
    }

    private void createViews() {
        Toolbar toolbar = findViewById(R.id.toolbar_passageiro);
        setSupportActionBar(toolbar);
        manager = new SharedPreferencesManager(getApplicationContext());
        databaseManager = new DatabaseManager(getApplicationContext());
        setupNavDrawer(toolbar);
        displayFragment(new PassageiroMapaFragment(), R.id.content_main_passageiros_layout);
    }

    private void setupNavDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_passageiro);
        NavigationView navigationView = findViewById(R.id.nav_view_passageiro);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Passageiro passageiro = manager.getPassageiro();//Buscar passageiro do SharedPrefs
        View headerView =  navigationView.getHeaderView(0);
        nome_completo = headerView.findViewById(R.id.nav_header_nome_completo);
        nome_completo.setText(passageiro.getNome()+" "+passageiro.getApelido());
        saldo_corrente = headerView.findViewById(R.id.nav_header_saldo_corrente);
        saldo_corrente.setText("Saldo: "+passageiro.getSaldo_corrente()+" MT");
        TextView view = headerView.findViewById(R.id.text_view_passageiro_placeholder);
        String duas_primeiras_nome = passageiro.getNome().substring(0,1)+passageiro.getApelido().substring(0,1);
        view.setText(duas_primeiras_nome.toUpperCase());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_passageiro);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
        switch (item.getItemId()){
            case R.id.nav_pagina_inicial:
                break;
            case R.id.nav_historico:
                break;
            case R.id.nav_recarregar_mpesa:
                break;
            case R.id.nav_promocao:
                break;
            case R.id.nav_configuracao:
                break;
            case R.id.nav_dar_boleia:
                if(manager.getCarro().getMatricula()==null){
                    startActivity(new Intent(getApplicationContext(), RegistoCaroActivity.class));
                }else{
                    databaseManager.changeMotoristaEstado(true);
                    startActivityByClass(MotoristaActivity.class);
                }
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_passageiro);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
