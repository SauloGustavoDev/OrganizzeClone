package com.example.organizzeclone.Ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.organizzeclone.Model.Movimentacao;
import com.example.organizzeclone.R;
import com.example.organizzeclone.ViewModel.Helper.DateCustom;
import com.example.organizzeclone.ViewModel.ViewModelFragments.DespesasFragmentViewModel;
import com.example.organizzeclone.databinding.FragmentDespesasBinding;
import com.example.organizzeclone.databinding.FragmentReceitasBinding;

public class DespesasFragment extends Fragment {
    private DespesasFragmentViewModel viewModel= new DespesasFragmentViewModel();
    private FragmentDespesasBinding binding;
    private Movimentacao movimentacao;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDespesasBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.edtDataDespesas.setText(DateCustom.dataAtual());

        binding.fabSalvarDespesas.setOnClickListener(view1 -> {
        verificaCampos(binding.edtDataDespesas.getText().toString(), binding.edtCategoriaDespesas.getText().toString(), binding.edtDescricaoDespesas.getText().toString(), binding.edtValorDespesas.getText().toString());
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
                    }
                }
            }
        }
    }

}