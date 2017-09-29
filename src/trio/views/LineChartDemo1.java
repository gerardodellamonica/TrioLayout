package trio.views;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

public class LineChartDemo1 extends TitleAreaDialog { 

CategoryDataset dataset = null; 
JFreeChart chart = null; 

/** 
* Creates a new demo. 
* 
* @param title the frame title. 
*/ 
public LineChartDemo1(Shell parent) { 
super(parent); 
//dataset = createDataset(); 
//chart = createChart(dataset); 
//	ChartPanel chartPanel = new ChartPanel(chart); 
//	chartPanel.setPreferredSize(new Dimension(500, 270)); 
//setContentPane(chartPanel); 
} 

protected Control createDialogArea(Composite parent) 
{ 

Display display = new Display(); 
Shell shell = new Shell(display); 

Composite chartComposite = new Composite(shell, SWT.EMBEDDED); 
CategoryDataset dataset = createDataset(); 
JFreeChart chart = createChart(dataset); 

//Creating a frame 
java.awt.Frame frame = SWT_AWT.new_Frame(chartComposite); 
javax.swing.JPanel panel = new javax.swing.JPanel( ); 

//	//Creating a Chart Panel 
ChartPanel chartPanel = new ChartPanel(chart); 
//	chartPanel.setPreferredSize(new Dimension(500, 270)); 
//	
//	//Adding a Chart Panel to a frame 
frame.add(chartPanel); 

return parent; 
} 


/** 
* Creates a sample dataset. 
* 
* @return The dataset. 
*/ 

private static CategoryDataset createDataset() { 
DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
dataset.addValue(212, "Classes", "JDK 1.0"); 
dataset.addValue(504, "Classes", "JDK 1.1"); 
dataset.addValue(1520, "Classes", "SDK 1.2"); 
dataset.addValue(1842, "Classes", "SDK 1.3"); 
dataset.addValue(2991, "Classes", "SDK 1.4"); 
return dataset; 
} 


/** 
* Creates a sample chart. 
* 
* @param dataset a dataset. 
* 
* @return The chart. 
*/ 


private static JFreeChart createChart(CategoryDataset dataset) { 
//	
String arg0 ="Java Standard Class Library"; 
String arg1 = "Release"; 
String arg2 = "Class Count"; 

//create the chart... 
JFreeChart chart = ChartFactory.createLineChart( 
arg0, // chart title 
arg1, // domain axis label 
arg2, // range axis label 
dataset, // data 
PlotOrientation.VERTICAL, // orientation 
false, // include legend 
true, // tooltips 
false // urls 
); 
chart.addSubtitle(new TextTitle("Number of Classes By Release")); 
TextTitle source = new TextTitle( 
"Source: Java In A Nutshell (4th Edition) " 
+ "by David Flanagan (O’Reilly)" 
); 
//source.setFont(new Font("SansSerif", Font.PLAIN, 10)); 
source.setPosition(RectangleEdge.BOTTOM); 
source.setHorizontalAlignment(HorizontalAlignment.RIGHT); 
chart.addSubtitle(source); 
//chart.setBackgroundPaint(Color.white); 
CategoryPlot plot = (CategoryPlot) chart.getPlot(); 
//plot.setBackgroundPaint(Color.lightGray); 
//plot.setRangeGridlinePaint(Color.white); 
//	customise the range axis... 
NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); 
rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
//	customise the renderer... 
LineAndShapeRenderer renderer 
= (LineAndShapeRenderer) plot.getRenderer(); 
renderer.setShapesVisible(true); 
renderer.setDrawOutlines(true); 
renderer.setUseFillPaint(true); 
//renderer.setFillPaint(Color.white); 
return chart; 
} 



} 