/**
 * ImagePrint for printing
 * 
 * @author Brother Industries, Ltd.
 * @version 2.2
 */
package com.print;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.brother.ptouch.sdk.PrinterInfo.ErrorCode;

public class ImagePrint extends BasePrint {

    protected ArrayList<String> mImageFiles;

    public ImagePrint(Context context, MsgHandle mHandle, MsgDialog mDialog) {
        super(context, mHandle, mDialog);
    }

    /** set print data */
    public void setFiles(ArrayList<String> files) {
        mImageFiles = files;
    }

    /** do the particular print */
    @Override
    protected void doPrint() {

        int count = mImageFiles.size();

        for (int i = 0; i < count; i++) {

            String strFile = mImageFiles.get(i);
            Log.v(null,"File found from print function"+strFile);
            mPrintResult = mPrinter.printFile(strFile);

            // if error, stop print next files
            if (mPrintResult.errorCode != ErrorCode.ERROR_NONE) {
                break;
            }
        }
    }
}