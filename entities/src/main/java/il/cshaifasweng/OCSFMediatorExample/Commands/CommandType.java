
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
	bringselectedexamCommand(13),
	savingtheeditedexamCommand(14),
	bringingallsubjectformanagerCommand(15),
	bringingallCoursesformanagerCommand(16),
    savingthesolvedexam(18),
    AllExamstoShowResultsTeacherCommand(19),
    displaySolvedExamCommand(20),

    bringsSpecificSolvedExamCommand(21),
    confirmSolvedExamCommand(22),
    AllExamstoShowResultsManagerCommand(23),
    BringExamOnExecuteCommand(24),
    AddTimeforExamTeacherCommand(25),
    checkingAddtimeRequestCommand(26),
    bringTimeRequestExamCommand(27),
    handleConfirmingtheaddingTimeByManagerCommand(28),

    onexecCommand(29), 
    savingtheuploadedexam(30), 
    getsolvedexam(31), 
    ifextra(32);


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