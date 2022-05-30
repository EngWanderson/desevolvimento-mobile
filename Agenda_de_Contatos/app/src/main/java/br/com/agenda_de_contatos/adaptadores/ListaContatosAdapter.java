package br.com.agenda_de_contatos.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.agenda_de_contatos.R;
import br.com.agenda_de_contatos.VerItemActivity2;
import br.com.agenda_de_contatos.entidades.Contatos;

public class ListaContatosAdapter extends RecyclerView.Adapter<ListaContatosAdapter.ContatoViewHolder> {

    ArrayList<Contatos> listaContatos;
    ArrayList<Contatos> listaOriginal;

    public ListaContatosAdapter(ArrayList<Contatos> listaContatos) {
        this.listaContatos = listaContatos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaContatos);
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contato, null, false);
        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
        holder.viewNome.setText(listaContatos.get(position).getNome());
        holder.viewTelefone.setText(listaContatos.get(position).getTelefone());
        holder.viewCorreio.setText(listaContatos.get(position).getCorreio_eletronico());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaContatos.clear();
            listaContatos.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contatos> collecion = listaContatos.stream()
                        .filter(i -> i.getNome().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaContatos.clear();
                listaContatos.addAll(collecion);
            } else {
                for (Contatos c : listaOriginal) {
                    if (c.getNome().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaContatos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return listaContatos.size();
    }

    public class ContatoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNome, viewTelefone, viewCorreio;

        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNome = itemView.findViewById(R.id.viewNome);
            viewTelefone = itemView.findViewById(R.id.viewTelefone);
            viewCorreio = itemView.findViewById(R.id.viewCorreio);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerItemActivity2.class);
                    intent.putExtra("ID", listaContatos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
