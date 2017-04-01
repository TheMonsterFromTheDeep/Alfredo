package alfredo.platform;

import alfredo.Canvas;
import alfredo.Game;
import alfredo.Scene;
import alfredo.geom.Vector;
import alfredo.gfx.Graphic;
import alfredo.inpt.Key;
import alfredo.inpt.Mouse;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Represents a device that can be accessed through AWT.
 * @author TheMonsterOfTheDeep
 */
public class Computer implements Game.Platform {

    private JFrame frame;
    private JPanel panel;
    
    private Canvas canvas;
    
    @Override
    public void create(Canvas canvas) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        this.canvas = canvas;
        //canvas = new Canvas(width, height);
        
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                
                Scene.getCurrent().render(canvas);
                
                g.drawImage(canvas.getRender(), 0, 0, null);
                
                repaint();
            }
        };
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                for(int i = 0; i < Key.keys.length; ++i) {
                    if(i == e.getKeyCode()) {
                        Key.keys[i].press();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for(int i = 0; i < Key.keys.length; ++i) {
                    if(i == e.getKeyCode()) {
                        Key.keys[i].release();
                    }
                }
            }
            
            @Override public void keyTyped(KeyEvent e) { }
        });
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                int button = e.getButton();
                (button == MouseEvent.BUTTON1 ? Mouse.LMB :      //I think this is probably cleaner than a switch
                 button == MouseEvent.BUTTON2 ? Mouse.MMB : Mouse.RMB).press();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int button = e.getButton();
                (button == MouseEvent.BUTTON1 ? Mouse.LMB :
                 button == MouseEvent.BUTTON2 ? Mouse.MMB : Mouse.RMB).release();
            }

            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Mouse.pointer.set(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Mouse.pointer.set(e.getX(), e.getY());
            }
        });
        
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                canvas.resize(panel.getWidth(), panel.getHeight());
            }
        });
        
        panel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
        frame.add(panel);
        frame.pack();
        
        
    }
    
    @Override
    public void title(String title) {
        frame.setTitle(title);
    }

    @Override
    public void size(float width, float height) {
        panel.setPreferredSize(new Dimension((int)width, (int)height));
        frame.pack();
    }

    @Override
    public void setIcon(Graphic icon) {
        BufferedImage b = icon.getRender();
        if(b != null) {
            frame.setIconImage(b);
        }
    }
    
    @Override
    public void run() {
        frame.setVisible(true);
    }

    @Override
    public void saveScreenshot(String path) {
        try {
            ImageIO.write(canvas.getRender(), "PNG", new File(path + Game.getTick() + ".png"));
        } catch (IOException ex) {
            System.err.println("Could not save screenshot: " + ex.getLocalizedMessage());
        }
    }
}
