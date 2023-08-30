import java.awt.*;
import java.awt.event.*;

public class Main {

  // Set up all the game variables to make the window work
  //lowering the fps because its actually horrendous to play with such a fast update time
  final int FPS = 45;
  final int WIDTH = 1200;
  final int HEIGHT = 800;
  final String TITLE = "My Drawing";
  Drawing drawing;

  // ▼▼▼▼ Your own class variables go under here ▼▼▼▼
  Cube player;
  MainScreen mainscreen;
  LevelOne level1;
  LevelTwo level2;
  EndScreen endscreen;

  int mouseX;
  int mouseY;

  
  // ▲▲▲▲ You own class variables go above here ▲▲▲▲


  
  // ☠ DO NOT TOUCH THE CODE IN THE MAIN METHOD ☠
  // this is what helps create the window
  // I have moved a bunch of code into another file to try and hide it from you
  public Main() {
    // initialize anything you need to before we see it on the screen
    start();
    // create the screen and show it
    drawing = new Drawing(TITLE, WIDTH, HEIGHT, FPS, this);
    // make sure key and mouse events work
    // this is like an actionListener on buttons except it's the keyboard and mouse
    drawing.addKeyListener(new Keyboard());
    Mouse m = new Mouse();
    drawing.addMouseListener(m);
    drawing.addMouseMotionListener(m);
    drawing.addMouseWheelListener(m);
    // start the game loop
    drawing.loop();
  }

  // use this method to set values to anything BEFORE the game starts
  // this only runs at the very beginning
  public void start() {
    //startin coords of cube
    this.player = new Cube(-100, 710, "ImageIcon.png");
    this.level1 = new LevelOne(1, "ImageBackground1.jpg");
    this.level2 = new LevelTwo(1, "ImageBackground2.jpg");
    this.mainscreen = new MainScreen(WIDTH, HEIGHT, 1, 1);
    this.endscreen = new EndScreen(WIDTH, HEIGHT, 1, 0);

    //testing levels variables
    //this.level1.setExitedLevel(true);
    //this.player.setCoords(-50, 110);
  }

  // this is where all the drawing happens
  // put all of your drawing code in this method
  public void paintComponent(Graphics2D g) {
    //only draw one level at a time obviously

    if(this.player.getCurrentLevel() == 0) {
      //draw the mainscreen
      this.mainscreen.draw(g);
    }
    //if level 1
    else if(this.player.getCurrentLevel() == 1) {
      //draw the stuff
      this.level1.draw(g, this.player);
      this.player.draw(g);
    }
    //if level 2
    else if(this.player.getCurrentLevel() == 2) {
      //draw second level stuff
      this.level2.draw(g, player);
      this.player.draw(g);
    }
    else if(this.player.getCurrentLevel() == 3) {
      //draw endscreen
      this.endscreen.draw(g);
    }
    
  }

  // this is the main game loop
  // this is where all of the program logic goes
  // this method automatically repeats over and over again
  // it tries to update as fast as the frames per second you set above (deault is
  // 60 updates each second)
  public void loop() {
    //first level updates
    if(this.player.getCurrentLevel() == 1) {
      //update player and collisions
      this.player.update(this.level1.getGravity());
      this.level1.handleCollisions(player);
    }
    else if(this.player.getCurrentLevel() == 2) {
        //update player and collisions in level 2
        this.player.update(this.level2.getGravity());
        this.level2.handleCollisions(player);
    }
  }
  
    

  // Used to implement any of the Mouse Actions
  private class Mouse extends MouseAdapter {

    // if a mouse button has been pressed down
    @Override
    public void mousePressed(MouseEvent e) {
      //handle in screens
      mainscreen.buttonInteractions(mouseX, mouseY, player);
      
    }

    // if a mouse button has been released
    @Override
    public void mouseReleased(MouseEvent e) {

      
    }

    // if the scroll wheel has been moved
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

      
    }

    // if the mouse has moved positions
    @Override
    public void mouseMoved(MouseEvent e) {
      mouseX = e.getX();
      mouseY = e.getY();
    }
  }

  // Used to implements any of the Keyboard Actions
  private class Keyboard extends KeyAdapter {

    // if a key has been pressed down
    @Override
    public void keyPressed(KeyEvent e) {
      // determine which key was pressed
      int key = e.getKeyCode();

      //determine if space is pressed
      if(key == KeyEvent.VK_UP) {
        player.jump();
      }

      
    }

    // if a key has been released
    @Override
    public void keyReleased(KeyEvent e) {
      // determine which key was pressed
      int key = e.getKeyCode();

      
    }
  }


  // the main method that launches the game when you hit run
  public static void main(String[] args) {
    Main game = new Main();
  }

}
