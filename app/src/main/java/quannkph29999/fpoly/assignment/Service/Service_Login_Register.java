package quannkph29999.fpoly.assignment.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import quannkph29999.fpoly.assignment.DAO.ThanhVienDAO;
import quannkph29999.fpoly.assignment.Model.ThanhVien;

public class Service_Login_Register extends Service {
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {

        super.onCreate();
        thanhVienDAO = new ThanhVienDAO(this);
        Log.d("quanquan", "onCreate: chay vao day");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }
    public boolean DangKy(ThanhVienDAO thanhVienDAO,String tendk,String mkdk){
        thanhVien = new ThanhVien();
        ThanhVien thanhVienthem = new ThanhVien(tendk,mkdk);
        if(thanhVienDAO.ThemThanhVien(thanhVienthem) > 0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean DangNhap(ThanhVienDAO thanhVienDAO,String tendn,String mk){
        if(thanhVienDAO.checkLogin(tendn,mk)){
            return true;
        }
        else {
            return false;
        }
    }


    public class MyBinder extends Binder {

        public Service_Login_Register getService_login() {

            return Service_Login_Register.this;

        }
    }

}
