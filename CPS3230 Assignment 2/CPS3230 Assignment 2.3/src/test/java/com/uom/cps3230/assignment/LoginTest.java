package com.uom.cps3230.assignment;

import com.uom.cps3230.assignment.enums.AlertsStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class LoginTest implements FsmModel {

    private Alerts systemUnderTest = new Alerts();

    //State Variables
    private AlertsStates modelAlert= AlertsStates.STARTING;
    private boolean loggedIn = false;
    private int eventLogType = 10;




    public Object getState() {
        return modelAlert;
    }

    public boolean getStatus() {
        return loggedIn;
    }

    public void reset(boolean var1) {
        if (var1) {
            systemUnderTest = new Alerts();
        }
        modelAlert = AlertsStates.STARTING;
        eventLogType = 10;
        loggedIn = false;
    }

    // BadLogin
    public boolean invalidLoginGuard(){ return getState().equals(AlertsStates.STARTING); }
    public @Action void invalidLogin(){
        // Updating SUT
        systemUnderTest.invalidLogin();

        //Updating model
        modelAlert = AlertsStates.BADLOGIN;
        loggedIn = false;

        assertEquals("INVALIDLOGIN: Login status does not match",loggedIn, systemUnderTest.isLoggedIn());

    }


    // Login
    public boolean loginGuard(){ return getState().equals(AlertsStates.STARTING) || getState().equals(AlertsStates.BADLOGIN); }
    public @Action void login(){
        //Updating SUT
        systemUnderTest.login();

        //Updating model
        modelAlert = AlertsStates.LOGGEDIN;
        loggedIn = true;

        //Checking correspondence between model and SUT.
        assertEquals("LOGIN - Login status do not match", loggedIn, systemUnderTest.isLoggedIn());

    }

    // Logout
    public boolean logoutGuard() {
        return getState().equals(AlertsStates.LOGGEDIN) || getState().equals((AlertsStates.ALERTS));
    }
    public @Action void logout() {
        //Updating SUT
        systemUnderTest.logout();

        //Updating model
        modelAlert = AlertsStates.STARTING;
        loggedIn = false;
        eventLogType = 6;


        //Checking correspondence between model and SUT.
        assertEquals("LOGOUT - logout status does not match", loggedIn, systemUnderTest.isLoggedIn());
    }


    // View Alerts
    public boolean viewAlertsGuard() {
        return getStatus() && getState().equals(AlertsStates.LOGGEDIN);
    }
    public @Action void viewAlerts() throws IOException {
        //Updating SUT
        systemUnderTest.viewAlerts();

        //Updating model
        modelAlert = AlertsStates.ALERTS;
        eventLogType = 7;

        assertEquals("VIEWALERTS - Event log types do not match", eventLogType, systemUnderTest.getEventLogType());
    }

    @Test
    public void AlertModelRunner() {
        final GreedyTester tester = new GreedyTester(new LoginTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
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
