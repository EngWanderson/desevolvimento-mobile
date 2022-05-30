package br.com.agenda_de_contatos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.agenda_de_contatos.DB.DbContatos;
import br.com.agenda_de_contatos.entidades.Contatos;

public class EditarItem extends AppCompatActivity {
    EditText txtNome, txtTelefone, txtCorreio;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correto = false;
    Contatos contato;
    int id = 0;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_item2);

        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtCorreio = findViewById(R.id.txtCorreioEletronico);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbContatos dbContatos = new DbContatos(EditarItem.this);
        contato = dbContatos.verContato(id);

        if (contato != null) {
            txtNome.setText(contato.getNome());
            txtTelefone.setText(contato.getTelefone());
            txtCorreio.setText(contato.getCorreio_eletronico());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //!txtNome -> SÓ VAI SALVAR NO BANCO SE ESSE CAMPO FOR DFIRENTE DE NULL
                if (!txtNome.getText().toString().equals("") && !txtTelefone.getText().toString().equals("")) {
                    correto = dbContatos.editarContato(id, txtNome.getText().toString(), txtTelefone.getText().toString(), txtCorreio.getText().toString());

                    if(correto){
                        Toast.makeText(EditarItem.this, "ITEM MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarItem.this, "ERROR AO MODIFICAR ITEM", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarItem.this, "É NECESSARIO PRENCHER TODOS OS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
