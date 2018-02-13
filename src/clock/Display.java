package clock;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private String title;
	private int size;
	private JFrame f;
	public static Canvas canvas;

	public Display(String title, int size) {
		this.title = title;
		this.size = size;
		createDisplay();
	}

	public void createDisplay() {
		f = new JFrame(title);
		f.setSize(size, size);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		
		canvas= new Canvas();
		canvas.setPreferredSize(new Dimension(size, size));
		canvas.setBackground(Color.cyan);
		f.add(canvas);
		f.pack();
	}
}
