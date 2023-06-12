package quannkph29999.fpoly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.DBHepler.DBHelper;
import quannkph29999.fpoly.assignment.Model.FavoriteMusic;

public class FavDAO {
    DBHelper dbHelper;

    public FavDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<FavoriteMusic> getDSFM() {
        ArrayList<FavoriteMusic> listfvm = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM fav", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                FavoriteMusic favoriteMusic = new FavoriteMusic();
                favoriteMusic.setIdfav(Integer.parseInt(cursor.getString(0)));
                favoriteMusic.setTennhac(cursor.getString(1));
                listfvm.add(favoriteMusic);
            } while (cursor.moveToNext());
        }
        return listfvm;
    }

    public long ThemFav(FavoriteMusic favoriteMusic) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennhac", favoriteMusic.getTennhac());

        return sqLiteDatabase.insert("fav", null, contentValues);
    }

    public long XoaFav(int idfav) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        return sqLiteDatabase.delete("fav", "idfav = ?", new String[]{String.valueOf(idfav)});
    }
    public boolean isFavorite(String tennhac) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM fav WHERE tennhac = ?";
        String[] selectionArgs = { String.valueOf(tennhac) };
        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }
}
