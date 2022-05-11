package dsw.rudok.app;

import dsw.rudok.actions.ActionManager;
import dsw.rudok.command.CommandManager;
import dsw.rudok.gui.RudokMenu;
import dsw.rudok.gui.RudokToolbar;
import dsw.rudok.gui.tab.ProjectView;
import dsw.rudok.gui.tree.model.RudokTreeModel;
import dsw.rudok.gui.tree.model.RudokTreeNode;
import dsw.rudok.gui.tree.view.RudokTree;
import dsw.rudok.model.workspace.Workspace;
import dsw.rudok.observer.RudokSubscriber;
import dsw.rudok.rudokerrors.RudokErrorFactory;
import dsw.rudok.rudokerrors.RudokGreska;
import dsw.rudok.view.dialogs.LoadWorkspaceDialog;
import dsw.rudok.view.frames.TextEditor;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements RudokSubscriber {

    private static MainFrame instance = null;
    private ActionManager actionManager;
    private RudokMenu menu;
    private RudokToolbar toolbar;
    private RudokTree rudokTree;
    private RudokTreeModel rudokTreeModel;
    private RudokErrorFactory rudokErrorFactory;
    private ProjectView projectView;
    private CommandManager commandManager;

    private TextEditor textEditor;

    private MainFrame(){

    }

    private void initialiseGui(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int screenWidth = screenDimension.width;
        int screenHeight = screenDimension.height;

        setSize(screenWidth / 2, screenHeight / 2);
        setTitle("RuDok");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        menu = new RudokMenu();
        this.setJMenuBar(menu);

        toolbar = new RudokToolbar();
        add(toolbar,BorderLayout.NORTH);

        rudokTreeModel = new RudokTreeModel(new RudokTreeNode(new Workspace("Workspace")));
        rudokTree = new RudokTree(rudokTreeModel);

        JPanel treePanel = new JPanel(new BorderLayout());
        treePanel.add(rudokTree, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(treePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMinimumSize(new Dimension(100,100));

        this.projectView = new ProjectView();

        this.rudokErrorFactory.addRudokSubscriber(this);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,projectView);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(250);
        add(split,BorderLayout.CENTER);

        LoadWorkspaceDialog loadWorkspaceDialog = new LoadWorkspaceDialog();
        loadWorkspaceDialog.setVisible(true);

        this.commandManager = new CommandManager();
        validate();
    }

    private void initialise(){
        actionManager = new ActionManager();
        rudokErrorFactory = new RudokErrorFactory();
    }


    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
            instance.initialiseGui();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public RudokTree getRudokTree() {
        return rudokTree;
    }

    public RudokTreeModel getRudokTreeModel() {
        return rudokTreeModel;
    }

    public RudokErrorFactory getRudokErrorFactory() {
        return rudokErrorFactory;
    }

    public ProjectView getProjectView() {
        return projectView;
    }

    public CommandManager getCommandManager(){return commandManager;}

    public TextEditor getTextEditor(){return textEditor;}

    public void setTextEditor(TextEditor textEditor){this.textEditor = textEditor;}

    @Override
    public void update(Object notification, String action) {
        if(notification instanceof RudokGreska && action.equalsIgnoreCase("rudokError")){
            JOptionPane.showMessageDialog(null, ((RudokGreska)notification).getContent(),((RudokGreska)notification).getTitle(),((RudokGreska)notification).getType());
        }
    }
}
