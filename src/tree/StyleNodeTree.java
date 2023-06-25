package tree;

import user.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class StyleNodeTree implements TreeCellRenderer {
	private JLabel nodeLabel;

	public StyleNodeTree() {
		nodeLabel = new JLabel();
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		NodeType node = ((NodeType) value);
		if (node instanceof Group) {
			nodeLabel.setText("- "+ node.getUniqueID());
			nodeLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
		} else {
			nodeLabel.setText("@ " + node.getUniqueID());
			nodeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 13));
		}
		if (selected) {
			nodeLabel.setOpaque(true);
		} else {
			nodeLabel.setOpaque(false);
		}

		return nodeLabel;
	}
}
