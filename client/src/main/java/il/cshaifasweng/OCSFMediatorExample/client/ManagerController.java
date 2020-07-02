package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mdsplyexamsbtn;

    @FXML
    private Button mdsplyquestionbtn;

    @FXML
    private Button mcnfrmaddtimebtn;

    @FXML
    private Button mdsplyexamrsltbtn;

    @FXML
    private Button msignoutbtn;

    @FXML
    private Text managerintr;  

    @FXML
    private Label addingtimerequestlabel;
    
    @FXML
    void mcnfrmaddtimeac(ActionEvent event) throws IOException {
    	
    	App.getInstance().bringTimeRequestExam();

    }

    @FXML
    void mdsplyexamrsltac(ActionEvent event) throws IOException {
    	App.getInstance().AllExamstoShowResultsManager();
    }

    @FXML
    void mdsplyexamsac(ActionEvent event) throws IOException {
    	App.getInstance().BringingallCoursedformanager();
    }

    @FXML
    void mdsplyquestionac(ActionEvent event) throws IOException {
    	App.getInstance().allsubjectforManager();
    }

    @FXML
    void msignoutac(ActionEvent event) throws IOException {
    	App.getInstance().LogOut();
    	App.getInstance().showBackToPrimaryView();
    }
    
    public Boolean checkAddtimeRequest() throws IOException
    {
    	return App.getInstance().checkingAddtimeRequest();
    }

    @FXML
    void initialize() {
    	
    	addingtimerequestlabel.setVisible(false);
    	
        assert mdsplyexamsbtn != null : "fx:id=\"mdsplyexamsbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert mdsplyquestionbtn != null : "fx:id=\"mdsplyquestionbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert mcnfrmaddtimebtn != null : "fx:id=\"mcnfrmaddtimebtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert mdsplyexamrsltbtn != null : "fx:id=\"mdsplyexamrsltbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert msignoutbtn != null : "fx:id=\"msignoutbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert managerintr != null : "fx:id=\"managerintr\" was not injected: check your FXML file 'manager.fxml'.";
        assert addingtimerequestlabel != null : "fx:id=\"addingtimerequestlabel\" was not injected: check your FXML file 'manager.fxml'.";


        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                	while(!checkAddtimeRequest()) {
                		
                		System.out.println("THREAAAAAAAAAD");
                		Thread.sleep(10000);
                	}
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	if(true) {
            		addingtimerequestlabel.setVisible(true);
				}
            }
        });
        new Thread(sleeper).start();
    
    }
}
