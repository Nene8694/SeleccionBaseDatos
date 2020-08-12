package com.example.seleccionbasedatos.model

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class model(
    @PrimaryKey
    var id: Int? = null,
    var userName: String? = null,
    var password: String? = null
):RealmModel

