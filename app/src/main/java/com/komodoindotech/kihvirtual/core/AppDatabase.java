package com.komodoindotech.kihvirtual.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.komodoindotech.kihvirtual.dao.ArticleDao;
import com.komodoindotech.kihvirtual.dao.PendaftaranDao;
import com.komodoindotech.kihvirtual.dao.RiwayatImunisasiDao;
import com.komodoindotech.kihvirtual.dao.RiwayatKehamilanDao;
import com.komodoindotech.kihvirtual.dao.RiwayatKeluhanDao;
import com.komodoindotech.kihvirtual.dao.RiwayatPersalinanDao;
import com.komodoindotech.kihvirtual.models.Article;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import org.jetbrains.annotations.NotNull;

@Database(entities = {
        Article.class, Pendaftaran.class, RiwayatKehamilan.class, RiwayatPersalinan.class, RiwayatImunisasi.class, RiwayatKeluhan.class
}, version = 8)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();
    public abstract PendaftaranDao pendaftaranDao();
    public abstract RiwayatKehamilanDao riwayatKehamilanDao();
    public abstract RiwayatPersalinanDao riwayatPersalinanDao();
    public abstract RiwayatImunisasiDao riwayatImunisasiDao();
    public abstract RiwayatKeluhanDao riwayatKeluhan();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {

        final Migration MIGRATION_7_8 = new Migration(7,8) {
            @Override
            public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE pendaftaran "
                        + " ADD COLUMN cid TEXT");
            }
        };

        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "kih-virtual")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .allowMainThreadQueries()
//                            .fallbackToDestructiveMigration()
                            .addMigrations(MIGRATION_7_8)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
