package dsw.rudok.slothandler;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.SlotView;

import javax.swing.text.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class TextHandler extends SlotHandler{

    private SlotView slotView;

    public TextHandler(SlotView slotView) {
        super(slotView);
        this.slotView = slotView;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255, 0, 0));
//        g.setFont(new Font(Font.SANS_SERIF,1,17));
//        g.drawString(slotView.getSlot().getContent(), slotView.getX(), slotView.getY());
        boolean bold = false;
        boolean italic = false;
        boolean underline = false;

        int x = slotView.getX();
        int y = slotView.getY() + 10;

        String slotContent = slotView.getSlot().getContent();
        int contentLength = slotContent.length();

        for(int i=0;i<contentLength;i++){
            char current = slotContent.charAt(i);

            if(current == '*') bold = true;
            else if(current == '?') italic = true;
            else if(current == '_') underline = true;
            else{
                Font font = null;
                if(bold && italic && underline){
                    Map<TextAttribute, Integer> map = new HashMap<>();
                    map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel", Font.ITALIC | Font.BOLD, 14).deriveFont(map);
                }else if(bold && italic){font = new Font("Ariel",Font.ITALIC | Font.BOLD, 14);}
                else if(bold && underline){
                    Map<TextAttribute, Integer> map = new HashMap<>();
                    map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel",Font.BOLD, 14).deriveFont(map);
                }else if(italic && underline){
                    Map<TextAttribute, Integer> map = new HashMap<>();
                    map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel", Font.ITALIC, 14).deriveFont(map);
                }
                else if(bold) font = new Font("Ariel", Font.BOLD, 14);
                else if(italic) font = new Font("Ariel", Font.ITALIC, 14);
                else if(underline){
                    Map<TextAttribute, Integer> map = new HashMap<>();
                    map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel", Font.PLAIN, 14).deriveFont(map);
                }else{
                    font = new Font("Ariel", Font.PLAIN, 14);
                    x+=1;
                }

                x+=7;
                g.setFont(font);
                g.drawString(""+current,x,y);
            }
        }
    }

    @Override
    public void write(String content) {
//        slotView.getSlot().setContent(content);
        int contentLength = content.length();
        String newContent = "";

        StyledDocument document = MainFrame.getInstance().getTextEditor().getTextPane().getStyledDocument();

        boolean bold = false;
        boolean italic = false;
        boolean underline = false;

        for(int i=0;i<contentLength;i++) {
            bold = false;
            italic = false;
            underline = false;

            if(document.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Bold) != null)
                bold = document.getCharacterElement(i).getAttributes().isDefined(StyleConstants.Bold);
            if(document.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Italic) != null)
                italic = document.getCharacterElement(i).getAttributes().isDefined(StyleConstants.Italic);
            if(document.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Underline) != null)
                underline = document.getCharacterElement(i).getAttributes().isDefined(StyleConstants.Underline);

            String toAppend = "" + content.charAt(i);

            if(bold)
                toAppend = "*" + toAppend + "*";
            if(italic)
                toAppend = "?" + toAppend + "?";
            if(underline)
                toAppend = "_" + toAppend + "_";

            newContent += toAppend;
        }
        System.out.println(newContent);
        slotView.getSlot().setContent(newContent);
    }

    @Override
    public String read() {
        boolean bold = false;
        boolean italic = false;
        boolean underline = false;

        StyledDocument document = new DefaultStyledDocument();

        String slotContent = slotView.getSlot().getContent();
        int slotContentSize = slotContent.length();

        for(int i=0;i<slotContentSize;i++){
            char current = slotContent.charAt(i);

            if(current == '*') bold = true;
            else if(current == '?') italic = true;
            else if(current == '_') underline = true;
            else{
                SimpleAttributeSet attributeSet = new SimpleAttributeSet();
                attributeSet.addAttribute(StyleConstants.Bold, bold);
                attributeSet.addAttribute(StyleConstants.Italic, italic);
                attributeSet.addAttribute(StyleConstants.Underline, underline);

                try{
                    document.insertString(document.getLength(),""+current,attributeSet);
                }catch (BadLocationException e){
                    MainFrame.getInstance().getRudokErrorFactory().makeErrorMessage("Cannot set style for document","Style Error",0);
                }
            }
        }
        MainFrame.getInstance().getTextEditor().getTextPane().setStyledDocument(document);
        return "";
    }
}
