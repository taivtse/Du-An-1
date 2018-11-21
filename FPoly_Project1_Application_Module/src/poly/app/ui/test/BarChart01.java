package poly.app.ui.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;

public class BarChart01 implements ExampleChart<CategoryChart> {

  public static void main(String[] args) {

    ExampleChart<CategoryChart> exampleChart = new BarChart01();
    CategoryChart chart = exampleChart.getChart();
    
    List<String> xData = new ArrayList<String>();
    xData.add("haha");
    xData.add("hihi");
    xData.add("hixhix");
    xData.add("huhu");
    
    List<Integer> yData = new ArrayList<Integer>();
    yData.add(1);
    yData.add(2);
    yData.add(5);
    yData.add(9);
    
    chart.addSeries("Trang thai", xData, yData);
    new SwingWrapper<CategoryChart>(chart).displayChart();
  }

  @Override
  public CategoryChart getChart() {

    // Create Chart
    CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Thong ke trang thai").xAxisTitle("Trang thai").yAxisTitle("So lan").theme(Styler.ChartTheme.GGPlot2).build();

    // Customize Chart
    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
    chart.getStyler().setHasAnnotations(true);

    return chart;
  }
}