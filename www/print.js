var PrintPlugin = function() {};

PrintPlugin.prototype.say = function(success, fail) {
    cordova.exec(success, fail, "Print","print", []);
};

var printVar = new PrintPlugin();
module.exports = printVar;