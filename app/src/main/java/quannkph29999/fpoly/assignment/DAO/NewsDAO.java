package quannkph29999.fpoly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.DBHepler.DBHelper;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.Model.News;

public class NewsDAO {
//    DBHelper dbHelper;
//
//    public NewsDAO(Context context) {
//        dbHelper = new DBHelper(context);
//    }
//
//    public ArrayList<News> getDataNews() {
//        ArrayList<News> listnews = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM bao", null);
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                News news = new News();
//                news.setIdbao(Integer.parseInt(cursor.getString(0)));
//                news.setTenbao(cursor.getString(1));
//                news.setLinkbao(cursor.getString(2));
//                news.setLinkbao(cursor.getString(3));
//                listnews.add(news);
//            } while (cursor.moveToNext());
//        }
//        return listnews;
//    }
//
//    public long ThemNews(News news) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("tenbao", news.getTenbao());
//        contentValues.put("linkbao", news.getLinkbao());
//        contentValues.put("linkimg", news.getLinkbao());
//        return sqLiteDatabase.insert("bao", null, contentValues);
//    }
//
//    public long SuaNews(News news) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("tenbao", news.getTenbao());
//        contentValues.put("linkbao", news.getLinkbao());
//        contentValues.put("linkimg", news.getLinkbao());
//        return sqLiteDatabase.update("bao", contentValues, "idbao = ?", new String[]{String.valueOf(news.getIdbao())});
//
//    }
//
//    public long XoaNews(int idbao) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        return sqLiteDatabase.delete("bao", "idbao = ?", new String[]{String.valueOf(idbao)});
//    }

}

