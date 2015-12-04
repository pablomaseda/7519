package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WarningDialog extends JDialog {

  public WarningDialog() {
    this.setLayout(new BorderLayout());
    JLabel warningLabel = new JLabel("FALTAN DATOS para iniciar la simulacion!!");
    this.getContentPane().add(warningLabel, BorderLayout.CENTER);
    this.setBounds(0,0,300,200);
    this.setVisible(true);
    this.addWindowListener(new WindowListener() {
      @Override
      public void windowOpened(WindowEvent windowEvent) {
      }

      @Override
      public void windowClosing(WindowEvent windowEvent) {
        System.exit(-1);
      }

      @Override
      public void windowClosed(WindowEvent e) {
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
  }

}
