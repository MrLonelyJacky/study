/*
package algorithm.dp;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.map4j.coordinates.WGS84Coordinate;
import org.map4j.layers.tile.TileLayer;
import org.map4j.layers.tile.TileLayerFactory;

*/
/**
 * @description:
 * @author: jacky
 * @create: 2023-04-04 16:55
 **//*

public class TileMapExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a new tile layer using the OpenStreetMap provider
        TileLayer tileLayer = TileLayerFactory.createOpenStreetMapLayer();

        // Create a map view pane to hold the tiles
        Pane mapViewPane = new Pane();

        // Add the tiles to the map view pane
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                // Get the tile image for the given x,y,z coordinate
                Image tileImage = tileLayer.getTileImage(x, y, 2);

                // Create an image view to display the tile image
                ImageView imageView = new ImageView(tileImage);

                // Set the position of the image view based on the tile coordinates
                imageView.setX(x * 256);
                imageView.setY(y * 256);

                // Add the image view to the map view pane
                mapViewPane.getChildren().add(imageView);
            }
        }

        // Create a scene with the map view pane
        Scene scene = new Scene(mapViewPane, 1024, 1024);

        // Show the stage with the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/
