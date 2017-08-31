package com.example.vii_mook.recyclecardviewgrid.model;

import com.example.vii_mook.recyclecardviewgrid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vii-mook on 8/31/2017 AD.
 */

public class Animal {

    private String title;
    private int imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static List<Animal> getData() {
        List<Animal> data = new ArrayList<>();

        int[] images = {
                R.drawable.bear_1,
                R.drawable.bear_02,
                R.drawable.p_bear,

                R.drawable.cat,
                R.drawable.cat2,
                R.drawable.cat_1,
                R.drawable.p_cat,
                R.drawable.p_cat1,

                R.drawable.eagle,
                R.drawable.eagle1,


                R.drawable.p_dog,
                R.drawable.p_dog1,

                R.drawable.p_parrot,
                R.drawable.parrot,
                R.drawable.parrot1,

                R.drawable.p_spider,
                R.drawable.spider,

                R.drawable.whiterabbit,
                R.drawable.rabbit

        };

        String[] Categories = {"Bear 1", "Bear 2", "Bear 3",
                "Cat 1", "Cat 2", "Cat 3", "Cat 4", "Cat 5",
                "Eagle 1", "Eagle 2",
                "Dog 1", "Dog 2",
                "Parrot 1", "Parrot 2", "Parrot 3",
                "Spider 1", "Spider 2",
                "Rabbit 1", "Rabbit 2"};

        for (int i = 0; i < images.length; i++) {
            Animal current = new Animal();
            current.setTitle(Categories[i]);
            current.setImageId(images[i]);
            data.add(current);
        }

        return data;
    }


}
