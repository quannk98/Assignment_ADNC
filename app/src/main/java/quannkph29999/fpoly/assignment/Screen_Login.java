package quannkph29999.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import quannkph29999.fpoly.assignment.DAO.ThanhVienDAO;
import quannkph29999.fpoly.assignment.Fragment.Person_Fragment;
import quannkph29999.fpoly.assignment.Service.Service_Login_Register;

public class Screen_Login extends AppCompatActivity {
    EditText ed_TenDangNhap, ed_MkDangNhap;
    Button btn_DangNhap, btn_DangKy;
    CheckBox luutaikhoan;
    ThanhVienDAO thanhVienDAO;
    SharedPreferences sharedPreferences;
    TextView tkkhach;
    SharedPreferences.Editor editor;
    Service_Login_Register service_loginRegister;
    boolean checkconnected;
    ServiceConnection sv_slogin = new ServiceConnection() {

        @Override

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Service_Login_Register.MyBinder myBinder = (Service_Login_Register.MyBinder) iBinder;
            service_loginRegister = myBinder.getService_login();
            checkconnected = true;


        }

        @Override

        public void onServiceDisconnected(ComponentName componentName) {
            checkconnected = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
        ed_TenDangNhap = findViewById(R.id.login_edtenDangNhap);
        ed_MkDangNhap = findViewById(R.id.login_edMatKhau);
        btn_DangNhap = findViewById(R.id.login_btnDangNhap);
        btn_DangKy = findViewById(R.id.login_btnDangKy);
        luutaikhoan = findViewById(R.id.login_cbluutaikhoan);
        tkkhach = findViewById(R.id.login_khach);
        thanhVienDAO = new ThanhVienDAO(getApplicationContext());
        service_loginRegister = new Service_Login_Register();
        initPreferences();
        SharedPreferences sharedPreferences1 = getSharedPreferences("DATALOGIN", MODE_PRIVATE);
        ed_TenDangNhap.setText(sharedPreferences1.getString("TENDN", ""));
        ed_MkDangNhap.setText(sharedPreferences1.getString("MKDN", ""));
        luutaikhoan.setChecked(sharedPreferences1.getBoolean("REMEMBER", false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        tkkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Screen_Login.this, MainActivity.class);
                intent.putExtra("khach",true);
                startActivity(intent);
            }
        });
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendn = ed_TenDangNhap.getText().toString();
                String mk = ed_MkDangNhap.getText().toString();
                Intent intentlogin = new Intent(Screen_Login.this, MainActivity.class);
                bindService(intentlogin, sv_slogin, Context.BIND_AUTO_CREATE);
                startService(intentlogin);
                if (ed_TenDangNhap.length() == 0) {
                    ed_TenDangNhap.requestFocus();
                    ed_TenDangNhap.setError("Không bỏ trống tên đăng nhập");
                } else if (ed_MkDangNhap.length() == 0) {
                    ed_MkDangNhap.requestFocus();
                    ed_MkDangNhap.setError("Không bỏ trống mật khẩu đăng nhập");
                } else {
                    if (service_loginRegister.DangNhap(thanhVienDAO, tendn, mk) == true) {
                        rememberLogin(tendn, mk, luutaikhoan.isChecked());
                        Toast.makeText(Screen_Login.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Screen_Login.this, MainActivity.class);
                        intent.putExtra("khach",false);
                        startActivity(intent);

                        Intent intent1 = new Intent(Screen_Login.this, Person_Fragment.class);
                        Bundle bundle = new Bundle();
                        intent1.putExtra("tendn",tendn);
                        intent1.putExtra("mkdn",mk);
                        intent1.putExtras(bundle);
                        

                        finish();
                    } else {
                        Toast.makeText(Screen_Login.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyendangky = new Intent(Screen_Login.this, Screen_register.class);
                startActivity(chuyendangky);
                finish();
            }
        });
    }


    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    public void rememberLogin(String nhoten, String nhomk, boolean check) {
        SharedPreferences sharedPreferences = getSharedPreferences("DATALOGIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!check) {
            editor.clear();
        } else {
            editor.putString("TENDN", nhoten);
            editor.putString("MKDN", nhomk);
            editor.putBoolean("REMEMBER", check);
        }
        editor.commit();
    }
}