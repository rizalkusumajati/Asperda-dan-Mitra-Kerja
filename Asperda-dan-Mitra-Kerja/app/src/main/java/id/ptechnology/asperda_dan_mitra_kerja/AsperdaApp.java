package id.ptechnology.asperda_dan_mitra_kerja;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by macmini2 on 11/11/16.
 */

public class AsperdaApp extends Application {
    private static AsperdaApp instance;
    private SharedPreferences sharedPreferences;

    private void setupSharedPreferences()
    {
        this.sharedPreferences = getSharedPreferences(AsperdaApp.class.getSimpleName(), 0);
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
        instance = this;
//        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(config);

        setupSharedPreferences();
    }

    public static AsperdaApp getInstance()
    {
        return instance;
    }

    public SharedPreferences getSharedPreferences()
    {
        return this.sharedPreferences;
    }

}
