package com.example.organizzeclone.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.organizzeclone.Model.Usuario;
import com.example.organizzeclone.Ui.Activitys.Config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivityViewModel extends ViewModel {
    private final FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    public MutableLiveData<Boolean> status= new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();

    public void validarLogin(Usuario usuario){
        status.postValue(false);
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener( task -> {
            if (task.isSuccessful()){
                status.postValue(true);
            }else {
                try {
                    throw task.getException();
                }catch (FirebaseAuthInvalidUserException e){
                    error.postValue("Usuário não está cadastrado!");
                }catch (FirebaseAuthInvalidCredentialsException e){
                    error.postValue("E-mail ou senha estão incorretos!");
                }catch (Exception e){
                    error.postValue("Erro ao autenticar usuário: " + e.getMessage());
                }
            }
        });
    }
}
