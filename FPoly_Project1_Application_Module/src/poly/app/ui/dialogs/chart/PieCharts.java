/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.ui.frames.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JPanel;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

/**
 *
 * @author MrCuong
 */
public class PieCharts {

    String title;
    public void displayChart(Map<String, Integer> map, String title) {
        PieChart chart = new PieChartBuilder().width(800).height(600).title(title).build();

        // Customize Chart
        Color[] sliceColors = new Color[]{new Color(29,255,92), new Color(11,78,156), new Color(53,49,18), new Color(143,210,49), new Color(164,116,179)};
        chart.getStyler().setSeriesColors(sliceColors);

        // Series
        for(Map.Entry<String, Integer> item : map.entrySet())
        {
            chart.addSeries(item.getKey(), item.getValue());
        }

        JPanel jp = new XChartPanel(chart);
        JDialog jd = new JDialog();
        jd.add(jp);
        jd.pack();
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
    }
}
