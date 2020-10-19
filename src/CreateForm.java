import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CreateForm implements NewWindow {

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

        DBConnect.Item DBitem = database.getDetails(itemName);

        JFrame formWindow = new JFrame(nameFrame);


        JPanel formContainer = new JPanel(new GridLayout(4,2));


        JPanel buttonHolder = new JPanel();
        buttonHolder.setPreferredSize(new Dimension(400, 400));

        JLabel name = new JLabel("name");
        JLabel category = new JLabel(("category"));
        JLabel platform = new JLabel("platform");

        JTextField appName = new JTextField(itemName);
        JTextField appCategory = new JTextField(DBitem.category);
        JTextField appPlatform = new JTextField(DBitem.platform);

        name.setLabelFor(appName);
        name.setHorizontalAlignment(SwingConstants.LEADING);
        category.setLabelFor(appCategory);
        platform.setLabelFor(appPlatform);

        appName.setEditable(false);


        formContainer.add(name);
        formContainer.add(appName);
        formContainer.add(category);
        formContainer.add(appPlatform);
        formContainer.add(platform);
        formContainer.add(appCategory);

        Button save = new Button("save");
        Button cancel = new Button("cancel");

  
        Dimension dimensions = new Dimension(200,100);
        appName.setPreferredSize(dimensions);
        appCategory.setPreferredSize(dimensions);
        appPlatform.setPreferredSize(dimensions);


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.update(appName.getText(),appCategory.getText(), appPlatform.getText());
                formWindow.setVisible(false); 
                formWindow.dispose(); 
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formWindow.setVisible(false); 
                formWindow.dispose(); 
            }
        });

        formContainer.add(cancel);
        formContainer.add(save);

        formWindow.add(formContainer);
        formWindow.setVisible(true);
        formWindow.setSize(1000, 500);
        formWindow.setResizable(true);
    }
}
