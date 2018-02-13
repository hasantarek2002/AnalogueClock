package clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class SetUp implements Runnable {

	private Display display;
	private String title;
	private int size;
	private Thread thread;
	private BufferStrategy buffer;
	private Graphics2D g;

	public SetUp(String title, int size) {
		this.title = title;
		this.size = size;
	}

	public void init() {

		display = new Display(title, size);
	}

	public void draw() {
		buffer = display.canvas.getBufferStrategy();
		if (buffer == null) {
			display.canvas.createBufferStrategy(3);
			return;
		}

		int centre = size / 2;
		g = (Graphics2D) buffer.getDrawGraphics();
		g.clearRect(0, 0, size, size);

		g.setColor(Color.BLACK);
		g.fillOval(10, 10, size - 20, size - 20);
		g.setColor(Color.white);
		g.fillOval(20, 20, size - 40, size - 40);

		int angleX, angleY, radius;
		double position;
		double time = System.currentTimeMillis();
		for (int i = 1; i <= 12; i++) {
			position = i / 12.0 * Math.PI * 2;
			radius = centre - 60;
			angleX = (int) ((centre) + (Math.sin(position) * radius));
			angleY = (int) ((centre) - (Math.cos(position) * radius));
			g.setColor(Color.black);
			g.setFont(new Font("arial", Font.BOLD, 35));
			String s = Integer.toString(i);
			g.drawString(s, angleX, angleY);
		}
		
		for (int i = 1; i <= 60; i++) {
			position = i / 60.0 * Math.PI * 2;
			radius = centre - 20;
			angleX = (int) ((centre) + (Math.sin(position) * radius));
			angleY = (int) ((centre) - (Math.cos(position) * radius));
			if(i==15 || i==30 || i==45 || i==60){
				radius=centre-60;
			}else{
				radius=centre-40;
			}
			int angleP = (int) ((centre) + (Math.sin(position) * radius));
			int angleQ = (int) ((centre) - (Math.cos(position) * radius));
			g.setColor(Color.black);
			g.drawLine(angleP, angleQ, angleX, angleY);
		}
		
		
		////hour
			radius = centre - 140;
			time = System.currentTimeMillis() / (60.0 *60.0*12* 1000.0)*Math.PI * 2;
			angleX = (int) ((centre) + (Math.sin(time) * radius));
			angleY = (int) ((centre) - (Math.cos(time) * radius));
			g.setColor(Color.red);
			g.setStroke(new BasicStroke(10));
			g.drawLine(centre, centre, angleX, angleY);
			g.setStroke(new BasicStroke(0));
		
		
		////minuite
		radius = centre - 90;
		time = System.currentTimeMillis() / (60.0 *60.0* 1000.0)*Math.PI * 2;
		angleX = (int) ((centre) + (Math.sin(time) * radius));
		angleY = (int) ((centre) - (Math.cos(time) * radius));
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(6));
		g.drawLine(centre, centre, angleX, angleY);
		g.setStroke(new BasicStroke(0));
		
		///second
		radius = centre - 50;
		time = System.currentTimeMillis() / (60.0 * 1000)*Math.PI * 2;
		angleX = (int) ((centre) + (Math.sin(time) * radius));
		angleY = (int) ((centre) - (Math.cos(time) * radius));
		g.setColor(Color.red);
		g.drawLine(centre, centre, angleX, angleY);

		g.setColor(Color.GREEN);
		g.fillOval(centre - 10, centre - 10, 20, 20);

		buffer.show();
		g.dispose();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		init();
		while (true) {
			draw();
		}

	}
}
