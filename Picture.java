import java.io.*;
import java.util.*;

public class Picture {

    public static void main(String[] args) throws FileNotFoundException {
	Canvas c = new Canvas(1000, 500, 16, 0, 32);
	for (int y = 20; y < 500; y += 10) {
	    for (int x = 0; x < 1000; x++) {
		if (Math.random() < 0.005) { // 0.5%
		    int g = (int)(256 * (y / 500.));
		    int r = (int)(g * 0.35);
		    int b = (int)(g * 0.35);
		    c.triangle(x, y, new Pixel(r, g, b));
		}
	    }
	}
	c.save("out.ppm");
    }
}

class Canvas {
    private Pixel[][] canvas; // Drawing Canvas
    private int x, y; // Dimensions

    // Constructors
    public Canvas() {
	canvas = new Pixel[300][600];
	x = 600;
	y = 300;
	fill(255, 255, 255);
    }
    public Canvas(int x, int y) {
	canvas = new Pixel[y][x];
	this.x = x;
	this.y = y;
	fill(255, 255, 255);
    }
    public Canvas(int x, int y, Pixel p) {
	this(x, y);
	int[] rgb = p.getRGB();
	fill(p);
    }
    public Canvas(int x, int y, int R, int G, int B) {
	this(x, y);
	fill(R, G, B);
    }

    // Accessors + Mutators
    public int[] getXY() {
	return new int[]{x, y};
    }
    public int getX() {
	return x;
    }
    public int getY() {
	return y;
    }

    // Canvas Methods
    public boolean fill(int R, int G, int B) {
	for (int i = 0; i < y; i++) {
	    for (int j = 0; j < x; j++) {
		canvas[i][j] = new Pixel(R, G, B);
	    }
	}
	return true;
    }
    public boolean fill(Pixel p) {
	int[] rgb = p.getRGB();
	return fill(rgb[0], rgb[1], rgb[2]);
    }
   
    public boolean triangle(int x, int y, Pixel p) {
	int layer = 0;
	while (y < this.y) {
	    for (int i = Math.max(0, x - layer); i < Math.min(x + layer + 1, this.x); i++) {
		canvas[y][i] = p;
	    }
	    layer++; y++;
	}
	return true;
    }

    // File Creation
    public boolean save(String name) throws FileNotFoundException {
	PrintWriter pw = new PrintWriter(new File(name));
	pw.print("P3 " + x + " " + y + " 255\n"); // Heading
	for (int i = 0; i < y; i++) {
	    for (int j = 0; j < x; j++) {
		pw.print(canvas[i][j]);
	    }
	}
	pw.close();
	return true;
    }
}

class Pixel {
    private int R;
    private int G;
    private int B;

    // Constructors
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
    public Pixel(Pixel p) {
	int[] rgb = p.getRGB();
	R = rgb[0];
	G = rgb[1];
	B = rgb[2];
    }
    public Pixel(int flag) {
	if (flag == 1) { // Random
	    R = (int)(Math.random() * 256);
	    G = (int)(Math.random() * 256);
	    B = (int)(Math.random() * 256);
	}
    }
    
    // Accessors and Mutators
    public int[] getRGB() {
	return new int[]{R, G, B};
    }
    public String toString() {
	return "" + R + " " + G + " " + B + "\n";
    }
}
