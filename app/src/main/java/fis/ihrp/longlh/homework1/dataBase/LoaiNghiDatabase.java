package fis.ihrp.longlh.homework1.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fis.ihrp.longlh.homework1.dao.LoaiNghiDAO;
import fis.ihrp.longlh.homework1.model.LoaiNghiResponse;

@Database(entities = {LoaiNghiResponse.class}, version = 1)
public abstract class LoaiNghiDatabase extends RoomDatabase {

    private static LoaiNghiDatabase loaiNghiDatabase;

    public abstract LoaiNghiDAO loaiNghiDAO();

    public static LoaiNghiDatabase getInMemoryDatabase(Context context) {
        if (loaiNghiDatabase == null) {
            loaiNghiDatabase = Room.databaseBuilder(context.getApplicationContext(), LoaiNghiDatabase.class, "LoaiNghi-Database")
                    .allowMainThreadQueries()
                    .build();
        }
        return loaiNghiDatabase;
    }

//    public static void destroyInstance() {
//        INSTANCE = null;
//    }

}
