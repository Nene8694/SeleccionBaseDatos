package com.example.seleccionbasedatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.seleccionbasedatos.dbflow.fragments.DBFlowFragment
import com.example.seleccionbasedatos.realm.fragments.RealmFragment
import com.example.seleccionbasedatos.room.fragments.RoomFragment

class MainActivity : AppCompatActivity() {

    val fragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_data_bases, menu)
        return super.onCreateOptionsMenu(menu)
    }




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
             R.id.action_room -> fragmentManager.beginTransaction().replace(R.id.rl_activity_main_contenedor,
                 RoomFragment()
             ).commit()
             R.id.action_dbflow -> fragmentManager.beginTransaction().replace(R.id.rl_activity_main_contenedor,
                 DBFlowFragment()
             ).commit()
             R.id.action_realm -> fragmentManager.beginTransaction().replace(R.id.rl_activity_main_contenedor,
                 RealmFragment()
             ).commit()

        }
        return super.onOptionsItemSelected(item)
    }
}
