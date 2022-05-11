package dsw.rudok.view.dialogs;

import dsw.rudok.controller.InfoController;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog{

    public InfoDialog(JFrame parent){
        super(parent,"Info",false);
        setLayout(new BorderLayout());
        setSize(600,400);
        setLocationRelativeTo(parent);

        ImagePanel slikaPanel = new ImagePanel(new ImageIcon("slike/slika.jpg").getImage());
        slikaPanel.setPreferredSize(new Dimension(200,200));

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100,100));
        topPanel.add(new JLabel("Informacije o studentu"),BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        BoxLayout layout = new BoxLayout(infoPanel,BoxLayout.Y_AXIS);
        infoPanel.setLayout(layout);

        Dimension prefDim = new Dimension(120,20);

        JPanel imePan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblIme = new JLabel("Ime: ");
        JTextField txtIme = new JTextField("Vid");
        lblIme.setPreferredSize(prefDim);
        txtIme.setPreferredSize(prefDim);
        txtIme.setEditable(false);
        imePan.add(lblIme);
        imePan.add(txtIme);

        JPanel prezimePan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblPrezime = new JLabel("Prezime: ");
        JTextField txtPrezime = new JTextField("Nikolic");
        lblPrezime.setPreferredSize(prefDim);
        txtPrezime.setPreferredSize(prefDim);
        txtPrezime.setEditable(false);
        prezimePan.add(lblPrezime);
        prezimePan.add(txtPrezime);

        JPanel indeksPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblIndeks = new JLabel("Indeks: ");
        JTextField txtIndeks = new JTextField("RN78/21");
        lblIndeks.setPreferredSize(prefDim);
        txtIndeks.setPreferredSize(prefDim);
        txtIndeks.setEditable(false);
        indeksPan.add(lblIndeks);
        indeksPan.add(txtIndeks);

        JPanel botPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botPan.setPreferredSize(new Dimension(100,100));
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new InfoController(this));
        botPan.add(btnOK);

        infoPanel.add(imePan);
        infoPanel.add(prezimePan);
        infoPanel.add(indeksPan);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(slikaPanel, BorderLayout.EAST);
        this.add(infoPanel, BorderLayout.WEST);
        this.add(botPan, BorderLayout.SOUTH);

        pack();
    }

    class ImagePanel extends JPanel{
        private Image img;

        public ImagePanel(String img){
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img){
            this.img = img;
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                    (int)(this.getSize().getHeight()-img.getHeight(null))/2,null);
        }
    }
}
