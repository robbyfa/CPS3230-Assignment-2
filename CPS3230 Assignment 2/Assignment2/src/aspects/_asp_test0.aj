package aspects;

import main.runner;


import larva.*;
public aspect _asp_test0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_test0.initialize();
}
}
before ( boolean loggedIn) : (call(* *.setLogged(..)) && args(loggedIn) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst.loggedIn = loggedIn;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 8/*logIn*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 8/*logIn*/);
}
}
before ( int eventLogType) : (call(* *.setEventLogType(..)) && args(eventLogType) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst.eventLogType = eventLogType;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 14/*logType*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 14/*logType*/);
}
}
after () returning () : (call(* *.deleteAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 18/*deleteAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 18/*deleteAlerts*/);
}
}
after () returning () : (call(* *.viewAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 2/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 2/*viewAlerts*/);
}
}
before ( int status) : (call(* *.setStatus(..)) && args(status) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst.status = status;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 12/*setStatus*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 12/*setStatus*/);
}
}
after () returning () : (call(* *.validLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 0/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 0/*validLogin*/);
}
}
after () returning () : (call(* *.createAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 16/*createAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 16/*createAlert*/);
}
}
after () returning () : (call(* *.invalidLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 6/*invalidLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 6/*invalidLogin*/);
}
}
after () returning () : (call(* *.logout(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 4/*logout*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 4/*logout*/);
}
}
before ( boolean empty) : (call(* *.setEmpty(..)) && args(empty) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst.empty = empty;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 10/*setEmpty*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 10/*setEmpty*/);
}
}
}