package com.example.organizzeclone.Ui.Activitys;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.organizzeclone.R;
import com.example.organizzeclone.Ui.Fragments.DespesasFragment;
import com.example.organizzeclone.Ui.Fragments.ReceitasFragment;
import com.example.organizzeclone.databinding.ActivityPrincipalBinding;
import com.github.clans.fab.FloatingActionMenu;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.menuReceita.setOnClickListener(view ->{
            replaceFragment(new ReceitasFragment());
        });

        binding.menuDespesa.setOnClickListener(view ->{
            replaceFragment(new DespesasFragment());
        });
    }


    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_content);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
}