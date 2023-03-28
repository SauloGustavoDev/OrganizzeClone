package com.example.organizzeclone.Ui.Activitys;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;




import com.example.organizzeclone.R;
import com.example.organizzeclone.Ui.Fragments.DespesasFragment;
import com.example.organizzeclone.Ui.Fragments.ReceitasFragment;
import com.example.organizzeclone.ViewModel.PrincipalActivityViewModel;
import com.example.organizzeclone.databinding.ActivityPrincipalBinding;
import com.example.organizzeclone.databinding.ContentPrincipalBinding;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class PrincipalActivity extends AppCompatActivity {


    private ActivityPrincipalBinding binding;
    private ContentPrincipalBinding bindingContent;
    PrincipalActivityViewModel viewModel = new PrincipalActivityViewModel();
    
    private View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());

        //Salvando a referencia de layout para utilizar no FrameLayout, assim não tendo o problema de sobrepor os fragments
        bindingContent = ContentPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("Organizze");
        setSupportActionBar(binding.toolbar);

        view1 = getLayoutInflater().inflate(R.layout.content_principal, null);
        binding.fragmentContent.addView(view1);
        
        viewModel.configuraCalendarView(findViewById(R.id.calendarView));


        binding.menuReceita.setOnClickListener(view ->{
            replaceFragment(new ReceitasFragment());
        });

        binding.menuDespesa.setOnClickListener(view ->{
            replaceFragment(new DespesasFragment());
        });
    }


    public void replaceFragment(Fragment fragment){
        binding.fragmentContent.removeAllViews();
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
        binding.fragmentContent.removeAllViews();
        binding.fragmentContent.addView(view1);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSair:
                viewModel.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}