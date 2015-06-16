package test.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class Print extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callbackContext.success("Hello World!" + args[0]);
        return true;
    }    
}
