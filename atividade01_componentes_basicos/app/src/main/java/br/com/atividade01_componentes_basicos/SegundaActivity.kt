package br.com.atividade01_componentes_basicos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_segunda.*

class SegundaActivity : AppCompatActivity(), AdapterView.OnItemClickListener {


    private var gridView: GridView ? = null
 private   var arrayList: ArrayList<Modal>? = null
    private var gridAdapter: gridAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)


        gridView=findViewById(R.id.meu_gridView)

        arrayList = ArrayList()
       arrayList= setDataList()
        gridAdapter = gridAdapter(applicationContext, arrayList!!)

        gridView?.adapter = gridAdapter
        gridView?.onItemClickListener

        gridView?.onItemClickListener = this


        ir_terceira_tela.setOnClickListener{
            IrTerceiraTela()
        }

    }


    private fun  setDataList() : ArrayList<Modal> {
        var arrayList:  ArrayList<Modal> = ArrayList()

       arrayList.add(Modal(R.drawable.kotlin1,"um"))
        arrayList.add(Modal(R.drawable.kolin2,"dois"))
        arrayList.add(Modal(R.drawable.kolin4,"tres"))
        arrayList.add(Modal(R.drawable.kolin5,"quatro"))
       arrayList.add(Modal(R.drawable.kolin6,"cinco"))
        arrayList.add(Modal(R.drawable.kotlin7,"seis"))
        arrayList.add(Modal(R.drawable.kotlin3,"sete"))
        arrayList.add(Modal(R.drawable.kolin4,"oito"))
        arrayList.add(Modal(R.drawable.kolin4,"tres"))



        return arrayList
    }

    override fun onItemClick(p0: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
        var modal:Modal = arrayList!!.get(p2)
        Toast.makeText(applicationContext, modal.nomes, Toast.LENGTH_LONG).show()

    }

    private fun IrTerceiraTela(){
        val tela3 = Intent(this, TerceiraActivity ::class.java)
        startActivity(tela3)
    }
}