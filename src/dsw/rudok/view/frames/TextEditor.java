package dsw.rudok.view.frames;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor extends JFrame {

    private JButton btnItalic;
    private JButton btnBold;
    private JButton btnUnderline;
    private JButton btnSave;

    private JToolBar toolBar;
    private JTextPane textPane;

    private Document document;

    private String content = "";

    public TextEditor(){
        setSize(500,600);
        setTitle("Edit Text");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnItalic = new JButton("Italic");
        btnBold = new JButton("Bold");
        btnUnderline = new JButton("Underline");
        btnSave = new JButton("Save");

        btnSave.setSize(new Dimension(120,20));

        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(btnItalic);
        toolBar.add(btnBold);
        toolBar.add(btnUnderline);

        textPane = new JTextPane();

        PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if(presentationView.getSelectedSlot().getSlot().getContent() == null){
            textPane.setText("");
        }else{
            content = presentationView.getSelectedSlot().getSlot().getContent();
            textPane.setText(content);
        }

        document = textPane.getStyledDocument();

        btnBold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StyledDocument document = textPane.getStyledDocument();
                int selectionStart = textPane.getSelectionStart();
                int selectionEnd = textPane.getSelectionEnd();

                if(selectionStart == selectionEnd){
                    return;
                }

                Element element = document.getCharacterElement(selectionStart);
                AttributeSet set = element.getAttributes();

                SimpleAttributeSet as = new SimpleAttributeSet(set.copyAttributes());
                StyleConstants.setBold(as,true);
                document.setCharacterAttributes(selectionStart,textPane.getSelectedText().length(),as, true);
            }
        });

        btnItalic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StyledDocument document = textPane.getStyledDocument();
                int selectionStart = textPane.getSelectionStart();
                int selectionEnd = textPane.getSelectionEnd();

                if(selectionStart == selectionEnd){
                    return;
                }

                Element element = document.getCharacterElement(selectionStart);
                AttributeSet set = element.getAttributes();

                SimpleAttributeSet as = new SimpleAttributeSet(set.copyAttributes());
                StyleConstants.setItalic(as,true);
                document.setCharacterAttributes(selectionStart,textPane.getSelectedText().length(),as, true);
            }
        });

        btnUnderline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StyledDocument document = textPane.getStyledDocument();
                int selectionStart = textPane.getSelectionStart();
                int selectionEnd = textPane.getSelectionEnd();

                if(selectionStart == selectionEnd){
                    return;
                }

                Element element = document.getCharacterElement(selectionStart);
                AttributeSet set = element.getAttributes();

                SimpleAttributeSet as = new SimpleAttributeSet(set.copyAttributes());
                StyleConstants.setUnderline(as,true);
                document.setCharacterAttributes(selectionStart,textPane.getSelectedText().length(),as, true);
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = textPane.getText();
                if(content != null){
                    PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
                    presentationView.getSelectedSlot().getSlotHandler().write(content);
                    setVisible(false);
                }
            }
        });

        this.add(toolBar, BorderLayout.NORTH);
        this.add(textPane, BorderLayout.CENTER);
        this.add(btnSave, BorderLayout.SOUTH);
    }

    public JTextPane getTextPane() {
        return textPane;
    }
}
