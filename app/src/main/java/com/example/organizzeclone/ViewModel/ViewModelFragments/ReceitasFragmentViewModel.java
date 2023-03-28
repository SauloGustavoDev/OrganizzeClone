package com.example.organizzeclone.ViewModel.ViewModelFragments;

import androidx.lifecycle.ViewModel;

import com.example.organizzeclone.Model.Movimentacao;
import com.example.organizzeclone.Model.Usuario;
import com.example.organizzeclone.Ui.Activitys.Config.ConfiguracaoFirebase;
import com.example.organizzeclone.ViewModel.Helper.Base64Custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasFragmentViewModel extends ViewModel {
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double receitaTotal;

    public void salvarDespesa(Movimentacao movimentacao){
        Double receitaAtualizada = receitaTotal + movimentacao.getValor();
        atualizarReceita(receitaAtualizada);
        movimentacao.setTipo("r");
        movimentacao.salvar(movimentacao.getData());
        recuperarReceitaTotal();
    }

    public void recuperarReceitaTotal(){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void atualizarReceita(Double receita){
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("receitaTotal").setValue(receita);


    }

}
