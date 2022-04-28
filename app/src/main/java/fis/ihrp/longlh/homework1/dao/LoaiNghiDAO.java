package fis.ihrp.longlh.homework1.dao;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fis.ihrp.longlh.homework1.model.LoaiNghiResponse;

@Dao
public interface LoaiNghiDAO {

    @Insert(onConflict = REPLACE)
    void insertLoaiNghi(LoaiNghiResponse loaiNghiResponse);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceLoaiNghi(LoaiNghiResponse... loaiNghiResponses);

    @Query("SELECT * FROM loainghi")
    public List<LoaiNghiResponse> getAllLoaiNghi();

}
