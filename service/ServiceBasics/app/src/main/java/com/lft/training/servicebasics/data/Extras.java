package com.lft.training.servicebasics.data;

import java.util.ArrayList;

/**
 * Created by laaptu on 8/31/15.
 */
public class Extras {

    public static final String DATA = "data";
    public static final String CALL_SERVICE = "com.lft.training.servicebasics.action.OPEN";
    public static final String STOP = "stop";

    public static final String URL_VENUE = "https://gist.githubusercontent.com/leapfrog-santosh/62009546c4f4f0687dad/raw/e431feac7240de018cf67f136146d3513ef6d87e/veneu0";
    public static final String ULR_REGION = "https://gist.githubusercontent.com/leapfrog-santosh/d14ee689c4d19301ee65/raw/da98f2adcec6a31a0cd1cc0eca18b35794c01047/regions";
    public static final String URL_DETAILS = "https://gist.githubusercontent.com/leapfrog-santosh/d7ff39075dce8199ac88/raw/cb8c85162e065ae4c03ecceb99d136c3db106d94/place_details";

    public static final String EVENT_DOWNLOADCOMPLETE = "downloadsakiyo";
    public static final String EVENT_DOWNLOADHUDAICHA = "downloadhudaicha";

    public static final String URL = "url";

    public static final ArrayList<String> URL_LIST = new ArrayList<String>() {{
        add(URL_VENUE);
        add(ULR_REGION);
        add(URL_DETAILS);
    }};
}
