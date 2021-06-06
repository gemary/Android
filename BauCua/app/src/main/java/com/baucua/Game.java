package com.baucua;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {

    private ImageView BtnPlay, BtnOpen;
    private ImageView Item1, Item2, Item3;
    private ImageView itemWin1, itemWin2, itemWin3;

    private ImageView itemGame0, itemGame1, itemGame2, itemGame3, itemGame4, itemGame5;

    private ImageView ItemChen;

    private RelativeLayout Bangame;
//    private Button NaiHClick, CuaHClick, BauHClick, TomHClick, CaHClick, GaHClick;
//    private TextView txtNai, txtBau, txtGa, txtCa, txtCua, txtTom;

    private Animation chen_animation;
    private Animation chen_open;
    public int number1 = 5;
    private int ran1, ran2, ran3;
    private int cr1, cr2, cr3;
    private boolean isCheat = false;
    private DatabaseReference myRef;
    private long recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        AnimationUtils.loadAnimation(this, R.anim.dattien_animation);
        chen_animation = AnimationUtils.loadAnimation(this, R.anim.chen_animation);
        chen_open = AnimationUtils.loadAnimation(this, R.anim.chen_open);
        AnimationUtils.loadAnimation(this, R.anim.chen_close);

        getRecipeFromFireBase();
        getResultFromFireBase();
        getElementId();

        ActionPlay();
        ActionOpen();
    }

//    public void dialogBox(int Message) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("Coin:" + Message);
//        alertDialogBuilder.setPositiveButton("Ok",
//                new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//                    }
//                });
//
//        alertDialogBuilder.setNegativeButton("cancel",
//                new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                    }
//                });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

    private void ActionPlay() {
        BtnPlay.setOnClickListener(v -> {
            VisibleItemGame();
            ItemChen.startAnimation(chen_animation);
            BtnPlay.setVisibility(View.INVISIBLE);
            BtnOpen.setVisibility(View.VISIBLE);

            itemWin1.setVisibility(View.INVISIBLE);
            itemWin2.setVisibility(View.INVISIBLE);
            itemWin3.setVisibility(View.INVISIBLE);
        });

    }

    private void ActionOpen() {

        BtnOpen.setOnClickListener(v -> {
            Random random = new Random();
            if (!isCheat) {
                cheatRule(random);
            } else {
                ran1 = cr1;
                ran2 = cr2;
                ran3 = cr3;
                isCheat = false;
            }

            showItems(ran1, ran2, ran3);
            showItemsWin(ran1, ran2, ran3);

            resetItemGame();

            BtnPlay.setVisibility(View.VISIBLE);
            BtnOpen.setVisibility(View.INVISIBLE);

            ItemChen.startAnimation(chen_open);

            InvisibleItemGame();
        });
    }

    public void resetItemGame() {
        Drawable default0 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow0", "drawable", getPackageName()));

        itemGame0.setImageDrawable(default0);

        Drawable default1 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow1", "drawable", getPackageName()));
        itemGame1.setImageDrawable(default1);

        Drawable default2 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow2", "drawable", getPackageName()));
        itemGame2.setImageDrawable(default2);

        Drawable default3 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow3", "drawable", getPackageName()));
        itemGame3.setImageDrawable(default3);

        Drawable default4 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow4", "drawable", getPackageName()));
        itemGame4.setImageDrawable(default4);

        Drawable default5 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow5", "drawable", getPackageName()));
        itemGame5.setImageDrawable(default5);
    }

    public void showItems(int ramdom1, int random2, int random3) {
        Drawable drawable1 = getResources().getDrawable(getResources()
                .getIdentifier("xx" + ramdom1, "drawable", getPackageName()));
        Drawable drawable2 = getResources().getDrawable(getResources()
                .getIdentifier("xx" + random2, "drawable", getPackageName()));
        Drawable drawable3 = getResources().getDrawable(getResources()
                .getIdentifier("xx" + random3, "drawable", getPackageName()));

        Item1.setImageDrawable(drawable1);
        Item2.setImageDrawable(drawable2);
        Item3.setImageDrawable(drawable3);
    }

    public void InvisibleItemGame() {

        itemGame0.setVisibility(View.INVISIBLE);
        itemGame1.setVisibility(View.INVISIBLE);
        itemGame2.setVisibility(View.INVISIBLE);
        itemGame3.setVisibility(View.INVISIBLE);
        itemGame4.setVisibility(View.INVISIBLE);
        itemGame5.setVisibility(View.INVISIBLE);
    }

    public void VisibleItemGame() {

        itemGame0.setVisibility(View.VISIBLE);
        itemGame1.setVisibility(View.VISIBLE);
        itemGame2.setVisibility(View.VISIBLE);
        itemGame3.setVisibility(View.VISIBLE);
        itemGame4.setVisibility(View.VISIBLE);
        itemGame5.setVisibility(View.VISIBLE);
    }

    public void showItemsWin(int random1, int random2, int random3) {

        Drawable drawable1 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow" + random1, "drawable", getPackageName()));
        Drawable drawable2 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow" + random2, "drawable", getPackageName()));
        Drawable drawable3 = getResources().getDrawable(getResources()
                .getIdentifier("itemshow" + random3, "drawable", getPackageName()));

        itemWin1.setImageDrawable(drawable1);
        itemWin1.setVisibility(View.VISIBLE);

        itemWin2.setImageDrawable(drawable2);
        itemWin2.setVisibility(View.VISIBLE);

        itemWin3.setImageDrawable(drawable3);
        itemWin3.setVisibility(View.VISIBLE);

    }

    public static int getRandom(List<Integer> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }

    private int recipeOne(int numOne, int numTwo, int numThree) {
        int result;
        if (numOne > numTwo) {
            result = numOne - numTwo + numThree;
            return result < 5 ? result : numOne - numTwo;
        } else if (numOne > numThree) {
            result = numOne - numThree + numTwo;
            return result < 5 ? result : numOne - numThree;
        } else if (numOne < numThree) {
            result = numThree - numOne + numTwo;
            return result < 5 ? result : numThree - numOne;
        } else {
            result = numTwo - numOne + numThree;
            return result < 5 ? result : numTwo - numOne;
        }

    }

    private void getRecipeFromFireBase() {

        myRef.child("baucua").child("recipe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long value = snapshot.getValue(Long.class);
                recipe = value != null ? value : 0;
                Log.d("recipe", "Value is: " + recipe);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", "Failed to read value.", error.toException());
            }
        });

    }

    private void getResultFromFireBase() {

        myRef.child("baucua").child("results").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Models value = snapshot.getValue(Models.class);
                    int oldValue1 = (int) value.num1;
                    int oldValue2 = (int) value.num2;
                    int oldValue3 = (int) value.num3;
                    if (oldValue1 == cr1 && oldValue2 == cr2 && oldValue3 == cr3) {
                        isCheat = false;
                    } else {
                        isCheat = true;
                        cr1 = oldValue1;
                        cr2 = (int) value.num2;
                        cr3 = (int) value.num3;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", "Failed to read value.", error.toException());
            }
        });

    }

    private void cheatRule(Random random) {
        List<Integer> DiamondArray = new ArrayList<>();

        DiamondArray.add(0);
        DiamondArray.add(1);
        DiamondArray.add(5);
        DiamondArray.add(3);
        DiamondArray.add(4);
        DiamondArray.add(2);
        if (DiamondArray.size() == 0) {
            ran1 = random.nextInt(number1);
            ran2 = random.nextInt(number1);
            ran3 = random.nextInt(number1);
        } else {
            int temp1 = ran1, temp2 = ran2, temp3 = ran3;
            ran1 = recipe == 1 ? recipeOne(temp1, temp2, temp3) : getRandom(DiamondArray);
            ran2 = getRandom(DiamondArray);
            ran3 = getRandom(DiamondArray);
        }

    }

    private void getElementId() {

        Bangame = findViewById(R.id.bangame);

        Item1 = findViewById(R.id.item1);
        Item2 = findViewById(R.id.item2);
        Item3 = findViewById(R.id.item3);
        ItemChen = findViewById(R.id.imgChen);
        ItemChen.setVisibility(View.VISIBLE);


        itemWin1 = findViewById(R.id.itemWin1);
        itemWin1.setVisibility(View.INVISIBLE);

        itemWin2 = findViewById(R.id.itemWin2);
        itemWin2.setVisibility(View.INVISIBLE);

        itemWin3 = findViewById(R.id.itemWin3);
        itemWin3.setVisibility(View.INVISIBLE);

        itemGame0 = findViewById(R.id.itemGame0);
        itemGame1 = findViewById(R.id.itemGame1);
        itemGame2 = findViewById(R.id.itemGame2);
        itemGame3 = findViewById(R.id.itemGame3);
        itemGame4 = findViewById(R.id.itemGame4);
        itemGame5 = findViewById(R.id.itemGame5);

        BtnPlay = findViewById(R.id.btnPlay);

        BtnOpen = findViewById(R.id.btnOpen);
        BtnOpen.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
