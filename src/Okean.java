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
	
	Font font=new Font("Arial", Font.BOLD, 20);
	int limit; // дробная едениця уровня воды, розделяет еденицу "y" на 50
	boolean work;//когда кнопка нажата
			
	
	
	
	MyPanel(){
		addMouseMotionListener( new MyMouse());
		addMouseListener( new MyMouse());
		timer.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(150,255,255));	
		//движущие объекты	
		//вода
		g.setColor(Color.blue);
		g.fillRect(water.x,water.y,water.width,water.hight);
		//водяраИгрока		
		g.fillRect(player.box_x,player.box_y,player.box_w,player.box_h);
		//инструмент
		g.setColor(Color.red);
		g.fillRect(tool.x,tool.y,tool.width,tool.hight);
		//отсек
		g.setColor(Color.black);
		g.fillRect(water.compartment_X,water.compartment_Y,water.compartment_W,water.compartment_H);
		
		interFace.paintComponent(g);
		//скорость
		g.setFont(font);
		g.setColor(Color.pink);
		g.drawString("*"+tool.speed, 130, 690);
		
		
	}
	
	void runWater(){
		
		limit+=1;
		
		//соложный алгоритм
		if(work){//конопка нажата
			
			if(tool.x>400 & tool.x<800) { limit-=1; player.water(); } //если курсор в шахте
			if(tool.x>20 & tool.x<250) {player.water();}//если в бочке .... добавить параметр в метод "вода"
			
			
			
			
		}
		
		//
		
		
		
		if(limit>50){//дробность стает еденицей "у++" воды
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
			runWater();	//движение воды		
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
	
	int box_x=20,box_y,box_w=250,box_h;
	int water;
	int limit;
	
	void water(){//Налить водяры - Себе      .... int power +/-
		limit++;//+-power
		
		if(limit>50){
			limit-=50; water++;	//дробность стает еденицей уровня воды "у++"	
			box_y=682-water; box_h=water;//поднять уровень воды вверх и увеличить его объем
		}
	}
}


class Water{
	
	int x=450,y=400,width=350,hight=500; //вода, пока не двигается		
	int compartment_X=400,compartment_Y=300,compartment_W=400,compartment_H=50;	//отсек-планка
	
	void move(int number){
		//отсек/планка - телепортирование
		compartment_Y+=number;
		//y-=number;
		if(compartment_Y<-100)compartment_Y=750;
		else if(compartment_Y>750)compartment_Y=-100;
	}
}





class Tool{
	int x,y,width=10,hight=50;
	
	int speed=1;
}





class InterFace extends JPanel{
	//клас статичной графики
	
	public void paintComponent(Graphics g){			
		//стоблы
		g.setColor(Color.black);	
		g.fillRect(400,-100,50,1000);
		g.fillRect(800,-100,50,1000);
		//бочка
		g.fillRect(20,400,20,300);
		g.fillRect(250,400,20,300);
		g.fillOval(18, 380, 253, 50);
		g.fillOval(20, 670, 250, 50);
	}
	
}