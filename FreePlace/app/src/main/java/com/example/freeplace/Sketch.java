package com.example.freeplace;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;


public class Sketch extends PApplet {
    Place[] places = new Place[10];

    public void settings() {
        fullScreen(P3D);
    }

    public void setup() {
        fullScreen(P3D);
        JSONObject json = loadJSONObject("places.json");
        JSONArray placeData = json.getJSONArray("places");

        // The size of the array of Place objects is determined by the total XML elements named "Place"
        places = new Place[placeData.size()];

        for (int i = 0; i < placeData.size(); i++) {
            // Get each object in the array
            JSONObject place = placeData.getJSONObject(i);
            // Get a position object
            JSONObject position = place.getJSONObject("position");
            // Get x,y from position
            int x = position.getInt("x");
            int y = position.getInt("y");

            JSONObject setColor = place.getJSONObject("color");
            // Get color
            int c = color(setColor.getInt("r"), setColor.getInt("g"), setColor.getInt("b"));


            // Put object in array
            places[i] = new Place(x, y, c);
        }
    }

    public void draw() {
        background(0x81B771);
        for (Place p : places) {
            p.display();
        }
    }

    class Place {
        float x;
        float y;
        int c;

        Place(float Xpos, float Ypos, int tempC) {
            x = Xpos;
            y = Ypos;
            c = tempC;
        }

        void display() {
            stroke(0);
            fill(c);
            rectMode(CENTER);
            rect(x, y, 200, 100);
        }
    }
}
