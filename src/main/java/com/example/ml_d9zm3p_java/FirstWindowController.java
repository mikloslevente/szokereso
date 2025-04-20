package com.example.ml_d9zm3p_java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Platform;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javafx.scene.control.Label;
public class FirstWindowController {

    public FirstWindowController(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("szokereso.txt"));
            String sor;
            while ((sor = reader.readLine()) != null){
                megoldasok.add(sor);
                maxPont += sor.length();
            }
            reader.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        for(int i = 0; i < megoldasok.size(); i++){
            megoldasokMasolata.add(megoldasok.get(i));
        }
    }

    @FXML
    AnchorPane ap_AnchorPane;
    @FXML
    Pane pn_Main;
    @FXML
    Pane pn_Side;
    @FXML
    Pane pn_Side2;
    @FXML
    Pane pn_Side3;
    @FXML
    Pane pn_FNev;
    @FXML
    Button btn_ValaszBevitele;
    @FXML
    Button btn_OsszesMegoldas;
    @FXML
    Button btn_CsakMegtalaltSzavak;
    @FXML
    Button btn_Ok;
    @FXML
    Button btn_Ok2;
    @FXML
    Button btn_Ok3;
    @FXML
    Button btn_Ujra;
    @FXML
    Button btn_Probak;
    @FXML
    Button btn_FNev;
    @FXML
    Button btn_Kilepes;
    @FXML
    Button btn_Torles;
    @FXML
    TextField txt_Valasz;
    @FXML
    TextField txt_Counter;
    @FXML
    TextField txt_Eredmeny;
    @FXML
    TextField txt_Lehetoseg;
    @FXML
    TextField txt_FNev;
    @FXML
    ListView<String> lv_NemMegtalalt;
    @FXML
    ListView<String> lv_Megtalalt;
    @FXML
    ListView<String> lv_Megtalalt2;
    @FXML
    ListView<String> lv_Probak;
    @FXML
    Label lb_HibaUzenet;
    @FXML
    Label lb_Hajra;

    private static final String FILE_PATH = "probalkozasok.txt";
    private ObservableList<String> megoldasok = FXCollections.observableArrayList();
    private ObservableList<String> megoldasokMasolata = FXCollections.observableArrayList();
    private ObservableList<String> toroltMegoldasok = FXCollections.observableArrayList();
    private ObservableList<String> valaszok = FXCollections.observableArrayList();
    private ObservableList<String> probak = FXCollections.observableArrayList();
    private int probalkozas = 0;
    private int maxPont = 0;
    private int elertPont = 0;
    private int elet = 0;
    private String fnev = "";

    @FXML
    private void btn_FNev_Click(){
        if(txt_FNev.getText().length() == 0){
            lb_HibaUzenet.setText("Felhasználónév kötelező!");
            txt_FNev.clear();
        }

        else if(txt_FNev.getText().length() < 4){
            lb_HibaUzenet.setText("Minimum 4 karakter hosszú lehet!");
            txt_FNev.clear();
        }

        else if(txt_FNev.getText().length() > 12){
            lb_HibaUzenet.setText("Maximum 12 karakter hosszú lehet!");
            txt_FNev.clear();
        }

        else{
            fnev = txt_FNev.getText();
            lb_Hajra.setText("Sok sikert, " + fnev + '!');

            probalkozas++;
            txt_Lehetoseg.setOpacity(1);
            txt_Lehetoseg.setText("" + probalkozas + '/' + "10");
            probalkozas--;

            pn_Main.setVisible(true);
            pn_Main.setDisable(false);
            pn_FNev.setVisible(false);
            pn_FNev.setDisable(true);
        }
    }

    @FXML
    private void btn_ValaszBevitele_Click(){
        elet++;
        if(megoldasok.contains(txt_Valasz.getText()) && !valaszok.contains(txt_Valasz.getText())){
            valaszok.add(txt_Valasz.getText());
            elertPont += txt_Valasz.getText().length();
            megoldasokMasolata.remove(txt_Valasz.getText());
            toroltMegoldasok.add(txt_Valasz.getText());
        }
        txt_Valasz.clear();

        txt_Counter.setOpacity(1);
        txt_Counter.setText("" + valaszok.size() + '/' + megoldasok.size());

        txt_Eredmeny.setOpacity(1);
        txt_Eredmeny.setText("" + elertPont + '/' + maxPont);

        if(elet == 11){
            btn_Ujra_Click();
            elet = 0;
        }
    }

    @FXML
    private void btn_Eredmeny_Click() {
        lv_NemMegtalalt.setItems(megoldasokMasolata);
        lv_Megtalalt.setItems(valaszok);

        pn_Main.setVisible(false);
        pn_Main.setDisable(true);
        pn_Side.setVisible(true);
        pn_Side.setDisable(false);
    }

    @FXML
    private void btn_Ok_Click(){
        pn_Main.setVisible(true);
        pn_Main.setDisable(false);
        pn_Side.setVisible(false);
        pn_Side.setDisable(true);
    }

    @FXML
    private void btn_CsakMegtalaltSzavak_Click(){
        lv_Megtalalt2.setItems(valaszok);

        pn_Main.setVisible(false);
        pn_Main.setDisable(true);
        pn_Side2.setVisible(true);
        pn_Side2.setDisable(false);
    }

    @FXML
    private void btn_Ok_Click2(){
        pn_Main.setVisible(true);
        pn_Main.setDisable(false);
        pn_Side2.setVisible(false);
        pn_Side2.setDisable(true);
    }

    @FXML
    private void btn_Ujra_Click(){
        if(probalkozas < 9){
            try{
                probalkozas++;
                BufferedWriter writer = new BufferedWriter(new FileWriter("probalkozasok.txt",true));
                writer.write(fnev +"      ");
                writer.write(Integer.toString(probalkozas));
                writer.write(". játék      ");
                writer.write(Integer.toString(elertPont) + '/' + Integer.toString(maxPont));
                writer.newLine();
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            if(probalkozas != 9){
                elertPont = 0;
            }
            txt_Valasz.clear();
            txt_Counter.clear();
            txt_Eredmeny.clear();
            txt_Lehetoseg.clear();
            valaszok.clear();
            for(int i = 0; i < toroltMegoldasok.size(); i++){
                megoldasokMasolata.add(toroltMegoldasok.get(i));
            }

            probalkozas++;
            txt_Lehetoseg.setOpacity(1);
            txt_Lehetoseg.setText("" + probalkozas + '/' + "10");
            probalkozas--;
        }

        else{
            txt_Valasz.setText("Nincs több lehetőség!");
            txt_Valasz.setDisable(true);
            txt_Valasz.setOpacity(1);
            btn_ValaszBevitele.setDisable(true);
        }
    }

    @FXML
    private void btn_Probak_Click(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("probalkozasok.txt"));
            String sor;
            while ((sor = reader.readLine()) != null){
                probak.add(sor);
            }
            reader.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        lv_Probak.setItems(probak);

        pn_Main.setVisible(false);
        pn_Main.setDisable(true);
        pn_Side3.setVisible(true);
        pn_Side3.setDisable(false);
    }

    @FXML
    private void btn_Ok_Click3(){
        probak.clear();
        lv_Probak.getItems().clear();
        elertPont = 0;

        pn_Main.setVisible(true);
        pn_Main.setDisable(false);
        pn_Side3.setVisible(false);
        pn_Side3.setDisable(true);
    }

    @FXML
    private void btn_Kilepes_Click(){
        probalkozas++;
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("probalkozasok.txt",true));
            writer.write(fnev +"      ");
            writer.write(Integer.toString(probalkozas));
            writer.write(". játék      ");
            writer.write(Integer.toString(elertPont) + '/' + Integer.toString(maxPont));
            writer.newLine();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Platform.exit();
    }
    @FXML
    private void btn_Torles_Click(){
        try {
            Files.write(new File(FILE_PATH).toPath(), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
