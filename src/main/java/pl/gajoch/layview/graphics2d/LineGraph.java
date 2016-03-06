package pl.gajoch.layview.graphics2d;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Piotr on 12/02/2016.
 */
public class LineGraph extends Group {

    private ArrayList<Vec2d> points;

    public LineGraph(){
        super();
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis,yAxis);
        //defining a series
        XYChart.Series series = new XYChart.Series();
        lineChart.setLegendVisible(false);
        //series.setName("My portfolio");
        //populating the series with data
        //lineChart.setCreateSymbols(false);//punkty

        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);

        lineChart.applyCss();

        Set<Node> nodes = lineChart.lookupAll(".series" + 0);

        for (Node n : nodes) {
            StringBuilder style = new StringBuilder();
            style.append("-fx-stroke: blue; -fx-background-color: blue, white; -fx-stroke-width: 10px;");
            n.setStyle(style.toString());
        }

        this.getChildren().add(lineChart);


    }
}
