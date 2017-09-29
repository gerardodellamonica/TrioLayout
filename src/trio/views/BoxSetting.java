package trio.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;

public class BoxSetting {
	
	//Crossover
	private Combo OP_Crossover;
	private Text Prob_Crossover;
	
	//Muntain
	private Combo OP_Muntain;
	private Text Prob_Muntain;
	
	//Selection
	private Combo OP_Selection;
	
	//OtherSetting
	private Text NumberCores_OtherSetting;
	private Text PopulationSize_OtherSetting;
	private Text MaxEvaluation_OtherSetting;
	
	public BoxSetting(Form form, Layout layout) {
		OP_Crossover=null;
		Prob_Crossover=null;
		OP_Muntain=null;
		Prob_Muntain=null;
		OP_Selection=null;
		NumberCores_OtherSetting=null;
		PopulationSize_OtherSetting=null;
		MaxEvaluation_OtherSetting=null;
		
		int style = SWT.ICON_INFORMATION | SWT.OK;
		final Shell dialog = new Shell(form.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		dialog.setLayout(new GridLayout(1,true));
		dialog.setSize(500, 500);

		TabFolder tabFolder = new TabFolder(dialog, SWT.NULL);
		tabFolder.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, false, 1, 1));
		
		ItemCrossover(tabFolder);
		ItemMutation(tabFolder);
		ItemSelection(tabFolder);
		ItemOtherSetting(tabFolder);
		
		
		Button button = new Button(dialog, SWT.PUSH);
		button.setText("Confirm");
		button.setSize(200, 50);
		button.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, false, 1, 1) );
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dialog.close();
			}
		});
		
		dialog.open();
	}
	public void ItemCrossover(TabFolder tabFolder) {
		TabItem ItemCrossover = new TabItem(tabFolder, SWT.NONE);
		ItemCrossover.setText("Crossover");

		Composite composite = new Composite(tabFolder, SWT.NULL);

		GridLayout gridLayout = new GridLayout(2, true);
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;

		composite.setLayout(gridLayout);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label = new Label(composite, SWT.NULL);
		label.setText("Operator");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		OP_Crossover=new Combo(composite,SWT.NULL);
		OP_Crossover.add("PMXCrossover");
		OP_Crossover.select(0);
		OP_Crossover.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		
		label = new Label(composite, SWT.NULL);
		label.setText("Probability");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		Prob_Crossover = new Text(composite, SWT.BORDER);
		Prob_Crossover.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		
		ItemCrossover.setControl(composite);
		
		
		
	}
	public void ItemMutation(TabFolder tabFolder) {
		TabItem ItemMutation = new TabItem(tabFolder, SWT.NONE);

		ItemMutation.setText("Mutation");

		Composite composite = new Composite(tabFolder, SWT.NULL);

		GridLayout gridLayout = new GridLayout(2, true);
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;

		composite.setLayout(gridLayout);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label = new Label(composite, SWT.NULL);
		label.setText("Operator");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		OP_Muntain=new Combo(composite,SWT.NULL);
		OP_Muntain.add("PermutationSwapMutation");
		OP_Muntain.select(0);
		OP_Muntain.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		
		label = new Label(composite, SWT.NULL);
		label.setText("Probability");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		Prob_Muntain = new Text(composite, SWT.BORDER);
		Prob_Muntain.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		
		ItemMutation.setControl(composite);
		
	}

	public void ItemSelection(TabFolder tabFolder) {
		TabItem ItemSelection = new TabItem(tabFolder, SWT.NONE);

		ItemSelection.setText("Selection");

		Composite composite = new Composite(tabFolder, SWT.NULL);

		GridLayout gridLayout = new GridLayout(2, true);
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;

		composite.setLayout(gridLayout);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label = new Label(composite, SWT.NULL);
		label.setText("Operator");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		OP_Selection=new Combo(composite,SWT.NULL);
		OP_Selection.add("BinaryTurnamentSelection");
		OP_Selection.select(0);
		OP_Selection.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		
		ItemSelection.setControl(composite);

	}

	public void ItemOtherSetting(TabFolder tabFolder) {
		TabItem ItemOtherSetting = new TabItem(tabFolder, SWT.NONE);
		ItemOtherSetting.setText("Other Setting");
		Composite composite = new Composite(tabFolder, SWT.NULL);

		GridLayout gridLayout = new GridLayout(2, true);
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.makeColumnsEqualWidth = true;

		composite.setLayout(gridLayout);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label = new Label(composite, SWT.NULL);
		label.setText("Number of Cores");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		NumberCores_OtherSetting = new Text(composite, SWT.BORDER);
		NumberCores_OtherSetting.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NULL);
		label.setText("Population Size");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		PopulationSize_OtherSetting = new Text(composite, SWT.BORDER);
		PopulationSize_OtherSetting.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		label = new Label(composite, SWT.NULL);
		label.setText("Max Evaluations");
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

		MaxEvaluation_OtherSetting = new Text(composite, SWT.BORDER);
		MaxEvaluation_OtherSetting.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		ItemOtherSetting.setControl(composite);
	}
	public Combo getOP_Crossover() {
		return OP_Crossover;
	}
	public void setOP_Crossover(Combo oP_Crossover) {
		OP_Crossover = oP_Crossover;
	}
	public Text getProb_Crossover() {
		return Prob_Crossover;
	}
	public void setProb_Crossover(Text prob_Crossover) {
		Prob_Crossover = prob_Crossover;
	}
	public Combo getOP_Muntain() {
		return OP_Muntain;
	}
	public void setOP_Muntain(Combo oP_Muntain) {
		OP_Muntain = oP_Muntain;
	}
	public Text getProb_Muntain() {
		return Prob_Muntain;
	}
	public void setProb_Muntain(Text prob_Muntain) {
		Prob_Muntain = prob_Muntain;
	}
	public Combo getOP_Selection() {
		return OP_Selection;
	}
	public void setOP_Selection(Combo oP_Selection) {
		OP_Selection = oP_Selection;
	}

}
