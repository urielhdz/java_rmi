
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame{
    private JMenuBar mb;
    private JMenu properties;
    private JMenu file;
    private JMenuItem exit;
    private JMenuItem credits;
    private JMenuItem setFont;
    private JMenuItem setFontColor;
    private JLabel console_content;
    private JTextField instructions;
    private JButton send_instructions;
    public Window(){
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpMenuBar();
        
        JPanel p = new JPanel();
        console_content = new JLabel();
        console_content.setPreferredSize(new Dimension(800,500));
        console_content.setVerticalAlignment(JLabel.TOP);
        console_content.setOpaque(true);
        console_content.setBackground(Color.black);
        console_content.setForeground(Color.white);
        console_content.setText("Test");
        
        instructions = new JTextField();
        instructions.setPreferredSize(new Dimension(680,50));
        
        send_instructions = new JButton("Send");
        send_instructions.setPreferredSize(new Dimension(100,50));
        
        p.add(console_content);
        p.add(instructions);
        p.add(send_instructions);
        add(p);
    }
    private void setUpMenuBar(){
        mb = new JMenuBar();
        properties = new JMenu("Properties");
        setFont = new JMenuItem("Font");
        setFontColor = new JMenuItem("Font Color");
        properties.add(setFont);
        properties.add(setFontColor);
        file = new JMenu("File");
        exit = new JMenuItem("Close");
        credits = new JMenuItem("Credits");
        file.add(credits);
        file.add(exit);
        mb.add(file);
        mb.add(properties);
        setJMenuBar(mb);
    }
}
