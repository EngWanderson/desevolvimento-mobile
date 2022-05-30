package br.com.agenda_de_contatos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.agenda_de_contatos.DB.DbContatos;
import br.com.agenda_de_contatos.entidades.Contatos;

public class VerItemActivity2 extends AppCompatActivity {

    EditText txtNome, txtTelefone, txtCorreio;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Contatos contato;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_item2);
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtCorreio = findViewById(R.id.txtCorreioEletronico);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setVisibility(View.INVISIBLE);

        //
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbContatos dbContatos = new DbContatos(VerItemActivity2.this);
        contato = dbContatos.verContato(id);

        if(contato != null){
            txtNome.setText(contato.getNome());
            txtTelefone.setText(contato.getTelefone());
            txtCorreio.setText(contato.getCorreio_eletronico());
            txtNome.setInputType(InputType.TYPE_NULL);
            txtTelefone.setInputType(InputType.TYPE_NULL);
            txtCorreio.setInputType(InputType.TYPE_NULL);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerItemActivity2.this, EditarItem.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerItemActivity2.this);
                builder.setMessage("Desea eliminar este contato?")
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbContatos.eliminarContato(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}