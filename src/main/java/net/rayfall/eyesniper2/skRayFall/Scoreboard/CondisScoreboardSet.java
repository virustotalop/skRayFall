package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondisScoreboardSet extends Condition{
	
	//side bar is set for %player% 
	
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean check(Event evt) {
		if(player.getSingle(evt).getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null){
			return true;
		}
			else{
				return false;
			}
		}
	}


