package br.com.atividade01_componentes_basicos

import android.content.Context
import android.view.View
import android.view.ViewGroup

import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class gridAdapter (var context: Context, var arrayList: ArrayList<Modal>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
    var view : View = View.inflate(context, R.layout.grid_custom_view_item, null)

        var nomes : TextView = view.findViewById(R.id.name_text_view)
        var imagens: ImageView = view.findViewById(R.id.icons)

        var modal : Modal =  arrayList.get(p0)

        imagens.setImageResource(modal.imagens!!)
        nomes.text = modal.nomes


        return view
    }

    override fun getItem(p0: Int): Any {
        return arrayList.get(p0)
    }


    override fun getItemId(p0: Int): Long {

        return p0.toLong()
    }

    override fun getCount(): Int {

        return arrayList.size
    }
}



