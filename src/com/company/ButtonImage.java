package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ButtonImage {
    private static Game game;
    public static void main(String[] args) {
        game = new Game();
        new ButtonImage();
    }

    public ButtonImage() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new MenuPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }

    public static class MenuPane extends JPanel {

        public static final Rectangle NEW_GAME_BOUNDS = new Rectangle(221, 157, 262, 85);
        public static final Rectangle EXIT_GAME_BOUNDS = new Rectangle(221, 396, 262, 85);
        private BufferedImage img;
        private Rectangle selectedBounds;

        public MenuPane() {
            try {
                img = ImageIO.read(getClass().getResource("./background.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (getNewGameBounds().contains(e.getPoint())) {
                        System.out.println("in new");
                        selectedBounds = getNewGameBounds();
                    } else if (getExitGameBounds().contains(e.getPoint())) {
                        System.out.println("in exit");
                        selectedBounds = getExitGameBounds();
                    } else {
                        selectedBounds = null;
                    }
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (getNewGameBounds().contains(e.getPoint())) {
                        System.out.println("New Game");
                    } else if (getExitGameBounds().contains(e.getPoint())) {
                        System.out.println("Exit Game");
                    }
                }
            };
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
        }

        protected Point getImageOffset() {

            Point p = new Point();
            if (img != null) {
                p.x = (getWidth() - img.getWidth()) / 2;
                p.y = (getHeight() - img.getHeight()) / 2;
            }

            return p;

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (img != null) {
                Graphics2D g2d = (Graphics2D) g.create();

                Point p = getImageOffset();

                g2d.drawImage(img, p.x, p.y, this);

                drawText(g2d, "New Game", getNewGameBounds());
                drawText(g2d, "Exit Game", getExitGameBounds());

                g2d.dispose();
            }
        }

        protected void drawText(Graphics2D g2d, String text, Rectangle bounds) {

            FontMetrics fm = g2d.getFontMetrics();

            g2d.setColor(Color.GRAY);
            if (selectedBounds != null) {
                if (bounds.contains(selectedBounds)) {
                    RadialGradientPaint rpg = new RadialGradientPaint(
                            new Point(bounds.x + (bounds.width / 2), bounds.y + (bounds.height / 2)),
                            Math.min(bounds.width, bounds.height),
                            new float[]{0f, 1f},
                            new Color[]{new Color(252, 180, 42), new Color(97, 205, 181)}
                    );
                    g2d.setPaint(rpg);
                    RoundRectangle2D fill = new RoundRectangle2D.Float(bounds.x, bounds.y, bounds.width, bounds.height, 22, 22);
                    g2d.fill(fill);
                    g2d.setColor(Color.WHITE);
                }
            }

            g2d.drawString(
                    text,
                    bounds.x + ((bounds.width - fm.stringWidth(text)) / 2),
                    bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent());

        }

        protected Rectangle getNewGameBounds() {
            return getButtonBounds(NEW_GAME_BOUNDS);
        }

        protected Rectangle getExitGameBounds() {
            return getButtonBounds(EXIT_GAME_BOUNDS);
        }

        protected Rectangle getButtonBounds(Rectangle masterBounds) {
            Rectangle bounds = new Rectangle(masterBounds);
            Point p = getImageOffset();
            bounds.translate(p.x, p.y);
            return bounds;
        }
    }
}