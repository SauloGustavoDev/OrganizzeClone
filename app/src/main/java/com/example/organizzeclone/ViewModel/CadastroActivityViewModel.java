package com.example.organizzeclone.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.organizzeclone.Model.Usuario;
import com.example.organizzeclone.Ui.Activitys.Config.ConfiguracaoFirebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivityViewModel extends ViewModel{
    private final FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    public MutableLiveData<Boolean> status = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();


    public void cadastrarUsuario(Usuario usuario) {
        status.postValue(false);
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                status.postValue(true);
            }else {
                try{
                    throw task.getException();
                }catch (FirebaseAuthWeakPasswordException e){
                    error.postValue("Digite uma senha mais forte");
                }catch (FirebaseAuthInvalidCredentialsException e){
                    error.postValue("Por favor, digite um e-mail válido");
                }catch (FirebaseAuthUserCollisionException e){
                    error.postValue("Esta conta já foi cadastrada");
                }catch (Exception e){
                    error.postValue("Erro ao cadastrar usuário: " + e.getMessage());
                    e.printStackTrace();
                }
                status.postValue(false);
            }

        });
    }
    }

