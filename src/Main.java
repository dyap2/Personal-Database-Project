import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class Main implements NewWindow {
    JFrame window; //entire window
    JPanel imageHolder;
    JPanel buttonsHolder;
    JButton viewButton;
    JButton addButton;
    JPanel splitWindow;
    JLabel welcomeLabel;

    public static void main(String[] args) {
        Main newProgram = new Main();
        newProgram.createNewFrame(); // trying out the button
    }



    @Override
    public void createNewFrame() {

        //displaying the pop up window
        window = new JFrame("Apps resources");
        window.setLayout(new GridBagLayout());
        splitWindow = new JPanel(new GridLayout(1, 2));

        //adding a button
        addButton = new JButton("add an app!");
        viewButton = new JButton("view the recommended apps");

        //listener for addButton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("adding!!!");
                //call class
                addNew adding = new addNew();
            }
        });

        //listener for viewButton
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("View!");
                InfoWindow informationView = new InfoWindow();
            }
        });

        //creating label
        welcomeLabel = new JLabel("Welcome to my app!");
        welcomeLabel.setSize(20, 20);

        //creating the JPanels
        buttonsHolder = new JPanel(new GridLayout(3, 1));
        imageHolder = new JPanel();

        //adding the button to the JPanel
        buttonsHolder.add(addButton);
        buttonsHolder.add(viewButton);


        //trying out this code
        splitWindow.add(new JLabel(new ImageIcon("resources/humaaans.png")));

        //adding holder into the windows
        splitWindow.add(buttonsHolder);


        window.add(welcomeLabel);
        window.add(splitWindow);
        //frame settings
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //window.pack();
        window.setVisible(true);
        window.setSize(1000, 500);

        window.setResizable(false);
    }
}
