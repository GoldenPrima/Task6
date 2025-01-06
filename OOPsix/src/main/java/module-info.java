module ru.vsu.cs.graphics.tasksix {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.cs.graphics to javafx.fxml;
    exports ru.vsu.cs.graphics;
    exports ru.vsu.cs.logic;
    opens ru.vsu.cs.logic to javafx.fxml;
}