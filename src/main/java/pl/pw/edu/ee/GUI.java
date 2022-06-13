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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GUI {
    Settings defaultSettings = new Settings();

    JFrame frame = new JFrame();
    JFrame newGameFrame = new JFrame("New game");
    JFrame blackWinGameFrame = new JFrame();
    JFrame whiteWinGameFrame = new JFrame();
    JFrame stalemateFrame = new JFrame();
    JFrame blackLoseTimeFrame = new JFrame();
    JFrame whiteLoseTimeFrame = new JFrame();

    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JPanel sideButtonsPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel sidePiecesPanel = new JPanel();
    JLabel scrollPanePanel = new JLabel();
    JPanel outerScrollPanel = new JPanel();

    JLabel textLabel = new JLabel();
    JLabel whiteMovesLabel = new JLabel();
    JLabel blackMovesLabel = new JLabel();
    JLabel[] leftLabels = new JLabel[defaultSettings.boardWidth];
    JLabel[] bottomLabels = new JLabel[defaultSettings.boardLength];
    JLabel[] sideLabel = new JLabel[3];

    JScrollPane scrollPane = new JScrollPane(scrollPanePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    JButton players = new JButton();
    JButton bot = new JButton();
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

    String newLine2 = new String("<br/>");
    // String newLine = new String("<br/><br/>");
    String startText = new String("<html>");
    String endText = new String("</html>");

    int numberOnGUI = 1;

    ImageIcon[] piecesIcons = {
            new ImageIcon("src\\pieces_icons\\standard\\bP.png"),
            new ImageIcon("src\\pieces_icons\\standard\\bR.png"),
            new ImageIcon("src\\pieces_icons\\standard\\bN.png"),
            new ImageIcon("src\\pieces_icons\\standard\\bB.png"),
            new ImageIcon("src\\pieces_icons\\standard\\bQ.png"),
            new ImageIcon("src\\pieces_icons\\standard\\bK.png"),
            new ImageIcon("src\\pieces_icons\\standard\\wP.png"),
            new ImageIcon("src\\pieces_icons\\standard\\wR.png"),
            new ImageIcon("src\\pieces_icons\\standard\\wN.png"),
            new ImageIcon("src\\pieces_icons\\standard\\wB.png"),
            new ImageIcon("src\\pieces_icons\\standard\\wQ.png"),
            new ImageIcon("src\\pieces_icons\\standard\\wK.png") };

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

        for (int i = 0; i < 3; i++) {
            sideLabel[i] = new JLabel();
            if (i == 0 || i == 2) {
                sideLabel[i] = new TimerLabel(10 * 60 * 1000 + 100);
                sideLabel[i].setFont(defaultSettings.font4);
            }
            if (i == 1) {
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

        sideLabel[0].setForeground(Color.white);

        whiteMovesLabel.setText(startText);
        whiteMovesLabel.setHorizontalAlignment(JLabel.LEFT);
        whiteMovesLabel.setVerticalAlignment(JLabel.TOP);
        whiteMovesLabel.setFont(defaultSettings.font6);
        whiteMovesLabel.setForeground(Color.white);
        whiteMovesLabel.setPreferredSize(new Dimension(125, 300));

        blackMovesLabel.setText(startText);
        blackMovesLabel.setHorizontalAlignment(JLabel.LEFT);
        blackMovesLabel.setVerticalAlignment(JLabel.TOP);
        blackMovesLabel.setFont(defaultSettings.font6);
        blackMovesLabel.setForeground(defaultSettings.mainColor1);
        blackMovesLabel.setPreferredSize(new Dimension(125, 300));

        scrollPane.getViewport().setBackground(defaultSettings.mainColor2);

        scrollPanePanel.setPreferredSize(new Dimension(329, 300));
        scrollPanePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

        outerScrollPanel.setPreferredSize(new Dimension(355, 400));
        outerScrollPanel.setBorder(border1);
        outerScrollPanel.setLayout(new BorderLayout());
        outerScrollPanel.setBackground(defaultSettings.mainColor2);

        scrollPanePanel.add(whiteMovesLabel);
        scrollPanePanel.add(blackMovesLabel);
        outerScrollPanel.add(scrollPane);

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
        sideLabelButtons[0].setIcon(new ImageIcon("src\\buttons_icons\\left_fast.png"));
        sideLabelButtons[1].setIcon(new ImageIcon("src\\buttons_icons\\left.png"));
        sideLabelButtons[2].setIcon(new ImageIcon("src\\buttons_icons\\right.png"));
        sideLabelButtons[3].setIcon(new ImageIcon("src\\buttons_icons\\right_fast.png"));
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
        sideButtonsPanel.add(outerScrollPanel);
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

    public void resizeScroll(int turnNumber) {
        int number = turnNumber / 2;
        whiteMovesLabel.setPreferredSize(new Dimension(125, number * 25 + 30));
        blackMovesLabel.setPreferredSize(new Dimension(125, number * 25 + 30));
        scrollPanePanel.setPreferredSize(new Dimension(329, number * 25 + 60));
        JScrollBar sb = scrollPane.getVerticalScrollBar();
        sb.setValue(sb.getMaximum());
    }

    // promotion
    public void reprintScroll(boolean whiteTurn, String figure, int fromX, int toX) {
        char c = (char) (fromX + 1 + 64 + 32);
        String X1 = String.valueOf(c);
        c = (char) (toX + 1 + 64 + 32);
        String X2 = String.valueOf(c);
        if (whiteTurn == true) {
            whiteMovesLabel
                    .setText(whiteMovesLabel.getText() + numberOnGUI++ + ". " + "P" + X1 + X2 + figure + newLine2);
        } else {
            blackMovesLabel.setText(blackMovesLabel.getText() + "P" + X1 + X2 + figure + newLine2);
        }
    }

    // normal
    public void reprintScroll(boolean whiteTurn, String figure, int fromX, int fromY, int toX, int toY,
            boolean isStrike) {
        String Y1 = Integer.toString(defaultSettings.boardWidth - fromY);
        String Y2 = Integer.toString(defaultSettings.boardWidth - toY);
        char c = (char) (fromX + 1 + 64 + 32);
        String X1 = String.valueOf(c);
        c = (char) (toX + 1 + 64 + 32);
        String X2 = String.valueOf(c);
        if (whiteTurn == true) {
            if (isStrike == true)
                whiteMovesLabel.setText(
                        whiteMovesLabel.getText() + numberOnGUI++ + ". " + figure + X1 + Y1 + "x" + X2 + Y2 + newLine2);
            else
                whiteMovesLabel
                        .setText(whiteMovesLabel.getText() + numberOnGUI++ + ". " + figure + X1 + Y1 + X2 + Y2
                                + newLine2);
        }
        if (whiteTurn == false) {
            if (isStrike == true)
                blackMovesLabel.setText(blackMovesLabel.getText() + figure + X1 + Y1 + "x" + X2 + Y2 + newLine2);
            else
                blackMovesLabel.setText(blackMovesLabel.getText() + figure + X1 + Y1 + X2 + Y2 + newLine2);
        }
    }

    // castle
    public void reprintScroll(boolean whiteTurn, boolean castle, boolean isLong) {
        if (whiteTurn == true) {
            if (isLong == true) {
                whiteMovesLabel.setText(whiteMovesLabel.getText() + numberOnGUI++ + ". " + "O-O-O" + newLine2);
            } else {
                whiteMovesLabel.setText(whiteMovesLabel.getText() + numberOnGUI++ + ". " + "O-O" + newLine2);
            }
        }
        if (whiteTurn == false) {
            if (isLong == true) {
                blackMovesLabel.setText(blackMovesLabel.getText() + "O-O-O" + newLine2);
            } else {
                blackMovesLabel.setText(blackMovesLabel.getText() + "O-O" + newLine2);
            }
        }
    }

    public void clearScroll() {
        numberOnGUI = 1;
        whiteMovesLabel.setText(startText);
        blackMovesLabel.setText(startText);
    }

    public void openNewGameWindow() {

        newGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newGameFrame.setIconImage(piecesIcons[11].getImage());
        newGameFrame.getContentPane().setBackground(defaultSettings.mainColor2);
        newGameFrame.setSize(600, 300);
        newGameFrame.setResizable(false);
        newGameFrame.setLayout(new BorderLayout());
        newGameFrame.setVisible(true);
        newGameFrame.setLocation(300, 315);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel();
        label.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        label.setBorder(border1);

        JLabel textLabel = new JLabel();
        textLabel.setBackground(defaultSettings.mainColor2);
        textLabel.setForeground(defaultSettings.mainColor1);
        textLabel.setFont(defaultSettings.font1);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Nowa gra?");
        textLabel.setOpaque(true);
        textLabel.setBorder(border1);

        players.setFocusable(false);
        players.setHorizontalAlignment(JLabel.CENTER);
        players.setPreferredSize(new Dimension(200, 100));
        players.setForeground(defaultSettings.mainColor1);
        players.setBackground(defaultSettings.mainColor2);
        players.setFont(defaultSettings.font7);
        players.setOpaque(true);
        players.setBorder(border1);
        players.setText("Z graczem");
        // players.addActionListener((ActionListener) this);

        bot.setFocusable(false);
        bot.setHorizontalAlignment(JLabel.CENTER);
        bot.setPreferredSize(new Dimension(200, 100));
        bot.setForeground(defaultSettings.mainColor1);
        bot.setBackground(defaultSettings.mainColor2);
        bot.setFont(defaultSettings.font7);
        bot.setOpaque(true);
        bot.setBorder(border1);
        bot.setText("Z botem");

        newGameFrame.add(textLabel, BorderLayout.NORTH);
        newGameFrame.add(label);
        label.add(players);
        label.add(bot);
    }

    public void openWinGameWindowWhite() {
        JLabel label = new JLabel();
        JLabel textLabel = new JLabel();

        textLabel.setText("White won by checkmate!");

        whiteWinGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        whiteWinGameFrame.setIconImage(piecesIcons[11].getImage());
        whiteWinGameFrame.getContentPane().setBackground(defaultSettings.mainColor2);
        whiteWinGameFrame.setSize(800, 300);
        whiteWinGameFrame.setResizable(false);
        whiteWinGameFrame.setLayout(new BorderLayout());
        whiteWinGameFrame.setVisible(true);
        whiteWinGameFrame.setLocation(300, 315);
        whiteWinGameFrame.setTitle("Mate");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        label.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        label.setBorder(border1);

        textLabel.setBackground(defaultSettings.mainColor2);
        textLabel.setForeground(defaultSettings.mainColor1);
        textLabel.setFont(defaultSettings.font1);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        label.add(textLabel);
        whiteWinGameFrame.add(label);
    }

    public void openWinGameWindowBlack() {
        JLabel label = new JLabel();
        JLabel textLabel = new JLabel();

        textLabel.setText("Black won by checkmate!");

        blackWinGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        blackWinGameFrame.setIconImage(piecesIcons[11].getImage());
        blackWinGameFrame.getContentPane().setBackground(defaultSettings.mainColor2);
        blackWinGameFrame.setSize(800, 300);
        blackWinGameFrame.setResizable(false);
        blackWinGameFrame.setLayout(new BorderLayout());
        blackWinGameFrame.setVisible(true);
        blackWinGameFrame.setLocation(300, 315);
        blackWinGameFrame.setTitle("Mate");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        label.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        label.setBorder(border1);

        textLabel.setBackground(defaultSettings.mainColor2);
        textLabel.setForeground(defaultSettings.mainColor1);
        textLabel.setFont(defaultSettings.font1);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        label.add(textLabel);
        blackWinGameFrame.add(label);
    }

    public void openDrawGameWindow() {
        JLabel label = new JLabel();
        JLabel textLabel = new JLabel();

        textLabel.setText("It's a stalemate!");

        stalemateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stalemateFrame.setIconImage(piecesIcons[11].getImage());
        stalemateFrame.getContentPane().setBackground(defaultSettings.mainColor2);
        stalemateFrame.setSize(800, 300);
        stalemateFrame.setResizable(false);
        stalemateFrame.setLayout(new BorderLayout());
        stalemateFrame.setVisible(true);
        stalemateFrame.setLocation(300, 315);
        stalemateFrame.setTitle("Stalemate");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        label.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        label.setBorder(border1);

        textLabel.setBackground(defaultSettings.mainColor2);
        textLabel.setForeground(defaultSettings.mainColor1);
        textLabel.setFont(defaultSettings.font1);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        label.add(textLabel);
        stalemateFrame.add(label);
    }

    public void openLoseTimeBlackWindow() {
        JLabel label = new JLabel();
        JLabel textLabel = new JLabel();

        textLabel.setText(startText + "Black run out of time!" + newLine2 + newLine2  + "<center>White wins!</center>" + endText);

        blackLoseTimeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        blackLoseTimeFrame.setIconImage(piecesIcons[11].getImage());
        blackLoseTimeFrame.getContentPane().setBackground(defaultSettings.mainColor2);
        blackLoseTimeFrame.setSize(800, 300);
        blackLoseTimeFrame.setResizable(false);
        blackLoseTimeFrame.setLayout(new BorderLayout());
        blackLoseTimeFrame.setVisible(true);
        blackLoseTimeFrame.setLocation(300, 315);
        blackLoseTimeFrame.setTitle("No time");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        label.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));
        label.setBorder(border1);

        textLabel.setBackground(defaultSettings.mainColor2);
        textLabel.setForeground(defaultSettings.mainColor1);
        textLabel.setFont(defaultSettings.font1);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        label.add(textLabel);
        blackLoseTimeFrame.add(label);
    }

    public void openLoseTimeWhiteWindow() {
        JLabel label = new JLabel();
        JLabel textLabel = new JLabel();

        textLabel.setText(startText + "White run out of time!" + newLine2 + newLine2 + "<center>Black wins!</center>");

        whiteLoseTimeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        whiteLoseTimeFrame.setIconImage(piecesIcons[11].getImage());
        whiteLoseTimeFrame.getContentPane().setBackground(defaultSettings.mainColor2);
        whiteLoseTimeFrame.setSize(800, 300);
        whiteLoseTimeFrame.setResizable(false);
        whiteLoseTimeFrame.setLayout(new BorderLayout());
        whiteLoseTimeFrame.setVisible(true);
        whiteLoseTimeFrame.setLocation(300, 315);
        whiteLoseTimeFrame.setTitle("No time");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        label.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));
        label.setBorder(border1);

        textLabel.setBackground(defaultSettings.mainColor2);
        textLabel.setForeground(defaultSettings.mainColor1);
        textLabel.setFont(defaultSettings.font1);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        label.add(textLabel);
        whiteLoseTimeFrame.add(label);
    }
}
