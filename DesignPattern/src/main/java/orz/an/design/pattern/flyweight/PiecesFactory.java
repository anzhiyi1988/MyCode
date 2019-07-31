package orz.an.design.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class PiecesFactory {

    private static Map<String,AbstractPieces> piecesMap = new HashMap();

    public static AbstractPieces getPieces(String color){


        AbstractPieces p = null;

        if(piecesMap.containsKey(color)){
            p = piecesMap.get(color);
        }else{
            p = new Pieces(color);
            piecesMap.put(color,p);
        }

        return p;

    }


}
