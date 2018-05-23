package com.innovati.felipehernandez.invenenvios;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MiAplicacion extends Application
{
    public static AtomicInteger ClientesID = new AtomicInteger();
    //public static AtomicInteger NoteId = new AtomicInteger();
    @Override
    public void onCreate()
    {
        super.onCreate();
        Realm.init(this);
        //configuración de la bd Realm
        setUpRealmConfig();
        //se ejecuta antes del MainActivity
        Realm realm = Realm.getDefaultInstance();
        //ClientesID = getIdByTable(realm, Board.class);
        realm.close();
    }

    private void setUpRealmConfig() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass)
    {
        //consulta en REALM de la tabla
        RealmResults<T> results = realm.where(anyClass).findAll();
        //si contiene resultados, recupera el último id o manda null
        return (results.size() > 0) ? new AtomicInteger(results.max("Clave").intValue()) : new AtomicInteger();
    }
}
