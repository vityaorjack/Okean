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
		setSize(1280,730);//730
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
	Player player =new Player();
	InterFace interFace=new InterFace();
	
	int limit=50;
	boolean work;
			
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
		//box		
		g.fillRect(player.box_x,player.box_y,player.box_w,player.box_h);
		//инструмент
		g.setColor(Color.red);
		g.fillRect(tool.x,tool.y,tool.width,tool.hight);
		//отсек
		g.setColor(Color.black);
		g.fillRect(water.compartment_X,water.compartment_Y,water.compartment_W,water.compartment_H);
		
		interFace.paintComponent(g);
		
		
	}
	
	void runWater(){
		
		limit+=1;
		if(work){
			limit-=3;
			player.water();
		}
		
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
			repaint();
		}
	}
	
	
	
	
	//мышка
	public class MyMouse extends MouseAdapter{			  				
		   
		public void mousePressed(MouseEvent event){	
			work=true;
			//System.out.println("-");
		}
		public void mouseReleased(MouseEvent event){	
			work=false;
			//System.out.println("+");
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
	
	int box_x=20,box_y=685,box_w=250,box_h=1;
	int water;
	int limit;
	
	void water(){
		limit++;
		
		if(limit>100){ 
			limit-=100; water++;		
			box_y=690-water; box_h=water;
		}
	}
}


class Water{
	
	int x=450,y=400,width=350,hight=500;		
	int compartment_X=400,compartment_Y=300,compartment_W=400,compartment_H=50;	
	
	void move(int number){
		
		compartment_Y+=number;
		//y-=number;
		if(compartment_Y<-100)compartment_Y=750;
		else if(compartment_Y>750)compartment_Y=-100;
	}
}





class Tool{
	int x,y,width=10,hight=50;
}





class InterFace extends JPanel{
	
	
	public void paintComponent(Graphics g){			
		//стоблы
		g.setColor(Color.black);	
		g.fillRect(400,-100,50,1000);
		g.fillRect(800,-100,50,1000);
		//бочка
		g.fillRect(20,400,20,300);
		g.fillRect(250,400,20,300);
		g.fillOval(20, 380, 250, 50);
		g.fillOval(20, 670, 250, 50);
	}
	
}