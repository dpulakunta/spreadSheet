/* ClassName : UndoAbleCommand
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * to create command pattern for undo command this is abstract 
 * class 
 */
package com.sdsu.spreadSheet;

public abstract class UndoAbleCommand extends Command {
	public abstract void undo();
}
