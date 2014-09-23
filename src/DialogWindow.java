import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;


public class DialogWindow {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    DialogWindow window = new DialogWindow();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public DialogWindow() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 492, 322);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
	
	JButton btnConsole = new JButton("Console");
	frame.getContentPane().add(btnConsole);
	
	JButton btnGui = new JButton("GUI");
	frame.getContentPane().add(btnGui);
	
	JButton btnCancel = new JButton("Cancel");
	btnCancel.setMnemonic('c');
	frame.getContentPane().add(btnCancel);
    }

}
