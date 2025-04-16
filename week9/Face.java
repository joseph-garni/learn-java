import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


// imported application, scene and stage subpackages required for application from JavaFX package

// class header

// as all JavaFX programs run as application we have to extend the Application class

public class Face extends Application{

    @Override 
    public void start (Stage stage){ // Application class requires you to code the start method
                                   // when start is called it is automatically sent an object of the Stage class -> which will be the main container for our graphic
        // first we create the main circle for the face

        Circle face = new Circle(100, 100, 75);
        face.setFill(Color.BLUE);
        face.setStroke(Color.WHITE);

        Circle rightEye = new Circle(90, 100, 6);
        rightEye.setFill(Color.BLACK);
        rightEye.setStroke(Color.RED);

        Circle leftEye = new Circle(110, 100, 6);
        leftEye.setFill(Color.BLACK);
        leftEye.setStroke(Color.RED);
        
        // new arc instance created named mouth where we pass through the Arc method using the values in the method we're asking for
        Arc mouth = new Arc(120, 150, 40, 32.5, 0, -180); 
        mouth.setFill(Color.BLACK);
        mouth.setStroke(Color.RED);
        mouth.setType(ArcType.OPEN);

        // Creating instance of text class called 'caption' -> our caption for our application, with its position x, y, and the text itself (the inputs of the method)
        Text caption = new Text(80, 220, "Joseph's Chapter 10 Intial Application!");
        caption.setFill(Color.BLACK);
        caption.setFont(Font.font("Verdana", 15));

        // now we have created all our features, we want them to stay together as a group
        Group root = new Group(face, rightEye, leftEye, mouth, caption);

        // we are using the convention of the first node we add to our scene as root
        // here we are using the constructor that allows us to set the size and height of the initial scene with the Scene class
        // later min and max width / height can be set for the Scene method
        Scene scene = new Scene(root, 250, 275, Color.BLACK);

        // add the scene to the stage, then set the title
        stage.setScene(scene);
        stage.setTitle("Joseph's JavaFX Face Application");

        // show the stage
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
        
}