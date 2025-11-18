package engine4j.editor3d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.plaf.metal.MetalButtonUI;

class E4JColors {
    public static final Color background = new Color(0x151515);
    public static final Color background2 = new Color(0x1A1A1A);
    public static final Color text = Color.lightGray;
}

class E4JTreeNode extends JButton {
    protected int indentLevel;
    protected JPanel leftColumn;
    protected JPanel centerColumn;
    protected JPanel rightColumn;
    protected JToggleButton visbilityButton;
    protected JToggleButton expandButton;
    protected JLabel typeLabel;

    public E4JTreeNode() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(25, 25));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setBackground(E4JColors.background);

        setUI(new MetalButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.white;
            }

            @Override
            protected Color getFocusColor() {
                return Color.blue;
            }
        });

        // Left column
        leftColumn = new JPanel();
        leftColumn.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(leftColumn, BorderLayout.WEST);

        // Center column
        centerColumn = new JPanel();
        centerColumn.setBackground(E4JColors.background);
        centerColumn.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        //add(centerColumn, BorderLayout.CENTER);

        // Right column
        rightColumn = new JPanel();
        rightColumn.setBackground(E4JColors.background);
        rightColumn.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(rightColumn, BorderLayout.EAST);

        // Visbility button
        visbilityButton = new JToggleButton("👁");
        visbilityButton.setForeground(E4JColors.text);
        visbilityButton.setPreferredSize(new Dimension(30, 25));
        visbilityButton.setBackground(E4JColors.background);
        visbilityButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftColumn.add(visbilityButton);

        visbilityButton.setUI(new MetalButtonUI() {
            @Override
            protected Color getSelectColor() {
                return E4JColors.background;
            }
        });

        visbilityButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent)
            {
                int state = itemEvent.getStateChange();

                if (state == ItemEvent.SELECTED) {
                    visbilityButton.setText("⌣  ");
                }
                else {
                    visbilityButton.setText("👁  ");
                }

                validate();
            }
        });

        // Type label
        typeLabel = new JLabel("Type");
        typeLabel.setPreferredSize(new Dimension(90, 25));
        typeLabel.setForeground(E4JColors.text);
        rightColumn.add(typeLabel);
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Editor");

        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(new Dimension(1200, 700));
        
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.setBackground(E4JColors.background2);
        panel.setPreferredSize(new Dimension(350, 450));
        panel.add(new E4JTreeNode(), BorderLayout.NORTH);
        
        mainFrame.add(panel, BorderLayout.WEST);
        
        // Show window
        mainFrame.setVisible(true);
    }
}
