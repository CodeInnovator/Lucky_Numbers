package lucky_stats.analyzer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;

public class Lucky_Stats_UI extends JFrame {

	private JPanel contentPane;
	JTextArea textArea = new JTextArea();
	JTextArea textArea_1 = new JTextArea();
	//private String file = "/Users/KB/JAVA_Developement/LOTTOMAX.csv";	
	private String file = "LOTTOMAX.csv";
	public URL link;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lucky_Stats_UI frame = new Lucky_Stats_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Lucky_Stats_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea.setBounds(61, 31, 334, 31);
		contentPane.add(textArea);
		
		textArea_1.setBounds(61, 100, 334, 31);
		contentPane.add(textArea_1);
		
		JButton btnGenerateButton = new JButton("Analyze");
		btnGenerateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				Parser parser1;
				Parser parser2;
				try {
					parser1 = new Parser();
					parser2 = new Parser();
					int best = 1 ;
					int worst = 0;
					
					double[] a = parser1.analyze();
					double[] b = parser2.analyze();
					pickNum pick = new pickNum(a, best);
					pickNum pick2 = new pickNum(b, worst);
					int [] result = pick.getBestSix();
					int [] result2 = pick2.getWorstSix();
					String show1 = Integer.toString(result[0]);
					String show2 = Integer.toString(result[1]);
					String show3 = Integer.toString(result[2]);
					String show4 = Integer.toString(result[3]);
					String show5 = Integer.toString(result[4]);
					String show6 = Integer.toString(result[5]);
					String show7 = Integer.toString(result[6]);
					
					//lowest probability numbers:
					String showW1 = Integer.toString(result2[0]);
					String showW2 = Integer.toString(result2[1]);
					String showW3 = Integer.toString(result2[2]);
					String showW4 = Integer.toString(result2[3]);
					String showW5 = Integer.toString(result2[4]);
					String showW6 = Integer.toString(result2[5]);
					String showW7 = Integer.toString(result2[6]);
					
					textArea.append(show1 +", ");
					textArea.append(show2 +", ");
					textArea.append(show3 +", ");
					textArea.append(show4 +", ");
					textArea.append(show5 +", ");
					textArea.append(show6 +", ");
					textArea.append(show7);
					
					//lowest probability display:
					textArea_1.append(showW1 +", ");
					textArea_1.append(showW2 +", ");
					textArea_1.append(showW3 +", ");
					textArea_1.append(showW4 +", ");
					textArea_1.append(showW5 +", ");
					textArea_1.append(showW6 +", ");
					textArea_1.append(showW7);
					
//					System.out.println(result[0]);
//					System.out.println(result[1]);
//					System.out.println(result[2]);
//					System.out.println(result[3]);
//					System.out.println(result[4]);
//					System.out.println(result[5]);
//					System.out.println(result[6]);
					String date = parser1.recentDate();
					textArea.append("      Last Date of Release:"+date);
					System.out.println("date? "+ date);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		btnGenerateButton.setBounds(180, 188, 101, 50);
		contentPane.add(btnGenerateButton);
		
		JLabel lblLuckyNumber = new JLabel("Lucky Number");
		lblLuckyNumber.setBounds(50, 6, 126, 16);
		contentPane.add(lblLuckyNumber);
		
		
		
		
		
		
		
	}
}
