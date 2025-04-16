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

public class NeutralFace extends Application

    @Override 
    public void start (Stage stage) // Application class requires you to code the start method
    {                               // when start is called it is automatically sent an object of the Stage class -> which will be the main container for our graphic
        // first we create the main circle for the face

        Circle face = new Circle(100, 100, 75);
        face.setFill(Color.BLUE);
        face.setStroke(Color.WHITE);

        Circle rightEye = new Circle(90, 100, 6);
        rightEye.setFill(Color.BLACK);
        rightEye.setStroke(Color.RED);

        Circle leftEye = new Circle(110, 100, 6);
        rightEye.setFill(Color.BLACK);
        rightEye.setStroke(Color.RED);
        
        Arc mouth = new Arc(120, 150, 40, 32.5, 0, -180);
        mouth.setFill(Color.BLACK);
        mouth.setStroke(Color.RED);
        mouth.setType(ArcType.OPEN);

        

    }
}