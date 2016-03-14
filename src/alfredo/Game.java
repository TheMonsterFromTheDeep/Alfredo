package alfredo;

import alfredo.input.Keys;
import alfredo.input.Mouse;
import alfredo.scene.Scene;
import alfredo.timing.Interval;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Interval {
    private static final int DEFAULT_DELAY = 60;
    
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;
    
    private class GamePanel extends JPanel {       
        public GamePanel(int width, int height) {
            this.setSize(width, height);
            this.setPreferredSize(new java.awt.Dimension(width, height));
            
            this.setBackground(new java.awt.Color(0x0));
            
            MouseAdapter adapter = Mouse.init();
            addMouseListener(adapter);
            addMouseWheelListener(adapter);
            addMouseMotionListener(adapter);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scene.draw();
            repaint();
        }
    }
    
    private class GameFrame extends JFrame {
        public GameFrame(String title) {
            super(title);
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            addKeyListener(Keys.init());
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