package br.com.atividade01_componentes_basicos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_terceira.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var toggleButton: ToggleButton

    private var mo: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.music)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1.toggleButton
        toggleButton=findViewById(R.id.toggle_button)
        toggleButton.setOnClickListener{
            if (toggleButton.isChecked){
                Toast.makeText(this, "Toggle Button: Ativado", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "Toggle Button: Desativado", Toast.LENGTH_SHORT).show()
            }
        }


        // 3.Lista
        val animals = mutableListOf(
            "casa" , "bola" , "palmeiras"  , "faca" , "notebook" , "manga" , "uva","urso","mesa","123","4536"
        )

        // initialize an instance of linear layout manager
        val layoutManager = LinearLayoutManager(
            this, // context
            RecyclerView.HORIZONTAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            recyclerView.layoutManager = this
        }

        // finally, data bind the recycler view with adapter
        RecyclerViewAdapter(animals).apply {
            recyclerView.adapter = this
        }

        //4.Como usar o autocomplete:
        val colorArray = arrayOf("casa" , "bola" , "palmeiras"  , "faca" , "notebook" , "manga" , "uva")
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,colorArray)

        multicmp.setAdapter(arrayAdapter)
        multicmp.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())


        //5.Como usar Spinners(Dropdownlist):

        val spin= findViewById<Spinner>(R.id.spinid) as Spinner
        val opcoes= arrayOf("casa" , "bola" , "palmeiras"  , "faca")
        val ArrayAdp = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcoes)
        spin.adapter=ArrayAdp


        //6. Como usar RadioButtons:

        val radio = findViewById<RadioGroup>(R.id.radioGroup) as RadioGroup
        val fundo = findViewById<LinearLayout>(R.id.linearFundo) as LinearLayout
        radio.setOnCheckedChangeListener{ group, checkerdId ->
            when(checkerdId){
                R.id.vermelho -> {

                    fundo.setBackgroundColor(Color.RED)
                }
                R.id.Azul ->{
                    fundo.setBackgroundColor(Color.BLUE)
                }
            }

            //8. Como Popup menu:
            val pop= findViewById<Button>(R.id.btn_popup) as Button
            val back_fundo = findViewById<LinearLayout>(R.id.fundo_popup_menu) as LinearLayout
            pop.setOnClickListener {
                val popMenu = PopupMenu(this, btn_popup)
                popMenu.menuInflater.inflate(R.menu.pop_menu, popMenu.menu)
                popMenu.setOnMenuItemClickListener ( object : PopupMenu.OnMenuItemClickListener{
                    override fun onMenuItemClick(item: MenuItem?): Boolean {
                        when(item!!.itemId){
                            R.id.color_vermelha -> back_fundo.setBackgroundColor(Color.GREEN)
                            R.id.color_azul -> back_fundo.setBackgroundColor(Color.YELLOW)
                        }
                        return true
                    }
                })
                popMenu.show()
            }
        }

        btn_tela2.setOnClickListener{
            IrSegundaTela()
        }

        controlSoud(currentSong[0])
    }
    private fun IrSegundaTela(){
        val tela2 = Intent(this, SegundaActivity ::class.java)
        startActivity(tela2)
    }
    private fun controlSoud(id : Int){

        fab_play.setOnClickListener{
            if (mo == null){
                mo = MediaPlayer.create(this, id)
                Log.d("MainActivity", "ID: ${mo!!.audioSessionId}")
                initialiseSeekBar()
            }
            mo?.start()
            Log.d("MainActivity", "Duration: ${mo!!.duration/1000} seconds")
        }
        fab_pause.setOnClickListener {
            if (mo !== null) mo?.pause()
            Log.d("MainActivity", "Paused at: ${mo!!.duration/1000} seconds")

        }

        fab_stop.setOnClickListener {
            if (mo !== null) {
                mo?.stop()
                mo?.reset()
                mo?.release()
                mo = null
            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) mo?.seekTo(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }
    private fun initialiseSeekBar(){
        seekbar.max = mo!!.duration
        val handler= Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                try {
                    seekbar.progress = mo!!.currentPosition
                    handler.postDelayed(this, 1000)

                }catch (e: Exception){
                    seekbar.progress = 0
                }
            }

        }, 0)
    }




}