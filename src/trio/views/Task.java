package trio.views;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class Task {
	
	public Task() {
		System.out.println("prima del thread thread.....");
		task();
	}

	public void task() {
		Job job = new Job("My First Job") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					TimeUnit.MILLISECONDS.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Hello World (from a background job)");
				return Status.OK_STATUS;
			}
		};

		job.schedule(); // start as soon as possible
		
		//mostra la schermata 2
		try {
			SchermataPrincipale schermataPrincipale= (SchermataPrincipale) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("trio.views.SchermataPrincipale");
			System.out.println(schermataPrincipale.getAlgorithm().get(0));
			
			
			
			SchermataSecondaria schermataSecondaria= (SchermataSecondaria) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("trio.views.SchermataSecondaria");
			schermataSecondaria.setGGAMeanAverage(schermataPrincipale.getAlgorithm().get(0));
			
			ArrayList<String> criterion=schermataPrincipale.getCriterion();
			for(int i=0;i<criterion.size();i++) {
				if(criterion.get(i).equals("StatementCoverage"))
					schermataSecondaria.getCriterionStatementCoverage().setSelection(true);
				if(criterion.get(i).equals("BranchCoverage"))
					schermataSecondaria.getCriterionBranchCoverage().setSelection(true);
				if(criterion.get(i).equals("PastFaults"))
					schermataSecondaria.getCriterionPastFaults().setSelection(true);
			}
			
			schermataSecondaria.getRunScale().setSelection(schermataPrincipale.getScale().getSelection());
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		
	
			
			 
	
		/*
		 mostra la seconda schermata
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("trio.views.SchermataSecondaria");
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		elimina la prima schermata
		 IWorkbenchPage wp=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
		 //Find desired view : 
		 IViewPart myView=wp.findView("trio.views.SchermataPrincipale"); 
		 //Hide the view : 
		 wp.hideView(myView);
		 */
	}

}
