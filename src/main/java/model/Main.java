package model;

/*
import view.MainWindow;
import view.WarningDialog;
import controller.ModelController;
import java.util.Timer;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
*/

import controller.ModelController2;
import uchicago.src.sim.engine.SimInit;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    /*CSVWriter writer = new CSVWriter(new FileWriter("output.csv"), ',');
    String[] entries = {"preys","predators","time"};
    writer.writeNext(entries);
    if (args.length > 6) {
      new WarningDialog();
    } else {
      MainWindow window = new MainWindow();
      ModelController controller = new ModelController(new LotkaVolterra(1, 1, 1, 0.2, 0.5, 2), window, writer);
      Timer t = new Timer();
      t.schedule(controller,0,1000);
    }*/
	  
	  SimInit init = new SimInit();
      ModelController2 model = new ModelController2();
      init.loadModel(model, "", false); 
  }
}

