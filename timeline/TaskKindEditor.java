package timeline;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class TaskKindEditor extends AbstractCellEditor implements TableCellEditor {

    JComboBox combo;
    public TaskKindEditor(){
        combo=new JComboBox();
        for(TaskName n: TaskName.values() ){
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
