package com.example.seleccionbasedatos.realm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seleccionbasedatos.R
import com.example.seleccionbasedatos.model.model
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.kotlin.delete
import kotlinx.android.synthetic.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class AbstractAdapterRealm : RecyclerView.Adapter<AbstractAdapterRealm.ViewHolder>() {

    var modelList: MutableList<model> = ArrayList()
    lateinit var context: Context

    fun AbstractAdapterRealm(modelList: MutableList<model>, context: Context) {
        this.modelList = modelList
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = modelList.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        return ViewHolder(layoutInflater)
    }


    override fun getItemCount(): Int {
        return modelList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val id = view.findViewById(R.id.tv_item_adapter_id) as TextView
        val username = view.findViewById(R.id.tv_item_adapter_username) as TextView
        val password = view.findViewById(R.id.tv_item_adapter_password) as TextView
        val item = view.findViewById(R.id.ll_item_adapter_item) as LinearLayout


        fun bind(model: model, context: Context) {

            var realm = Realm.getDefaultInstance()
            username.text = model.userName
            password.text = model.password
            id.text = model.id.toString()


            item.setOnLongClickListener(View.OnLongClickListener {
                context.alert("Select the action", "Dialog title") {
                    positiveButton("Modificar") {
                        realm.beginTransaction()
                        model.userName = if(model.userName == "rene"){
                            "elisa"
                        }
                        else{
                            "rene"
                        }
                        realm.insertOrUpdate(model)
                        realm.commitTransaction()
                    }
                    negativeButton("Visualizar"){
                        realm.beginTransaction()
                        var result = realm.where(model::class.java).equalTo("id", model.id).findAll()
                        var model = result.get(0)
                        realm.commitTransaction()
                    }
                    neutralPressed("Eliminar") {
                        realm.beginTransaction()
                        var result = realm.where(model::class.java).equalTo("id", model.id).findAll()
                        result.deleteAllFromRealm()
                        realm.commitTransaction()
                    }
                }.show()

                return@OnLongClickListener true
            })


        }

    }


}

