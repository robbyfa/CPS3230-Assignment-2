package com.uom.cps3230.assignment;

import com.uom.cps3230.assignment.enums.AlertsStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AlertsTest implements FsmModel {

    private Alerts systemUnderTest = new Alerts();

    //State Variables
    private AlertsStates modelAlert= AlertsStates.STARTING;
    private boolean loggedIn = false, empty = true, full = false;
    private int eventLogType = 10;
    private int noOfAlerts = 0;
    private int status;
    private boolean goodLogin = true;


    public Object getState() {
        return modelAlert;
    }


    public void reset(boolean var1) {
        if (var1) {
            systemUnderTest = new Alerts();
        }
        modelAlert = AlertsStates.EMPTY;
        noOfAlerts = 0;
        eventLogType = 10;
        status = 0;
        empty = true;
        full = false;
    }


    // Create Alert
    public boolean createAlertGuard() {
        return !(getState().equals(AlertsStates.FULL)) && (getState().equals(AlertsStates.EMPTY) || getState().equals(AlertsStates.NOTEMPTY) || getState().equals(AlertsStates.BADUPLOAD));
    }
    public @Action void createAlert() throws IOException {
        //Updating SUT
        systemUnderTest.createAlert(true);

        //Updating model
        if (getState().equals(AlertsStates.EMPTY)) {
            modelAlert = AlertsStates.NOTEMPTY;
            noOfAlerts ++;
            eventLogType = 0;
            status = 201;
            //Checking correspondence between model and SUT.
            // assertEquals("CREATEALERT - Number of alerts do not match", noOfAlerts, systemUnderTest.getNoOfAlerts());
            assertEquals("CREATEALERT - Event log types do not match", eventLogType, systemUnderTest.getEventLogType());
            //  assertEquals("CREATEALERT - Status codes do not match", status, systemUnderTest.getStatus());

        } else if(noOfAlerts<5 && getState().equals(AlertsStates.NOTEMPTY)){
            modelAlert = AlertsStates.NOTEMPTY;
            noOfAlerts++;
            eventLogType = 0;
            status = 201;

            //Checking correspondence between model and SUT.
            // assertEquals("CREATEALERT - Number of alerts do not match", noOfAlerts, systemUnderTest.getNoOfAlerts());
            assertEquals("CREATEALERT - Event log types do not match", eventLogType, systemUnderTest.getEventLogType());
            //   assertEquals("CREATEALERT - Status codes do not match", status, systemUnderTest.getStatus());

        }
        else if(noOfAlerts<5 && getState().equals(AlertsStates.BADUPLOAD)){
            modelAlert = AlertsStates.NOTEMPTY;
            noOfAlerts ++;
            eventLogType = 0;
            status = 201;
            assertEquals("CREATEALERT - Event log types do not match", eventLogType, systemUnderTest.getEventLogType());
            //    assertEquals("CREATEALERT - Status codes do not match", status, systemUnderTest.getStatus());


        }
        else if(noOfAlerts>=5){
            eventLogType = 0;
            modelAlert = AlertsStates.FULL;
            noOfAlerts++;
            full = true;
            //   assertEquals("The SUT's bulb does not match the model's bulb after decreasing the bulb's brightness", noOfAlerts, systemUnderTest.getNoOfAlerts());
            //   assertEquals("The SUT's event log type does not match the model's event log type", eventLogType, systemUnderTest.getEventLogType());
            assertEquals("CREATEALERT - The SUT's full status does not match the model's full status", full, systemUnderTest.isFull());

        }
    }

    // Bad Upload
    public boolean badUploadGuard(){
        return  getState().equals(AlertsStates.EMPTY) || getState().equals(AlertsStates.NOTEMPTY) || getState().equals(AlertsStates.BADUPLOAD);
    }
    public @Action void badUpload() throws IOException {
        // Updating SUT
        systemUnderTest.createAlert(false);

        status = 400;
        modelAlert = AlertsStates.BADUPLOAD;

        assertEquals("BADUPLOAD - Status codes do not match", status, systemUnderTest.getStatus());

    }

    // Fill Alerts
    public boolean fillAlertsGuard(){
        return (getState().equals(AlertsStates.EMPTY) || getState().equals(AlertsStates.NOTEMPTY))&& !getState().equals(AlertsStates.FULL);
    }
    public @Action void fillAlerts() throws IOException {

        //Updating SUT
        systemUnderTest.fillAlerts();

        modelAlert = AlertsStates.FULL;
        noOfAlerts = 5;
        eventLogType = 0;
        status = 201;
        //Checking correspondence between model and SUT.
        //       assertEquals("CREATEALERT - Number of alerts do not match", noOfAlerts, systemUnderTest.getNoOfAlerts());
        assertEquals("CREATEALERT - Event log types do not match", eventLogType, systemUnderTest.getEventLogType());
        //  assertEquals("CREATEALERT - Status codes do not match", status, systemUnderTest.getStatus());



    }


    // Delete Alerts
    public boolean deleteAlertsGuard() {
        return  getState().equals(AlertsStates.EMPTY) || getState().equals(AlertsStates.NOTEMPTY) || getState().equals(AlertsStates.FULL);
    }
    public @Action void deleteAlerts() throws IOException {
        //Updating SUT
        systemUnderTest.deleteAlerts();

        //Updating model
        modelAlert = AlertsStates.EMPTY;
        noOfAlerts = 0;
        eventLogType = 1;
        full = false;
        status = 200;

        //Checking correspondence between model and SUT.
        assertEquals("DELETEALERTS - Number of alerts do not match", noOfAlerts, systemUnderTest.getNoOfAlerts());
        // I don't necessarily have to test this since I never specified that I only need to be in one brightness state at once, but I have opted to do it anyway for completeness.
        // Feel free to NOT test variables which have not been altered in this transition (except if you are explicitly told to do so)
        assertEquals("DELETEALERTS - Full status does not match", full, systemUnderTest.isFull());
        assertEquals("DELETEALERTS - Event log types do not match", eventLogType, systemUnderTest.getEventLogType());
        assertEquals("DELETEALERT - Status codes do not match", status, systemUnderTest.getStatus());

    }

    @Test
    public void AlertsRunner() {
        final GreedyTester tester = new GreedyTester(new AlertsTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(500); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
}
