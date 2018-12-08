/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.Histogram;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.demo.charts.bar.BarChart06;
import org.knowm.xchart.style.Styler.LegendPosition;

/**
 *
 * @author MrCuong
 */
public class OverlappedChart {

    public void displayChart(List<String> xData, List<Integer> yData, List<Integer> y1Data, List<Integer> y2Data, List<Integer> y3Data, JFrame frame, String xTitle, String yTitle) {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Thống Kê Vé").xAxisTitle(xTitle).yAxisTitle(yTitle).build();

        // Customize Chart
        chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
        chart.getStyler().setAvailableSpaceFill(0.9);
        chart.getStyler().setMarkerSize(20);
        chart.getStyler().setStacked(true);
        // Series
        chart.addSeries("Vé 2D", xData, yData);
        chart.addSeries("Vé 3D", xData, y1Data);
        chart.addSeries("Vé 4D", xData, y2Data);
        chart.addSeries("Vé IMAX", xData, y3Data);

        JPanel jp = new XChartPanel(chart);
        JDialog jd = new JDialog();
        jd.add(jp);
        jd.pack();
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
    }
}