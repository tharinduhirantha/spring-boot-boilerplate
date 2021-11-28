package com.firststep.rest;

import org.json.simple.JSONObject;

public class JSONFormatter {

    public String convertJsonToStr(JSONObject obj){

        return obj.toJSONString();

    }


}
