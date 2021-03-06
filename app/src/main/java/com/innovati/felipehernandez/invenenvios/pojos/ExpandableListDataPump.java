package com.innovati.felipehernandez.invenenvios.pojos;

import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump
{
    private static List<String> nombres;
    private static List<Object> cantidades;

    public ExpandableListDataPump(List<String> nombres, List<Object> cantidades)
    {
        this.nombres = nombres;
        this.cantidades = cantidades;
    }

    public static HashMap<String, List<Float>> getData()
    {
        HashMap<String, List<Float>> expandableListDetail = new HashMap<String, List<Float>>();

        for(int x=0; x<nombres.size(); x++)
        {
                List<Float> lista = (List<Float>) cantidades.get(x);
                expandableListDetail.put(nombres.get(x), lista);
        }
        return expandableListDetail;
    }
}
