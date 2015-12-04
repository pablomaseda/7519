package view;

import au.com.bytecode.opencsv.CSVWriter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class MainWindow extends JFrame {


  JPanel counterPanel = new JPanel();
  JLabel prayLabel = new JLabel();
  JLabel predatorLabel = new JLabel();
  JLabel secondLabel = new JLabel();
  private CSVWriter writer;

  public MainWindow() {
    this.setTitle("Pray-Predator Model");
    this.setLayout(new BorderLayout());
    this.counterPanel.setLayout(new GridLayout(2,1));
    this.prayLabel.setForeground(Color.GREEN);
    this.predatorLabel.setForeground(Color.RED);
    this.secondLabel.setForeground(Color.BLACK);
    this.counterPanel.setLayout(new GridLayout(3,1));
    this.counterPanel.add(this.secondLabel);
    this.counterPanel.add(this.prayLabel);
    this.counterPanel.add(this.predatorLabel);
    this.counterPanel.setBackground(Color.WHITE);
    this.counterPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    this.add(this.counterPanel, BorderLayout.SOUTH);
    this.counterPanel.setPreferredSize(new Dimension(this.getWidth(), 70));
    this.getContentPane().setBackground(Color.BLACK);
    this.setBounds(0,0,300,100);
    this.addWindowListener(new WindowListener() {
      @Override
      public void windowOpened(WindowEvent windowEvent) {

      }

      @Override
      public void windowClosing(WindowEvent windowEvent) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        System.exit(0);
      }

      @Override
      public void windowClosed(WindowEvent windowEvent) {

      }

      @Override
      public void windowIconified(WindowEvent windowEvent) {

      }

      @Override
      public void windowDeiconified(WindowEvent windowEvent) {

      }

      @Override
      public void windowActivated(WindowEvent windowEvent) {

      }

      @Override
      public void windowDeactivated(WindowEvent windowEvent) {

      }
    });
    this.setVisible(true);
    this.setResizable(false);
  }

  public void update(int preyCount, int predatorCount, int sec) {
    this.prayLabel.setText("Presas: "+preyCount);
    this.predatorLabel.setText("Depredadores: "+predatorCount);
    this.secondLabel.setText("Tiempo: "+sec+" seg");
  }

  public void setPreyCount(int count) {
    this.prayLabel.setText("Presas: "+count);
  }

  public void setPredatorCount(int count) {
    this.predatorLabel.setText("Depredadores: "+count);
  }

  public void setSeconds(int sec) {
    this.secondLabel.setText("Tiempo: "+sec+" seg");
  }

  public void setWriter(CSVWriter writer) {
    this.writer = writer;
  }
}
