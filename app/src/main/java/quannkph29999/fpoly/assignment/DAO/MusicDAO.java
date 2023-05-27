package quannkph29999.fpoly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.DBHepler.DBHelper;
import quannkph29999.fpoly.assignment.Model.Music;

public class MusicDAO {
    private DBHelper dbHelper;

    public MusicDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Music> getDataMusic() {
        ArrayList<Music> listmusic = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nhac", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Music music = new Music();
                music.setIdnhac(Integer.parseInt(cursor.getString(0)));
                music.setTennhac(cursor.getString(1));
                music.setLinknhac(cursor.getString(2));
                listmusic.add(music);
            } while (cursor.moveToNext());
        }
        return listmusic;
    }

    public long ThemMusic(Music music) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennhac", music.getTennhac());
        contentValues.put("linknhac", music.getLinknhac());

        return sqLiteDatabase.insert("nhac", null, contentValues);
    }

    public long SuaMusic(Music music) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennhac", music.getTennhac());
        contentValues.put("linknhac", music.getLinknhac());

        return sqLiteDatabase.update("nhac", contentValues, "idnhac = ?", new String[]{String.valueOf(music.getIdnhac())});

    }

    public long XoaMusic(int idnhac) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("nhac", "idnhac = ?", new String[]{String.valueOf(idnhac)});
    }

}
