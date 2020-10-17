import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InfoWindow implements NewWindow {
    JFrame viewingWindow;

    public InfoWindow() {
        createNewFrame();
    }

    @Override
    public void createNewFrame() {
         viewingWindow = new JFrame("Viewing database...");
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        //arraylist for combo box contents
        ArrayList<String> container = new ArrayList<>();
        database.retrieveCategories(container);

        //left panel has the buttons/selections we use the JCombobox
        JComboBox<String> categories = new JComboBox<>();
        for (String item : container
        ) {
            categories.addItem(item);
        }

        // check this one out
        DefaultListModel<String> items = new DefaultListModel<>();
        categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("testing click!");
                items.clear(); // if we don't do this then it just piles up

                //then show all contents in that category
                ArrayList<String> itemss = database.showCategoryItems(categories.getSelectedItem().toString());
                //we need to attach this info to component
                System.out.println(database.showCategoryItems(categories.getSelectedItem().toString()));

                // add these items into a component
                for (String item : itemss) {
                    items.addElement(item);
                }
            }
        });


        JList itemList = new JList(items);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) {
                    super.mouseClicked(e);
                    System.out.println(itemList.getSelectedIndex());
                    CreateForm editableInfo = new CreateForm(itemList.getSelectedValue().toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu(e, itemList.getSelectedValue().toString());
                }
            }
        });

        leftPanel.add(categories);
        rightPanel.add(itemList);

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);


        viewingWindow.add(pane);
        viewingWindow.setVisible(true);
        viewingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewingWindow.setSize(1000, 500);
        viewingWindow.setResizable(false);
    }



    private void popupMenu(MouseEvent e, String name){
        final JPopupMenu popup = new JPopupMenu("Edit");
        JMenuItem delete = new JMenuItem("delete");
        popup.add(delete);
        popup.show(viewingWindow , e.getXOnScreen(),e.getYOnScreen());
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.delete(name);
            }
        });
    }
}
