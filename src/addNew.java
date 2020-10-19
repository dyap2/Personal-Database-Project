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
        JFrame addWindow = new JFrame("Adding something new!");

        JPanel container = new JPanel(new GridLayout(4, 2));

        JLabel name = new JLabel("name");
        JLabel category = new JLabel("category");
        JLabel platform = new JLabel("platform");

        JTextField nameField = new JTextField();
        JTextField catField = new JTextField();
        JTextField platField = new JTextField();

        name.setLabelFor(nameField);
        category.setLabelFor(catField);
        platform.setLabelFor(platField);

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

        container.add(name);
        container.add(nameField);
        container.add(category);
        container.add(catField);
        container.add(platform);
        container.add(platField);

        container.add(cancel);
        container.add(add);

        addWindow.add(container);
        addWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindow.setVisible(true);
        addWindow.setSize(1000, 500);
        addWindow.setResizable(false);
    }

    }

