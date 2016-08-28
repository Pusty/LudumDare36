package me.game.event;

import me.game.dialog.Dialog;
import me.game.entity.Entity;
import me.game.entity.EntityLiving;
import me.game.entity.EntityScript;
import me.game.main.Engine;

public class EntityEvent implements Event{
	EntityLiving player;
	Entity collide;
	int x;
	int z;
	int index;
	public EntityEvent(int index,EntityLiving user,int x,int z,Entity target) {
		player=user;
		collide=target;
		this.x=x;
		this.z=z;
		this.index=index;
	}

	@Override
	public String getName() {
		return "entity";
	}

	/**
	 * Index:
	 * 
	 *  0 = EntityLiving turn Event
	 * 
	 */
	
	@Override
	public void event(final Engine game) {
	
		if(index==EntityScript.POTTERY) {
			if(game.getVariable().get("pottery_broken") == 0) {
				Runnable run = new Runnable() {
					@Override
					public void run() {
						
						//
						String[] t = {" "};
						Runnable run = new Runnable() {
							@Override
							public void run() {
								
								Runnable run = new Runnable() {
									@Override
									public void run() {
										
										//SOUND
										game.getVariable().put("pottery_broken", 1);
										((EntityScript)collide).setIndex(EntityScript.POTTERY_BROKEN);
										game.getSound().playClip("break");
										
										
										Runnable run = new Runnable() {
											@Override
											public void run() {
												game.setDialog(new Dialog("player",new String[] {"...","ehh","..."}, null));
											}			
										};
										game.setDialog(new Dialog("broken",new String[] { " " }, run));
									}			
								};
								
								//
								String[] t = {"*cling*","*cling*"};
								game.setDialog(new Dialog("player",t,run));
								//
								
							}			
						};
						game.setDialog(new Dialog("pot",t,run));
						//
						
					}			
				};
				game.setDialog(new Dialog("player",new String[] {"This is a  nice piece of pottery.", "It must have been very hard","to make this!", "It's so shiny...", "*touch*"},run));
			}
		}else if(index==EntityScript.POTTERY_BROKEN) {
			if(game.getVariable().get("pottery_broken") == 1) {
				game.setDialog(new Dialog("player",new String[] {"... what should I do..."},null));
			}	
			if(game.getVariable().get("pottery_broken") == 2) {
				game.setDialog(new Dialog("player",new String[] {"I need to replace this!","M aybe the stuff here will help"},null));
			}	
		}else if(index==EntityScript.CLAY_1) {
			if(game.getVariable().get("clay") == 0) {
				Runnable run = new Runnable() {
					@Override
					public void run() {
						//SOUND
						game.getSound().playClip("up");
						game.getEventHandler().queueEvent(new ScreenEvent(0));
						game.getVariable().put("clay", 1);
						((EntityScript)collide).setIndex(EntityScript.WOOD);
						((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("table_0")]).setIndex(EntityScript.CLAY_1);
					}			
				};
				game.setDialog(new Dialog("player",new String[] {"Ah! This is the clay  I need"},run));
		
			}else {
				game.setDialog(new Dialog("player",new String[] {"This is the clay  I need"},null));
			}
		}else if(index==EntityScript.GOLD) {
			if(game.getVariable().get("furnace")==0) {
				game.setDialog(new Dialog("player",new String[] {"A golden bow l.","might be usefull","but how  wo uld I use this?","I can't just put it on the pot"},null));
			}else {
				if(game.getVariable().get("gold") == 0) {
					Runnable run = new Runnable() {
						@Override
						public void run() {
							//SOUND
							game.getSound().playClip("up");
							game.getEventHandler().queueEvent(new ScreenEvent(0));
							game.getVariable().put("gold", 1);
							((EntityScript)collide).setIndex(EntityScript.MAINTABLE);
							((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("table_1")]).setIndex(EntityScript.WOOD_GOLD);
						}			
					};
					game.setDialog(new Dialog("player",new String[] {"A golden bowl.","I could melt it down"},run));	
				}
			}
		}else if(index==EntityScript.COLOR_BLUE) {
			if(game.getVariable().get("color") == 0) {
				Runnable run = new Runnable() {
					@Override
					public void run() {
						//SOUND
						game.getSound().playClip("up");
						game.getEventHandler().queueEvent(new ScreenEvent(0));
						game.getVariable().put("color", 1);
						((EntityScript)collide).setIndex(EntityScript.MAINTABLE);
						((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("table_2")]).setIndex(EntityScript.WOOD_BLUE);
					}			
				};
				game.setDialog(new Dialog("player",new String[] {"A blue stone","it was used to color stuff...","this will help me!"},run));
		
			}
		}else if(index==EntityScript.FURNACE) {
			if(game.getVariable().get("done") == 1) {
				game.setDialog(new Dialog("player",new String[] {"A furnace to fire clay"},null));	
			}else if(game.getVariable().get("furnace") == 0) {
				game.setDialog(new Dialog("player",new String[] {"I can use this furnace to","fire the clay","or to melt stuff"},null));
				game.getSound().playClip("up");
				game.getVariable().put("furnace", 1);
			}else {
				if(game.getVariable().get("clay")==1 && game.getVariable().get("gold")==1 && game.getVariable().get("color")==1) {
					Runnable run = new Runnable() {
						@Override
						public void run() {
							//SOUND
							game.getSound().playClip("up");
							game.getEventHandler().queueEvent(new ScreenEvent(0));
							game.getVariable().put("done", 1);
							((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("table_0")]).setIndex(EntityScript.WOOD);
							((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("table_1")]).setIndex(EntityScript.WOOD_POTTERY);
							((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("table_2")]).setIndex(EntityScript.WOOD);
						}			
					};
					game.setDialog(new Dialog("player",new String[] {"Now  I can make a new pot!"},run));	
					
				}else {
					game.setDialog(new Dialog("player",new String[] {"I need clay","blue color,","and gold"},null));	
				}
			}
		}else if(index==EntityScript.CLAY_2) 
			game.setDialog(new Dialog("player",new String[] {"Is this even clay? smells weird"},null));
		else if(index==EntityScript.WOOD) 
			game.setDialog(new Dialog("player",new String[] {"This is a  table. Very interesting!"},null));
		else if(index==EntityScript.WOOD_BLUE) 
			game.setDialog(new Dialog("player",new String[] {"This is a  rock that can be used", "to color stuff blue"},null));
		else if(index==EntityScript.WOOD_GOLD) 
			game.setDialog(new Dialog("player",new String[] {"It's gold. Very shiny."},null));
		else if(index==EntityScript.WOOD_POTTERY) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					//SOUND
					game.getEventHandler().queueEvent(new ScreenEvent(0));
					game.getVariable().put("done", 2);
					game.getVariable().put("pottery_broken", 3);
					game.getSound().playClip("up");
					((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("table_1")]).setIndex(EntityScript.WOOD);
					((EntityScript)game.getZone().getEntityArray()[game.getVariable().get("maintable")]).setIndex(EntityScript.POT_NEW);
					game.getZone().setBlockID(14, 27, 7);
					game.getZone().setBlockID(15, 27, 3);
					game.getZone().setBlockID(16, 27, 5);
				}			
			};
			game.setDialog(new Dialog("player",new String[] {"M y  pot looks amazing!", "Looks almost the same"},run));
		}else if(index==EntityScript.POT_NEW) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					game.setDialog(new Dialog("new",new String[] {" "},null));
				}			
			};
			game.setDialog(new Dialog("player",new String[] {"M y copy looks perfect!"},run));
		}
		else if(index==EntityScript.COLOR_BLACK) 
			game.setDialog(new Dialog("player",new String[] {"Charcoal. Used to color stuff black"},null));
		else if(index==EntityScript.COLOR_RED) 
			game.setDialog(new Dialog("player",new String[] {"A very red stone"},null));
		else if(index==EntityScript.COLOR_WHITE)
			game.setDialog(new Dialog("player",new String[] {"Some kind of white dust"},null));
		else if(index==EntityScript.GLASS) 
			game.setDialog(new Dialog("player",new String[] {"I can't really see in it.","Someone should clean this!"},null));
		else if(index==EntityScript.GRAIL) 
			game.setDialog(new Dialog("player",new String[] {"'holy grail'","some kind of pot","with a  w eird  liquid  in it","seems useless.."},null));
		else if(index==EntityScript.POT) 
			game.setDialog(new Dialog("player",new String[] {"A chabby pot..","doesn't seem usable to me"},null));
		else if(index==EntityScript.STONE) 
			game.setDialog(new Dialog("player",new String[] {"A very old stone","stuff is writen on it","but I can't read it"},null));
		else if(index==EntityScript.WHEEL) 
			game.setDialog(new Dialog("player",new String[] {"A wheel made out of stone","very round","very stony", "very old"},null));
		else if(index==EntityScript.SIGN) 
			game.setDialog(new Dialog("player",new String[] {"A ancient pot","over 2000 years old","polished with gold and blue color"},null));
		else if(index==EntityScript.SIGN1)
			game.setDialog(new Dialog("player",new String[] {"A workshop for pottery"},null));
		else if(index==EntityScript.SIGN2) 
			game.setDialog(new Dialog("player",new String[] {"A room filled with stones"},null));
		else if(index==EntityScript.SIGN3) 
			game.setDialog(new Dialog("player",new String[] {"Stuff made out of gold lays here"},null));
		else if(index==EntityScript.SIGN4) 
			game.setDialog(new Dialog("player",new String[] {"A room filled with colors"},null));
	}
	

	public static void handleEntityCollision(Engine engine, EntityLiving e,
			int x, int z, Entity collide) {
		if(collide instanceof EntityScript) {
			EntityScript script = (EntityScript)collide;
			engine.getEventHandler().queueEvent(new EntityEvent(script.getEntityIndex(),e,x,z,collide));
		}
	}

	@Override
	public int getIndex() {
		return index;
	}
}
