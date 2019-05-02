package mz.co.mm_consultoria.mbelamova.activites;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;

public class FragmentedActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    public FragmentedActivity() {
        this.fragmentManager = new FragmentManager(FragmentedActivity.this);
    }

    public void displayFragmentBackStack(Fragment fragment, int id){
        fragmentManager.replaceCurrentFragmentBackStack(getSupportFragmentManager(), fragment, id);
    }

    public void displayFragment(Fragment fragment, int id){
        fragmentManager.replaceCurrentFragment(getSupportFragmentManager(), fragment, id);
    }

    public void startActivityByClass(Class aClass) {
        Intent i = new Intent(getApplicationContext(), aClass);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
