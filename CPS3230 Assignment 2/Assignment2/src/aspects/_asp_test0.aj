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
_cls_inst._call(thisJoinPoint.getSignature().toString(), 148/*logIn*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 148/*logIn*/);
}
}
before ( int eventLogType) : (call(* *.setEventLogType(..)) && args(eventLogType) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst.eventLogType = eventLogType;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 154/*logType*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 154/*logType*/);
}
}
before () : (call(* *.deleteAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 158/*deleteAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 158/*deleteAlerts*/);
}
}
before () : (call(* *.viewAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 142/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 142/*viewAlerts*/);
}
}
before ( int status) : (call(* *.setStatus(..)) && args(status) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst.status = status;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 152/*setStatus*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 152/*setStatus*/);
}
}
before () : (call(* *.validLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 140/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 140/*validLogin*/);
}
}
before () : (call(* *.createAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 156/*createAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 156/*createAlert*/);
}
}
before () : (call(* *.invalidLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 146/*invalidLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 146/*invalidLogin*/);
}
}
before () : (call(* *.logout(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 144/*logout*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 144/*logout*/);
}
}
before ( boolean empty) : (call(* *.setEmpty(..)) && args(empty) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_test0.lock){

_cls_test0 _cls_inst = _cls_test0._get_cls_test0_inst();
_cls_inst.empty = empty;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 150/*setEmpty*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 150/*setEmpty*/);
}
}
}