package edu.birzeit.studystar.Model;

import java.util.ArrayList;
import java.util.List;

import edu.birzeit.studystar.R;

public class DAItem {

    ArrayList<Item> items =new ArrayList<>();

    public  DAItem()
    {
        items.add(new Item( 1,"Blue", R.drawable.color1, "colors"));
        items.add(new Item( 2,"Yellow", R.drawable.color2,"colors"));
        items.add(new Item( 3,"Red",R.drawable.color3, "colors"));
        items.add(new Item( 4,"Pink", R.drawable.color4, "colors"));
        items.add(new Item( 5,"White", R.drawable.color5,"colors"));
        items.add(new Item( 6,"gray",R.drawable.color6, "colors"));
        items.add(new Item( 7,"Black", R.drawable.color7, "colors"));
        items.add(new Item( 8,"Green", R.drawable.color8,"colors"));

        items.add(new Item( 9,"Square",R.drawable.square, "shapes"));
        items.add(new Item( 10,"Rectangle",R.drawable.rectangle, "shapes"));
        items.add(new Item( 11,"Triangle ",R.drawable.triangle, "shapes"));
        items.add(new Item( 12,"Circle  ",R.drawable.color2, "shapes"));
        items.add(new Item( 13,"Star   ",R.drawable.star, "shapes"));
        items.add(new Item( 14,"Rhombus",R.drawable.rhombus, "shapes"));
        items.add(new Item( 15,"Pentagon",R.drawable.pentagon, "shapes"));
        items.add(new Item( 16,"Hexagon",R.drawable.hexagon, "shapes"));
        items.add(new Item( 17,"Heptagon",R.drawable.heptagon, "shapes"));
    }

    public List<Item> getItemsByCat(String cat)
    {
        List<Item> itemList =new ArrayList<>();

        for (Item i:items) {
            if(i.getCategory().equals(cat))
            {
                itemList.add(i);
            }
        }
        return itemList;

    }

    public String[] getcat()
    {
        String[] cat={"colors","shapes"};
        return  cat;
    }
}
