package controller;

import au.com.bytecode.opencsv.CSVWriter;
import model.LotkaVolterra;
import view.MainWindow;

import java.io.IOException;
import java.util.TimerTask;

public class ModelController extends TimerTask {

  private LotkaVolterra model;
  private MainWindow window;
  private CSVWriter writer;

  public ModelController(LotkaVolterra model, MainWindow window, CSVWriter writer) {
    this.model = model;
    this.window = window;
    this.window.setPreyCount(this.getPreyCount());
    this.window.setPredatorCount(this.getPredatorCount());
    this.window.setSeconds(0);
    this.writer = writer;
    this.window.setWriter(this.writer);
  }

  @Override
  public void run() {
    try {
      this.model.update();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.window.update(this.getPreyCount(), this.getPredatorCount(), this.model.getSeconds());
    String[] entries = {String.valueOf(this.model.getPreysCount()),String.valueOf(this.model.getPredatorsCount()),String.valueOf(this.model.getSeconds())};
    this.writer.writeNext(entries);
  }

  public int getPreyCount() {
    return (int) Math.floor(this.model.getPreysCount());
  }

  public int getPredatorCount() {
    return (int) Math.floor(this.model.getPredatorsCount());
  }
}
