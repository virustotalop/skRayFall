package net.rayfall.eyesniper2.skRayFall.GeneralEffects;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.metadata.FixedMetadataValue;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetMetaData extends Effect{
	
	//set meta %string% for %entity% to %string%
	
	private Expression<String> id;
	private Expression<String> value;
	private Expression<Entity> target;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[0];
		target = (Expression<Entity>) e[1];
		value = (Expression<String>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(target.getSingle(evt) != null && value.getSingle(evt) != null && id.getSingle(evt) != null){
			target.getSingle(evt).setMetadata(id.getSingle(evt), new FixedMetadataValue(skRayFall.plugin, value.getSingle(evt)));
		}
		
	}

}
