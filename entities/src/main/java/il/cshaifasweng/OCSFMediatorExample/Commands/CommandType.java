package il.cshaifasweng.OCSFMediatorExample.Commands;

public enum CommandType {

	loginCommand(1),
	checkSubjectCommand(2),
	addQuestionCommand(3),
	loginExamCommand(4),
    startExamCommand(5),
    logoutCommand(6);
	
    private int CommandTypeValue;

    CommandType(int value) {
         this.CommandTypeValue = value;
    }

    public int getCommandTypeValue() {
         return this.CommandTypeValue;
    }

    public String getCommandTypeName() {
         return this.name();
    }
	
}