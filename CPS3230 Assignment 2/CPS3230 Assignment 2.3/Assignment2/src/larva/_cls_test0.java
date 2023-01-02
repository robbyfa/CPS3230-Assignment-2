package larva;


import main.runner;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_test0 implements _callable{

public static PrintWriter pw; 
public static _cls_test0 root;

public static LinkedHashMap<_cls_test0,_cls_test0> _cls_test0_instances = new LinkedHashMap<_cls_test0,_cls_test0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\rober\\workspace\\Assignment2/src/output_test.txt");

root = new _cls_test0();
_cls_test0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_test0 parent; //to remain null - this class does not have a parent!
public static int eventLogType;
public static boolean loggedIn;
public static boolean empty;
public static int status;
int no_automata = 2;
 public int alerts =0 ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_test0() {
}

public void initialisation() {
}

public static _cls_test0 _get_cls_test0_inst() { synchronized(_cls_test0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_test0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_test0_instances){
_performLogic_loginProperty(_info, _event);
_performLogic_alertsProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_test0[] a = new _cls_test0[1];
synchronized(_cls_test0_instances){
a = _cls_test0_instances.keySet().toArray(a);}
for (_cls_test0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_test0_instances){
_cls_test0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_loginProperty = 59;

public void _performLogic_loginProperty(String _info, int... _event) {

_cls_test0.pw.println("[loginProperty]AUTOMATON::> loginProperty("+") STATE::>"+ _string_loginProperty(_state_id_loginProperty, 0));
_cls_test0.pw.flush();

if (0==1){}
else if (_state_id_loginProperty==58){
		if (1==0){}
		else if ((_occurredEvent(_event,144/*logout*/)) && (loggedIn ==false )){
		_cls_test0.pw .println ("Logged out \nEvent log type: "+eventLogType );

		_state_id_loginProperty = 59;//moving to state starting
		_goto_loginProperty(_info);
		}
}
else if (_state_id_loginProperty==59){
		if (1==0){}
		else if ((_occurredEvent(_event,146/*invalidLogin*/)) && (loggedIn ==false )){
		_cls_test0.pw .println ("Bad Login");

		_state_id_loginProperty = 56;//moving to state invalidLogin
		_goto_loginProperty(_info);
		}
		else if ((_occurredEvent(_event,140/*validLogin*/)) && (loggedIn ==true )){
		_cls_test0.pw .println ("Login Page. \nEvent log type: "+eventLogType +"\nEmpty: "+empty );

		_state_id_loginProperty = 57;//moving to state login
		_goto_loginProperty(_info);
		}
		else if ((_occurredEvent(_event,142/*viewAlerts*/)) && (loggedIn ==true )){
		_cls_test0.pw .println ("Login Page \nEvent log type: "+eventLogType );

		_state_id_loginProperty = 57;//moving to state login
		_goto_loginProperty(_info);
		}
		else if ((_occurredEvent(_event,142/*viewAlerts*/)) && (loggedIn ==true )){
		_cls_test0.pw .println ("Viewing Alerts. Event log type: "+eventLogType );

		_state_id_loginProperty = 58;//moving to state alerts
		_goto_loginProperty(_info);
		}
}
else if (_state_id_loginProperty==56){
		if (1==0){}
		else if ((_occurredEvent(_event,140/*validLogin*/)) && (loggedIn ==true )){
		_cls_test0.pw .println ("Login Page. \nEvent log type: "+eventLogType +"\nEmpty: "+empty );

		_state_id_loginProperty = 57;//moving to state login
		_goto_loginProperty(_info);
		}
}
else if (_state_id_loginProperty==57){
		if (1==0){}
		else if ((_occurredEvent(_event,142/*viewAlerts*/)) && (loggedIn ==true )){
		_cls_test0.pw .println ("Alerts Page \nEvent log type: "+eventLogType );

		_state_id_loginProperty = 58;//moving to state alerts
		_goto_loginProperty(_info);
		}
		else if ((_occurredEvent(_event,144/*logout*/)) && (loggedIn ==false )){
		_cls_test0.pw .println ("Logged out \nEvent log type: "+eventLogType );

		_state_id_loginProperty = 59;//moving to state starting
		_goto_loginProperty(_info);
		}
}
}

public void _goto_loginProperty(String _info){
_cls_test0.pw.println("[loginProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_loginProperty(_state_id_loginProperty, 1));
_cls_test0.pw.flush();
}

public String _string_loginProperty(int _state_id, int _mode){
switch(_state_id){
case 58: if (_mode == 0) return "alerts"; else return "alerts";
case 56: if (_mode == 0) return "invalidLogin"; else return "!!!SYSTEM REACHED BAD STATE!!! invalidLogin "+new _BadStateExceptiontest().toString()+" ";
case 57: if (_mode == 0) return "login"; else return "login";
case 59: if (_mode == 0) return "starting"; else return "starting";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}
int _state_id_alertsProperty = 63;

public void _performLogic_alertsProperty(String _info, int... _event) {

_cls_test0.pw.println("[alertsProperty]AUTOMATON::> alertsProperty("+") STATE::>"+ _string_alertsProperty(_state_id_alertsProperty, 0));
_cls_test0.pw.flush();

if (0==1){}
else if (_state_id_alertsProperty==60){
		if (1==0){}
		else if ((_occurredEvent(_event,156/*createAlert*/)) && (empty ==false &&status ==201 )){
		alerts ++;
_cls_test0.pw .println ("Alert created. Number of alerts: "+alerts +". Event log type: "+eventLogType +"\nEmpty: "+empty );

		_state_id_alertsProperty = 61;//moving to state notEmpty
		_goto_alertsProperty(_info);
		}
}
else if (_state_id_alertsProperty==61){
		if (1==0){}
		else if ((_occurredEvent(_event,156/*createAlert*/)) && (alerts <5 &&status ==201 )){
		alerts ++;
_cls_test0.pw .println ("Alert created. Number of alerts: "+alerts +"\nStatus: "+status +"\nEventLogType: "+eventLogType );

		_state_id_alertsProperty = 61;//moving to state notEmpty
		_goto_alertsProperty(_info);
		}
		else if ((_occurredEvent(_event,156/*createAlert*/)) && (alerts >=5 &&status ==201 )){
		alerts ++;
_cls_test0.pw .println ("Alerts full. Number of alerts: "+alerts +"\nStatus: "+status +"\nEventLogType: "+eventLogType );

		_state_id_alertsProperty = 62;//moving to state full
		_goto_alertsProperty(_info);
		}
		else if ((_occurredEvent(_event,158/*deleteAlerts*/)) && (empty ==true &&status ==200 )){
		alerts =0 ;
_cls_test0.pw .println ("Alerts deleted. Event log type: "+eventLogType +"\nEmpty: "+empty );

		_state_id_alertsProperty = 63;//moving to state empty
		_goto_alertsProperty(_info);
		}
		else if ((_occurredEvent(_event,156/*createAlert*/)) && (status ==400 )){
		_cls_test0.pw .println ("Bad upload. Number of alerts: "+alerts +"\nStatus: "+status );

		_state_id_alertsProperty = 60;//moving to state badUpload
		_goto_alertsProperty(_info);
		}
}
else if (_state_id_alertsProperty==63){
		if (1==0){}
		else if ((_occurredEvent(_event,156/*createAlert*/)) && (empty ==false &&status ==201 )){
		alerts =0 ;
alerts ++;
_cls_test0.pw .println ("Alert created. Number of alerts: "+alerts +". Event log type: "+eventLogType +"\nEmpty: "+empty );

		_state_id_alertsProperty = 61;//moving to state notEmpty
		_goto_alertsProperty(_info);
		}
		else if ((_occurredEvent(_event,156/*createAlert*/)) && (status ==400 )){
		_cls_test0.pw .println ("Bad upload. Status: "+status );

		_state_id_alertsProperty = 60;//moving to state badUpload
		_goto_alertsProperty(_info);
		}
}
else if (_state_id_alertsProperty==62){
		if (1==0){}
		else if ((_occurredEvent(_event,158/*deleteAlerts*/)) && (empty ==true &&status ==200 )){
		alerts =0 ;
_cls_test0.pw .println ("Alerts deleted. Event log type: "+eventLogType +"\nEmpty: "+empty );

		_state_id_alertsProperty = 63;//moving to state empty
		_goto_alertsProperty(_info);
		}
}
}

public void _goto_alertsProperty(String _info){
_cls_test0.pw.println("[alertsProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_alertsProperty(_state_id_alertsProperty, 1));
_cls_test0.pw.flush();
}

public String _string_alertsProperty(int _state_id, int _mode){
switch(_state_id){
case 60: if (_mode == 0) return "badUpload"; else return "!!!SYSTEM REACHED BAD STATE!!! badUpload "+new _BadStateExceptiontest().toString()+" ";
case 61: if (_mode == 0) return "notEmpty"; else return "notEmpty";
case 62: if (_mode == 0) return "full"; else return "full";
case 63: if (_mode == 0) return "empty"; else return "empty";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}