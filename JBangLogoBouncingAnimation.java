//DEPS org.openjfx:javafx-controls:11.0.2:${os.detected.jfxname}
//DEPS org.openjfx:javafx-graphics:11.0.2:${os.detected.jfxname}

// TODO: how to make this work? FILES jbang-logo-green.png
//FILES images/jbang-logo-green.png
//FILES images/jbang-logo-multicolors.png
//FILES images/jbang-logo-red.png
//FILES images/jbang-logo.png

import java.io.FileInputStream;
import java.io.IOException;

import javafx.animation.AnimationTimer; 
import javafx.animation.RotateTransition; 
import javafx.animation.ScaleTransition;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;  
import javafx.util.Duration; 

/**
References:
https://www.tutorialspoint.com/javafx/javafx_images.htm
https://www.tutorialspoint.com/javafx/javafx_animations.htm
*/
public class JBangLogoBouncingAnimation extends Application {

	private Scene scene;
	private ImageView imageView;
	private Image imageA, imageB, imageC, imageD;
	private int idImg = 1;
	private double t = 0;
	private AnimationTimer timer;
	private boolean movingUP, movingLeft;
	
   @Override 
   public void start(Stage stage) throws IOException {
      //Creating an image 
      imageA = new Image(getClass().getResource("/images/jbang-logo-green.png").openStream());
      imageB = new Image(getClass().getResource("/images/jbang-logo-red.png").openStream());
      imageC = new Image(getClass().getResource("/images/jbang-logo.png").openStream());
      imageD = new Image(getClass().getResource("/images/jbang-logo-multicolors.png").openStream());
      
      //Setting the image view 
      imageView = new ImageView(imageA); 
      
      //Setting the position of the image 
      imageView.setX(300); 
      imageView.setY(300); 
      
      //setting the fit height and width of the image view 
      // imageView.setFitHeight(455); 
      // imageView.setFitWidth(500); 
	  imageView.setFitHeight(200);
      imageView.setFitWidth(200);
      
      //Setting the preserve ratio of the image view 
      imageView.setPreserveRatio(true);
	  
	  grow();
      
      //Creating a Group object  
      Group root = new Group(imageView);  
      
      //Creating a scene object 
      scene = new Scene(root, 1200, 800);  
      
      //Setting title to the Stage 
      stage.setTitle("JBang!!!");  
      
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show();
   }
   
   private void updateImage() {
	   switch(idImg) {
		   case 1: imageView.setImage(imageA); break;
		   case 2: imageView.setImage(imageB); break;
		   case 3: imageView.setImage(imageC); break;
		   case 4: imageView.setImage(imageD); break;
	   }
	   if (++idImg > 4) idImg = 1;
   }
   
   private void grow() {
	   //Creating scale Transition 
      ScaleTransition scaleTransition = new ScaleTransition(); 
	  
	  scaleTransition.setOnFinished(e -> {
		  updateImage();
		  rotate();
	  });
      
      //Setting the duration for the transition 
      scaleTransition.setDuration(Duration.millis(1500)); 
      
      //Setting the node for the transition 
      scaleTransition.setNode(imageView); 
      
      //Setting the dimensions for scaling 
      scaleTransition.setByY(1.5); 
      scaleTransition.setByX(1.5); 
      
      //Setting the cycle count for the translation 
      // scaleTransition.setCycleCount(3); 
      scaleTransition.setCycleCount(1); 
      
      //Setting auto reverse value to true 
      scaleTransition.setAutoReverse(false); 
      
      //Playing the animation 
      scaleTransition.play(); 
   }
   
   private void rotate() {
	   
	   //Creating a rotate transition    
      RotateTransition rotateTransition = new RotateTransition();
	  
	  rotateTransition.setOnFinished(e -> {
		  updateImage();
		  timer = new AnimationTimer() {
			public void handle(long now) {
				t += 0.02;
				
				if (t > 0.04) { // the lower the fastest
					moveImage();
					t = 0;
				}
			}
		};
		
		timer.start();
	  });
      
      //Setting the duration for the transition 
      rotateTransition.setDuration(Duration.millis(1500)); 
      
      //Setting the node for the transition 
      rotateTransition.setNode(imageView);       
      
      //Setting the angle of the rotation 
      rotateTransition.setByAngle(360); 
      
      //Setting the cycle count for the transition 
      // rotateTransition.setCycleCount(3);
      rotateTransition.setCycleCount(1);
      
      //Setting auto reverse value to false 
      rotateTransition.setAutoReverse(false); 
      
      //Playing the animation 
      rotateTransition.play(); 
   }
   
   private void moveImage() {
		if (movingUP) {
			double p = imageView.getY() - 5;
			if (p - 20 < 0) { movingUP = false; updateImage(); }
			else imageView.setY(p);
		} else {
			double p = imageView.getY() + 5;
			if (p + 80 > scene.getHeight()) { movingUP = true; updateImage(); }
			else imageView.setY(p);
		}
		if (movingLeft) {
			double p = imageView.getX() - 5;
			if (p - 120 < 0) { movingLeft = false; updateImage(); }
			else imageView.setX(p);
		} else {
			double p = imageView.getX() + 5;
			if (p + 330 > scene.getWidth()) { movingLeft = true; updateImage(); }
			else imageView.setX(p);
		}
	   // System.out.println(scene.getWidth() + " - " + imageView.getX());
	   // System.out.println(scene.getHeight() + " - " + imageView.getY());
   }
   
   public static void main(String args[]) { 
      launch(args); 
   } 
}
