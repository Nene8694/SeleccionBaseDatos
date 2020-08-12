package com.example.seleccionbasedatos

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        var configuration: RealmConfiguration = RealmConfiguration.Builder()
            .name("Realm.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.getInstance(configuration)
        Realm.setDefaultConfiguration(configuration)
    }

}