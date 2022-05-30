package br.com.agenda_de_contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.agenda_de_contatos.DB.DbContatos;

public class AddItemActivity extends AppCompatActivity {

    EditText txtNome, txtTelefone,txtCorreioEletronico;
    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        txtNome=findViewById(R.id.txtNome);
        txtTelefone=findViewById(R.id.txtTelefone);
        txtCorreioEletronico=findViewById(R.id.txtCorreioEletronico);
        btnGuarda=findViewById(R.id.btnGuarda);
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContatos dbContatos = new DbContatos(AddItemActivity.this);
                long id = dbContatos.inserirContatos(txtNome.getText().toString(),
                        txtTelefone.getText().toString(), txtCorreioEletronico.getText().toString());
                if (id > 0){
                    voltarTelaInicial();
                    Toast.makeText(AddItemActivity.this, "Contato Salvo", Toast.LENGTH_LONG).show();
                    limpar();
                }else {
                    Toast.makeText(AddItemActivity.this, "Erro", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    private void limpar(){
        txtNome.setText("");
        txtTelefone.setText("");
        txtCorreioEletronico.setText("");
    }
    private void voltarTelaInicial(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}