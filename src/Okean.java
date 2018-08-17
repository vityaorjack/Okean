import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


public class Okean {	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{		
		MyFrame frame = new MyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Okean");			
		frame.show();
}}
class MyFrame extends JFrame {	
	public MyFrame() {
		setSize(1280,530);//730
		MyPanel panel = new MyPanel();
		Container pane = getContentPane();
		pane.add(panel);
		Cursor c1 = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon(new byte[0])).getImage(), new Point(0,0),	"custom");
		pane.setCursor(c1);//убрать курсор
}}






class MyPanel extends JPanel {
	
	Water water=new Water();
	Timer timer=new Timer(1, new Dvigok());
	Tool tool=new Tool();
	InterFace interFace=new InterFace();
	
	int limit=50;
	
			
	MyPanel(){
		addMouseMotionListener( new MyMouse());
		addMouseListener( new MyMouse());
		timer.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(150,255,255));	
			
		//вода
		g.setColor(Color.blue);
		g.fillRect(water.x,water.y,water.width,water.hight);
		//инструмент
		g.setColor(Color.red);
		g.fillRect(tool.x,tool.y,tool.width,tool.hight);
		
		interFace.paintComponent(g);
		
	}
	
	void runWater(){		
		limit+=1;
		
		if(limit>50){
			limit-=50;
			water.move(1);
		}
		if(limit<-50){
			limit+=50;
			water.move(-1);
		}
	}
	
	
	
	
	//анимацыя
	class Dvigok implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			runWater();
			interFace.compartment_Y+=0;//как передвинуть отсек ?
			repaint();
		}
	}
	
	
	
	
	//мышка
	public class MyMouse extends MouseAdapter{			  				
		   
		public void mousePressed(MouseEvent event){	
			water.work=true;
			System.out.println("-");
		}
		public void mouseReleased(MouseEvent event){	
			water.work=false;
			System.out.println("+");
	}

		public void mouseDragged(MouseEvent e) {
			//System.out.println("-");
			
		}

		public void mouseMoved(MouseEvent e) {
			tool.x=e.getX();
			tool.y=e.getY();
			
		}
		
	}
}











class Player{
	
}


class Water{
	
	int x=450,y=400,width=350,hight=500;
	
	boolean work;
	
	void run(){					
		move(1);//течет вода		
		if(work){move(-3);}//игрок нажал кнопку
	}	
	
	void move(int number){		
				y-=1;			
	}
}





class Tool{
	int x,y,width=10,hight=50;
}





class InterFace extends JPanel{
	int compartment_X=400,compartment_Y=400,compartment_W=400,compartment_H=50;
	
	public void paintComponent(Graphics g){			
		//стоблы
		g.setColor(Color.black);	
		g.fillRect(400,-100,50,1000);
		g.fillRect(800,-100,50,1000);
		//отсек
		g.fillRect(compartment_X,compartment_Y,compartment_W,compartment_H);
	}
	void run(){
		//compartment_Y
	}
}