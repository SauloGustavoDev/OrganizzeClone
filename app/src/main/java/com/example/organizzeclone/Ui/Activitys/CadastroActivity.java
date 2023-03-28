package com.example.organizzeclone.Ui.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.organizzeclone.Model.Usuario;
import com.example.organizzeclone.R;
import com.example.organizzeclone.ViewModel.CadastroActivityViewModel;
import com.example.organizzeclone.databinding.ActivityCadastroBinding;
import com.google.android.material.snackbar.Snackbar;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    CadastroActivityViewModel viewModel = new CadastroActivityViewModel();
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //getSupportActionBar().setTitle("Cadastro");
        //getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.color_primary));


        binding.btnCadastrar.setOnClickListener(view -> {
            veficaCamposCadastro(binding.edtNome.getText().toString(), binding.edtEmail.getText().toString(), binding.edtSenha.getText().toString());
        });
    }

    public void veficaCamposCadastro(String nome, String email, String senha){
        if(!nome.isEmpty()){
            if(!email.isEmpty()){
                if (!senha.isEmpty()){
                    usuario = new Usuario(nome, email, senha);
                    viewModel.cadastrarUsuario(usuario);
                    viewModel.status.observe(this, status ->{
                        if (status == true){
                            toastCreate("Sucesso ao cadastrar usuário!");
                            finish();
                        }else{
                            viewModel.error.observe(this, error ->{
                                toastCreate(error);
                            });
                        }
                    } );
                    }else
                        toastCreate("Preencha a senha!");
                }else
                    toastCreate("Preencha o e-mail!");
            }else
                toastCreate("Preencha o nome!");
        }


    public void toastCreate(String toastText){
        Toast.makeText(
                this, toastText, Toast.LENGTH_SHORT
        ).show();
    }
}