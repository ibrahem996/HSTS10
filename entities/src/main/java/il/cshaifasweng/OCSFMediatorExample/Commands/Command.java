package il.cshaifasweng.OCSFMediatorExample.Commands;

import java.io.Serializable; 


public class Command  implements Serializable  {

	private Object command;											// we deleted final
    private final CommandType type;

    public Command( Object command,final CommandType type) {
        this.command = command;
        this.type = type;
    }

	/*
	 * public <T> T getCommand(Class<T> clazz) { return (T) command; }
	 */
    
    public Object getCommand() {
        return command; 
    }

    public CommandType getType() {
        return type;
    }
    
    public void setCommand(Object object)
    {  System.out.print( "HHHHHHHHHHHHHH");
    	this.command = object;
    }
}
