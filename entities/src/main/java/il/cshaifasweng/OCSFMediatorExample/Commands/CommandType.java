
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
	CreatingCodeCommand(9),
	loginExamCommand(10),
    startExamCommand(11),
    logoutCommand(12),
    savingtheeditedexamCommand(13),
    savingthesolvedexam(14);
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