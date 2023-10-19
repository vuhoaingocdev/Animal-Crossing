package com.example.animal__crossing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Khai bao
    TextView txtDiem;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;
    ImageButton ibtnPlay;

    int soDiem = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        //chặn người dùng không được phép kéo SeekBar khi đang chạy ứng dụng
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);

        //Kiểm tra dự đoán của người chs là đúng hay sai, đúng thì cộng điểm, sai thì trừ điểm
        txtDiem.setText(soDiem + "");

        CountDownTimer countDownTimer = new CountDownTimer(60000, 500) {
            @Override
            public void onTick(long l) {
                //Tạo một con số random để random tốc độ chạy của 3 con vật
                int number = 5;
                //Tốc độ chạy của ba con vật nhanh hay chậm phụ thuộc vào con số trong hàm random
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three =  random.nextInt(number);

                //Lấy dữ liệu hiện tại của cộng dữ liệu ở hàm random rồi gán cho tiến trình thực hiện
                skOne.setProgress(skOne.getProgress() +  one);
                skTwo.setProgress(skTwo.getProgress()  + two);
                skThree.setProgress(skThree.getProgress() + three);

                //Kiểm tra con nào Win, khi con nào đến vạch đích thì ta sẽ cho nó dừng lại
                //Khi đã tìm được con dành chiến thắng mà người dùng muốn chơi tiếp thì:
                //+Con nào tới đích rồi thì thì dừng lại và ta cho hiển thị nút play lên
                //+Khi người dùng click vào phím play thì ta phải set lại vị trsi cho các con vật về vị trsi thứ 0(đầu tiên)
                if(skOne.getProgress() >= skOne.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Hươu cao cổ WINNN    ", Toast.LENGTH_SHORT).show();

                    //Kiểm tra đặt cược xem người chơi cược đúng hay không, cược đúng thì cộng điểm
                    if(cbOne.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Chúc mừng bạn đoán đúng!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai! Chúc bạn may mắn lần sau.", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }

                else if(skTwo.getProgress() >= skTwo.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Ngựa WINNN", Toast.LENGTH_SHORT).show();

                    //Kiểm tra đặt cược xem người chơi cược đúng hay không, cược đúng thì cộng điểm
                    if(cbTwo.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Chúc mừng bạn đoán đúng!", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai! Chúc bạn may mắn lần sau.", Toast.LENGTH_SHORT).show();

                    }
                    txtDiem.setText(soDiem+" ");
                    EnableCheckBox();

                }

                else if(skThree.getProgress() >= skThree.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Gấu trúc WINNN", Toast.LENGTH_SHORT).show();

                    //Kiểm tra đặt cược xem người chơi cược đúng hay không, cược đúng thì cộng điểm
                    if(cbThree.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Chúc mừng bạn đoán đúng!", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai! Chúc bạn may mắn lần sau.", Toast.LENGTH_SHORT).show();

                    }
                    txtDiem.setText(soDiem+" ");
                    EnableCheckBox();

                }


            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Trước khi người dùng chơi phải kiểm tra xem họ đã đặt cược hay chưa
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked())
                {
                    //set lại vị trí cho các con vật về vị trsi thứ 0
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);

                    //Cho ẩn button trước khi chạy
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                    DisableCheckBox();

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược trước khi chơi.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                {
                    //Bỏ chọn 2 và 3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        //Tạo checkBox cho người chơi có thể đặt cược
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    //bo chọn 1 và 3
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    //Bỏ chọn 1 và 2
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });

    }

    private  void  AnhXa()
    {
        txtDiem  = (TextView) findViewById(R.id.textviewDiemSo);
        ibtnPlay = (ImageButton) findViewById(R.id.buttonPlay);
        cbOne    = (CheckBox) findViewById(R.id.checkBoxOne);
        cbTwo    = (CheckBox) findViewById(R.id.checkBoxTwo);
        cbThree  = (CheckBox) findViewById(R.id.checkBoxThree);
        skOne    = (SeekBar) findViewById(R.id.seekbarOne);
        skTwo    = (SeekBar) findViewById(R.id.seekbarTwo);
        skThree  = (SeekBar) findViewById(R.id.seekbarThree);
    }

    //Hàm cho người dùng tương tác với checkBox
    private void EnableCheckBox()
    {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    //Hàm không cho người dùng tương tác với checkBox khi ứng dựng đang chạy
    private void DisableCheckBox()
    {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }



}