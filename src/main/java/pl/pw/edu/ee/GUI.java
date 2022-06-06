package pl.pw.edu.ee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GUI {
    Settings defaultSettings = new Settings();

    JFrame frame = new JFrame();

    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JPanel sideButtonsPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel sidePiecesPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JLabel[] leftLabels = new JLabel[defaultSettings.boardWidth];
    JLabel[] bottomLabels = new JLabel[defaultSettings.boardLength];
    JLabel[] sideLabel = new JLabel[4];

    JButton[] sideLabelButtons = new JButton[6];
    JButton[] sideButtons = new JButton[4];
    JButton[][] buttons = new JButton[defaultSettings.boardLength][defaultSettings.boardWidth];

    JLayeredPane[][] buttonsLayeredPane = new JLayeredPane[defaultSettings.boardLength][defaultSettings.boardWidth];

    Border border1 = BorderFactory.createLineBorder(defaultSettings.mainColor1, 4);
    Border border2 = BorderFactory.createLineBorder(defaultSettings.mainColor2, 4);
    Border emptyBorder1 = BorderFactory.createEmptyBorder();
    Border emptyBorder2 = BorderFactory.createEmptyBorder(4, 4, 4, 4);
    Border defaultBorder = (new JButton().getBorder());

    Color defaultColor = UIManager.getColor("Panel.background");

    ImageIcon[] piecesIcons = {
            new ImageIcon("src\\pieces_icons\\bP.png"),
            new ImageIcon("src\\pieces_icons\\bR.png"),
            new ImageIcon("src\\pieces_icons\\bN.png"),
            new ImageIcon("src\\pieces_icons\\bB.png"),
            new ImageIcon("src\\pieces_icons\\bQ.png"),
            new ImageIcon("src\\pieces_icons\\bK.png"),
            new ImageIcon("src\\pieces_icons\\wP.png"),
            new ImageIcon("src\\pieces_icons\\wR.png"),
            new ImageIcon("src\\pieces_icons\\wN.png"),
            new ImageIcon("src\\pieces_icons\\wB.png"),
            new ImageIcon("src\\pieces_icons\\wQ.png"),
            new ImageIcon("src\\pieces_icons\\wK.png") };

    public GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 930);
        frame.setTitle("Best Chess Ever Made");
        frame.setResizable(false); // cant resize window
        frame.setIconImage(piecesIcons[11].getImage());
        frame.getContentPane().setBackground(defaultSettings.mainColor2);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textLabel.setBackground(defaultSettings.mainColor2);
        textLabel.setForeground(defaultSettings.mainColor1);
        textLabel.setFont(defaultSettings.font1);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Wie Pan Co To Znaczy Trappin?");
        textLabel.setOpaque(true);
        textLabel.setBorder(border1);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(1200, 83));

        sidePanel.setLayout(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(400, 800));
        sidePanel.setBorder(border1);
        sidePanel.setBackground(defaultSettings.mainColor2);

        sideButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        sideButtonsPanel.setPreferredSize(new Dimension(400, 680));
        sideButtonsPanel.setBackground(defaultSettings.mainColor2);

        for (int i = 0; i < 4; i++) {
            sideLabel[i] = new JLabel();
            if (i == 0 || i == 2) {
                sideLabel[i] = new TimerLabel(10 * 60 * 1000 + 100);
                sideLabel[i].setFont(defaultSettings.font4);
            }
            if (i == 1 || i == 3) {
                sideLabel[i].setFont(defaultSettings.font3);
            }

            sideLabel[i].setFocusable(false);
            sideLabel[i].setHorizontalAlignment(JLabel.CENTER);
            sideLabel[i].setPreferredSize(new Dimension(105, 62));
            sideLabel[i].setForeground(defaultSettings.mainColor1);
            sideLabel[i].setBackground(defaultSettings.mainColor2);
            sideLabel[i].setOpaque(true);
            sideLabel[i].setBorder(border1);
        }
        sideLabel[3].setPreferredSize(new Dimension(355, 400));
        sideLabel[3].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        // JPanel whiteMovesPanel = new JPanel();
        // JPanel blackMovesPanel = new JPanel();
        JLabel whiteMovesLabel = new JLabel();
        JLabel blackMovesLabel = new JLabel();
        whiteMovesLabel.setText("text");
        blackMovesLabel.setText("text");
        whiteMovesLabel.setPreferredSize(new Dimension(130, 300));
        blackMovesLabel.setPreferredSize(new Dimension(130, 300));
        whiteMovesLabel.setForeground(Color.white);
        blackMovesLabel.setForeground(defaultSettings.mainColor1);
        sideLabel[3].add(whiteMovesLabel);
        sideLabel[3].add(blackMovesLabel);

        sideLabel[0].setBackground(Color.white);

        for (int i = 0; i < 6; i++) {
            sideLabelButtons[i] = new JButton();
            sideLabelButtons[i].setFocusable(false);
            sideLabelButtons[i].setHorizontalAlignment(JLabel.CENTER);
            sideLabelButtons[i].setPreferredSize(new Dimension(74, 62));
            sideLabelButtons[i].setForeground(defaultSettings.mainColor1);
            sideLabelButtons[i].setBackground(defaultSettings.mainColor2);
            sideLabelButtons[i].setOpaque(true);
            sideLabelButtons[i].setBorder(border1);
        }
        sideLabelButtons[4].setPreferredSize(new Dimension(168, 62));
        sideLabelButtons[5].setPreferredSize(new Dimension(168, 62));
        sideLabelButtons[4].setIcon(new ImageIcon("src\\buttons_icons\\add32.png"));
        sideLabelButtons[5].setIcon(new ImageIcon("src\\buttons_icons\\settings32.png"));

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(800, 800));
        bottomPanel.setBorder(border1);
        bottomPanel.setBackground(defaultSettings.mainColor2);

        sidePiecesPanel.setLayout(new GridLayout(1, 4));
        sidePiecesPanel.setPreferredSize(new Dimension(400, 100));
        sidePiecesPanel.setBackground(defaultSettings.mainColor2);
        sidePiecesPanel.setVisible(false);

        for (int i = 0; i < 4; i++) {
            sideButtons[i] = new JButton();
            sidePiecesPanel.add(sideButtons[i]);
            sideButtons[i].setFocusable(false);
            sideButtons[i].setBorder(emptyBorder2);
            if (i % 2 == 1) {
                sideButtons[i].setBackground(defaultSettings.tileColor1);
            } else
                sideButtons[i].setBackground(defaultSettings.tileColor2);
        }

        buttonPanel.setLayout(new GridLayout(defaultSettings.boardWidth, defaultSettings.boardLength));
        buttonPanel.setBackground(defaultSettings.mainColor2);
        buttonPanel.setPreferredSize(new Dimension(800, 800));

        for (int i = 0; i < defaultSettings.boardWidth; i++) {
            for (int j = 0; j < defaultSettings.boardLength; j++) {
                buttons[j][i] = new JButton();
                buttonPanel.add(buttons[j][i]);
                buttons[j][i].setFocusable(false);
                buttons[j][i].setBorder(emptyBorder2);
                if ((i + j) % 2 == 0) {
                    buttons[j][i].setBackground(defaultSettings.tileColor1);
                    buttons[j][i].setForeground(defaultSettings.tileColor1);
                } else
                    buttons[j][i].setBackground(defaultSettings.tileColor2);
                buttons[j][i].setForeground(defaultSettings.tileColor2);
                if (j == 0) {
                    leftLabels[i] = new JLabel();
                    leftLabels[i].setText(Integer.toString(defaultSettings.boardWidth - i));
                    leftLabels[i].setFont(defaultSettings.font2);
                    if ((i + j) % 2 == 0)
                        leftLabels[i].setForeground(defaultSettings.tileColor2);
                    else
                        leftLabels[i].setForeground(defaultSettings.tileColor1);
                    buttons[j][i].setLayout(new BorderLayout());
                    buttons[j][i].add(leftLabels[i], BorderLayout.NORTH);
                }
                if (i == defaultSettings.boardWidth - 1) {
                    bottomLabels[i] = new JLabel();
                    char c = (char) (j + 1 + 64 + 32);
                    String s = String.valueOf(c);
                    bottomLabels[i].setText(s);
                    bottomLabels[i].setFont(defaultSettings.font2);
                    bottomLabels[i].setHorizontalAlignment(JLabel.RIGHT);
                    if ((i + j) % 2 == 0)
                        bottomLabels[i].setForeground(defaultSettings.tileColor2);
                    else
                        bottomLabels[i].setForeground(defaultSettings.tileColor1);
                    if (j != 0)
                        buttons[j][i].setLayout(new BorderLayout());
                    buttons[j][i].add(bottomLabels[i], BorderLayout.SOUTH);
                }
            }
        }

        titlePanel.add(textLabel);
        sidePanel.add(sidePiecesPanel, BorderLayout.SOUTH);
        sideButtonsPanel.add(sideLabel[0]);
        sideButtonsPanel.add(sideLabel[1]);
        sideButtonsPanel.add(sideLabel[2]);
        sideButtonsPanel.add(sideLabel[3]);
        sideButtonsPanel.add(sideLabelButtons[0]);
        sideButtonsPanel.add(sideLabelButtons[1]);
        sideButtonsPanel.add(sideLabelButtons[2]);
        sideButtonsPanel.add(sideLabelButtons[3]);
        sideButtonsPanel.add(sideLabelButtons[4]);
        sideButtonsPanel.add(sideLabelButtons[5]);
        sidePanel.add(sideButtonsPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(sidePanel, BorderLayout.EAST);
        frame.add(bottomPanel);
    }
}
