package to;

import java.util.ArrayList;

import json.JSONArray;
import json.JSONException;
import  json.JSONObject;

public class ListaVooTO extends ArrayList<VooTO>{

	private static final long serialVersionUID = 1L;

	public String toJSON(){
		JSONArray v = new JSONArray();
		for(VooTO to:this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("id", to.id);
				obj.put("codigo", to.codigo);
				obj.put("idAeronave", to.idAeronave);
				obj.put("destino", to.destino);
				obj.put("origem", to.origem);
				obj.put("data", to.data);
				obj.put("hora", to.hora);
				obj.put("situacao", to.situacao);
				obj.put("valor", to.valor);
				v.put(obj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return v.toString();	
	}
}
