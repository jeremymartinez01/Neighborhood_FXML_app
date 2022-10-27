module com.mycompany.proyectopoo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail ;
    opens com.mycompany.proyectopoo2 to javafx.fxml;
    exports com.mycompany.proyectopoo2;
    exports modelo;
}
