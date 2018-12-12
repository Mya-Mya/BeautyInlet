package timeline;

import javafx.scene.control.TableCell;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class TimeEditor extends AbstractCellEditor implements TableCellEditor {
    TimeEditor(){

    }
    Object value;
    @Override
    public Object getCellEditorValue() {
        return value;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return null;
    }
}
