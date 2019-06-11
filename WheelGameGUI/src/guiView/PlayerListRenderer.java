package guiView;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerListRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            Player player = (Player) value;
            setText(player.getPlayerName());
        }
//        if (index == -1) {
//            setText("no player chosen");
//        }
        return this;
    }
}