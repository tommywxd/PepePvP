package xyz.pepepvp.core;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.UUID;
import java.util.Random;

public class NPC implements CommandExecutor {
    private final Main plugin;

    public NPC(Main plugin) {
        this.plugin = plugin;
    }

    public void forceEmote(Player receiver, UUID npcUUID, int emoteId ) {
        JsonArray array = new JsonArray();

        JsonObject forcedEmote = new JsonObject();
        forcedEmote.addProperty( "uuid", npcUUID.toString() );
        forcedEmote.addProperty( "emote_id", emoteId );
        array.add(forcedEmote);

        LabyModPlugin.getInstance().sendServerMessage( receiver, "emote_api", array );
    }

    private void spawnPlayerInWorld( int entityId, UUID uuid, Location location ) {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket( PacketType.Play.Server.NAMED_ENTITY_SPAWN );
		
        packet.getIntegers().write( 0, entityId );
        packet.getUUIDs().write( 0, uuid );

        packet.getIntegers().write( 1, ( int ) Math.floor( location.getX() * 32D ) );
        packet.getIntegers().write( 2, ( int ) Math.floor( location.getY() * 32D ) );
        packet.getIntegers().write( 3, ( int ) Math.floor( location.getZ() * 32D ) );
        packet.getBytes().write( 0, ( byte ) ( location.getYaw() * 256F / 360F ) );
        packet.getBytes().write( 1, ( byte ) ( location.getPitch() * 256F / 360F ) );

        packet.getIntegers().write( 4, ( int ) 0 /* Item in hand id */ );

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        watcher.setObject( 0, ( byte ) 0 );
        watcher.setObject( 10, ( byte ) 127 );
        packet.getDataWatcherModifier().write( 0, watcher );

        for ( Player player : Bukkit.getOnlinePlayers() ) {
            try {
                ProtocolLibrary.getProtocolManager().sendServerPacket( player, packet );
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onCommand(CommandSender pSender, org.bukkit.command.Command pCmd, String s, String[] args) {
        UUID ruuid = UUID.randomUUID();
        forceEmote((Player) pSender, ruuid, Integer.parseInt(args[0]));
        spawnPlayerInWorld(1, ruuid, ((Player) pSender).getLocation());
        return true;
    }
}
