package br.com.atividade01_componentes_basicos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_view.view.*

class RecyclerViewAdapter(private val item_recle: MutableList<String>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    //diz qual layout é dos itens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_view,parent,false)

        // return the view holder
        return ViewHolder(view)
    }


    //inserir informacões no layout
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.animal.text = item_recle[position]



    }

    //diz qual o tamanho da lista(no caso vai ser do tamanho da variavel criada na
    //classe que é uma lista"item_recle")
    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return item_recle.size
    }


    //pega os elemento do layout(ligar variaveis com itens/elemento do layout
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val animal: TextView = itemView.item_customi
    }


    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
}