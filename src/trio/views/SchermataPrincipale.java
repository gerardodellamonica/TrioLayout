package trio.views;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.dialogs.ShowViewDialog;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

public class SchermataPrincipale extends ViewPart implements IViewPart{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "trio.views.SchermataPrincipale";

	private FillLayout layout;
	private FormToolkit toolkit;
	private Form form;
	private TabFolder tabFolder;
	private TabItem tab1;
	private TabItem tab2;

	// path in principal
	private Text projectJar;
	private Text auxiliaryFolder;
	private Text testCaseFolder;
	private Text outputFolder;
	// path in other matrice
	private String projectJarMatrice;
	private Combo type;
	private ArrayList<Button> radios;
	private Composite comp;
	private Button butto;

	// nella schermata principale
	private Scale scaleRun;
	private Combo problem;
	private ArrayList<String> algorithm;
	private ArrayList<String> criterion;

	private BoxSetting boxSetting_Hyper;
	private BoxSetting boxSetting_NSGA;
	
	
	public Button bottoneAdd;
	public SchermataSecondaria s;

	public SchermataPrincipale() {
		super();

	}

	public void setFocus() {
	}

	@Override
	public void createPartControl(Composite parent) {
		
		algorithm = new ArrayList<String>();
		criterion = new ArrayList<String>();
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createForm(parent);
		
		layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		form.getBody().setLayout(layout);

		GenerateTabFolder();

		Composite composite = new Composite(form.getBody(), SWT.NULL);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;
		composite.setLayout(gridLayout);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		Label label = new Label(composite, SWT.NONE);
		label.setText("Run");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		scaleRun = new Scale(composite, SWT.NULL);
		scaleRun.setMaximum(30);
		scaleRun.setMinimum(1);
		scaleRun.setPageIncrement(1);
		scaleRun.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 5, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Problem");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		problem = new Combo(composite, SWT.NULL);
		problem.add("Test Case Minimization");
		problem.add("Test Case Selection");
		problem.add("Test Case Prioritization");
		problem.select(0);
		problem.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Algorithm");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		Button button = new Button(composite, SWT.CHECK);
		button.setText("Greedy");
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (algorithm.indexOf("Greedy") == -1)
					algorithm.add("Greedy");
				else
					algorithm.remove("Greedy");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Criterion");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		button = new Button(composite, SWT.CHECK);
		button.setText("Statement Coverage");
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (criterion.indexOf("StatementCoverage") == -1)
					criterion.add("StatementCoverage");
				else
					criterion.remove("StatementCoverage");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		button = new Button(composite, SWT.CHECK);
		button.setText("Additional Greedy");
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (algorithm.indexOf("AdditionalGreedy") == -1)
					algorithm.add("AdditionalGreedy");
				else
					algorithm.remove("AdditionalGreedy");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		button = new Button(composite, SWT.CHECK);
		button.setText("Branch Coveragey");
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (criterion.indexOf("BranchCoveragey") == -1)
					criterion.add("BranchCoveragey");
				else
					criterion.remove("BranchCoveragey");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		button = new Button(composite, SWT.CHECK);
		button.setText("Hypervolume Genetic Algoritm");
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (algorithm.indexOf("HypervolumeGeneticAlgoritm") == -1)
					algorithm.add("HypervolumeGeneticAlgoritm");
				else
					algorithm.remove("HypervolumeGeneticAlgoritm");
			}
		});

		button = new Button(composite, SWT.NULL);
		button.setImage(getImage("setting.png"));
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setBoxSetting_Hyper(new BoxSetting(form, layout));
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		button = new Button(composite, SWT.CHECK);
		button.setText("Past Faults");
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (criterion.indexOf("PastFaults") == -1)
					criterion.add("PastFaults");
				else
					criterion.remove("PastFaults");
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		button = new Button(composite, SWT.CHECK);
		button.setText("NSGA-II");
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (algorithm.indexOf("NSGA-II") == -1)
					algorithm.add("NSGA-II");
				else
					algorithm.remove("NSGA-II");
			}
		});

		button = new Button(composite, SWT.NULL);
		button.setImage(getImage("setting.png"));
		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setBoxSetting_NSGA(new BoxSetting(form, layout));
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		button = new Button(composite, SWT.PUSH);
		button.setText("Run experiment");
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 6, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/* HO MESSO TRA COMMENTI PER ESEGUIRE LE VARIE PROVE 
				//controlli
				if((algorithm.size()>0)&&(criterion.size()>0)) {
					if((!projectJar.getText().equals("search"))&&(!auxiliaryFolder.getText().equals("search"))&&(!testCaseFolder.getText().equals("search"))&&(!outputFolder.getText().equals("search"))) {
						MessageDialog.openConfirm(form.getShell(), "Confirm", "Please confirm");
						Task task=new Task();
					}
					else if(radios.size()>0) {
						MessageDialog.openConfirm(form.getShell(), "Confirm", "Please confirm");
						Task task=new Task();
					}
					else {
						MessageDialog.openError(form.getShell(), "Error", "Error controlla se hai inserito i path");
					}
					
					
				}
				else {
					MessageDialog.openError(form.getShell(), "Error", "Error controlla se hai impostato gli algoritmi o criterion");
				}
				*/
				
				Task task=new Task();
				
				
			}
		});
		
	}

	public void GenerateTabFolder() {
		tabFolder = new TabFolder(form.getBody(), SWT.NULL);
		tab1 = new TabItem(tabFolder, SWT.NONE);
		tab1.setText("Principal");
		tab2 = new TabItem(tabFolder, SWT.NONE);
		tab2.setText("Other Matrices");

		Composite composite = new Composite(tabFolder, SWT.NULL);

		GridLayout gridLayout = new GridLayout(6, true);
		gridLayout.numColumns = 6;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;

		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Project jar");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GenerateFileDialog(composite, 5);

		label = new Label(composite, SWT.NONE);
		label.setText("Auxiliary folder");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GenerateAuxiliaryFolderDirectoryDialog(composite, 5);

		label = new Label(composite, SWT.NONE);
		label.setText("Test cases folder");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GenerateOutputFolderDirectoryDialog(composite, 5);

		label = new Label(composite, SWT.NONE);
		label.setText("Output Folder");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GenerateTestCaseFolderDirectoryDialog(composite, 5);

		tab1.setControl(composite);

		setProjectJarMatrice("");
		
		composite = new Composite(tabFolder, SWT.NULL);

		gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;
		composite.setLayout(gridLayout);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("Project jar");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		GenerateFileDialogCsv(composite, 3);

		label = new Label(composite, SWT.NONE);
		label.setText("Type");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		type = new Combo(composite, SWT.NULL);
		type.add("Statement Coverage");
		type.add("Branch Coverage");
		type.add("Past Faults");
		type.add("Costs' Array");
		type.add("Faults' Matrix");
		type.select(0);
		type.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 4, 1));

		Button buttonPiu = new Button(composite, SWT.NULL);
		buttonPiu.setText("+");
		buttonPiu.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		Button buttonMeno = new Button(composite, SWT.NULL);
		buttonMeno.setText("-");
		buttonMeno.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		radios = new ArrayList<Button>();
		comp = new Composite(composite, SWT.FILL);
		comp.setLayout(layout);
		comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		buttonPiu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				bottoneAdd = new Button(comp, SWT.RADIO);
				bottoneAdd.setText(projectJar.getText());
				bottoneAdd.setBounds(10, 5, 75, 30);
				bottoneAdd.setVisible(true);
				comp.pack();
				radios.add(bottoneAdd);
			
				bottoneAdd.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						butto = (Button) e.getSource();
						setProjectJarMatrice(projectJar.getText());
						
						
					}
				});

			}
		});

		buttonMeno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				radios.remove(butto);
				butto.dispose();
				comp.pack();

			}
		});
		tab2.setControl(composite);
		
	

	}

	public static Image getImage(String imageName) {
		ImageData source = new ImageData(SchermataPrincipale.class.getResourceAsStream(imageName));
		ImageData mask = source.getTransparencyMask();
		Image image = new Image(null, source, mask);
		return image;
	}

	public void GenerateAuxiliaryFolderDirectoryDialog(Composite composite, int colonne) {

		auxiliaryFolder = new Text(composite, SWT.BORDER);
		auxiliaryFolder.setText("search");
		Button button = new Button(composite, SWT.NULL);
		button.setImage(getImage("folder.png"));
		button.setText("Browse...");

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dialog = new DirectoryDialog(composite.getShell());
				String folder = dialog.open();
				int length = folder.length() - 1;

				if (!(("" + folder.charAt(length - 3)).equals(".")))

					auxiliaryFolder.setText("" + folder);

				else
					auxiliaryFolder.setText("file non valido");
			}
		});

		auxiliaryFolder.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, colonne - 1, 1));

		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

	}

	public void GenerateOutputFolderDirectoryDialog(Composite composite, int colonne) {

		outputFolder = new Text(composite, SWT.BORDER);
		outputFolder.setText("search");
		Button button = new Button(composite, SWT.NULL);
		button.setImage(getImage("folder.png"));
		button.setText("Browse...");

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dialog = new DirectoryDialog(composite.getShell());
				String folder = dialog.open();
				int length = folder.length() - 1;

				if (!(("" + folder.charAt(length - 3)).equals(".")))

					outputFolder.setText(folder);

				else
					outputFolder.setText("file non valido");
			}
		});

		outputFolder.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, colonne - 1, 1));

		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

	}

	public void GenerateTestCaseFolderDirectoryDialog(Composite composite, int colonne) {

		testCaseFolder = new Text(composite, SWT.BORDER);
		testCaseFolder.setText("search");
		Button button = new Button(composite, SWT.NULL);
		button.setImage(getImage("folder.png"));
		button.setText("Browse...");

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dialog = new DirectoryDialog(composite.getShell());
				String folder = dialog.open();
				int length = folder.length() - 1;

				if (!(("" + folder.charAt(length - 3)).equals(".")))

					testCaseFolder.setText(folder);

				else
					testCaseFolder.setText("file non valido");
			}
		});

		testCaseFolder.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, colonne - 1, 1));

		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

	}

	public void GenerateFileDialog(Composite composite, int colonne) {

		projectJar = new Text(composite, SWT.BORDER);
		projectJar.setText("search");
		Button button = new Button(composite, SWT.NULL);
		button.setImage(getImage("folder.png"));
		button.setText("Browse...");

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dialog = new FileDialog(composite.getShell());
				String folder = dialog.open();
				int length = folder.length() - 1;

				String tipo = "";
				for (int i = length - 3; i < length; i++) {
					tipo = tipo + folder.charAt(length);
				}
				if (tipo.equals(".jar"))
					projectJar.setText(folder);

				else
					projectJar.setText("file non valido");
			}
		});

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;

		projectJar.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, colonne - 1, 1));

		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

	}

	public void GenerateFileDialogCsv(Composite composite, int colonne) {

		projectJar = new Text(composite, SWT.BORDER);
		projectJar.setText("search");
		Button button = new Button(composite, SWT.NULL);
		button.setImage(getImage("folder.png"));
		button.setText("Browse...");

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dialog = new FileDialog(composite.getShell());
				String folder = dialog.open();
				int length = folder.length() - 1;

				String tipo = "";
				for (int i = length - 3; i < length; i++) {
					tipo = tipo + folder.charAt(length);
				}
				if (tipo.equals(".csv"))
					projectJar.setText(folder);

				else
					projectJar.setText("file non valido");
			}
		});

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;

		projectJar.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, colonne - 1, 1));

		button.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
	}

	public Scale getScale() {
		return scaleRun;
	}

	public void setScale(Scale scale) {
		this.scaleRun = scale;
	}

	private Text getAuxiliaryFolder() {
		return auxiliaryFolder;
	}

	private Text getTestCaseFolder() {
		return testCaseFolder;
	}

	private Text getOutputFolder() {
		return outputFolder;
	}

	private Text getProjectJar() {
		return projectJar;
	}

	private Combo getProblem() {
		return problem;
	}

	private Combo getType() {
		return type;
	}

	public String getProjectJarMatrice() {
		return projectJarMatrice;
	}

	public void setProjectJarMatrice(String projectJarMatrice) {
		this.projectJarMatrice = projectJarMatrice;
	}

	public ArrayList<String> getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(ArrayList<String> algorithm) {
		this.algorithm = algorithm;
	}

	public ArrayList<String> getCriterion() {
		return criterion;
	}

	public void setCriterion(ArrayList<String> criterion) {
		this.criterion = criterion;
	}

	public BoxSetting getBoxSetting_Hyper() {
		return boxSetting_Hyper;
	}

	public void setBoxSetting_Hyper(BoxSetting boxSetting_Hyper) {
		this.boxSetting_Hyper = boxSetting_Hyper;
	}

	public BoxSetting getBoxSetting_NSGA() {
		return boxSetting_NSGA;
	}

	public void setBoxSetting_NSGA(BoxSetting boxSetting_NSGA) {
		this.boxSetting_NSGA = boxSetting_NSGA;
	}

}
