package com.example.lesson_4_1;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText1 = findViewById(R.id.edit_Text);
        EditText editText2 = findViewById(R.id.edit_Text_3);
        Button button = findViewById(R.id.button_btn);
        TextView textView = findViewById(R.id.text_View);
        TextView textView2 = findViewById(R.id.text2_txt);
        TextView textView3 = findViewById(R.id.fog_pass);


        button.setBackgroundColor(Color.GRAY);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            private void checkButtonState() {
                if (editText1.getText().length() > 0 || editText2.getText().length() > 0) {
                    button.setBackgroundColor(Color.parseColor("#FFA500")); // Оранжевый
                } else {
                    button.setBackgroundColor(Color.GRAY); // Серый
                }
            }
        };

        editText1.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);


        button.setOnClickListener(view -> {
            String login = editText1.getText().toString();
            String email = editText2.getText().toString();

            if (login.equals("admin") && email.equals("admin")) {
                editText1.setVisibility(View.GONE);
                editText2.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                Snackbar.make(findViewById(R.id.main), "Успешно зашли", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", v -> { /* Действие по нажатию на кнопку OK */ })
                        .show();
            } else if (login.isEmpty() || email.isEmpty()) {
                Snackbar.make(findViewById(R.id.main), "Ячейки не должны быть пустыми", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", v -> { /* Действие по нажатию на кнопку OK */ }).show();
            } else {
                Snackbar.make(findViewById(R.id.main), "Неверный пароль или логин", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", v -> { /* Действие по нажатию на кнопку OK */ })
                        .show();
            }
        });

    }
}