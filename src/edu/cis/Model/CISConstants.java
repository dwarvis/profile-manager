package edu.cis.Model;

public class CISConstants
{
    //return strings
    public static final String PROF_EXIST_ERR = "Error: dumb piece of shit.";
    public static final String PROF_NOTPRES_ERR = "Error: that profile doesn't exist.";
    public static final String INVALID_FRND = "Error: can't add friends";
    public static final String SUCCESS = "success";
    public static final String TRUE_RET = "true";
    public static final String FALSE_RET = "false";

    //Parameters for requests
    public static final String NAME = "name";
    public static final String STATUS = "status";
    public static final String IMAGE_STR = "imageString";
    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";

    //Commands
    public static final String PING = "ping";
    public static final String ADD_PROF = "addProfile";
    public static final String CONTAINS_PROF = "containsProfile";
    public static final String DELETE_PROF = "deleteProfile";
    public static final String SET_STATUS = "setStatus";
    public static final String GET_STATUS = "getStatus";
    public static final String SET_IMG = "setImage";
    public static final String GET_IMG = "getImage";
    public static final String ADD_FRND = "addFriend";
    public static final String GET_FRND = "getFriends";

    //Errors
    public static final String PORT_UNAVAIL = "is not available, likely because \nit's already being used by another " +
            "Java program running. \nClose all your server windows and try again.";

}
