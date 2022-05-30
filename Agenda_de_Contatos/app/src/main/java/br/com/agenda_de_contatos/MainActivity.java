package br.com.agenda_de_contatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.com.agenda_de_contatos.DB.DbContatos;
import br.com.agenda_de_contatos.adaptadores.ListaContatosAdapter;
import br.com.agenda_de_contatos.entidades.Contatos;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContatos;
    ListaContatosAdapter adapter;
    ArrayList<Contatos> listaArrayContatos;
   FloatingActionButton addcontato;
   FloatingActionButton editarContatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos= findViewById(R.id.listaContatosRecyclerview);
        addcontato=findViewById(R.id.addContato);


        editarContatos=findViewById(R.id.editarContatos);

        listaContatos.setLayoutManager(new LinearLayoutManager(this));
        DbContatos  dbContatos = new DbContatos(MainActivity.this);

        listaArrayContatos = new ArrayList<>();
        adapter = new ListaContatosAdapter(dbContatos.mostrarContatos());
        listaContatos.setAdapter(adapter);

        addcontato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novoRegistro();
            }
        });
        editarContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarContato();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNovo:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void novoRegistro(){
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
    private void modificarContato(){
        Intent intent = new Intent(this, EditarContatos.class);
        startActivity(intent);
    }

}