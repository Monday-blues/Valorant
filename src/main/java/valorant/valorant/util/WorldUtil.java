package valorant.valorant.util;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class WorldUtil {
    public static void WorldGenerator(String name, WorldEnviroment type){
        WorldCreator wc = new WorldCreator(name);
        if(type == WorldEnviroment.VOID){
            wc.environment(World.Environment.NORMAL);
            wc.type(WorldType.FLAT);
        }
        if(type == WorldEnviroment.NORMAL){
            wc.environment(World.Environment.NORMAL);
            wc.type(WorldType.NORMAL);
        }
        if(type == WorldEnviroment.FLAT){
            wc.environment(World.Environment.NORMAL);
            wc.type(WorldType.FLAT);
            wc.generatorSettings("2;0;1;");
        }
        if(type == WorldEnviroment.NETHER){
            wc.environment(World.Environment.NETHER);
            wc.type(WorldType.NORMAL);
        }
        if(type == WorldEnviroment.THE_END){
            wc.environment(World.Environment.THE_END);
            wc.type(WorldType.NORMAL);
        }
        wc.createWorld();
    }
    public enum WorldEnviroment{
        VOID,
        NORMAL,
        FLAT,
        NETHER,
        THE_END,
        ;
    }

}
