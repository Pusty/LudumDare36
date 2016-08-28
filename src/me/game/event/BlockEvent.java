package me.game.event;

import com.badlogic.gdx.Gdx;

import me.game.dialog.Dialog;
import me.game.dif.EndTick;
import me.game.entity.EntityLiving;
import me.game.entity.EntityScript;
import me.game.main.Engine;

public class BlockEvent implements Event{
	EntityLiving living;
	int x;
	int z;
	int id;
	int index;
	public BlockEvent(int index,EntityLiving l,int x,int z,int id) {
		this.living=l;
		this.x=x;
		this.z=z;
		this.id=id;
		this.index=index;
	}
	
	public static boolean handleBlockCollision(Engine engine,EntityLiving e,int x,int z,int id) {
		if(engine.getVariable().get("police")==0) 
			if(z==24)
				engine.getEventHandler().queueEvent(new BlockEvent(EntityScript.SIGN,e,x,z,id));
		
		if(x==16 && z==24)
			engine.getEventHandler().queueEvent(new BlockEvent(EntityScript.WARD,e,x,z,id));
		
		if(x==15 && z==27 && engine.getVariable().get("pottery_broken") == 3) {
			engine.getEventHandler().queueEvent(new BlockEvent(EntityScript.POT_NEW,e,x,z,id));
		}
		
		if(engine.getVariable().get("pottery_broken")==0) {
			if(x < 12 || x > 18 || z < 16) {
				engine.getEventHandler().queueEvent(new BlockEvent(EntityScript.POTTERY,e,x,z,id));
				return false;
			}
		}
		if(engine.getVariable().get("pottery_broken")==1) {
			if(x < 12 || z > 22 || z < 16) {
				engine.getEventHandler().queueEvent(new BlockEvent(EntityScript.NEED_REPAIR,e,x,z,id));
				return false;
			}else if(x > 18) {
				engine.getEventHandler().queueEvent(new BlockEvent(EntityScript.CLAY_1,e,x,z,id));
			}
		}
		if(engine.getVariable().get("pottery_broken")==2) {
			if(z > 22) {
				engine.getEventHandler().queueEvent(new BlockEvent(EntityScript.POTTERY_BROKEN,e,x,z,id));
				return false;
			}
		}
		if(id==0 || id == 1 || id == 3 || id == 10 || id == 11 || id == 12 ) return true;
		return false;
	}
	
	
	@Override
	public String getName() {
		return "block";
	}
	

	@Override
	public void event(final Engine game) {
		if(getIndex()==EntityScript.POTTERY) {
			if(living.getDirection()==1)
				living.setLastDirection(2);
			else if(living.getDirection()==2)
				living.setLastDirection(1);
			else if(living.getDirection()==3)
				living.setLastDirection(4);
			else if(living.getDirection()==4)
				living.setLastDirection(3);	
			
			String[] text = {"Hmm I can't go there yet.","I want to look at that pot!"};
			game.setDialog(new Dialog("player",text,null));
		}else if(getIndex()==EntityScript.POTTERY_BROKEN) {
			if(living.getDirection()==1)
				living.setLastDirection(2);
			else if(living.getDirection()==2)
				living.setLastDirection(1);
			else if(living.getDirection()==3)
				living.setLastDirection(4);
			else if(living.getDirection()==4)
				living.setLastDirection(3);	
			String[] text = {"I can't just go out.", "They will catch me if I w on't","replace it!"};
			game.setDialog(new Dialog("player",text,null));
		}else if(getIndex()==EntityScript.NEED_REPAIR) {
			if(living.getDirection()==1)
				living.setLastDirection(2);
			else if(living.getDirection()==2)
				living.setLastDirection(1);
			else if(living.getDirection()==3)
				living.setLastDirection(4);
			else if(living.getDirection()==4)
				living.setLastDirection(3);	
			String[] text = {"I don't think going there", "is a  good idea"};
			game.setDialog(new Dialog("player",text,null));
		}else if(getIndex()==EntityScript.CLAY_1) {
			String[] text = {"A pottery workshop!","I could try making a copy!","What do I need? hmm.."," -I need brown clay"," -I need blue color"," -and I need gold"};
			game.setDialog(new Dialog("player",text,null));
			game.getVariable().put("pottery_broken", 2);
		}else if(getIndex()==EntityScript.SIGN) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					Runnable run = new Runnable() {
						@Override
						public void run() {
							game.setDialog(new Dialog("player",new String[] {"hahaha"}, null));
						}			
					};
					game.setDialog(new Dialog("police",new String[] {"W elcome to the M useum","W e are currently showing very","old ancient technology","W atch out that you","don't break anything."}, run));
				}			
			};
			
			//
			String[] t = {"Hello!","I'm here to look","at all the ancient stuff here!"};
			game.setDialog(new Dialog("player",t,run));
			game.getVariable().put("police", 1);
		}else if(getIndex()==EntityScript.WARD) {
			if(game.getVariable().get("pottery_broken")==3) {
				String[] text = {"Have a good day!"};
				game.setDialog(new Dialog("police",text,null));	
			}else {
				String[] text = {"Just w atch out that you","don't break anything."};
				game.setDialog(new Dialog("police",text,null));
			}
		}else if(getIndex()==EntityScript.POT_NEW) {
			//GAME END
			EndTick tick = new EndTick(game);
			game.setScreen(tick);
		    Gdx.input.setInputProcessor(tick);
		}
	}

	@Override
	public int getIndex() {
		return index;
	}
}

