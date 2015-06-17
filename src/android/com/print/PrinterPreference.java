
package com.print;

import java.util.EventListener;

import android.content.Context;
import android.os.Message;

import com.brother.ptouch.sdk.BluetoothPreference;
import com.brother.ptouch.sdk.PrinterStatus;
import com.printPlugin.R;

public class PrinterPreference extends BasePrint {
    public final static int COMMAND_SEND = 0;
    public final static int COMMAND_RECEIVE = 1;
    private BluetoothPreference btPref;
    private int commandType = 0;
    private Context context;
    private PrinterPreListener listener = null;

    public interface PrinterPreListener extends EventListener {
        public void finish(PrinterStatus status, BluetoothPreference btPre);
    }
 
    public PrinterPreference(Context context, MsgHandle mHandle, MsgDialog mDialog) {
        super(context, mHandle, mDialog);
        this.context = context;
    }

    /**
     * Updating the printer settings
     * The results are reported in listener
     * @param btPref
     * @param listener
     */
    public void updatePrinterSetting(BluetoothPreference btPref, PrinterPreListener listener) {

        this.commandType = COMMAND_SEND;
        this.btPref = btPref;
        PrinterPrefThred pref = new PrinterPrefThred();
        pref.start();
    }

    /**
     * Getting the printer settings
     * @param listener
     */
    public void getPrinterSetting(PrinterPreListener listener) {
        if (listener == null) {
            mDialog.showAlertDialog(context.getString(R.string.msg_title_warning),
                    context.getString(R.string.error_input));
            return;
        }
        this.listener = listener;

        this.commandType = COMMAND_RECEIVE;
        PrinterPrefThred pref = new PrinterPrefThred();
        pref.start();
    }

    private class PrinterPrefThred extends Thread {
        @Override
        public void run() {

            // set info. for printing
            setPrinterInfo();

            // start message
            Message msg = mHandle.obtainMessage(Common.MSG_TRANSFER_START);
            mHandle.sendMessage(msg);
            mHandle.setFunction(MsgHandle.FUNC_SETTING);
            
            mPrintResult = new PrinterStatus();

            if (commandType == COMMAND_SEND) {
                mPrintResult = mPrinter.updateBluetoothPreference(btPref);

            } else if (commandType == COMMAND_RECEIVE) {
                btPref = new BluetoothPreference();
                mPrintResult = mPrinter.getBluetoothPreference(btPref);
            }

            if (listener != null) {
                listener.finish(mPrintResult, btPref);
            }

            // end message
            mHandle.setResult(showResult());
            mHandle.setBattery(getBattery());

            msg = mHandle.obtainMessage(Common.MSG_DATA_SEND_END);
            mHandle.sendMessage(msg);
        }
    }

    @Override
    protected void doPrint() {
    }

}
