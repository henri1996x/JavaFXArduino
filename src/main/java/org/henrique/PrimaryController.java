package org.henrique;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import com.fazecast.jSerialComm.SerialPort;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox cbPortas;
    @FXML
    private Button btnConectar;
    @FXML
    private Button ligaDesliga;

    private SerialPort porta;
    private int led = 0;



    @Override
    public void initialize(URL location, ResourceBundle resources) { // metodo do initialize
        carregarPortas();
        ligaDesliga.setDisable(true);
    }

    private void carregarPortas(){
        SerialPort[] portNames = SerialPort.getCommPorts(); //Carrega as portas e as coloca em um array

        for(SerialPort portName : portNames){//para cada objeto SerialPort dentro da minha lista portNames...
            cbPortas.getItems().add(portName.getSystemPortName());//adiciona no comboBox
            System.out.println("OK");
        }
    }

    @FXML
    private void conectar(ActionEvent event){ //metodo para conectar
        ligaDesliga.setDisable(false);
        if(btnConectar.getText().equals("Conectar")){
            porta = SerialPort.getCommPort(cbPortas.getSelectionModel().getSelectedItem().toString());
            //Pega a porta selecionada no combobox
            if(porta.openPort()){ // se a porta estiver conectada
                btnConectar.setText("Desconectar");
                cbPortas.setDisable(true);
            }
        }
        else{
            porta.closePort();
            cbPortas.setDisable(false);
            btnConectar.setText("Conectar");
            ligaDesliga.setDisable(true);
        }
    }

    @FXML
    private void ligarLed(){

        PrintWriter output = new PrintWriter(porta.getOutputStream());

        if(led == 0){
            output.print("1");
            output.flush();
            led = 1;
        }
        else{
            output.print("0");
            output.flush();
            led = 0;
        }

    }
}
