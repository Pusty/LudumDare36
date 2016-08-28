package me.game.entity;


//LINK TO ME/GAME/EVENT/ENTITYEVENT.CLASS
public class EntityScript extends EntityLiving {
	public static final int CLAY_1=102,CLAY_2=103,COLOR_RED=104,COLOR_BLUE=105,COLOR_BLACK=106,POTTERY=107,
	MAINTABLE=108,COLOR_WHITE=109,POTTERY_BROKEN=110,GOLD=111,GRAIL=112,WHEEL=113,STONE=114,POT=115,
	WOOD_BLUE=116,GLASS=117,WOOD_GOLD=118,WOOD=119,WOOD_POTTERY=120,NEED_REPAIR=121,FURNACE=122,SIGN=123,
	SIGN1=124,SIGN2=125,SIGN3=126,SIGN4=127,WARD=128,POT_NEW=129;
	private int entityIndex;
	public EntityScript(int x, int y,int e) {
		super(x, y);
		entityIndex = e;
	}
	public int getEntityIndex() {
		return entityIndex;
	}
	
	public void setIndex(int in) {
		entityIndex = in;
	}
	
	public String getTextureName() {
		if(entityIndex==CLAY_1)
			return "wood_clay_0";
		else if(entityIndex==CLAY_2)
			return "wood_clay_1";
		else if(entityIndex==COLOR_RED)
			return "table_red";
		else if(entityIndex==COLOR_BLUE)
			return "table_blue";
		else if(entityIndex==COLOR_BLACK)
			return "table_black";
		else if(entityIndex==COLOR_WHITE)
			return "table_white";
		else if(entityIndex==STONE)
			return "table_stone";
		else if(entityIndex==WHEEL)
			return "table_wheel";
		else if(entityIndex==POTTERY)
			return "table_pottery";
		else if(entityIndex==POTTERY_BROKEN)
			return "table_broken";
		else if(entityIndex==MAINTABLE)
			return "table";
		else if(entityIndex==GOLD)
			return "table_gold";
		else if(entityIndex==GRAIL)
			return "table_grail";
		else if(entityIndex==POT)
			return "table_pot";
		else if(entityIndex==WOOD_BLUE)
			return "wood_blue";
		else if(entityIndex==WOOD_GOLD)
			return "wood_gold";
		else if(entityIndex==GLASS)
			return "table_glass";
		else if(entityIndex==WOOD)
			return "wood";
		else if(entityIndex==WOOD_POTTERY)
			return "wood_pottery";
		else if(entityIndex==SIGN)
			return "sign";
		else if(entityIndex==SIGN1)
			return "sign";
		else if(entityIndex==SIGN2)
			return "sign";
		else if(entityIndex==SIGN3)
			return "sign";
		else if(entityIndex==SIGN4)
			return "sign";
		else if(entityIndex==FURNACE)
			return "furnace";
		else if(entityIndex==WARD)
			return "police";
		else if(entityIndex==POT_NEW)
			return "table_new";
		
		return "empty";
	}
	public String getMovingTexture() {
		return "empty";
	}
	
	public boolean hasDirections() {
		return false;
		}

	//Get Speed 30 = 1 sec
	public int getSpeed() {
		return 10;
	}
	
}


