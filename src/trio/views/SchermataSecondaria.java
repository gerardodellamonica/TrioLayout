package trio.views;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.dialogs.ShowViewDialog;
import org.eclipse.ui.part.ViewPart;
import org.swtchart.Chart;
import org.swtchart.IAxisSet;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesSet;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

public class SchermataSecondaria extends ViewPart implements IViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "trio.views.SchermataSecondaria";

	private FillLayout layout;
	private FormToolkit toolkit;
	private Form form;
	private TabFolder tabFolder;
	private TabItem ItemMeanStandard;
	private TabItem ItemBoxplot;

	// Item Mean and Standard Devation
	// primo blocco
	private Scale RunScale;
	private Button CriterionStatementCoverage;
	private Button CriterionBranchCoverage;
	private Button CriterionPastFaults;
	private ArrayList<String> Criiterion;

	// Item Mean and Standard Devation
	private Label LabelMinutes;
	private Button ConvertMinutes;
	// Itam GGA
	private Text GGAMeanAverage;
	private Text GGAMeanTime;
	private Text GGAStandardAvarage;
	private Text GGAStandardTime;

	// Item Mean and Standard Devation
	// Itam GDE3
	private Text GDEMeanAverage;
	private Text GDEMeanTime;
	private Text GDEStandardAvarage;
	private Text GDEStandardTime;

	// Item Mean and Standard Devation
	// Itam MOEAD
	private Text MOEADMeanAverage;
	private Text MOEADMeanTime;
	private Text MOEADStandardAvarage;
	private Text MOEADStandardTime;

	public FormToolkit getToolkit() {
		return toolkit;
	}

	public Form getForm() {
		return form;
	}

	public SchermataSecondaria() {
		super();
	}

	public SchermataSecondaria(Composite parent) {
		super();
		createPartControl(parent);

	}

	public void setFocus() {
	}

	@Override
	public void createPartControl(Composite parent) {
		layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		// inizializzo la mia schermata grafica di eclipse
		toolkit = new FormToolkit(parent.getDisplay());
		// creo il form
		form = toolkit.createForm(parent);
		// setto il layout del mio form
		form.getBody().setLayout(layout);

		// genero TabFolder
		GenerateTabFolder();

	}

	public void GenerateTabFolder() {
		Criiterion = new ArrayList<String>();
		// inizializzo il widget per ottenere le schede
		tabFolder = new TabFolder(form.getBody(), SWT.NULL);
		// inizializzo la prima scheda
		ItemMeanStandard = new TabItem(tabFolder, SWT.NONE);
		ItemMeanStandard.setText("Mean and Standard Devation");
		// inizializzo la seconda schermata
		ItemBoxplot = new TabItem(tabFolder, SWT.NONE);
		ItemBoxplot.setText("Boxplots");

		Composite compositePrincipale = new Composite(tabFolder, SWT.NULL);
		compositePrincipale.setLayout(layout);

		Composite composite = new Composite(compositePrincipale, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(4, true);
		gridLayout.numColumns = 4;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;

		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Run");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		RunScale = new Scale(composite, SWT.NULL);
		RunScale.setMaximum(30);
		RunScale.setMinimum(1);
		RunScale.setPageIncrement(1);
		RunScale.setEnabled(false);
		RunScale.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Criiterion");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		CriterionStatementCoverage = new Button(composite, SWT.CHECK);
		CriterionStatementCoverage.setText("Statement Coverage");
		CriterionStatementCoverage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		CriterionStatementCoverage.setEnabled(false);
		CriterionStatementCoverage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Criiterion.indexOf("StatementCoverage") == -1)
					Criiterion.add("StatementCoverage");
				else
					Criiterion.remove("StatementCoverage");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("Problem");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Test Suite Prioritization");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		CriterionBranchCoverage = new Button(composite, SWT.CHECK);
		CriterionBranchCoverage.setText("Branch Coverage");
		CriterionBranchCoverage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		CriterionBranchCoverage.setEnabled(false);
		CriterionBranchCoverage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Criiterion.indexOf("BranchCoverage") == -1)
					Criiterion.add("BranchCoverage");
				else
					Criiterion.remove("BranchCoverage");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		CriterionPastFaults = new Button(composite, SWT.CHECK);
		CriterionPastFaults.setText("Past Faults");
		CriterionPastFaults.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		CriterionPastFaults.setEnabled(false);
		CriterionPastFaults.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Criiterion.indexOf("PastFaults") == -1)
					Criiterion.add("PastFaults");
				else
					Criiterion.remove("PastFaults");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		// inizializzo il widget per ottenere le schede
		TabFolder tabFolder2 = new TabFolder(compositePrincipale, SWT.NULL);
		// inizializzo la prima scheda

		ItemGGA(tabFolder2);
		ItemGDE3(tabFolder2);
		ItemMOEAD(tabFolder2);

		ItemMeanStandard.setControl(compositePrincipale);

		///////

		Composite compositeSecond = new Composite(tabFolder, SWT.NULL);
		compositeSecond.setLayout(layout);
		/*
		 * Chart chart=new Chart(compositeSecond, SWT.NONE); double[] ySeries = { 0.3,
		 * 1.4, 1.3, 1.9, 2.1 }; ISeriesSet seriesSet = chart.getSeriesSet(); ISeries
		 * series = seriesSet.createSeries(SeriesType.LINE, "line series");
		 * series.setYSeries(ySeries); IAxisSet axisSet = chart.getAxisSet();
		 * axisSet.adjustRange();
		 */
	//	new LineChartDemo1(compositeSecond.getShell());
	

		ItemBoxplot.setControl(compositeSecond);

	}

	public void ItemGGA(TabFolder tabFolder2) {
		TabItem ItemGGA = new TabItem(tabFolder2, SWT.NONE);
		ItemGGA.setText("GGA");

		Composite composite = new Composite(tabFolder2, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(4, true);
		gridLayout.numColumns = 4;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;
		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Average Fault Detection Percentage");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Time in millisecond");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Mean");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GGAMeanAverage = new Text(composite, SWT.BORDER);
		GGAMeanAverage.setText("212");
		GGAMeanAverage.setEditable(false);
		GGAMeanAverage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Mean");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GGAMeanTime = new Text(composite, SWT.BORDER);
		GGAMeanTime.setText("212");
		GGAMeanTime.setEditable(false);
		GGAMeanTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Standard Detection");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GGAStandardAvarage = new Text(composite, SWT.BORDER);
		GGAStandardAvarage.setText("212");
		GGAStandardAvarage.setEditable(false);
		GGAStandardAvarage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Standard Detection");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GGAStandardTime = new Text(composite, SWT.BORDER);
		GGAStandardTime.setText("212");
		GGAStandardTime.setEditable(false);
		GGAStandardTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		ConvertMinutes = new Button(composite, SWT.PUSH);
		ConvertMinutes.setText("Convert in minutes");
		ConvertMinutes.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		ConvertMinutes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.getSource();
			}
		});

		LabelMinutes = new Label(composite, SWT.NONE);
		LabelMinutes.setText("1 minutes=60000ms");
		LabelMinutes.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		ItemGGA.setControl(composite);

	}

	public void ItemGDE3(TabFolder tabFolder2) {
		TabItem ItemGDE3 = new TabItem(tabFolder2, SWT.NONE);
		ItemGDE3.setText("GDE3");

		Composite composite = new Composite(tabFolder2, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(4, true);
		gridLayout.numColumns = 4;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;
		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Average Fault Detection Percentage");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Time in millisecond");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Mean");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GDEMeanAverage = new Text(composite, SWT.BORDER);
		GDEMeanAverage.setText("212");
		GDEMeanAverage.setEditable(false);
		GDEMeanAverage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Mean");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GDEMeanTime = new Text(composite, SWT.BORDER);
		GDEMeanTime.setText("212");
		GDEMeanTime.setEditable(false);
		GDEMeanTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Standard Detection");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GDEStandardAvarage = new Text(composite, SWT.BORDER);
		GDEStandardAvarage.setText("212");
		GDEStandardAvarage.setEditable(false);
		GDEStandardAvarage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Standard Detection");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GDEStandardTime = new Text(composite, SWT.BORDER);
		GDEStandardTime.setText("212");
		GDEStandardTime.setEditable(false);
		GDEStandardTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		ConvertMinutes = new Button(composite, SWT.PUSH);
		ConvertMinutes.setText("Convert in minutes");
		ConvertMinutes.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		ConvertMinutes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.getSource();
			}
		});

		LabelMinutes = new Label(composite, SWT.NONE);
		LabelMinutes.setText("1 minutes=60000ms");
		LabelMinutes.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		ItemGDE3.setControl(composite);

	}

	public void ItemMOEAD(TabFolder tabFolder2) {
		TabItem ItemMOEAD = new TabItem(tabFolder2, SWT.NONE);
		ItemMOEAD.setText("MOEAD");

		Composite composite = new Composite(tabFolder2, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(4, true);
		gridLayout.numColumns = 4;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;
		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Average Fault Detection Percentage");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Time in millisecond");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Mean");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		MOEADMeanAverage = new Text(composite, SWT.BORDER);
		MOEADMeanAverage.setText("212");
		MOEADMeanAverage.setEditable(false);
		MOEADMeanAverage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Mean");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		MOEADMeanTime = new Text(composite, SWT.BORDER);
		MOEADMeanTime.setText("212");
		MOEADMeanTime.setEditable(false);
		MOEADMeanTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Standard Detection");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		MOEADStandardAvarage = new Text(composite, SWT.BORDER);
		MOEADStandardAvarage.setText("212");
		MOEADStandardAvarage.setEditable(false);
		MOEADStandardAvarage.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Standard Detection");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		MOEADStandardTime = new Text(composite, SWT.BORDER);
		MOEADStandardTime.setText("212");
		MOEADStandardTime.setEditable(false);
		MOEADStandardTime.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		ConvertMinutes = new Button(composite, SWT.PUSH);
		ConvertMinutes.setText("Convert in minutes");
		ConvertMinutes.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		ConvertMinutes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.getSource();
			}
		});

		LabelMinutes = new Label(composite, SWT.NONE);
		LabelMinutes.setText("1 minutes=60000ms");
		LabelMinutes.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		ItemMOEAD.setControl(composite);

	}

	public ArrayList<String> getCriiterion() {
		return Criiterion;
	}

	public void setCriiterion(ArrayList<String> criiterion) {
		Criiterion = criiterion;
	}
	
	public Scale getRunScale() {
		return RunScale;
	}

	public void setRunScale(int increment) {
		RunScale.setIncrement(increment);
	}
	
	public Label getLabelMinutes() {
		return LabelMinutes;
	}
	public void setLabelMinutes(String LabelMinutes) {
		this.LabelMinutes.setText(LabelMinutes);
	}
	



	
	public Text getGGAMeanAverage() {
		
		return GGAMeanAverage;
	}
	public void setGGAMeanAverage(String LabelMinutes) {
		this.GGAMeanAverage.setText(LabelMinutes);
	}
	
	public Text getGGAMeanTime() {
		return GGAMeanTime;
	}
	public void setGGAMeanTime(String LabelMinutes) {
		this.GGAMeanTime.setText(LabelMinutes);
	}
	
	public Text getGGAGGAStandardAvarage() {
		return GGAStandardAvarage;
	}
	public void setGGAStandardAvarage(String LabelMinutes) {
		this.GGAStandardAvarage.setText(LabelMinutes);
	}
	
	public Text getGGAStandardTime() {
		return GGAStandardTime;
	}
	public void setGGAStandardTime(String LabelMinutes) {
		this.GGAStandardTime.setText(LabelMinutes);
	}
	
	
	public Text getGDEMeanAverage() {
		return GDEMeanAverage;
	}
	public void setGDEMeanAverage(String LabelMinutes) {
		this.GDEMeanAverage.setText(LabelMinutes);
	}
	
	public Text getGDEMeanTime() {
		return GDEMeanTime;
	}
	public void setGDEMeanTime(String LabelMinutes) {
		this.GDEMeanTime.setText(LabelMinutes);
	}
	
	public Text getGDEStandardAvarage() {
		return GDEStandardAvarage;
	}
	public void setGDEStandardAvarage(String LabelMinutes) {
		this.GDEStandardAvarage.setText(LabelMinutes);
	}
	
	public Text getGDEStandardTime() {
		return GDEStandardTime;
	}
	public void setGDEStandardTime(String LabelMinutes) {
		this.GDEStandardTime.setText(LabelMinutes);
	}
	
	
	
	
	public Text getMOEADMeanAverage() {
		return MOEADMeanAverage;
	}
	public void setMOEADMeanAverage(String LabelMinutes) {
		this.MOEADMeanAverage.setText(LabelMinutes);
	}
	
	
	public Text getMOEADMeanTime() {
		return MOEADMeanTime;
	}
	public void setMOEADMeanTime(String LabelMinutes) {
		this.MOEADMeanTime.setText(LabelMinutes);
	}
	
	public Text getMOEADStandardAvarage() {
		return MOEADStandardAvarage;
	}
	public void setMOEADStandardAvarage(String LabelMinutes) {
		this.MOEADStandardAvarage.setText(LabelMinutes);
	}
	
	
	public Text getMOEADStandardTime() {
		return MOEADStandardTime;
	}
	public void setMOEADStandardTime(String LabelMinutes) {
		this.MOEADStandardTime.setText(LabelMinutes);
	}
}
