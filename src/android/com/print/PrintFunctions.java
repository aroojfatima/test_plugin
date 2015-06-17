package com.print;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.brother.ptouch.sdk.Printer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.printPlugin.R;

public class PrintFunctions {
    
	 protected SharedPreferences sharedPreferences;
     protected MsgHandle mHandle;
     protected MsgDialog mDialog;
     protected BasePrint myPrint = null;
     private ArrayList<String> mFiles = new ArrayList<String>();

     public void setPrefereces(Context c) {    	
			sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
			
	        // initialization for print
			PrinterInfo printerInfo = new PrinterInfo();
			Printer printer = new Printer();
	        printerInfo = printer.getPrinterInfo();
	      //  if (!sharedPreferences.getString("printerModel", "").equals("")) {
	            SharedPreferences.Editor editor = sharedPreferences.edit();
	            editor.putString("printerModel", PrinterInfo.Model.QL_720NW.toString());
	            editor.putString("port", PrinterInfo.Port.NET.toString());
	            editor.putString("address", "192.168.1.8");
	            editor.putString("macAddress", printerInfo.macAddress.toString());
	            editor.putString("paperSize", "W62H100");
	            editor.putString("orientation", PrinterInfo.Orientation.LANDSCAPE.toString());
	            editor.putString("numberOfCopies", Integer.toString(printerInfo.numberOfCopies));
	            editor.putString("halftone", PrinterInfo.Halftone.ERRORDIFFUSION.toString());
	            editor.putString("printMode", PrinterInfo.PrintMode.FIT_TO_PAGE.toString());
	            editor.putString("pjCarbon", Boolean.toString(printerInfo.pjCarbon));
	            editor.putString("autoCut", Boolean.toString(true));
	            editor.putString("pjDensity", Integer.toString(printerInfo.pjDensity));
	            editor.putString("pjFeedMode", printerInfo.pjFeedMode.toString());
	            editor.putString("align", printerInfo.align.toString());
	            editor.putString("leftMargin", Integer.toString(printerInfo.margin.left));
	            editor.putString("valign", printerInfo.valign.toString());
	            editor.putString("topMargin", Integer.toString(printerInfo.margin.top));
	            editor.putString("customPaperWidth", Integer.toString(printerInfo.customPaperWidth));
	            editor.putString("customPaperLength", Integer.toString(printerInfo.customPaperLength));
	            editor.putString("customFeed", Integer.toString(printerInfo.customFeed));
	            editor.putString("paperPostion", printerInfo.paperPosition.toString());
	            editor.putString("customSetting", sharedPreferences.getString("customSetting", ""));
	            editor.putString("rjDensity", Integer.toString(printerInfo.rjDensity));
	            editor.putString("rotate180", Boolean.toString(printerInfo.rotate180));
	            editor.putString("peelMode", Boolean.toString(printerInfo.peelMode));
	        
	            editor.commit();
	       // }
	        
	    }
	 public void doPrint(String sourceData, Context ctx){
		 byte[] decodedString = Base64.decode(sourceData, Base64.DEFAULT);
	        InputStream inputStream  = new ByteArrayInputStream(decodedString);
	        
	        //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
	        
	        File outputDir = ctx.getCacheDir(); 
	        File outputFile = null;
	        try {
				 outputFile = File.createTempFile("print", ".png", outputDir);
				 FileOutputStream outputStream = new FileOutputStream(outputFile);
		
				 byte buffer[] = new byte[1024];
				 int dataSize;
				 int loadedSize = 0;
				 while ((dataSize = inputStream.read(buffer)) != -1) {
					 loadedSize += dataSize;
					 outputStream.write(buffer, 0, dataSize);
				 }
			     outputStream.close();
			} catch (IOException e) {
				Log.v(null,"Error "+e.getLocalizedMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
	        BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = false;
	        options.inPreferredConfig = Config.RGB_565;
	        options.inDither = true;
	        Bitmap bmp = BitmapFactory.decodeFile(outputFile.getPath());
	     
           
	        mDialog = new MsgDialog(ctx);
	        mHandle = new MsgHandle(ctx, mDialog);
	        myPrint = new ImagePrint(ctx, mHandle, mDialog);
		
	        //when use bluetooth print set the adapter
	       // BluetoothAdapter bluetoothAdapter = super.getBluetoothAdapter();
	       // myPrint.setBluetoothAdapter(bluetoothAdapter);
	        myPrint.getPrinterStatus();
		    	
		   String filePath = outputFile.getPath(); //"/mnt/sdcard/bro/abc.jpg"; 
		   File f = new File(filePath);
		   if(f.exists()) Log.v(null, "File Found" );
		   else Log.v(null, "Noooooooooooooooooooooo   File Found ");
			   
		   mFiles.add(filePath);
		  // Log.v(null, "File Path "+filePath);
	       ((ImagePrint)myPrint).setFiles(mFiles);
	        myPrint.print();  

	 }
}
