package dsw.rudok.view.dialogs;

import dsw.rudok.app.MainFrame;
import dsw.rudok.command.concrete.AddNodeCommand;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.model.workspace.Project;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.model.workspace.nodes.RuNode;
import dsw.rudok.model.workspace.nodes.RuNodeComposite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoadWorkspaceDialog extends JDialog {

    private JButton btnOpenWorkspace;
    private JButton btnLastSaved;

    public LoadWorkspaceDialog() {
        setTitle("Choose workspace to work with");
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.add(new JLabel("Choose option"), BorderLayout.NORTH);

        btnOpenWorkspace = new JButton("Open Workspace");
        btnLastSaved = new JButton("Open Last Saved Workspace");

        btnOpenWorkspace.setSize(new Dimension(60, 20));
        btnLastSaved.setSize(new Dimension(60, 20));

        this.add(btnOpenWorkspace, BorderLayout.EAST);
        this.add(btnLastSaved, BorderLayout.WEST);

        btnOpenWorkspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                ObjectInputStream ois = null;
                BufferedReader br = null;
                File fileToLoad = null;

                if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    fileToLoad = fileChooser.getSelectedFile();
                }
                if (fileToLoad == null) return;

                if (fileToLoad.toString().endsWith(".txt")) {
                    try {
                        br = new BufferedReader(new FileReader(fileToLoad));

                        while (br.readLine() != null) {
                            ois = new ObjectInputStream(new FileInputStream(br.readLine()));

                            Project p = null;

                            try {
                                p = (Project) ois.readObject();
                            } catch (ClassNotFoundException ex) {
                                System.out.println("[-] Error LoadAction Workspace");
                            }

                            Workspace ws = (Workspace) ((RudokTreeNode) MainFrame.getInstance().getRudokTreeModel().getRoot()).getRuNode();
                            RuNode child = (RuNode) p;
                            RuNodeComposite parent = (RuNodeComposite) ws;
                            child.setParent(parent);
                            parent.addChild(child);
                            RudokTreeNode childTree = new RudokTreeNode(p);
                            MainFrame.getInstance().getCommandManager().addCommand(new AddNodeCommand((RudokTreeNode) MainFrame.getInstance().getRudokTreeModel().getRoot(), childTree));
                            getParent().setVisible(false);
                        }
                    } catch (IOException ex) {
                        System.out.println("[-] Error LoadAction");
                    } finally {
                        if (br != null) {
                            try {
                                br.close();
                            } catch (IOException ex) {
                                System.out.println("[-] LoadAction error while closing br");
                            }
                        }

                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (IOException ex) {
                                System.out.println("[-] LoadAction error while closing ois");
                            }
                        }
                    }
                } else {
                    MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Wrong type of file, choose txt file for your workspace", "File Error", 0);
                    return;
                }
            }
        });

        btnLastSaved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                ObjectInputStream ois = null;
                BufferedReader br = null;
                File fileToLoad = new File("src/dsw/rudok/saved.txt");

                if (fileToLoad.toString().endsWith(".txt")) {
                    try {
                        br = new BufferedReader(new FileReader(fileToLoad));

                        while (br.readLine() != null) {
                            ois = new ObjectInputStream(new FileInputStream(br.readLine()));

                            Project p = null;

                            try {
                                p = (Project) ois.readObject();
                            } catch (ClassNotFoundException ex) {
                                System.out.println("[-] Error LoadAction Workspace");
                            }

                            Workspace ws = (Workspace) ((RudokTreeNode) MainFrame.getInstance().getRudokTreeModel().getRoot()).getRuNode();
                            RuNode child = (RuNode) p;
                            child.setParent((RuNodeComposite) ws);
                            ((RuNodeComposite) ws).addChild(child);
                            RudokTreeNode childTree = new RudokTreeNode(p);
                            MainFrame.getInstance().getCommandManager().addCommand(new AddNodeCommand((RudokTreeNode) MainFrame.getInstance().getRudokTreeModel().getRoot(), childTree));
                        }
                    } catch (IOException ex) {
                        System.out.println("[-] Error LoadAction");
                    } finally {
                        if (br != null) {
                            try {
                                br.close();
                            } catch (IOException ex) {
                                System.out.println("[-] LoadAction error while closing br");
                            }
                        }

                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (IOException ex) {
                                System.out.println("[-] LoadAction error while closing ois");
                            }
                        }
                        getParent().setVisible(false);
                    }
                } else {
                    MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Wrong type of file, choose txt file for your workspace", "File Error", 0);
                    return;
                }
            }
        });

        this.pack();
        this.revalidate();
        this.repaint();
    }
}
