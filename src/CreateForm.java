import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CreateForm implements NewWindow {

    // the properties that we have for each maybe store this in an array list since we don't know how much we're gonna have
    String nameFrame;
    ArrayList<String> propNames = new ArrayList<>();
    String itemName;

    public CreateForm(String name) {
        this.itemName = name;
        System.out.println(name);
        createNewFrame();
    }


    @Override
    public void createNewFrame() {

        // connecting to the database item to retrieve individual details
        DBConnect.Item DBitem = database.getDetails(itemName);

        //new jframe that shows more details and the ability to edit
        JFrame formWindow = new JFrame(nameFrame);

        //panel to hold the text fields
        //todo this part
        JPanel formContainer = new JPanel(new GridLayout(4,2));


//        formContainer.setPreferredSize(new Dimension(400, 400));
        JPanel buttonHolder = new JPanel();
        buttonHolder.setPreferredSize(new Dimension(400, 400));

        //creating the labels
        JLabel name = new JLabel("name");
        JLabel category = new JLabel(("category"));
        JLabel platform = new JLabel("platform");

        //name , platform, and category text fields
        JTextField appName = new JTextField(itemName);
        JTextField appCategory = new JTextField(DBitem.category);
        JTextField appPlatform = new JTextField(DBitem.platform);

        //setting the labels for the text fields
        name.setLabelFor(appName);
        name.setHorizontalAlignment(SwingConstants.LEADING);
        category.setLabelFor(appCategory);
        platform.setLabelFor(appPlatform);

        //app name not editable
        appName.setEditable(false);

        //adding text fields
        //adding the labels
        formContainer.add(name);
        formContainer.add(appName);
        formContainer.add(category);
        formContainer.add(appPlatform);
        formContainer.add(platform);
        formContainer.add(appCategory);




        //add a save and cancel button
        Button save = new Button("save");
        Button cancel = new Button("cancel");

        //setting the sizes
        Dimension dimensions = new Dimension(200,100);
        appName.setPreferredSize(dimensions);
        appCategory.setPreferredSize(dimensions);
        appPlatform.setPreferredSize(dimensions);


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the database update function!
                // retrieve the information form the textfields
                database.update(appName.getText(),appCategory.getText(), appPlatform.getText());
                formWindow.setVisible(false); //you can't see me!
                formWindow.dispose(); //Destroy the JFrame object
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formWindow.setVisible(false); //you can't see me!
                formWindow.dispose(); //Destroy the JFrame object
            }
        });

        // add the buttons
        formContainer.add(cancel);
        formContainer.add(save);


//        buttonHolder.add(save);
//        buttonHolder.add(cancel);

        //adding it to the main frame
        formWindow.add(formContainer);
//        formWindow.add(buttonHolder);

        //show the frame
        formWindow.setVisible(true);
        formWindow.setSize(1000, 500);
        formWindow.setResizable(true);
    }
}
