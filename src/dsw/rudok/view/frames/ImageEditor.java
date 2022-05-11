package dsw.rudok.view.frames;

import dsw.rudok.app.MainFrame;
import dsw.rudok.gui.tab.PresentationView;
import dsw.rudok.model.workspace.Presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageEditor extends JFrame {

    private JButton btnOpen;
    private JButton btnSave;

    private JToolBar toolBar;
    private ImagePanel imagePanel;

    private String imagePath;

    public ImageEditor(){
        setSize(500,600);
        setTitle("Edit Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnOpen = new JButton("Open");
        btnSave = new JButton("Save");

        if(((PresentationView)MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).getSelectedSlot().getSlot().getContent() == null){
            imagePath = "src/dsw/rudok/view/frames/noImg.jpg";
        }else{
            imagePath = ((PresentationView)MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).getSelectedSlot().getSlot().getContent();
        }

        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(btnOpen);
        toolBar.add(btnSave);

        imagePanel = new ImagePanel(this.imagePath);
        imagePanel.setSize(new Dimension(100,100));

        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView());

                FileFilter filter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());
                jFileChooser.setFileFilter(filter);

                int r = jFileChooser.showOpenDialog(null);

                if(r == JFileChooser.APPROVE_OPTION){
                    imagePath = jFileChooser.getSelectedFile().getAbsolutePath();
                    imagePanel.setImg(new ImageIcon(imagePath).getImage());
                }else{
                    System.out.println("cancelled");
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
                presentationView.getSelectedSlot().getSlotHandler().write(imagePath);
                System.out.println(presentationView.getSelectedSlot().getSlot().getContent());
                setVisible(false);
            }
        });

        this.add(toolBar, BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    class ImagePanel extends JPanel{
        private Image img;

        public ImagePanel(String img){
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img){
            this.img = img;
        }

        public void setImg(Image img){this.img = img;}

        public void paintComponent(Graphics g) {
            g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                    (int)(this.getSize().getHeight()-img.getHeight(null))/2,null);
            this.repaint(); this.revalidate();
        }
    }
}
