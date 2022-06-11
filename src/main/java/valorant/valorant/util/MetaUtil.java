package valorant.valorant.util;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import valorant.valorant.VALORANT;

import java.util.List;

public class MetaUtil {

    static VALORANT getPlugin() {
        return ((VALORANT) Bukkit.getPluginManager().getPlugin("VALORANT"));
    }

    public static void setMetaDataAsString(Player p, String name, String value){
        p.setMetadata(name, new FixedMetadataValue(getPlugin(), value));
    }

    public static String getMetaDataAsString(Player p, String name){
        MetadataValue data = sibal(p, name);
        if(data != null){
            return data.asString();
        }else {return "";}
    }

    public static void setMetaDataAsInteger(Player p, String name, Integer value){
        p.setMetadata(name, new FixedMetadataValue(getPlugin(), value));
    }

    public static Integer getMetaDataAsInteger(Player p, String name){
        MetadataValue data = sibal(p, name);
        if(data != null){
            return data.asInt();
        }else {return 0;}
    }


    public static void setMetaDataAsBoolean(Player p, String name, Boolean value){
        p.setMetadata(name, new FixedMetadataValue(getPlugin(), value));
    }

    public static Boolean getMetaDataAsBoolean(Player p, String name){
        MetadataValue data = sibal(p, name);
        if(data != null){
            return data.asBoolean();
        }else {return false;}
    }



    private static MetadataValue sibal(Player p, String name){
        List<MetadataValue> Meta = p.getMetadata(name);
        if (Meta.size() != 0){
            return Meta.get(0);
        }else{return null;}
    }
}
