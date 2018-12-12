package timeline;

import basicBI.BITask;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class BITaskKindEditor extends AbstractCellEditor implements TableCellEditor {

    JComboBox combo;
    public BITaskKindEditor(){
        combo=new JComboBox();
        for(BITaskName n:BITaskName.values() ){
            combo.addItem( n.toString());
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        combo.setSelectedItem(value);
        return combo;
    }

    @Override
    public Object getCellEditorValue() {
        return (String)combo.getSelectedItem();
    }
}
