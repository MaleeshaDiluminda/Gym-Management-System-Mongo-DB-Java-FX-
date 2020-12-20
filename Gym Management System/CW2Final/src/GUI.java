import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;


public class GUI extends Application {

    static MongoClient gym_customer = new MongoClient("localhost", 27017);
    static MongoDatabase client_database = gym_customer.getDatabase("GYM_MANAGEMENT");
    static MongoCollection database = client_database.getCollection("Client");

    static TableView list_table = new TableView();
    static TableColumn Client_first_name_bar = new TableColumn();
    static TableColumn Client_last_name_bar = new TableColumn();
    static TableColumn Client_contact_bar = new TableColumn();
    static TableColumn Client_index_bar = new TableColumn();
    static TableColumn Commencing_date_bar = new TableColumn();
    static TableColumn School_or_instutute_bar = new TableColumn();
    static TableColumn member_age_bar = new TableColumn();
    static ComboBox select_option = new ComboBox();
    static Button find_button = new Button("Find");
    static Button show_list_button = new Button("Show the List");
    static TextField search_field = new TextField();
    static RadioButton choose_gym_index = new RadioButton(" Gym Index");
    static RadioButton choose_first_name = new RadioButton(" First Name ");
    static ToggleGroup toggleGroup = new ToggleGroup();
    static Label label_field = new Label();

    @Override
    public void start(Stage gym_stage) throws Exception{

        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane,1050,900);
        gym_stage.setTitle(" GYM MANAGEMENT SYSTEM");
        gym_stage.setScene(scene);

        gym_stage.show();


        search_field.setLayoutX(600);
        search_field.setLayoutY(35);

        label_field.setLayoutX(670);
        label_field.setLayoutY(35);

        show_list_button.setLayoutX(450);
        show_list_button.setLayoutY(35);
        show_list_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list_table.getItems().clear();
                list_table.getItems().clear();
                DiasplayAll();

            }
        });

        select_option.setLayoutX(300);
        select_option.setLayoutY(35);
        select_option.getButtonCell();

        find_button.setLayoutX(150);
        find_button.setLayoutY(35);

        list_table.setLayoutX(100);
        list_table.setLayoutY(150);
        list_table.setPrefSize(800,600);

        choose_gym_index.setToggleGroup(toggleGroup);
        choose_first_name.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton radioButton = (RadioButton)toggleGroup.getSelectedToggle();

                if (radioButton==choose_gym_index){
                    String text = radioButton.getText();

                    label_field.setText(text);
                }
            }
        });

        pane.getChildren().addAll(search_field,select_option,show_list_button,find_button,list_table);

        FindIterable<Document> findIterable = database.find();

    }

    public void DiasplayAll() {
        Client_index_bar.setCellValueFactory(new PropertyValueFactory<>("Gym Index"));
        Client_index_bar.setText("Gym Index");

        Client_first_name_bar.setCellValueFactory(new PropertyValueFactory<>("First Name"));
        Client_first_name_bar.setText("First Name");

        Client_last_name_bar.setCellValueFactory(new PropertyValueFactory<>("Last Name"));
        Client_last_name_bar.setText("Last Name");

        Commencing_date_bar.setCellValueFactory(new PropertyValueFactory<>("Commencing Date"));
        Commencing_date_bar.setText("Commencing date");

        Client_contact_bar.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        Client_contact_bar.setText("Contact");

        School_or_instutute_bar.setCellValueFactory(new PropertyValueFactory<>("School or Institute"));
        School_or_instutute_bar.setText("School or Institute");

        member_age_bar.setCellValueFactory(new PropertyValueFactory<>("Age"));
        member_age_bar.setText("Age");

        list_table.getColumns().addAll(Client_first_name_bar,Client_last_name_bar,Client_index_bar,Client_contact_bar,Commencing_date_bar,School_or_instutute_bar,member_age_bar);
        FindIterable<Document> findIterable = database.find();

        Document document1 = new Document();
        document1.put(" Gym Index ",Integer.valueOf(search_field.getText()));

        ObservableList observableList = FXCollections.observableArrayList();

        for (Document document:findIterable){
            DisplayALL result = new DisplayALL();
            result.setClient_index(document.getString(" Gym Index "));
            result.setClient_last_name(document.getString("Last Name"));
            result.setClient_first_name(document.getString("First Name"));
            result.setClient_contact(document.getString(" Contact "));
            result.setCommencing_date(document.getString("Commencing Date"));
            result.setMember_age(document.getString("Age"));
            result.setSchool_or_institute("School or institute");
            observableList.add(result);

        }

        list_table.setItems(observableList);

    }


}

