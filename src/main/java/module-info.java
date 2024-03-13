module com.example.matlali {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.matlali to javafx.fxml;
    exports com.example.matlali;
}