package mz.co.mm_consultoria.mbelamova.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;

import mz.co.mm_consultoria.mbelamova.fragments.RegistoDadosFragment;

public class FragmentManager {
    private Context context;

    public FragmentManager(Context context) {
        this.context=context;
    }

    public void replaceCurrentFragment(android.support.v4.app.FragmentManager fragmentManager, Fragment fragment, int fragment_container_id){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragment_container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
