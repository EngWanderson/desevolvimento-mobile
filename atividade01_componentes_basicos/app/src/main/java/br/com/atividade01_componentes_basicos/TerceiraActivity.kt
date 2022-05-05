package br.com.atividade01_componentes_basicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import br.com.tabs_test.FragementPrimeiro
import br.com.tabs_test.FragmentoSegundo
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TerceiraActivity : AppCompatActivity() {


    lateinit var primeitoButtom : Button
    lateinit var segundoButtom : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terceira)




        primeitoButtom= findViewById(R.id.ButtonPrimeiro)
        segundoButtom = findViewById(R.id.ButtonSegundo)

        primeitoButtom.setOnClickListener {
            val primeiroFragment = FragementPrimeiro()
            loadFragment(primeiroFragment)
        }

        segundoButtom.setOnClickListener {
            val segundoFragment = FragmentoSegundo()
            loadFragment(segundoFragment)

        }
    }


    private  fun  loadFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTrasaction = fragmentManager.beginTransaction()
        fragmentTrasaction.replace(R.id.fragmentView,fragment)
        fragmentTrasaction.commit()
    }

}