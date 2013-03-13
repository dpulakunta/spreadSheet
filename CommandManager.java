/* ClassName : CommandManager
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * CommandManager controls execution of the command
 * It has a stack where it stores the command which presently getting executed
 * when undo is called we pop the command and then execute it
 */
package com.sdsu.spreadSheet;

import java.util.Stack;

public class CommandManager
{
    private Stack<Command> commandStack = new Stack<Command>();

    public void executeCommand(Command cmd)
    {
        cmd.execute();
        if (cmd instanceof UndoAbleCommand)
        {
            commandStack.push(cmd);
        }    
    }


    public void undo()
    {
        if (commandStack.size() > 0)
        {
            UndoAbleCommand cmd = (UndoAbleCommand)commandStack.pop();
            cmd.undo();
        }
    }

}
