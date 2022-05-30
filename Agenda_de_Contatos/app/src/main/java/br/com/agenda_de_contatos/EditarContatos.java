package br.com.agenda_de_contatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

import br.com.agenda_de_contatos.DB.DbContatos;
import br.com.agenda_de_contatos.adaptadores.ListaContatosAdapter;
import br.com.agenda_de_contatos.entidades.Contatos;

public class EditarContatos extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView listaContatos;
    SearchView txtBuscar2;
    ArrayList<Contatos> listaArrayContatos;
    ListaContatosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contato);

        listaContatos= findViewById(R.id.listaContatosRecyclerview2);
        txtBuscar2 = findViewById(R.id.txtBuscar2);
        txtBuscar2.setQueryHint("Buscar contato pelo nome");

        listaContatos.setLayoutManager(new LinearLayoutManager(this));
        DbContatos dbContatos = new DbContatos(EditarContatos.this);

        listaArrayContatos = new ArrayList<>();
        adapter = new ListaContatosAdapter(dbContatos.mostrarContatos());
        listaContatos.setAdapter(adapter);

        txtBuscar2.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}