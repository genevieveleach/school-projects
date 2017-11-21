import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;


public class AdminWindow extends JFrame implements AdminPanel {

  private static AdminWindow instance;
  private JPanel contentPane;
  private JButton addUserButton;
  private JButton addGroupButton;
  private JButton openUserViewButton;
  private JButton showTotalUsersButton;
  private JButton showTotalMessagesButton;
  private JButton showTotalGroupsButton;
  private JButton showPositiveWordsButton;
  private JTextArea txtrUserId;
  private JTextArea txtrUserGroupId;
  private JTree tree;
  private TreeDataHandler treeDataHandler;
  private PopUp popUp = new PopUp();
  private JPanel panel1;
  private JPanel panel2;
  private JPanel panel3;

  private AdminWindow() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Admin Panel");
    setBounds(100, 100, 730, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    treeDataHandler = new TreeDataHandler(new HashMap<>());

    panel1 = new JPanel();
    panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tree View", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    panel1.setBounds(0, 11, 251, 426);
    contentPane.add(panel1);
    panel1.setLayout(null);
    tree = new JTree(treeDataHandler.getModel());
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.setBounds(0, 0, 235, 400);
    JScrollPane scrollPane = new JScrollPane(tree);
    scrollPane.setBounds(10, 21, 231, 393);
    panel1.add(scrollPane);

    Icon leafIcon = UIManager.getIcon("FileView.fileIcon");
    Icon nonLeafIcon = UIManager.getIcon("FileView.directoryIcon");
    tree.setCellRenderer(new MyCellRenderer(leafIcon, nonLeafIcon));

    //for all actionListening
    Handler handler = new Handler();

    addUserButton = new JButton("Add User");
    addUserButton.addActionListener(handler);
    addUserButton.setBounds(450, 108, 190, 62);
    contentPane.add(addUserButton);

    addGroupButton = new JButton("Add Group");
    addGroupButton.addActionListener(handler);
    addGroupButton.setBounds(450, 28, 190, 62);
    contentPane.add(addGroupButton);

    openUserViewButton = new JButton("Open User View");
    openUserViewButton.addActionListener(handler);
    openUserViewButton.setBounds(284, 190, 364, 71);
    contentPane.add(openUserViewButton);

    showTotalUsersButton = new JButton("Show User Total");
    showTotalUsersButton.addActionListener(handler);
    showTotalUsersButton.setBounds(265, 280, 190, 71);
    contentPane.add(showTotalUsersButton);

    showTotalMessagesButton = new JButton("Show Messages Total");
    showTotalMessagesButton.addActionListener(handler);
    showTotalMessagesButton.setBounds(265, 361, 190, 71);
    contentPane.add(showTotalMessagesButton);

    showTotalGroupsButton = new JButton("Show Group Total");
    showTotalGroupsButton.addActionListener(handler);
    showTotalGroupsButton.setBounds(482, 361, 190, 71);
    contentPane.add(showTotalGroupsButton);

    showPositiveWordsButton = new JButton("Show Positive Percentage");
    showPositiveWordsButton.addActionListener(handler);
    showPositiveWordsButton.setBounds(482, 280, 190, 71);
    contentPane.add(showPositiveWordsButton);

    panel2 = new JPanel();
    panel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Group ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    panel2.setBounds(261, 20, 163, 73);
    contentPane.add(panel2);
    panel2.setLayout(null);

    txtrUserGroupId = new JTextArea();
    txtrUserGroupId.setBounds(6, 16, 151, 50);
    panel2.add(txtrUserGroupId);

    panel3 = new JPanel();
    panel3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    panel3.setBounds(261, 100, 163, 73);
    contentPane.add(panel3);
    panel3.setLayout(null);

    txtrUserId = new JTextArea();
    txtrUserId.setBounds(6, 16, 151, 50);
    panel3.add(txtrUserId);

    this.setVisible(true);
  }

  //singleton
  public static AdminWindow getInstance() {
    if (instance == null) {
      instance = new AdminWindow();
    }
    return instance;
  }

  @Override
  public void setIcons() {
    tree.setCellRenderer(new DefaultTreeCellRenderer() {
      private Icon groupIcon = UIManager.getIcon("FileView.directoryIcon");
      private Icon userIcon = UIManager.getIcon("FileView.fileIcon");

      @Override
      public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean isLeaf, int row, boolean focused) {
        Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, isLeaf, row, focused);
        if (isLeaf) {
          setIcon(userIcon);
        } else {
          setIcon(groupIcon);
        }
        return c;
      }
    });
  }

  @Override
  public void openUserView(User user) {
    User node = getSelectedNode(this.tree);
    if (!(node instanceof IndividualUser)) {
      popUp.infoBox("No user view for groups.", "ERROR");
    } else {
      new IndividualUserWindow((IndividualUser) node, treeDataHandler);
    }
  }

  public User getSelectedNode(JTree tree) {
    TreePath parentPath = tree.getSelectionPath();
    User selectedNode;
    if (parentPath == null) {
      selectedNode = (User) treeDataHandler.getModel().getRoot();
    } else {
      selectedNode = (User) (parentPath.getLastPathComponent());
    }
    return selectedNode;
  }


  private class Handler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      User selectedNode = getSelectedNode(tree);

      if (e.getSource() == addUserButton) {
        String userId = txtrUserId.getText().trim();
        long creation = System.currentTimeMillis();
        User newUser = new IndividualUser(userId, creation);
        selectedNode = getSelectedNode(tree);
        if (userId.isEmpty()) {
          return;
        }
        if(!newUser.validateID(userId)) {
          popUp.infoBox("Invalid characters in ID.", "ERROR");
          return;
        }
        if (treeDataHandler.addNode(selectedNode, newUser)) {
          tree.scrollPathToVisible(new TreePath(newUser.getPath()));
          System.out.printf("Creation time for %s: %d\n", userId, creation);
        } else {
          return;
        }
      }

      if (e.getSource() == addGroupButton) {
        String groupId = txtrUserGroupId.getText().trim();
        long creation = System.currentTimeMillis();
        User newUserGroup = new GroupUser(groupId, creation);
        if (groupId.isEmpty()) {
          return;
        }
        if (!newUserGroup.validateID(groupId)) {
          popUp.infoBox("Invalid characters in ID.", "ERROR");
          return;
        }
        if (treeDataHandler.addNode(selectedNode, newUserGroup)) {
          tree.scrollPathToVisible(new TreePath(newUserGroup.getPath()));
          System.out.printf("Creation time for %s: %d\n", groupId, creation);
        } else {
          return;
        }
      }

      if (e.getSource() == openUserViewButton) {
        openUserView(selectedNode);
        System.out.printf("Opened user view for %s, creation time of %s: %d\n", selectedNode.getID(), selectedNode.getID(), selectedNode.getCreationTime());
      } else {
        if (e.getSource() == showTotalUsersButton) {
          TotalUsers totalUsers = new TotalUsers();
          treeDataHandler.accept(totalUsers);
          popUp.infoBox("There are " + totalUsers.result() + " users.", "Total Users");
        }
        if (e.getSource() == showTotalGroupsButton) {
          TotalGroups totalGroups = new TotalGroups();
          treeDataHandler.accept(totalGroups);
          popUp.infoBox("There are " + totalGroups.result() + " groups.", "Total Groups");

        }
        if (e.getSource() == showTotalMessagesButton) {
          TotalMessages totalMessages = new TotalMessages();
          treeDataHandler.accept(totalMessages);
          popUp.infoBox("There are " + totalMessages.result() + " messages.", "Total Messages");
        }
        if (e.getSource() == showPositiveWordsButton) {
          PositiveWords positivePercentage = new PositiveWords("good happy awesome great excellent amazing brilliant sweet" +
              "kind caring love");
          treeDataHandler.accept(positivePercentage);
          popUp.infoBox(String.format("%.02f", positivePercentage.result()) + "% of messages are positive.",
              "Positive Percentage of Messages");
        }
      }
    }
  }
}
