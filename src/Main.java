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
        newProgram.createNewFrame(); 
    }

    @Override
    public void createNewFrame() {
        window = new JFrame("Apps resources");
        window.setLayout(new GridBagLayout());
        splitWindow = new JPanel(new GridLayout(1, 2));

        addButton = new JButton("add an app!");
        viewButton = new JButton("view the recommended apps");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("adding!!!");
                //call class
                addNew adding = new addNew();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("View!");
                InfoWindow informationView = new InfoWindow();
            }
        });

        welcomeLabel = new JLabel("Welcome to my app!");
        welcomeLabel.setSize(20, 20);

        buttonsHolder = new JPanel(new GridLayout(3, 1));
        imageHolder = new JPanel();

        buttonsHolder.add(addButton);
        buttonsHolder.add(viewButton);


        splitWindow.add(new JLabel(new ImageIcon("../resources/humaaans.png")));
        splitWindow.add(buttonsHolder);


        window.add(welcomeLabel);
        window.add(splitWindow);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setSize(1000, 500);
        window.setResizable(false);
    }
}
