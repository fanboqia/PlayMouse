import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import audio.Audio;

public class PlayMouse extends JFrame implements Runnable {

	// variables to use
	private int num = 0;
	private static final long serialVersionUID = 1L;
	JLabel lb_grade = null;
	JLabel[] lb_img_mouse = null;
	JLabel lb_img_background = null;
	ImageIcon img_mouse = null;
	ImageIcon img_background = null;
	// Point[] arrayPoint = new Point[6]; //array difference here...

	public PlayMouse() {
		init();
	}

	public void init() {

		// initialize the control
		lb_grade = new JLabel("Your Grade: ");
		lb_grade.setBounds(600, 0, 230, 50);
		lb_grade.setFont(new Font("", Font.BOLD, 20));
		lb_grade.setForeground(Color.blue);
		lb_img_mouse = new JLabel[6];
		lb_img_background = new JLabel();
		img_mouse = new ImageIcon("img/mouse.png");
		img_background = new ImageIcon("img/background.jpg");
		lb_img_background.setBounds(0, 0, img_background.getIconWidth(), img_background.getIconHeight());
		lb_img_background.setIcon(img_background);
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("img/hammer.png"),
				new Point(), "self"));

		for (int i = 0; i < 6; i++) {
			lb_img_mouse[i] = new JLabel();
			// lb_img_mouse[i].setIcon(img_mouse);
			lb_img_mouse[i].setSize(img_mouse.getIconWidth(), img_mouse.getIconHeight());
			lb_img_mouse[i].addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e){
					setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("img/hammer.png"),
							new Point(), "self"));
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("img/hammerAfter.png"),
							new Point(), "self"));
					
					Object object = e.getSource();	//get the event object 
					System.out.println(e.getLocationOnScreen());
					if (object instanceof JLabel) {	//determine whether it is the label
						JLabel jLabel = (JLabel) object;
						if (jLabel.getIcon() != null) {
							num++;
							lb_grade.setText("Your Grade: " + num);
							new Audio(); // audio effect
						}
						jLabel.setIcon(null);
					}
				}
			});
			getContentPane().add(lb_img_mouse[i]);
		}

		lb_img_mouse[0].setLocation(106, 24);
		lb_img_mouse[1].setLocation(354, 24);
		lb_img_mouse[2].setLocation(584, 24);
		lb_img_mouse[3].setLocation(106, 271);
		lb_img_mouse[4].setLocation(354, 271);
		lb_img_mouse[5].setLocation(584, 271);

		// add to container
		getContentPane().add(lb_grade);
		getContentPane().add(lb_img_background);

		// frame setup
		setSize(500, 400);
		setTitle("打地鼠小游戏");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

		setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			// C++ java difference here is the random()!!!
			int randomIndex = (int) (Math.random() * 6);
			lb_img_mouse[randomIndex].setIcon(img_mouse);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lb_img_mouse[randomIndex].setIcon(null);
			setVisible(true);
		}
	}

	public static void main(String[] args) {
		PlayMouse p1 = new PlayMouse();
		Thread thread = new Thread(p1); // put object into thread constructor to
										// produce a thread
		thread.start();
		
		//while(true){}
	}
}
