
import java.io.Serializable;

import java.rmi.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Screen extends JPanel implements Serializable, ActionListener{

    
    private JTextArea console_content;
    private JTextField instructions;
    private JButton send_instructions;
    public String user;
    public Screen(String user){
        
        
        this.user = user;
        console_content = new JTextArea();
        console_content.setPreferredSize(new Dimension(800,500));
        
        console_content.setOpaque(true);
        console_content.setBackground(Color.black);
        console_content.setForeground(Color.white);
        
        
        instructions = new JTextField();
        instructions.setPreferredSize(new Dimension(680,50));
        
        send_instructions = new JButton("Send");
        send_instructions.addActionListener(this);
        send_instructions.setPreferredSize(new Dimension(100,50));
        
        this.add(console_content);
        this.add(instructions);
        this.add(send_instructions);
        
    }
    public void setTheme(Theme t){
        this.console_content.setBackground(Color.decode(t.getBackgroundColor()));
        this.console_content.setForeground(Color.decode(t.getFontColor()));
    }

    public void actionPerformed(ActionEvent x) {
        if(x.getSource()==send_instructions){
            try{
                Shell myClientShell = (Shell)Naming.lookup("rmi://localhost:1099/Shell");
                String respusta = (String) myClientShell.getCommand( this.instructions.getText() );
                
                this.console_content.setText(this.console_content.getText()+ "\n Shell: "+ this.instructions.getText() +"  \n"+ respusta);
                this.instructions.setText("");

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }//Fin del if
    }

}