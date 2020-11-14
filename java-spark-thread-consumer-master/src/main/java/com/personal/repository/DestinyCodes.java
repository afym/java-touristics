package com.personal.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by angel on 7/7/17.
 */
public class DestinyCodes {
    private static String HTP = "HTP";
    private static String EZL = "EZL";
    private static String FTV = "FTV";

    public static String[] getDestiny(String supplierCode, String destiniy)
    {
        Map<String, String[]> destinies = new HashMap<>();

        if (supplierCode.equals(DestinyCodes.HTP)) {
            destinies.put("50001", new String[] {"10001", "10002"});
        }

        if (supplierCode.equals(DestinyCodes.EZL)) {
            destinies.put("50001", new String[] {"20001", "20002", "20003"});
        }

        if (supplierCode.equals(DestinyCodes.FTV)) {
            destinies.put("50001", new String[] {"30001"});
        }

        String[] result =  destinies.get(destiniy);

        if (result == null) {
            return new String[]{"45001"};
        }

        return result;
    }
}
