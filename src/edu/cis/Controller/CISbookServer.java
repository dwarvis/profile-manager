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
            println(profList);
            return CISConstants.SUCCESS;
        }

        //Contains profile method
        if (request.getCommand().equals(CISConstants.CONTAINS_PROF))
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

        //delete profile method
        if (request.getCommand().equals(CISConstants.DELETE_PROF))
        {
            for (CISbookProfile profile : profList)
            {
                if (profile.getName().equals(request.getParam(CISConstants.NAME)))
                {
                    profList.remove(profile);
                    return CISConstants.SUCCESS;
                }
            }
            return CISConstants.PROF_EXIST_ERR;
        }

        //Set Status method
        if (request.getCommand().equals(CISConstants.SET_STATUS))
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

        //Get status method
        if (request.getCommand().equals(CISConstants.GET_STATUS))
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

        //Set Image method
        if (request.getCommand().equals(CISConstants.SET_IMG))
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

        //Get image method
        if (request.getCommand().equals(CISConstants.GET_IMG))
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

        //
        if (request.getCommand().equals(CISConstants.ADD_FRND))
        {
            for (CISbookProfile profile1 : profList)
            {
                if (profile1.getName().equals(request.getParam(CISConstants.NAME1)))
                {
                    for (CISbookProfile profile2 : profList)
                    {
                        if (profile2.getName().equals(request.getParam(CISConstants.NAME2)))
                        {

//                            if (profile2.getFriends().contains(profile1.getName()) ||
//                                    profile1.getFriends().contains(profile2.getName()))
//                            {
//                                println( "bruh they already in it");
//                                return CISConstants.INVALID_FRND;
//                            }
//
                            if (profile1.addFriend(profile2.getName()) && profile2.addFriend(profile1.getName()))
                            {
                                println("prof2 freind are:" + profile2.getFriends());
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

        return "Error: Unknown command " + cmd + ".";
    }


    public static void main(String[] args)
    {
        CISbookServer f = new CISbookServer();
        f.start(args);
    }
}
