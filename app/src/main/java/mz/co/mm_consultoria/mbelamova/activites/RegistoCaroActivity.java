package mz.co.mm_consultoria.mbelamova.activites;


import android.os.Bundle;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.fragments.RegistoCaroIdentificacoFragment;

public class RegistoCaroActivity extends FragmentedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista_registo_caro);
        displayFragment(new RegistoCaroIdentificacoFragment(), R.id.activity_registo_caro_layout);
    }

}
