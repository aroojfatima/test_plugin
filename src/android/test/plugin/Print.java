package test.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import org.apache.cordova.PluginResult;
import com.brother.ptouch.sdk.Printer; 
import com.brother.ptouch.sdk.PrinterInfo; 
import com.brother.ptouch.sdk.PrinterStatus;

/**
 * This class echoes a string called from JavaScript.
 */
public class Print extends CordovaPlugin {
	public CallbackContext printCallback;
	private ArrayList<String> mFiles = new ArrayList<String>();
	protected SharedPreferences sharedPreferences;
	PrinterModelInfo modelInfo = new PrinterModelInfo();
	protected String sourceData;
	private Context context;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callbackContext.success("Hello World!" + action);
        return true;
    }    
}
