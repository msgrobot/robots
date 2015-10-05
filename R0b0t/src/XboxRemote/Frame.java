package XboxRemote;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	JProgressBar progressBar;
	JProgressBar progressBar_1;
	JProgressBar progressBar_2;
	
	public Frame() {
		setTitle("Ev3 Legoroboter Fernbedienung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGang = new JLabel("Gang:");
		lblGang.setBounds(10, 11, 36, 14);
		contentPane.add(lblGang);
		
		JLabel lblWenderadius = new JLabel("Wenderadius");
		lblWenderadius.setBounds(10, 58, 71, 14);
		contentPane.add(lblWenderadius);
		
		JLabel lblLautstrke = new JLabel("Lautst\u00E4rke");
		lblLautstrke.setBounds(10, 114, 51, 14);
		contentPane.add(lblLautstrke);
		
		progressBar = new JProgressBar();
		progressBar.setValue(20);
		progressBar.setBounds(10, 136, 324, 14);
		contentPane.add(progressBar);
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(10, 83, 324, 14);
		contentPane.add(progressBar_1);
		
		progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(10, 36, 324, 14);
		contentPane.add(progressBar_2);
	}

	public void setGang(int gang){
		progressBar_2.setValue(gang *3);
	}

	public void setVol(int vol){
		progressBar.setValue(vol);
	}
	
	public void setWR(int WR){
		progressBar_1.setValue(WR*3);
	}
}
