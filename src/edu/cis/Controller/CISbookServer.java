/*
 * File: FacePamphletServer.java
 * ------------------------------
 * When it is finished, this program will implement a basic
 * social network management server.  Remember to update this comment!
 */

package edu.cis.Controller;

import acm.program.*;
import edu.cis.Model.CISConstants;
import edu.cis.Model.CISbookProfile;
import edu.cis.Model.Request;
import edu.cis.Model.SimpleServerListener;
import edu.cis.Utils.SimpleServer;
import java.util.ArrayList;

public class CISbookServer extends ConsoleProgram
        implements SimpleServerListener
{

    /* The internet port to listen to requests on */
    private static final int PORT = 8000;

    /* The server object. All you need to do is start it */
    private SimpleServer server = new SimpleServer(this, PORT);
    private ArrayList<CISbookProfile> profList = new ArrayList<>();

    /**
     * Starts the server running so that when a program sends
     * a request to this server, the method requestMade is
     * called.
     */

    public void run()
    {
        println("Starting server on port " + PORT);
        server.start();
    }

    /**
     * When a request is sent to this server, this method is
     * called. It must return a String.
     */
    public String requestMade(Request request)
    {
        String cmd = request.getCommand();
        println(request.toString());

        // your code here.
        if (request.getCommand().equals(CISConstants.PING))
        {
            final String PING_MSG = "Hello, internet";
            println("   => " + PING_MSG);
            return PING_MSG;
        }

        //Add Profile method
        if (request.getCommand().equals(CISConstants.ADD_PROF))
        {
            return addProf(request);
        }

        //Contains profile method
        if (request.getCommand().equals(CISConstants.CONTAINS_PROF))
        {
            return containsProf(request);
        }

        //delete profile method
        if (request.getCommand().equals(CISConstants.DELETE_PROF))
        {
            return deleteProf(request);
        }

        //Set Status method
        if (request.getCommand().equals(CISConstants.SET_STATUS))
        {
            return setStatus(request);
        }

        //Get status method
        if (request.getCommand().equals(CISConstants.GET_STATUS))
        {
            return getStatus(request);
        }

        //Set Image method
        if (request.getCommand().equals(CISConstants.SET_IMG))
        {
            return setImage(request);
        }

        //Get image method
        if (request.getCommand().equals(CISConstants.GET_IMG))
        {
            return getImage(request);
        }

        // add friends (big bird nest)
        if (request.getCommand().equals(CISConstants.ADD_FRND))
        {
            return addFriend(request);
        }

        //get friends
        if (request.getCommand().equals(CISConstants.GET_FRND))
        {
            return getFriends(request);
        }

        return "Error: Unknown command " + cmd + ".";
    }


    /* Server Methods */
    private String addProf(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {
                println(CISConstants.PROF_ALRD_EXIST + request.getParam(CISConstants.NAME));
                return CISConstants.PROF_ALRD_EXIST + request.getParam(CISConstants.NAME);
            }
        }
        CISbookProfile newProf = new CISbookProfile(request.getParam(CISConstants.NAME));
        profList.add(newProf);
        return CISConstants.SUCCESS;
    }

    private String containsProf(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {
                return CISConstants.TRUE_RET;
            }
        }
        return CISConstants.FALSE_RET;
    }

    private String deleteProf(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getFriends().contains(request.getParam(CISConstants.NAME))) {
                profile.removeFriend(request.getParam(CISConstants.NAME));
            }
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {
                profList.remove(profile);
                return CISConstants.SUCCESS;
            }
        }
        return CISConstants.PROF_EXIST_ERR;
    }

    private String setStatus(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {
                profile.setStatus(request.getParam(CISConstants.STATUS));
                return CISConstants.SUCCESS;
            }
        }
        return CISConstants.PROF_EXIST_ERR;
    }

    private String getStatus(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {

                if (profile.getStatus().equals(null))
                {
                    println("ya did it?");
                    return CISConstants.EMPTY;
                }
                println("nope");
                return profile.getStatus();
            }
        }
        println("empty");
        return CISConstants.PROF_EXIST_ERR;
    }

    private String setImage(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {
                profile.setImage(request.getParam(CISConstants.IMAGE_STR));
                return CISConstants.SUCCESS;
            }
        }
        return CISConstants.PROF_EXIST_ERR;
    }

    private String getImage(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {
                return profile.getImage();
            }
        }
        return CISConstants.PROF_EXIST_ERR;
    }

    private String addFriend(Request request)
    {
        for (CISbookProfile profile1 : profList)
        {
            if (profile1.getName().equals(request.getParam(CISConstants.NAME1)))
            {
                for (CISbookProfile profile2 : profList)
                {
                    if (profile2.getName().equals(request.getParam(CISConstants.NAME2)))
                    {
                        if (profile1.addFriend(profile2.getName()) && profile2.addFriend(profile1.getName()))
                        {
                            println("not yet freinds");
                            return CISConstants.SUCCESS;
                        }
                        if (profile1.getName() == profile2.getName())
                        {
                            println( "bruh they same");
                            return CISConstants.INVALID_FRND;
                        }
                    }
                }
            }
        }
        println(CISConstants.PROF_EXIST_ERR);
        return CISConstants.PROF_EXIST_ERR;
    }

    private String getFriends(Request request)
    {
        for (CISbookProfile profile : profList)
        {
            if (profile.getName().equals(request.getParam(CISConstants.NAME)))
            {
                return profile.getFriends().toString();
            }
        }
        return CISConstants.PROF_EXIST_ERR;
    }
    /* End of Server Methods */

    public static void main(String[] args)
    {
        CISbookServer f = new CISbookServer();
        f.start(args);
    }
}
