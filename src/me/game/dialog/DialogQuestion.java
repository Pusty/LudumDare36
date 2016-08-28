package me.game.dialog;

import me.game.main.Engine;

public class DialogQuestion extends Dialog{
	int chosenIndex=0;
	Runnable[] runs;
	public DialogQuestion(String owner,String[] te,Runnable[] runnables) {
		super(owner,te,null);
		runs=runnables;
	}
	public int getChosen() {
		return chosenIndex;
	}
	public void setChosen(int c) {
		chosenIndex=c;
	}
	public Runnable[] getRunnables() {
		return runs;
	}
	@Override
	public void nextLine(Engine e) {
		e.setDialog(null);
		try {
			if(runs[chosenIndex] != null)
				runs[chosenIndex].run();
		}catch(Exception ex){}
	}
	@Override
	public void up(Engine e) {
		chosenIndex++;
		if(chosenIndex>getText().length-2)
			chosenIndex=0;
	}
	@Override
	public void down(Engine e) {
		chosenIndex--;
		if(chosenIndex<0)
			chosenIndex=getText().length-2;
	}
}
