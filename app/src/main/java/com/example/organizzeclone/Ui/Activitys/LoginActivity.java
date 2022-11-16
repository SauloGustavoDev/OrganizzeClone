package com.example.organizzeclone.Ui.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.organizzeclone.Model.Usuario;
import com.example.organizzeclone.R;
import com.example.organizzeclone.ViewModel.LoginActivityViewModel;
import com.example.organizzeclone.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel viewModel = new LoginActivityViewModel();
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signBtn.setOnClickListener(view ->{
            verificaCamposLogin(binding.edtEmail.getText().toString(), binding.edtSenha.getText().toString());
        });
    }

    public void verificaCamposLogin(String email, String senha) {
        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                usuario.setEmail(email);
                usuario.setSenha(senha);
                viewModel.validarLogin(usuario);
                viewModel.status.observe(this, status ->{
                    if (status == true){
                        toastCreate("Sucesso ao autenticar!");
                        finish();
                    }else {
                        viewModel.error.observe(this, error -> {
                            toastCreate(error);
                        });
                    }
                });
            }else
                toastCreate("Preencha a senha!");
        } else
            toastCreate("Preencha o e-mail!");

    }

    public void toastCreate(String toastText){
        Toast.makeText(
                this, toastText, Toast.LENGTH_SHORT
        ).show();
    }
    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}