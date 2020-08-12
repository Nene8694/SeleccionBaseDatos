package com.example.seleccionbasedatos.realm.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.seleccionbasedatos.R
import com.example.seleccionbasedatos.model.model
import com.example.seleccionbasedatos.realm.adapters.AbstractAdapterRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * A simple [Fragment] subclass.
 */
class RealmFragment : Fragment() {


    private lateinit var btnInsertar: Button
    private lateinit var btnEliminarTodos: Button
    private lateinit var realm: Realm
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        realm = Realm.getDefaultInstance()
        var view: View = inflater.inflate(R.layout.fragment_realm, container, false)
        btnInsertar = view.findViewById(R.id.btn_fragment_realm_insertar)
        btnInsertar.setOnClickListener(View.OnClickListener {
            addModel()
        })
        btnEliminarTodos = view.findViewById(R.id.btn_fragment_realm_eliminar_todos)
        btnEliminarTodos.setOnClickListener(View.OnClickListener {
            removeAll()
        })
        recyclerView = view.findViewById(R.id.rv_fragment_realm)

        return view
    }



    private fun removeAll() {
        //      --------------------------------------------- ELIMINAMOS TODOS LOS ELEMENTOS DE LA TABLA MODEL
        //      SE COMIENZA UNA TRANSACCION
        realm.beginTransaction()
        //      SE OBTINEN TODAS LAS TUPLAS Y SE GUARDAN EN UN RESULT QUE ES EL ENCARGADO DE HACER LA TRANSACCION E IR ELIMINANDO TODOS LOS VALORES QUE SE CORRESPONDAN EN LA TABLA
//        var result: RealmResults<model> = realm.where(model::class.java).findAll()
//        result.deleteAllFromRealm()
        realm.delete(model::class.java)
        //      SE FINALIZA LA TRANSACCION
        realm.commitTransaction()
        onResume()
    }


    override fun onResume() {
        super.onResume()
        // OBTENEMOS TODAS LAS TUPLAS DE OBJETOS MODEL EN LA BASE DE DATOS
        var result: RealmResults<model> = realm.where(model::class.java).findAll()
        recyclerView.layoutManager = LinearLayoutManager(context)
        var abstractAdapterRealm: AbstractAdapterRealm = AbstractAdapterRealm()
        abstractAdapterRealm.AbstractAdapterRealm(result, context!!)
        recyclerView.adapter = abstractAdapterRealm

    }


    private fun addModel() {
        try {
            realm.beginTransaction()
            var lastId: Number? = realm.where(model::class.java).max("id")
            var newId: Int = if (lastId == null) {
                0
            } else {
                lastId.toInt() + 1
            }
            var model: model = model()
            model.id = newId
            model.userName = "rene"
            model.password = "asd"

            realm.copyToRealmOrUpdate(model)
            realm.commitTransaction()
            onResume()

        } catch (e: Exception) {
            context!!.toast("Error")
        }
    }


}
