package edu.birzeit.studystar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.birzeit.studystar.Model.DAItem;
import edu.birzeit.studystar.Model.Item;

public class MainActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private Button showButton;

    private Button puzzuleButton;
    private ListView itemListView;
    private ItemAdapter itemAdapter;

    int cat, number, score = 0;
    String correctResult, incorrectResult1, incorrectResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.spinner);
        itemListView = findViewById(R.id.myList);
        itemAdapter = new ItemAdapter();
        itemListView.setAdapter(itemAdapter);

        ImageView num1 = (ImageView) findViewById(R.id.imageView2);
        Button result1 = (Button) findViewById(R.id.result1);
        Button result2 = (Button) findViewById(R.id.result2);
        Button result3 = (Button) findViewById(R.id.result3);
        Button restart = (Button) findViewById(R.id.restart);
        TextView scoreText=(TextView) findViewById(R.id.score);


        DAItem items = new DAItem();
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, items.getcat());
        categorySpinner.setAdapter(categoryAdapter);

        String selectedCategory = categorySpinner.getSelectedItem().toString();
        List<Item> itemList = items.getItemsByCat(selectedCategory);
        itemAdapter.setItems(itemList);

        showButton = findViewById(R.id.button);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedCategory = categorySpinner.getSelectedItem().toString();
                List<Item> itemList = items.getItemsByCat(selectedCategory);
                itemAdapter.setItems(itemList);
            }
        });

        TextView puzzle1 = findViewById(R.id.puzzle1);
        LinearLayout puzzle2 = findViewById(R.id.puzzle2);
        TextView puzzle3 = (TextView) findViewById(R.id.puzzle3);
        LinearLayout puzzle4 = findViewById(R.id.puzzle4);
        LinearLayout puzzle5 = findViewById(R.id.puzzle5);
        LinearLayout puzzle6 = findViewById(R.id.puzzle6);

        puzzuleButton = findViewById(R.id.Button2);
        puzzuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                TextView textView = (TextView) findViewById(R.id.textView2);
                if (categorySpinner.getVisibility() == View.VISIBLE) {
                    categorySpinner.setVisibility(View.GONE);
                    itemListView.setVisibility(View.GONE);
                    showButton.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    puzzuleButton.setText("back");
                    puzzle1.setVisibility(View.VISIBLE);
                    puzzle2.setVisibility(View.VISIBLE);
                    puzzle3.setVisibility(View.VISIBLE);
                    puzzle4.setVisibility(View.VISIBLE);
                    puzzle5.setVisibility(View.VISIBLE);
                    puzzle6.setVisibility(View.VISIBLE);
                    GetRandom(0); // Generate random numbers
                } else {
                    categorySpinner.setVisibility(View.VISIBLE);
                    itemListView.setVisibility(View.VISIBLE);
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
                GetRandom(1); // generating new random number for num1
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
                scoreText.setText(String.valueOf(score));
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


    private class ItemAdapter extends BaseAdapter {
        private List<Item> itemList = new ArrayList<>();

        public void setItems(List<Item> itemList) {
            this.itemList = itemList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                view = inflater.inflate(R.layout.list_item, parent, false);
            }

            Item item = itemList.get(position);
            TextView nameTextView = view.findViewById(R.id.text_view);
            ImageView imageView = view.findViewById(R.id.image_view);
            nameTextView.setText(item.getName());
            imageView.setImageResource(item.getImage());

            return view;
        }
    }

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
