package adelkin.egeapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level26 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; // переменная для левой картинки + текст
    public int numRight; // переменная для правой картинки + текст
    Array array = new Array(); //новй объект класса Array
    Random random = new Random(); //для генерации случайных чисел
    public int count = 0; // счётчик правильных ответов

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level26);//установили текст
        text_levels.setTextColor(R.color.black95);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        //код который скругляет углы левыой картинки
        img_left.setClipToOutline(true);

        //путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        //путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        //код который скругляет углы правой картинки
        img_right.setClipToOutline(true);

        //равернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) ;
        //равернуть игру на весь экран - конец

        //Устанавливаем фон диалогового окна начало
        ImageView background = (ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level3);
        //Устанавливаем фон диалогового окна конец

        //Вызов диалогового окна в начале игры
        dialog= new Dialog (this); //создаем диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем диалоговое окно
        dialog.setContentView(R.layout.previewdialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false);//окно нельзя закрыть кнопкой "Назад"

        //Устанавливаем картинку в диалоговое окно начало
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg3);
        //Устанавливаем картинку в диалоговое окно конец

        //Устанавливаем фон диалогового окна начало
        LinearLayout dialogfon = (LinearLayout)dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackground3);
        //Устанавливаем фон диалогового окна конец

        //Устанавливаем описание задания - начало
        TextView textdescription = (TextView)dialog.findViewById(R.id.descreption);
        textdescription.setText(R.string.levelthree);
        //Устанавливаем описание задания - конец



        //кнопка которое закрывает диалоговое окно  начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                //начало - обрабатываем нажатие кнопки
                try {
                    //начало - вернуться назад к выбору уровня
                    Intent intent = new Intent(Level26.this, GameLevels.class); // создаем намерение для перехода
                    startActivity(intent); //Старт намерения
                    finish(); //закрыть этот класс
                    //конец - вернуться назад к выбору уровня
                } catch (Exception e) {
                    //catch на случай ошибок
                }
                dialog.dismiss(); // Закрываем диалоговое окно
                //начало - обрабатываем нажатие кнопки
            }
        });
        //кнопка которое закрывает диалоговое окно  конец

        // кнопака "Продолжить" - начало
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); //закрываем диалоговое окно
            }
        });
        // кнопака "Продолжить" - конец

        dialog.show(); //показать диалоговое окно

        //__________________________________________________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd= new Dialog (this); //создаем диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем диалоговое окно
        dialogEnd.setContentView(R.layout.dialogend); // путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT); //разворачивает диалоговое окно на весь экран
        dialogEnd.setCancelable(false);//окно нельзя закрыть кнопкой "Назад"

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfonEnd= (LinearLayout)dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackground3);
        //Устанавливаем фон диалогового окна - начало

        //интерестный факт начало
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textDescriptionEnd);
        textdescriptionEnd.setText(R.string.levelthreeEnd);
        //интерестный факт начало




        // начало кнопка которое закрывает диалоговое окно
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                //начало - обрабатываем нажатие кнопки
                try {
                    //начало - вернуться назад к выбору уровня
                    Intent intent = new Intent(Level26.this, GameLevels.class); // создаем намерение для перехода
                    startActivity(intent); //Старт намерения
                    finish(); //закрыть этот класс
                    //конец - вернуться назад к выбору уровня
                } catch (Exception e) {
                    //catch на случай ошибок
                }
                dialogEnd.dismiss(); // Закрываем диалоговое окно
                //начало - обрабатываем нажатие кнопки
            }
        });
        // конец кнопка которое закрывает диалоговое окно

        // кнопака "Продолжить" - начало
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level26.this, Level27.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){ }

                dialogEnd.dismiss(); //закрываем диалоговое окно
            }
        });
        // кнопака "Продолжить" - конец
        //__________________________________________________________________________

        //Кнопка назад начало
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btn_back.setTextColor(R.color.black95);
        btn_back.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //обрабатываем нажатие кнопки "назад" - начало
                try {
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level26.this, GameLevels.class); //Создали намерение
                    startActivity(intent);
                    finish();//закрыть этот класс
                    //Вернуться назад к выбору уровня конец

                }catch (Exception e){

                }
                //обрабатываем нажатие кнопки "назад" - конец
            }
        });
        //Кнопка назад конец

        //Массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };
        //Массив для прогресса игры - конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level26.this, R.anim.alpha);

        //Подключаем анимацию - конец

        numLeft = random.nextInt(21); //Генерируем слйчайное число
        img_left.setImageResource(array.images3[numLeft]); //ДОстаем из массива картинку
        text_left.setText(array.texts3[numLeft]); //Достаем из массива текст

        numRight = random.nextInt(21); //Генерируем слйчайное число от

        //Цикл с предусловием, проверяющий равенство чисел - начало
        while (numLeft==numRight){
            numRight = random.nextInt(21);
        }
        //Цикл с предусловием, проверяющий равенство чисел - конец

        img_right.setImageResource(array.images3[numRight]); //ДОстаем из массива картинку
        text_right.setText(array.texts3[numRight]); //Достаем из массива текст

        // Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) { //автоматом выдавало motionEvent, передеалал на как в видео
                //условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //если коснулся картинки - начало
                    img_right.setEnabled(false); //Блокируем правую картинку
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //если коснулся картинки - конец
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    //если отпустил палец - начало
                    if (numLeft>numRight){
                        //если левая картинка больше
                        if(count<20){
                            count=count+1;
                        }
                        //закрашивем прогресс серым цветом - начало
                        for (int i = 0; i < 20 ; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашивем прогресс серым цветом - начало

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - начало

                    }else {
                        //если левая картинка меньше
                        if (count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        //закрашивем прогресс серым цветом - начало
                        for (int i = 0; i < 19 ; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашивем прогресс серым цветом - начало

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - начало
                    }

                    //если отпустил палец - конец
                    if(count==20){
                            //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if(level>26){
                            //пусто
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 27);
                            editor.commit();
                        }
                        dialogEnd.show();

                    }else{
                        numLeft = random.nextInt(21); //Генерируем слйчайное число
                        img_left.setImageResource(array.images3[numLeft]); //ДОстаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]); //Достаем из массива текст

                        numRight = random.nextInt(21); //Генерируем слйчайное число

                        //Цикл с предусловием, проверяющий равенство чисел - начало
                        while (numLeft==numRight){
                            numRight = random.nextInt(21);
                        }
                        //Цикл с предусловием, проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images3[numRight]); //ДОстаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]); //Достаем из массива текст

                        img_right.setEnabled(true); //Включаем обратно правую картинку
                    }
                }
                //условие касания картинки - начало
                return true;
            }
        });
        // Обрабатываем нажатие на левую картинку - конец
        // Обрабатываем нажатие на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) { //автоматом выдавало motionEvent, передеалал на как в видео
                //условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //если коснулся картинки - начало
                    img_left.setEnabled(false); //Блокируем левую картинку
                    if (numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //если коснулся картинки - конец
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    //если отпустил палец - начало
                    if (numLeft<numRight){
                        //если правая картинка больше
                        if(count<20){
                            count=count+1;
                        }
                        //закрашивем прогресс серым цветом - начало
                        for (int i = 0; i < 20 ; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашивем прогресс серым цветом - начало

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - начало

                    }else {
                        //если правая картинка меньше
                        if (count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        //закрашивем прогресс серым цветом - начало
                        for (int i = 0; i < 19 ; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашивем прогресс серым цветом - начало

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - начало
                    }

                    //если отпустил палец - конец
                    if(count==20){
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if(level>26){
                            //пусто
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 27);
                            editor.commit();
                        }
                        dialogEnd.show();

                    }else{
                        numLeft = random.nextInt(21); //Генерируем слйчайное число
                        img_left.setImageResource(array.images3[numLeft]); //ДОстаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]); //Достаем из массива текст

                        numRight = random.nextInt(21); //Генерируем слйчайное число

                        //Цикл с предусловием, проверяющий равенство чисел - начало
                        while (numLeft==numRight){
                            numRight = random.nextInt(21);
                        }
                        //Цикл с предусловием, проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images3[numRight]); //ДОстаем из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]); //Достаем из массива текст

                        img_left.setEnabled(true); //Включаем обратно левую картинку
                    }
                }
                //условие касания картинки - начало
                return true;
            }
        });
        // Обрабатываем нажатие на правую картинку - конец

    }
    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        //обрабатываем нажатие кнопки "назад" - начало
        try {
            //Вернуться назад к выбору уровня - начало
            Intent intent = new Intent(Level26.this, GameLevels.class); //Создали намерение
            startActivity(intent);
            finish();//закрыть этот класс
            //Вернуться назад к выбору уровня конец

        }catch (Exception e){

        }
        //обрабатываем нажатие кнопки "назад" - конец
    }
    //Системная кнопка "Назад" - конец

}