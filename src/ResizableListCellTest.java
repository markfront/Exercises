/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author owner
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
 
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
 
 
public class ResizableListCellTest extends JFrame
{
    private JList list;
    public ResizableListCellTest()
    {
        
        //setup the data model
        DefaultListModel model = new DefaultListModel();
        
        //create the list
        list = new JList(model);
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
        //set the cell renderer with a -1 selection
        list.setCellRenderer(new ResizableListCellRenderer(-1));
        
        //add the listener that will adjust the height of the selected cell.
        list.addListSelectionListener(new ListSelectionListener(){
 
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
              if (e.getValueIsAdjusting())
              {
                list.setCellRenderer(
                        new ResizableListCellRenderer(
                                list.getSelectedIndex()));
              }
            }});
 
        
        //add data to the model
        model.addElement("Test Item 1");
        model.addElement("Test Item 2");
        model.addElement("Test Item 3");
        model.addElement("Test Item 4");
        model.addElement("Test Item 5");
     
        //init the frame
        this.add(list);
        this.setSize(200,500);
        this.setVisible(true);
    }
    public class ResizableListCellRenderer implements ListCellRenderer
    {
        public ResizableListCellRenderer(int index)
        {
            selectedIndex = index;
        }
        private int selectedIndex = -1;
 
        @Override
        public Component getListCellRendererComponent(JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus)
        {
            JLabel label = new JLabel((String)value);
            label.setOpaque(true);
            if (isSelected)
                label.setBackground(Color.white);
            else
                label.setBackground(Color.gray);
            if (selectedIndex > -1 && index == selectedIndex)
                label.setPreferredSize(new Dimension(100,105));
            else
                label.setPreferredSize(new Dimension(100,20));
            return label;
        }
    }
    
    static public void main(String args[])
    {
        new ResizableListCellTest();
    }
}