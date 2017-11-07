import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MyCellRenderer extends DefaultTreeCellRenderer {

  private Icon leafIcon;
  private Icon nonLeafIcon;

  public MyCellRenderer(Icon leafIcon, Icon nonLeafIcon) {
    this.leafIcon = leafIcon;
    this.nonLeafIcon = nonLeafIcon;
  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
    if (value instanceof IndividualUser) {
      setIcon(leafIcon);
    } else {
      setIcon(nonLeafIcon);
    }
    return this;
  }
}
