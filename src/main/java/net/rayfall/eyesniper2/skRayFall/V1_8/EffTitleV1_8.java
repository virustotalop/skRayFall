package net.rayfall.eyesniper2.skRayFall.V1_8;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R1.PlayerConnection;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;



public class EffTitleV1_8 extends Effect{
	//send %player% title %string% [with subtitle %string%] for %timespan% [with %timespan% fade in and %timespan% fade out]
	
	private Expression<String> title;
	private Expression<String> subTitle;
	private Expression<Player> player;
	private Expression<Timespan> time;
	private Expression<Timespan> fadeIn;
	private Expression<Timespan> fadeOut;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) e[0];
		title = (Expression<String>) e[1];
		subTitle = (Expression<String>) e[2];
		time = (Expression<Timespan>) e[3];
		fadeIn = (Expression<Timespan>) e[4];
		fadeOut = (Expression<Timespan>) e[5];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		PlayerConnection connection = ((CraftPlayer) player.getSingle(evt).getPlayer()).getHandle().playerConnection;
		int timeTick = 60;
		if(time != null){
			timeTick = time.getSingle(evt).getTicks();
		}

		if (fadeIn != null && fadeOut != null){
			PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.getSingle(evt).getTicks(), timeTick, fadeOut.getSingle(evt).getTicks());
	        connection.sendPacket(packetPlayOutTimes);
		}
		else if(fadeIn == null && fadeOut != null){
			PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 5, timeTick, fadeOut.getSingle(evt).getTicks());
	        connection.sendPacket(packetPlayOutTimes);
		}
		else if(fadeIn != null && fadeOut == null){
			PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.getSingle(evt).getTicks(), timeTick, 5);
	        connection.sendPacket(packetPlayOutTimes);
		}
		else{
			PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 5, timeTick, 5);
	        connection.sendPacket(packetPlayOutTimes);
		}
		
		
		if (subTitle != null) {
            IChatBaseComponent finalSub = ChatSerializer.a("{\"text\": \"" + subTitle.getSingle(evt).toString() + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, finalSub);
            connection.sendPacket(packetPlayOutSubTitle);
        }

   

        if (title != null) {
            IChatBaseComponent finalTitle = ChatSerializer.a("{\"text\": \"" + title.getSingle(evt).toString() + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, finalTitle);
            connection.sendPacket(packetPlayOutTitle);
            
            
  
        }
    }
	  
		
		
	}


