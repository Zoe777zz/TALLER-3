/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistasmodeloTabla;

/**
 *
 * @author InnoVausuario
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ColorRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        if(value != null)
            setText(value.toString());
        else
            setText("");
        if(row % 2 == 0){
            setBackground(new Color(255, 215, 214));
            setForeground(Color.BLACK);
        }else{
            setBackground(new Color(219, 224, 152));
            setForeground(Color.BLACK);
        }
            
            //setForeground(Color.getHSBColor(TOP_ALIGNMENT, TOP_ALIGNMENT, TOP_ALIGNMENT));
        return this;
    }
}