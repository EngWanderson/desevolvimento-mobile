package br.com.tabs_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.atividade01_componentes_basicos.R

class FragementPrimeiro : Fragment () {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_primeiro, container, false)
        val textView = view.findViewById<TextView>(R.id.fragmentPrimeiroText)
        textView.setOnClickListener {
            Toast.makeText(view.context, "Primeiro Fragmento Ativado", Toast.LENGTH_LONG).show()
        }



        return view
    }



}