package com.example.brioz.siwakaadmin.Common;

import com.example.brioz.siwakaadmin.Model.User;

public class Common {
    public static User currentUser;

    public static final String UPDATE="Update";
    public static final String DELETE="Delete";

    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Order Placed";
        else if(code.equals("1"))
            return "Order is being Prepared";
        else
            return "Order was picked up";

    }
}
