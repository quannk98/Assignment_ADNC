package quannkph29999.fpoly.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.DBHepler.DBHelper;
import quannkph29999.fpoly.assignment.Model.News;
import quannkph29999.fpoly.assignment.Model.ThanhVien;

public class ThanhVienDAO {
    DBHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<ThanhVien> getDataThanhVien() {
        ArrayList<ThanhVien> listthanhvien = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM thanhvien", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setTentv(cursor.getString(1));
                thanhVien.setMatkhau(cursor.getString(2));
                thanhVien.setImg(cursor.getString(3));
                listthanhvien.add(thanhVien);
            } while (cursor.moveToNext());
        }
        return listthanhvien;
    }

    public long ThemThanhVien(ThanhVien thanhVien) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentv", thanhVien.getTentv());
        contentValues.put("matkhau", thanhVien.getMatkhau());

        return sqLiteDatabase.insert("thanhvien", null, contentValues);
    }
    public long ThemAnh(ThanhVien thanhVien) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("img", thanhVien.getImg());


        return sqLiteDatabase.insert("thanhvien", null, contentValues);
    }

    public boolean checkLogin(String tentv,String mk){

           SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
           Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM thanhvien WHERE tentv = ? AND matkhau = ? ",
                   new String[]{tentv,mk});
           if(cursor.getCount() > 0){
                  return  true;
           }
           else {
               return false;
           }
    }
}
