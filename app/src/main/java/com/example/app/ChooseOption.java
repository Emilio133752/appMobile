package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChooseOption extends AppCompatActivity {

    private boolean isPhoneLogin; // Variável para armazenar o tipo de login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_choose);

        // Recebe a informação do tipo de login
        isPhoneLogin = getIntent().getBooleanExtra("isPhoneLogin", false);

        // Configuração do padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referências para os cards (substituindo os botões)
        CardView btnBarbeiro = findViewById(R.id.btnBarbeiro);
        CardView btnCliente = findViewById(R.id.btnCliente);

        // Ação de clique para abrir a tela de Registro como Barbeiro
        btnBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaRegistro(true);
            }
        });

        // Ação de clique para abrir a tela de Registro como Cliente
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaRegistro(false);
            }
        });
    }

    // Método para abrir a tela de registro e passar os parâmetros necessários
    private void abrirTelaRegistro(boolean isBarbeiro) {
        Intent intent;

        if (isPhoneLogin) {
            // Se for login por telefone, abre a tela de registro de telefone
            intent = new Intent(ChooseOption.this, SendCodeActivity.class);
        } else {
            // Se for login por e-mail, abre a tela de registro normal
            intent = new Intent(ChooseOption.this, RegisterActivity.class);
        }

        intent.putExtra("isBarbeiro", isBarbeiro);
        intent.putExtra("isPhoneLogin", isPhoneLogin);
        startActivity(intent);
    }
}