
package  util;

import java.util.Collection;
import java.util.Map;

import json.JSONException;

import  core.FlightCore;


public class JSONObject extends json.JSONObject {

  private final FlightCore core = FlightCore.getInstance();

  @Override
  public json.JSONObject put(String arg0, boolean arg1) {
    try {
      return super.put(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject put(String arg0, Collection arg1) {
    try {
      return super.put(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject put(String arg0, double arg1) {
    try {
      return super.put(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject put(String arg0, int arg1) {
    try {
      return super.put(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject put(String arg0, long arg1) {
    try {
      return super.put(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject put(String arg0, Map arg1) {
    try {
      return super.put(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject put(String arg0, Object arg1) {
    try {
      return super.put(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject putOnce(String arg0, Object arg1) {
    try {
      return super.putOnce(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

  @Override
  public json.JSONObject putOpt(String arg0, Object arg1) {
    try {
      return super.putOpt(arg0, arg1);
    } catch (JSONException e) {
      core.logError("JSON Error", e);
    }
    return null;
  }

}