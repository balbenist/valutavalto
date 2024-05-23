import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ValutaValtoApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Betölti az FXML fájlt és létrehozza a felhasználói felületet
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/valutavalto.fxml"));
        primaryStage.setTitle("Valutaváltó"); // Beállítja az ablak címét
        primaryStage.setScene(new Scene(root)); // Hozzáad egy jelenetet az ablakhoz
        primaryStage.show(); // Megjeleníti az ablakot
    }
}
