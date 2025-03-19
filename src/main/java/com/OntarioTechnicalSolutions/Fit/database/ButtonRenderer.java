package com.OntarioTechnicalSolutions.Fit.database;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            JButton Button = (JButton) value; // Correct type
            return Button;
        }
        return new JLabel("Error: Not a Button");
    }
}
