package quannkph29999.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import quannkph29999.fpoly.assignment.DAO.ThanhVienDAO;

public class Screen_Login extends AppCompatActivity {
    EditText ed_TenDangNhap, ed_MkDangNhap;
    Button btn_DangNhap, btn_DangKy;
    CheckBox luutaikhoan;
    ThanhVienDAO thanhVienDAO;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
        ed_TenDangNhap = findViewById(R.id.login_edtenDangNhap);
        ed_MkDangNhap = findViewById(R.id.login_edMatKhau);
        btn_DangNhap = findViewById(R.id.login_btnDangNhap);
        btn_DangKy = findViewById(R.id.login_btnDangKy);
        luutaikhoan = findViewById(R.id.login_cbluutaikhoan);
        initPreferences();
        SharedPreferences sharedPreferences1 = getSharedPreferences("DATALOGIN", MODE_PRIVATE);
        ed_TenDangNhap.setText(sharedPreferences1.getString("TENDN", ""));
        ed_MkDangNhap.setText(sharedPreferences1.getString("MKDN", ""));
        luutaikhoan.setChecked(sharedPreferences1.getBoolean("REMEMBER", false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendp = ed_TenDangNhap.getText().toString();
                String mk = ed_MkDangNhap.getText().toString();
                thanhVienDAO = new ThanhVienDAO(getApplicationContext());
                if (ed_TenDangNhap.length() == 0) {
                    ed_TenDangNhap.requestFocus();
                    ed_TenDangNhap.setError("Không bỏ trống tên đăng nhập");
                } else if (ed_MkDangNhap.length() == 0) {
                    ed_MkDangNhap.requestFocus();
                    ed_MkDangNhap.setError("Không bỏ trống mật khẩu đăng nhập");
                } else {
                    if (thanhVienDAO.checkLogin(tendp, mk)) {
                        rememberLogin(tendp,mk,luutaikhoan.isChecked());
                        Intent dangnhap = new Intent(Screen_Login.this, MainActivity.class);
                        startActivity(dangnhap);
                        Toast.makeText(Screen_Login.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Screen_Login.this, "Đăng Nhập Thất Bại(Sai Mật Khẩu Hoặc Tên Đăng Nhập)", Toast.LENGTH_SHORT).show();
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