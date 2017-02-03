import java.io.*;
import java.util.*;

public class Picture {



    public static void main(String[] args) {


	Pixel a = new Pixel(128, 0, 0);
	System.out.println(a);
	return;
    }

    

}

class Canvas {
    Pixel[][] canvas;

    public Canvas() {
	canvas = new Pixel[300][600];
    }
}

class Pixel {
    int R;
    int G;
    int B;

    public Pixel() { // White Pixel
	R = 255;
	G = 255;
	B = 255;
    }
    public Pixel(int R, int G, int B) {
	this.R = R;
	this.G = G;
	this.B = B;
    }

    public String toString() {
	return "" + R + " " + G + " " + B; // Consider Adding Space or Newline
    }

}
