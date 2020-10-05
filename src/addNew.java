import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addNew implements NewWindow {

    public addNew(){
        createNewFrame();
    }
    @Override
    public void createNewFrame() {
        //JFrame
        JFrame addWindow = new JFrame("Adding something new!");

        //JPANEL
        JPanel container = new JPanel(new GridLayout(4, 2));

        //need 3 textfields and 3 lables
        JLabel name = new JLabel("name");
        JLabel category = new JLabel("category");
        JLabel platform = new JLabel("platform");

        JTextField nameField = new JTextField();
        JTextField catField = new JTextField();
        JTextField platField = new JTextField();

        name.setLabelFor(nameField);
        category.setLabelFor(catField);
        platform.setLabelFor(platField);

        // creating the buttons
        Button add = new Button("add!");
        Button cancel = new Button("cancel");


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                database.createRow(nameField.getText(), catField.getText(),platField.getText());
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWindow.setVisible(false);
                addWindow.dispose();
            }
        });

        // add all the components to the screen!
        container.add(name);
        container.add(nameField);
        container.add(category);
        container.add(catField);
        container.add(platform);
        container.add(platField);

        container.add(cancel);
        container.add(add);

        //frame settings
        addWindow.add(container);
        addWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //window.pack();
        addWindow.setVisible(true);
        addWindow.setSize(1000, 500);
        addWindow.setResizable(false);
    }

    }

