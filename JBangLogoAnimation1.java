//DEPS org.openjfx:javafx-controls:11.0.2:${os.detected.jfxname}
//DEPS org.openjfx:javafx-graphics:11.0.2:${os.detected.jfxname}

// TODO: how to make this work? FILES jbang-logo-green.png

import java.io.FileInputStream; 

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
public class JBangLogoAnimation1 extends Application {

	ImageView imageView;
	Image imageA, imageB, imageC;
	int idImg = 1;
	
   @Override 
   public void start(Stage stage) {         
      //Creating an image 
      imageA = new Image("https://raw.githubusercontent.com/tivrfoa/javafx-basic-animation/main/images/jbang-logo-green.png");  
      imageB = new Image("https://raw.githubusercontent.com/tivrfoa/javafx-basic-animation/main/images/jbang-logo-red.png");  
      imageC = new Image("https://raw.githubusercontent.com/tivrfoa/javafx-basic-animation/main/images/jbang-logo.png");  
      
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
      Scene scene = new Scene(root, 800, 800);  
      
      //Setting title to the Stage 
      stage.setTitle("JBang!!!");  
      
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show(); 
   }
   
   private void updateImage() {
	   switch(++idImg) {
		   case 1: imageView.setImage(imageA); break;
		   case 2: imageView.setImage(imageB); break;
		   case 3: imageView.setImage(imageC); break;
	   }
	   if (idImg > 3) idImg = 1;
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
      scaleTransition.setCycleCount(3); 
      
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
	  });
      
      //Setting the duration for the transition 
      rotateTransition.setDuration(Duration.millis(1500)); 
      
      //Setting the node for the transition 
      rotateTransition.setNode(imageView);       
      
      //Setting the angle of the rotation 
      rotateTransition.setByAngle(360); 
      
      //Setting the cycle count for the transition 
      rotateTransition.setCycleCount(3); 
      
      //Setting auto reverse value to false 
      rotateTransition.setAutoReverse(false); 
      
      //Playing the animation 
      rotateTransition.play(); 
   }
   
   public static void main(String args[]) { 
      launch(args); 
   } 
}
