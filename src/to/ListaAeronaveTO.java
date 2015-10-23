package to;

import java.util.ArrayList;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;

public class ListaAeronaveTO extends ArrayList<AeronaveTO>{

	private static final long serialVersionUID = 1L;

	public String toJSON(){
		JSONArray v = new JSONArray();
		for(AeronaveTO to:this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("id", to.id);
				obj.put("codigo", to.codigo);
				obj.put("nome", to.nome);
				obj.put("assentos", to.assentos);
				v.put(obj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return v.toString();	
	}
}
