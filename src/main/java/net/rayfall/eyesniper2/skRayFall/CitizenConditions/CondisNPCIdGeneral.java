package net.rayfall.eyesniper2.skRayFall.CitizenConditions;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondisNPCIdGeneral extends Condition{
	
	//%entity% is (citizen|npc) %number%
	
	private Expression<Entity> test;
	private Expression<Number> id;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		test = (Expression<Entity>) e[0];
		id = (Expression<Number>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	public boolean check(Event evt) {
		if(test.getSingle(evt) != null && id.getSingle(evt) != null && test.getSingle(evt).hasMetadata("NPC")){
			NPCRegistry registry = CitizensAPI.getNPCRegistry();
			if (registry.getNPC(test.getSingle(evt)).getId() == id.getSingle(evt).intValue()){
				return true;
			}
			return false;
		}
		else
		return false;
	}

}
