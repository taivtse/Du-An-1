/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.chart;

import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler.LegendPosition;

/**
 *
 * @author MrCuong
 */
public class OverlappedChart {

    public void displayChart(List<String> xData, List<Integer> yData, List<Integer> y1Data, List<Integer> y2Data, List<Integer> y3Data, JFrame frame, String xTitle, String yTitle) {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Thống Kê Vé").xAxisTitle(xTitle).yAxisTitle(yTitle).build();

        Color[] sliceColors = new Color[]{Color.decode("#1D95F6"), Color.decode("#7CBA29"), Color.decode("#FBB300"), Color.decode("#29B998"), Color.decode("#F98300"), Color.decode("#CE3933"), Color.decode("#DA6F13")};
        chart.getStyler().setSeriesColors(sliceColors);
        
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
        
        JFrame wrapperFrame = new JFrame();
        wrapperFrame.add(jp);
        wrapperFrame.pack();
        wrapperFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        wrapperFrame.setLocationRelativeTo(null);
        wrapperFrame.setVisible(true);
    }
}
