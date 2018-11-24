/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.dialogs.chart;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

/**
 *
 * @author MrCuong
 */
public class BarChart {

    public void displayChart(List<String> xData, List<Integer> yData,String title, JFrame parent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.getWidth());
        int height = (int)(screenSize.getHeight());
        
        
        CategoryChart chart = new CategoryChartBuilder().width(width).height(height).title(title).xAxisTitle("Tên Đồ Ăn").yAxisTitle("Doanh Thu").build();
//        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setDecimalPattern("#,###,###");

        chart.addSeries("Tên đồ ăn", xData, yData);
        JPanel panel = new XChartPanel<CategoryChart>(chart);
        JDialog dialog = new JDialog(parent,true);
        
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }
    public static void main(String[] args) {
        List<String> xData = new ArrayList<>();
        List<Integer> yData = new ArrayList<>();
        JFrame frame = new JFrame();
        xData.add("a");
        xData.add("b");
        xData.add("c");
        
        yData.add(25000);
        yData.add(250000);
        yData.add(45000);
        
        new BarChart().displayChart(xData, yData,"hello", frame);
    }
}
