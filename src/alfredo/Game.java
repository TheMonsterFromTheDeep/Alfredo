package alfredo;

import alfredo.input.Keys;
import alfredo.input.Mouse;
import alfredo.scene.Scene;
import alfredo.timing.Interval;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Interval {
    private static final int DEFAULT_DELAY = 60;
    
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;
    
    private static final int MIN_WIDTH = 128;
    private static final int MIN_HEIGHT = 64;
    
    private final class GamePanel extends JPanel {
        BufferedImage buffer;
        Graphics bufGraphics;
        
        int bufWidth, bufHeight; //The size that the buffer is drawn at
        int bufX, bufY; //The position that the buffer is drawn at
        
        public GamePanel(int width, int height) {
            this.setSize(width, height);
            this.setPreferredSize(new java.awt.Dimension(width, height));
            
            this.setBackground(new java.awt.Color(0x0));
            
            MouseAdapter adapter = Mouse.init();
            addMouseListener(adapter);
            addMouseWheelListener(adapter);
            addMouseMotionListener(adapter);
            
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            bufGraphics = buffer.createGraphics();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scene.draw();
            g.drawImage(buffer, bufX, bufY, bufWidth, bufHeight, null);
            repaint();
        }
        
        private void doGraphics() {
            //Recalculates size and offset of graphical buffer
            if((float)panel.getWidth() / buffer.getWidth() > (float)panel.getHeight() / buffer.getHeight()) {
                bufHeight = panel.getHeight();
                bufWidth = (int)(bufHeight * ((float)buffer.getWidth() / buffer.getHeight()));
                bufX = (panel.getWidth() - bufWidth) / 2;
                bufY = 0;
            }
            else {
                bufWidth = panel.getWidth();
                bufHeight = (int)(bufWidth * ((float)buffer.getHeight() / buffer.getWidth()));
                bufY = (panel.getHeight() - bufHeight) / 2;
                bufX = 0;
            }
        }
    }
    
    private final class GameFrame extends JFrame {
        public GameFrame(String title) {
            super(title);
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            addKeyListener(Keys.init());
            
            this.setMinimumSize(new java.awt.Dimension(MIN_WIDTH, MIN_HEIGHT));
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int width = GameFrame.this.getWidth();
                    int height = GameFrame.this.getHeight();
                    
                    if (width < MIN_WIDTH) { width = MIN_WIDTH; }
                    if (height < MIN_HEIGHT) { height = MIN_HEIGHT; }
                    
                    GameFrame.this.setSize(width, height);
                    
                    panel.doGraphics();
                }
            });
        }
    }
    
    GameFrame frame;
    GamePanel panel;
    
    Scene scene;
    
    public Game(String title, int width, int height) {
        super(DEFAULT_DELAY);
        
        frame = new GameFrame(title);
        panel = new GamePanel(width, height);
        
        frame.add(panel);
        frame.pack();
        
        scene = Scene.getEmptyScene();
    }
    
    public Game(String title) {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public Game(int width, int height) {
        this("Untitled Game", width, height);
    }
    
    public Game() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public void setup() { }
    
    public void run() {
        setup();
        frame.setVisible(true);
        this.start();
    }
    
    @Override
    public final void loop() {
        scene.loop();
    }
    
    public final void setScene(Scene scene) {
        this.scene = scene;
    }
}