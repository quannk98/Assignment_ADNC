package quannkph29999.fpoly.assignment.DBHepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "APP_NGHE_NHAC_DOC_BAO", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String bangnhac = "CREATE TABLE nhac(idnhac integer primary key autoincrement,tennhac text UNIQUE NOT NULL," +
                "linknhac text UNIQUE NOT NULL)";
        db.execSQL(bangnhac);
//        String bangbao = "CREATE TABLE bao(idbao integer primary key autoincrement,tenbao text NOT NULL," +
//                "linkbao text NOT NULL,linkimg text NOT NULL)";
//        db.execSQL(bangbao);
        String bangthanhvien = "CREATE TABLE thanhvien(tentv text primary key," +
                "matkhau NOT NULL)";
        db.execSQL(bangthanhvien);
        String themtv = "INSERT INTO thanhvien VALUES('quannk98','1234')";
        db.execSQL(themtv);
        String bangfavmusic = "CREATE TABLE fav(idfav integer primary key autoincrement,tennhac text UNIQUE REFERENCES nhac(tennhac) )";
        db.execSQL(bangfavmusic);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deletebangnhac = "DROP TABLE IF EXISTS nhac";
        db.execSQL(deletebangnhac);
//        String deletebangbao = "DROP TABLE IF EXISTS bao";
//        db.execSQL(deletebangbao);
        String deletebangthanhvien = "DROP TABLE IF EXISTS thanhvien";
        db.execSQL(deletebangthanhvien);
        onCreate(db);
    }
}
