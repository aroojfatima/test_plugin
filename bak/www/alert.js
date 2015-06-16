var alertTest = function() {};

alertTest.prototype.alertIt = function(success, fail) {
    cordova.exec(success, fail, "Alert","alert", ["Test Title", "Hello", "Button Label"]);
};

var alertVar = new alertTest();
module.exports = alertVar;