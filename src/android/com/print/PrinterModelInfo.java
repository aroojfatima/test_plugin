/**
 * Information defined for printer models
 * 
 * @author Brother Industries, Ltd.
 * @version 2.2
 */

package com.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.brother.ptouch.sdk.PrinterInfo;

public class PrinterModelInfo {

    private ArrayList<HashMap<String, String[]>> mPrinterModelInfo =
        new ArrayList<HashMap<String, String[]>>();

    public static final String[] model = new String[] {
        "MW_140BT",
        "MW_145BT",
        "MW_260",
        "PJ_520",
        "PJ_522",
        "PJ_523",
        "PJ_560",
        "PJ_562",
        "PJ_563",
        "PJ_622",
        "PJ_623",
        "PJ_662",
        "PJ_663",
        "RJ_4030",
        "RJ_4040",
        "RJ_3050",
        "RJ_3150",
        "TD_2020",
        "TD_2120N",
        "TD_2130N",
        "TD_4000",
        "TD_4100N",
        "QL_710W",
        "QL_720NW",
        "PT_E550W",
		"PT_P750W"};

    public PrinterModelInfo() {

        int count = model.length;

        // initialize for model information
        for(int i = 0; i < count; i++)
        {
            addModelInfoMap(model[i]);
        }

    }

    /**set a particular model's information*/
    private void addModelInfoMap(String model)
    {
        HashMap<String, String[]> mapData = new HashMap<String, String[]>();

        mapData.put(Common.SETTINGS_MODEL, new String[]{model});

        switch (PrinterInfo.Model.valueOf(model)) {
            case MW_140BT:
            case MW_145BT:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.BLUETOOTH, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"A7"});
                break;
            case MW_260:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.BLUETOOTH, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"A6"});
                break;
            case PJ_520:
            case PJ_522:
            case PJ_523:
            case PJ_622:
            case PJ_623:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"A4", "LETTER", "LEGAL", "A5", "CUSTOM"});
                break;                
            case PJ_560:
            case PJ_562:
            case PJ_563:
            case PJ_662:
            case PJ_663:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.BLUETOOTH, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"A4", "LETTER", "LEGAL", "A5", "CUSTOM"});
                break;
            case RJ_4030:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.BLUETOOTH, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"CUSTOM"});
                break;
            case RJ_4040:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.NET, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"CUSTOM"});
                break;
            case RJ_3150:
            case RJ_3050:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.NET, Common.BLUETOOTH, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"CUSTOM"});
                break;
            case TD_2020:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"CUSTOM"});
                break;
            case TD_2120N:
            case TD_2130N:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.NET, Common.BLUETOOTH, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"CUSTOM"});
                break;
            case TD_4000:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.USB,Common.BLUETOOTH});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"CUSTOM"});
                break;
            case TD_4100N:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.NET,Common.USB,Common.BLUETOOTH});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"CUSTOM"});
                break;
            case QL_710W:
            case QL_720NW:
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.NET, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"W17H54","W17H87","W23H23","W29H42","W29H90","W38H90","W39H48","W52H29","W62H29","W62H100","W60H86","W12","W29","W38","W50","W54","W62"});
                break;
                
            case PT_E550W:
    		case PT_P750W:	
                mapData.put(Common.SETTINGS_PORT, new String[]{Common.NET, Common.USB});
                mapData.put(Common.SETTINGS_PAPERSIZE, new String[]{"W3_5","W6","W9","W12","W18","W24","HS_W6","HS_W9","HS_W12","HS_W18","HS_W24"});
                break;
            
            default:
                break;
        }
        mPrinterModelInfo.add(mapData);

    }

    /**get the port or paper size information */
    public String[] getPortOrPaperSizeInfo(String model, String value)
    {
        int count = mPrinterModelInfo.size();
        Map<String, String[]> mapData;

        String[] result = null;

        for (int i = 0; i < count; i++) {
            mapData = mPrinterModelInfo.get(i);
            if(mapData.get(Common.SETTINGS_MODEL)[0].equalsIgnoreCase(model))
            {
                result = mapData.get(value);
                break;
            }
        }

        return result;
    }

}