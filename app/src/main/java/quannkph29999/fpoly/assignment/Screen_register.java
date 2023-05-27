package quannkph29999.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import quannkph29999.fpoly.assignment.DAO.ThanhVienDAO;
import quannkph29999.fpoly.assignment.Model.ThanhVien;

public class Screen_register extends AppCompatActivity {
    EditText ed_TenDangKy,ed_MkDangKy,ed_NlMkDangKy;
    Button btn_DangKy,btn_ThoatDangKy;
    ThanhVienDAO thanhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_register);
        ed_TenDangKy = findViewById(R.id.register_edtenDangKy);
        ed_MkDangKy = findViewById(R.id.register_edMatKhauDangKy);
        ed_NlMkDangKy = findViewById(R.id.register_edNhapLaimk);
        btn_DangKy = findViewById(R.id.register_btnDangKy);
        btn_ThoatDangKy = findViewById(R.id.register_btnThoatDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendangky = ed_TenDangKy.getText().toString();
                String matkhaudk = ed_MkDangKy.getText().toString();
                if(ed_TenDangKy.length() == 0){
                    ed_TenDangKy.requestFocus();
                    ed_TenDangKy.setError("Không để trống tên đăng ký");
                }
                else if(ed_MkDangKy.length() == 0){
                    ed_MkDangKy.requestFocus();
                    ed_MkDangKy.setError("Không bỏ trống mật khẩu");
                }
                else if(ed_NlMkDangKy.length() == 0){
                    ed_NlMkDangKy.requestFocus();
                    ed_NlMkDangKy.setError("Không bỏ trống nhập lại mật khẩu");
                }
                else if(!ed_MkDangKy.getText().toString().equals(ed_NlMkDangKy.getText().toString())){
                    ed_NlMkDangKy.requestFocus();
                    ed_NlMkDangKy.setError("Mật khẩu không khớp với nhau");
                }
                else {
                    thanhVienDAO = new ThanhVienDAO(getApplicationContext());
                    ThanhVien themthanhVien = new ThanhVien(tendangky,matkhaudk);
                      if(thanhVienDAO.ThemThanhVien(themthanhVien) > 0){
                          Toast.makeText(Screen_register.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                      }
                      else {
                          Toast.makeText(Screen_register.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                      }

                }
            }
        });
        btn_ThoatDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thoatdangky = new Intent(Screen_register.this,Screen_Login.class);
                startActivity(thoatdangky);
                finish();
            }
        });
    }
}