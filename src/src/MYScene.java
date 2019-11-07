import javafx.application.Application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MYScene extends Application {
    TextField nameText;
    TextField SurnameText;
    TextField TCtext;
    TextField dateText;
    boolean isUpdate;
    Label tik;
    Label tik1;
    Label tik2;
    Label tik3;
    List<File> files;

    Button btn;
    Button btn1;
    RadioButton updateButton;
    RadioButton fixButton;
    String date;
    String statu;
    int child;
    File file=null;
    Button fileButton;
    List<Label> tikNames=new LinkedList<>();
    boolean chBoll=false;
    boolean stBoll=false;
    Button dbButton;
    boolean isDbFine=false;
    TextField dbText;


    @Override
    public void start(Stage primaryStage) {
        try {


            //FXMLLoader loader = new FXMLLoader(getClass().getResource("firstScene.fxml"));
              Parent root = FXMLLoader.load(getClass().getResource("firstScene.fxml"));
         //   AnchorPane root = (AnchorPane) loader.load();
            SplitMenuButton ma=(SplitMenuButton) root.lookup("#status");
            SplitMenuButton mb=(SplitMenuButton) root.lookup("#child");

            nameText=(TextField)root.lookup("#name") ;
            SurnameText=(TextField)root.lookup("#surname") ;

            TCtext =(TextField)root.lookup("#tc") ;
            dateText=(TextField)root.lookup("#DateLabel") ;
            dbText=(TextField)root.lookup("#DateLabel1") ;
            fileButton=(Button) root.lookup("#yükle") ;
            dbButton=(Button) root.lookup("#yükle1") ;

            btn=(Button) root.lookup("#add") ;
            btn1=(Button) root.lookup("#add1") ;
            updateButton=(RadioButton) root.lookup("#Update") ;
            fixButton=(RadioButton) root.lookup("#fix") ;
            tik=(Label) root.lookup("#tik") ;
            tik1=(Label) root.lookup("#tik1") ;
            tik2=(Label) root.lookup("#tik2") ;
            tik3=(Label) root.lookup("#tik3") ;


            EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    System.out.println(((MenuItem)e.getSource()).getText());
                    statu=((MenuItem)e.getSource()).getText();
                    stBoll=true;

                }
            };
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    System.out.println(((MenuItem)e.getSource()).getText());
                    child=Integer.parseInt(((MenuItem)e.getSource()).getText());
                    chBoll=true;

                }



            };


            EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    isUpdate=!isUpdate;
                    System.out.println(isUpdate);
                    btn1.setDisable(isUpdate);
                    ma.setDisable(isUpdate);
                    mb.setDisable(isUpdate);
                    fixButton.setDisable(isUpdate);



                }





            };
         tikNames.add(tik);tikNames.add(tik1);tikNames.add(tik2);tikNames.add(tik3);


            fileButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pdf files (*.pdf)", "*.pdf","*.png");
                    fileChooser.getExtensionFilters().add(extFilter);
                    files =fileChooser.showOpenMultipleDialog(primaryStage);
                    tik.setVisible(false);
                    tik1.setVisible(false);
                    tik2.setVisible(false);
                    tik3.setVisible(false);
                    if (files!=null){
                    if (files.size()==4) {

                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText("Eksik Dosya seçtiniz");
                        alert.showAndWait();
                    }

                        for (int i=0;i<files.size();i++) {
                           tikNames.get(i).setVisible(true);
                        }
                    }
                }
            });
            dbButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dbText.setDisable(false);
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DB file (*.db)", "*.db");
                    fileChooser.getExtensionFilters().add(extFilter);
                    file =fileChooser.showOpenDialog(primaryStage);
                    if (file!=null){
                        System.out.println(file.getAbsolutePath());
                        dbText.setText(file.getName());
                        btn.setDisable(false);
                        dbText.setDisable(true);
                    }
                }
            });






            //Splitt menu barr

            ObservableList<MenuItem> Status=ma.getItems();//izin tipi menüsü
            for (int i = 0; i <Status.size() ; i++) {
                Status.get(i).setOnAction(event1);
            }
            updateButton.setOnAction(event2);
            ObservableList<MenuItem> Child=mb.getItems();//izin tipi menüsü
            for (int i = 0; i <Child.size() ; i++) {
                Child.get(i).setOnAction(event);
            }




            btn1.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   DatePicker datePicker=new DatePicker();
                   Button bt=new Button();

                   try {
                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aaa.fxml"));

                       Parent root1 = (Parent) fxmlLoader.load();
                       bt=(Button)root1.lookup("#add2");
                       datePicker=(DatePicker)root1.lookup("#dp");
                       Stage stage = new Stage();
                       DatePicker finalDatePicker = datePicker;
                       bt.setOnAction(new EventHandler<ActionEvent>() {
                          @Override
                          public void handle(ActionEvent event) {
                              dateText.setDisable(false);
                              System.out.println(finalDatePicker.getValue());
                              date=finalDatePicker.getValue().toString();
                              dateText.setText(date);
                              dateText.setDisable(true);
                          }
                      });


                       stage.setScene(new Scene(root1));
                       stage.show();
                   } catch(Exception e) {
                       e.printStackTrace();
                   }
               }
           });







            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String sName = nameText.getText();
                    String sSurname = SurnameText.getText();
                    String rb1 = updateButton.getText();
                    System.out.println(rb1);

                    String sTc = TCtext.getText();
                    dateText.setText(date);
                    //   insertToPersonnelInfo(sTc, sName, sSurname, date, CalculateHoliday(date), statu, child);
                    if ( sName.equals("") || sSurname.equals("")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Uyarı");
                        alert.setHeaderText("İsim ve Soya isim alanlarını doldur");
                        alert.showAndWait();
                    }else{

                        if (isUpdate) {
                            if (!isTCKNCorrect(sTc)) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Warning");
                                alert.setHeaderText("Correct the TC infos");
                                alert.showAndWait();
                            } else if (contains(sTc)) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Warning");
                                alert.setHeaderText("Başarı ile Güncellendi");
                                alert.showAndWait();
                                updateThePersonnel(sTc, sName, sSurname);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Uyarı");
                                alert.setHeaderText("Böyle biri yok");
                                alert.showAndWait();
                            }


                        } else {
                            if (!chBoll || !stBoll ){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Uyarı");
                                alert.setHeaderText("Medeni durum & çoçuk bölümünü doldur");
                                alert.showAndWait();
                            }else {


                            if (!isTCKNCorrect(sTc)) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setHeaderText("TC alanını duzelt");
                                alert.showAndWait();
                            } else if (contains(sTc)) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Warning");
                                alert.setHeaderText("Kayıtlı Personel");
                                alert.showAndWait();

                            } else {
                                if (date != null && files != null) {

//-----------------------------------------UPdate from here
                                    if (files.size() == 4) {
                                        insertToPersonnelInfo(sTc, sName, sSurname, date, CalculateHoliday(date), statu, child);
                                        //updatePicture(sTc, files.get(0).getAbsolutePath());
                                        updateFiles(sTc, files.get(0).getAbsolutePath(), files.get(1).getAbsolutePath(), files.get(2).getAbsolutePath(), files.get(3).getAbsolutePath());

                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Confirmed");
                                        alert.setHeaderText("Başarı ile Eklendi");
                                        alert.showAndWait();
                                        System.out.println(sName);
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Jata");
                                        alert.setHeaderText("eksisk dosya");
                                        alert.showAndWait();
                                    }
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("Uyarı");
                                    alert.setHeaderText("Tarih & Dosya seç");
                                    alert.showAndWait();

                                }
                            }
                        }
                    }


                    }
                }
            });



            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }

    }
    private Connection setConnect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:demoSheet.db";
        String url = "jdbc:sqlite:"+file.getAbsolutePath();

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public boolean contains(String tc){
        String sql = "SELECT * "
                + "FROM Personnel_Info WHERE TC = ?";


        try (Connection conn = setConnect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,tc);
            //
            ResultSet rs  = pstmt.executeQuery();
            boolean flag=true;


            // loop through the result set
            while (rs.next()) {
                flag=false;
                break;
            }
            return !flag;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    //inserts data to Personnel info
    public void insertToPersonnelInfo( String  TC,String name,String Surname,String Date,String firstHoliday,String status,int child) {
        String sql = "INSERT INTO Personnel_Info(TC,Name,Surname,Date,firstHoliday,Status,Child) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = setConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, TC);
            pstmt.setString(2, name);
            pstmt.setString(3, Surname);
            pstmt.setString(4, Date);
            pstmt.setString(5, firstHoliday);
            pstmt.setString(6, status);
            pstmt.setInt(7,child);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private boolean isTCKNCorrect(String id) {
        if (id == null) return false;

        if (id.length() != 11) return false;

        char[] chars = id.toCharArray();
        int[] a = new int[11];

        for(int i=0; i<11; i++) {
            a[i] = chars[i] - '0';
        }

        if(a[0] == 0) return false;
        if(a[10] % 2 == 1) return false;

        if(((a[0] + a[2] + a[4] + a[6] + a[8]) * 7 - (a[1] + a[3] + a[5] + a[7])) % 10 != a[9]) return false;

        if((a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9]) % 10 != a[10]) return false;

        return true;
    }//controls the TC
    public String CalculateHoliday(String Date){
        String split[]=Date.split("-");
        int y=Integer.parseInt(split[0]);
        y++;
        return String.valueOf(y)+"-"+split[1]+"-"+split[2];
    }
    public void updateThePersonnel(String TC, String name, String surname) {
        String sql = "UPDATE Personnel_Info SET Name = ? , "
                + "SurName = ? "
                + "WHERE TC = ?";
        try (Connection conn = this.setConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);

            pstmt.setString(3, TC);




            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    private byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
    public void updateFiles(String TC, String f,String f1,String f2,String f3) {
        // update sql
        String updateSQL = "UPDATE Personnel_Info "
                + "SET File = ? , File1 = ? , File2 = ? , File3 = ? "
                + "WHERE TC=?";

        try (Connection conn = this.setConnect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // set parameters
            pstmt.setBytes(1, readFile(f));
            pstmt.setBytes(2, readFile(f1));
            pstmt.setBytes(3, readFile(f2));
            pstmt.setBytes(4, readFile(f3));
            pstmt.setString(5, TC);

            pstmt.executeUpdate();
            System.out.println("Stored the file in the BLOB column.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updatePDF_1(String TC, String filename) {
        // update sql
        String updateSQL = "UPDATE Personnel_Info "
                + "SET File1 = ? "
                + "WHERE TC=?";

        try (Connection conn = this.setConnect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // set parameters
            pstmt.setBytes(1, readFile(filename));
            pstmt.setString(2, TC);

            pstmt.executeUpdate();
            System.out.println("Stored the file in the BLOB column.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updatePDF_2(String TC, String filename) {
        // update sql
        String updateSQL = "UPDATE Personnel_Info "
                + "SET File2 = ? "
                + "WHERE TC=?";

        try (Connection conn = this.setConnect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // set parameters
            pstmt.setBytes(1, readFile(filename));
            pstmt.setString(2, TC);

            pstmt.executeUpdate();
            System.out.println("Stored the file in the BLOB column.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updatePDF_3(String TC, String filename) {
        // update sql
        String updateSQL = "UPDATE Personnel_Info "
                + "SET File3 = ? "
                + "WHERE TC=?";

        try (Connection conn = this.setConnect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // set parameters
            pstmt.setBytes(1, readFile(filename));
            pstmt.setString(2, TC);

            pstmt.executeUpdate();
            System.out.println("Stored the file in the BLOB column.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}