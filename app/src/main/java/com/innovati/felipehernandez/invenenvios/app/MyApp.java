package com.innovati.felipehernandez.invenenvios.app;

import android.app.Application;
import org.greenrobot.greendao.database.Database;

import com.innovati.felipehernandez.invenenvios.database.DaoMaster;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.DaoMaster.DevOpenHelper;
import com.innovati.felipehernandez.invenenvios.security.EncryptionAndDecryption;

public class MyApp extends Application
{
    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;
    @Override
    public void onCreate()
    {
        super.onCreate();

        DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "inven_e_encrypted" : "inven_e");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
