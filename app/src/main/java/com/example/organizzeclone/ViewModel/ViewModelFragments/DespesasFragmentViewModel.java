package com.example.organizzeclone.ViewModel.ViewModelFragments;

import androidx.lifecycle.ViewModel;

import com.example.organizzeclone.Model.Movimentacao;
import com.example.organizzeclone.Ui.Activitys.Config.ConfiguracaoFirebase;
import com.example.organizzeclone.ViewModel.Helper.Base64Custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class DespesasFragmentViewModel extends ViewModel {

    public void salvarDespesa(Movimentacao movimentacao){
        movimentacao.salvar(movimentacao.getData());
    }


}
