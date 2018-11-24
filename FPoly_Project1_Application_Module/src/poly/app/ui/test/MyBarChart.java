package poly.app.ui.test;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

public class MyBarChart {

    public void displayChart(JFrame owner) {
//height of the task bar
//        Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        CategoryChart chart = new CategoryChartBuilder().width(800).height(500).title("Thong ke trang thai").xAxisTitle("Trang thai").yAxisTitle("So lan").build();

        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setDecimalPattern("#,###,###");

        List<String> xData = new ArrayList<String>();
        xData.add("haha");
        xData.add("hihi");
        xData.add("hixhix");
        xData.add("huhu");

        List<Integer> yData = new ArrayList<Integer>();
        yData.add(100000);
        yData.add(200000);
        yData.add(250000);
        yData.add(250000);

        chart.addSeries("Trang thai", xData, yData);
        chart.getStyler().setSeriesColors(new Color[]{Color.PINK});

        JPanel chartPan = new XChartPanel<CategoryChart>(chart);

        Dialog dialog = new Dialog(owner, true);
        dialog.setLayout(new FlowLayout());

        JButton a = new JButton("a");
        a.addActionListener((e) -> {
//            XYChart a = new XYChart(chartBuilder)
        });
        dialog.add(chartPan);
        dialog.add(a);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MyBarChart().displayChart(new JFrame());
    }
}
