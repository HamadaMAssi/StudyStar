package edu.birzeit.studystar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.birzeit.studystar.Model.CaptionedImagesAdapter;
import edu.birzeit.studystar.Model.DAItem;
import edu.birzeit.studystar.Model.Item;

public class MainActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private Button showButton;
    private Button puzzuleButton;
    private RecyclerView recycler;

    int cat, number, score = 0; // for puzzle
    String correctResult, incorrectResult1, incorrectResult2; // for puzzle results

    private List<Item> itemsFromSharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.spinner);
        recycler = findViewById(R.id.item_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        ImageView num1 = (ImageView) findViewById(R.id.imageView2);
        Button result1 = (Button) findViewById(R.id.result1);
        Button result2 = (Button) findViewById(R.id.result2);
        Button result3 = (Button) findViewById(R.id.result3);
        Button restart = (Button) findViewById(R.id.restart);
        TextView scoreText=(TextView) findViewById(R.id.score);


        //DAItem items = new DAItem();
//        ********************************      *****************       *****************************

        String[] options = { "colors", "shapes" };

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String str = prefs.getString("myData", "");

        if (str.isEmpty()) {
            // if my shared preferences is empty i will fill it with default data
            List<Item> items = new ArrayList<>();

            items.add(new Item(1, "Blue", R.drawable.color1, "colors"));
            items.add(new Item(2, "Yellow", R.drawable.color2, "colors"));
            items.add(new Item(3, "Red", R.drawable.color3, "colors"));
            items.add(new Item(4, "Pink", R.drawable.color4, "colors"));
            items.add(new Item(5, "White", R.drawable.color5, "colors"));
            items.add(new Item(6, "gray", R.drawable.color6, "colors"));
            items.add(new Item(7, "Black", R.drawable.color7, "colors"));
            items.add(new Item(8, "Green", R.drawable.color8, "colors"));

            items.add(new Item(9, "Square", R.drawable.square, "shapes"));
            items.add(new Item(10, "Rectangle", R.drawable.rectangle, "shapes"));
            items.add(new Item(11, "Triangle ", R.drawable.triangle, "shapes"));
            items.add(new Item(12, "Circle  ", R.drawable.color2, "shapes"));
            items.add(new Item(13, "Star   ", R.drawable.star, "shapes"));
            items.add(new Item(14, "Rhombus", R.drawable.rhombus, "shapes"));
            items.add(new Item(15, "Pentagon", R.drawable.pentagon, "shapes"));
            items.add(new Item(16, "Hexagon", R.drawable.hexagon, "shapes"));
            items.add(new Item(17, "Heptagon", R.drawable.heptagon, "shapes"));

            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            String itemsString = gson.toJson(items); // to string

            editor.putString("myData", itemsString);// write
            editor.commit();

            itemsFromSharedPref = items; // my data to show
            Toast.makeText(this, "Add Data into shared preferences successfully", Toast.LENGTH_LONG).show();
        } else {
            // if shared preferences not empty
            Gson gson = new Gson();
            Type itemListType = new TypeToken<ArrayList<Item>>() {}.getType();
            itemsFromSharedPref = gson.fromJson(str, itemListType);
            Toast.makeText(this, "Read Data from shared preferences successfully", Toast.LENGTH_LONG).show();
        }


//        ********************************      *****************       *****************************
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, options);
        categorySpinner.setAdapter(categoryAdapter);

        String selectedCategory = categorySpinner.getSelectedItem().toString();

        List<Item> itemList = getItemsByCat(selectedCategory);
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(MainActivity.this,
                itemList);
        recycler.setAdapter(adapter);

        showButton = findViewById(R.id.button); // show items (colors or shapes)
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedCategory = categorySpinner.getSelectedItem().toString();
                List<Item> itemList = getItemsByCat(selectedCategory);
                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(MainActivity.this,
                        itemList);
                recycler.setAdapter(adapter);
            }
        });

        TextView puzzle1 = findViewById(R.id.puzzle1);
        LinearLayout puzzle2 = findViewById(R.id.puzzle2);
        TextView puzzle3 = (TextView) findViewById(R.id.puzzle3);
        LinearLayout puzzle4 = findViewById(R.id.puzzle4);
        LinearLayout puzzle5 = findViewById(R.id.puzzle5);
        LinearLayout puzzle6 = findViewById(R.id.puzzle6);

        puzzuleButton = findViewById(R.id.Button2); // hide some components and show the puzzle components because we must use single activity
        puzzuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                TextView textView = (TextView) findViewById(R.id.textView2);
                if (categorySpinner.getVisibility() == View.VISIBLE) {
                    categorySpinner.setVisibility(View.GONE); // hide
                    recycler.setVisibility(View.GONE);
                    showButton.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    puzzuleButton.setText("back");
                    puzzle1.setVisibility(View.VISIBLE); // show
                    puzzle2.setVisibility(View.VISIBLE);
                    puzzle3.setVisibility(View.VISIBLE);
                    puzzle4.setVisibility(View.VISIBLE);
                    puzzle5.setVisibility(View.VISIBLE);
                    puzzle6.setVisibility(View.VISIBLE);
                    GetRandom(0); // Generate random color or shape
                } else {
                    categorySpinner.setVisibility(View.VISIBLE);
                    recycler.setVisibility(View.VISIBLE);
                    showButton.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    puzzuleButton.setText("start puzzle");
                    puzzle1.setVisibility(View.GONE);
                    puzzle2.setVisibility(View.GONE);
                    puzzle3.setVisibility(View.GONE);
                    puzzle4.setVisibility(View.GONE);
                    puzzle5.setVisibility(View.GONE);
                    puzzle6.setVisibility(View.GONE);
                }
            }
        });

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRandom(1); // generating new random item for num1
                scoreText.setText(String.valueOf(score)); // convert score to String to display it in the TextView
            }
        });

        result1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(result1.getText().equals(correctResult)) {
                    score++; // Increment the score
                    TOAST_TEXT = "Correct !";
                } else {
                    score--;
                    TOAST_TEXT = "Incorrect !";
                }
                Toast.makeText(MainActivity.this, TOAST_TEXT, Toast.LENGTH_SHORT).show();
                scoreText.setText(String.valueOf(score)); // toast notification
                GetRandom(0);
            }
        });

        result2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(result2.getText().equals(correctResult)) {
                    score++; // Increment the score
                    TOAST_TEXT = "Correct !";
                } else {
                    score--;
                    TOAST_TEXT = "Incorrect !";
                }
                Toast.makeText(MainActivity.this, TOAST_TEXT, Toast.LENGTH_SHORT).show();
                scoreText.setText(String.valueOf(score));
                GetRandom(0);
            }
        });

        result3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(result3.getText().equals(correctResult)) {
                    score++; // Increment the score
                    TOAST_TEXT = "Correct !";
                } else {
                    score--;
                    TOAST_TEXT = "Incorrect !";
                }
                Toast.makeText(MainActivity.this, TOAST_TEXT, Toast.LENGTH_SHORT).show();
                scoreText.setText(String.valueOf(score));
                GetRandom(0);
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRandom(0);
                score = 0; // Reset the score
                scoreText.setText(String.valueOf(score));
            }
        });
    }

    //    *******************************       **********************      *******************************
    public List<Item> getItemsByCat(String cat)
    {
        List<Item> itemList =new ArrayList<>();
        for (Item i:itemsFromSharedPref) {
            if(i.getCategory().equals(cat))
            {
                itemList.add(i);
            }
        }
        return itemList;
    }
    //    *******************************       **********************      *******************************

    String TOAST_TEXT = " correct ";

    private void GetRandom(int type) {
        ImageView num1 = findViewById(R.id.imageView2);
        Button result1 = findViewById(R.id.result1);
        Button result2 = findViewById(R.id.result2);
        Button result3 = findViewById(R.id.result3);

        Random rand = new Random();
        int cat = rand.nextInt(2) + 1; // Generate random number between 1 and 2
        int[] q = null;
        String[] a = null;

        if (cat == 1) {
            q = new int[]{R.drawable.color1, R.drawable.color2, R.drawable.color3, R.drawable.color4, R.drawable.color5, R.drawable.color6, R.drawable.color7, R.drawable.color8};
            a = new String[]{"blue", "yellow", "red", "pink", "white", "gray", "black", "green"};
        } else {
            q = new int[]{R.drawable.square, R.drawable.star, R.drawable.rectangle, R.drawable.pentagon, R.drawable.rhombus, R.drawable.triangle, R.drawable.hexagon, R.drawable.heptagon};
            a = new String[]{"square", "star", "rectangle", "pentagon", "rhombus", "triangle", "hexagon", "heptagon"};
        }

        int number = rand.nextInt(8); // Generate random number between 0 and 7

        correctResult = a[number];
        int incorrectIndex = rand.nextInt(4) + 1; // Generate random index between 1 and 4
        incorrectResult1 = a[(number + incorrectIndex) % 8];
        incorrectResult2 = a[(number - incorrectIndex + 8) % 8];

        num1.setImageResource(q[number]);

        int correctButton = rand.nextInt(3) + 1;
        switch (correctButton) {
            case 1:
                result1.setText(correctResult);
                result2.setText(incorrectResult1);
                result3.setText(incorrectResult2);
                break;
            case 2:
                result1.setText(incorrectResult1);
                result2.setText(correctResult);
                result3.setText(incorrectResult2);
                break;
            case 3:
                result1.setText(incorrectResult1);
                result2.setText(incorrectResult2);
                result3.setText(correctResult);
                break;
        }
    }
}
