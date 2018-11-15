package poly.app.ui.custom;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JComboBoxListener extends KeyAdapter {

    @SuppressWarnings("rawtypes")
    JComboBox cbListener;
    @SuppressWarnings("rawtypes")
    Vector vector;

    @SuppressWarnings("rawtypes")
    public JComboBoxListener(JComboBox cbListenerParam, Vector vectorParam) {
        cbListener = cbListenerParam;
        vector = vectorParam;
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyCode() == KeyEvent.VK_UP) {
            return;
        }
        
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            ((JTextField) cbListener.getEditor().getEditorComponent()).setFocusable(false);
            return;
        }
        
        String text = ((JTextField) key.getSource()).getText();
        
        if (text.length() == 0) {
            cbListener.setPopupVisible(false);
            return;
        }
        
        cbListener.setModel(new DefaultComboBoxModel(getFilteredList(text)));
        cbListener.setSelectedIndex(-1);
        ((JTextField) cbListener.getEditor().getEditorComponent()).setText(text);
        
        cbListener.showPopup();
//        if (!key.isControlDown()) {
//            cbListener.showPopup();
//        }else{
//            cbListener.setPopupVisible(false);
//        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Vector getFilteredList(String text) {
        Vector v = new Vector();
        for (int a = 0; a < vector.size(); a++) {
            if (vector.get(a).toString().toLowerCase().contains(text.toLowerCase())) {
                v.add(vector.get(a).toString());
            }
        }
        return v;
    }
}
