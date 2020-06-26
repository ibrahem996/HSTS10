package il.cshaifasweng.OCSFMediatorExample.Commands;

public enum CommandType {

	loginCommand(1),
	checkSubjectCommand(2),
	addQuestionCommand(3), 
	bringQuestionbySubjectCommand(4),
	checkCourseCommand(5),
	bringquestionsforspecialcoursebysubjectCommand(6),
	savingTheExamCommand(7), 
	bringExamInfoCommand(8),
	CreatingCodeCommand(9);
	
    
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