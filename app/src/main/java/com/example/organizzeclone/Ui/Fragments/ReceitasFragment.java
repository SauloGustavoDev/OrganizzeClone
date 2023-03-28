package com.example.organizzeclone.Ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;

import com.example.organizzeclone.Model.Movimentacao;
import com.example.organizzeclone.R;
import com.example.organizzeclone.ViewModel.Helper.DateCustom;
import com.example.organizzeclone.ViewModel.ViewModelFragments.ReceitasFragmentViewModel;
import com.example.organizzeclone.databinding.FragmentReceitasBinding;

public class ReceitasFragment extends Fragment {
    private ReceitasFragmentViewModel viewModel = new ReceitasFragmentViewModel();
    private FragmentReceitasBinding binding;
    private Movimentacao movimentacao;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentReceitasBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.edtDataReceitas.setText(DateCustom.dataAtual());
        viewModel.recuperarReceitaTotal();
        binding.fabSalvarReceitas.setOnClickListener(view1 -> {
            verificaCampos(binding.edtDataReceitas.getText().toString(), binding.edtCategoriaReceitas.getText().toString(), binding.edtDescricaoReceitas.getText().toString(), binding.edtValorReceitas.getText().toString());

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void verificaCampos(String data, String categoria, String descricao, String valor){
        if (!data.isEmpty()){
            if (!categoria.isEmpty()){
                if (!descricao.isEmpty()){
                    if (!valor.isEmpty()) {
                        movimentacao = new Movimentacao(data, categoria, descricao, valor);
                        viewModel.salvarDespesa(movimentacao);
                    }else
                        toastCreate("O valor não foi preenchido!");
                }else
                    toastCreate("A descrição não foi preenchida!");
            }else
                toastCreate("A categoria não foi preenchida!");
        }else
            toastCreate("A data não foi preenchida!");
    }

    public void toastCreate(String toastText){
        Toast.makeText(
                getContext(), toastText, Toast.LENGTH_SHORT
        ).show();
    }

}