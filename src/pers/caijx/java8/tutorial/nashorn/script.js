/**
 * 
 * Created by Administrator on 2017/8/25/025.
 */

var fun1 = function (name) {
    print('Hi there from Javascript, ' + name);
    return 'greetings from javascript';
}

var fun2 = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
}