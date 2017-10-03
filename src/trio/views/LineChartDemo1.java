package trio.views;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

public class LineChartDemo1 {

	CategoryDataset dataset = null;
	JFreeChart chart = null;

	/**
	 * Creates a new demo.
	 * 
	 * @param title
	 *            the frame title.
	 */
	public LineChartDemo1(Composite composite) {
		JFreeChart chart = createChart(createDataset());

		ChartComposite frame = new ChartComposite(composite, SWT.NONE, chart, true);
		frame.setLayoutData(new GridData(GridData.FILL_BOTH));
		frame.setDisplayToolTips(true);
		frame.setHorizontalAxisTrace(false);
		frame.setVerticalAxisTrace(false);
		frame.setDisplayToolTips(false);
		frame.pack();

	}
	

	private static CategoryDataset createDataset() {
		DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		String series1 = "Java";
		String series2 = "PHP";
		String series3 = "C++";
		String series4 = "C#";

		dataset.add(new BoxAndWhiskerItem(new Double(1.0), new Double(2.0),
		            new Double(3.0), new Double(4.0), new Double(5.0),
		            new Double(6.0), new Double(7.0), new Double(8.0),
		            new ArrayList()), "ROW1", "COLUMN1");
		return dataset;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset  the dataset.
	 * 
	 * @return A chart.
	 */
	private static JFreeChart createChart(CategoryDataset dataset) {


		 

	        String boxplot_title = "Top 5 Energy Greedy Methods";

	        CategoryAxis xAxis = new CategoryAxis("Signatures");
	        NumberAxis yAxis = new NumberAxis("Consumptions");

	        yAxis.setAutoRangeIncludesZero(false);
	        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();

	        renderer.setFillBox(true);
	        renderer.setBaseToolTipGenerator(new BoxAndWhiskerToolTipGenerator());

	        CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

	        JFreeChart chart = new JFreeChart(
	                boxplot_title,
	                
	                plot
	        );

		return chart;


	}

}