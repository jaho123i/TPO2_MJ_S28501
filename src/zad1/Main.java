/**
 *
 *  @author Matłosz Jan S28501
 *
 */

package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.*;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Niemcy");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    System.out.println(rate1+"\n"+rate2);

    SwingUtilities.invokeLater(() -> {
      JFXPanel jfxPanel = new JFXPanel();
      Platform.runLater(() -> {
        JFrame frame = new JFrame("Frame");
        frame.setLayout(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        JTextPane weather = new JTextPane();
        weather.setText("Test");
        weather.setEditable(false);
        top.add(weather,BorderLayout.WEST);

        JTextPane course = new JTextPane();
        course.setText("Course rate: ");
        course.setEditable(false);
        top.add(course,BorderLayout.CENTER);

        frame.add(top,BorderLayout.NORTH);
        frame.add(jfxPanel,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        Scene scene = new Scene(browser);
        jfxPanel.setScene(scene);
        webEngine.load("https://pl.wikipedia.org/wiki/"+s.country);
      });
    });
  }
}