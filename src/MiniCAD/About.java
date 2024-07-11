package MiniCAD;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class About extends JFrame {
	private JPanel contentPane;
	public About() {
		setTitle("About");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);	
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JEditorPane About = new JEditorPane();
		About.setEditable(false);
		About.setText(" About Application:\r\n\n"
				+ "  * Design 1 by: Bui Duong Kha Quan \r\n"
				+ "     - MSSV: 20130372 \r\n"
				+ "     - Class: DH20DTA \n\n"
				+ "  * Design 1 by: Huynh Ngoc Tran \r\n"
				+ "     - MSSV: 20130444 \r\n"
				+ "     - Class: DH20DTC \n\n"
				+ " => Imperial Citadel of Day: 29 - 06 - 2021");
		contentPane.add(About, BorderLayout.CENTER);
	}

}


