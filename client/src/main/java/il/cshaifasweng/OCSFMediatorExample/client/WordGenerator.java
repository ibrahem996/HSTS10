package il.cshaifasweng.OCSFMediatorExample.client;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordGenerator {

	// Create Word
	public void createWord(Exam exam) throws IOException {
		// Blank Document
		XWPFDocument document = new XWPFDocument();
		// Write the Document in file system
		FileOutputStream out = new FileOutputStream(new File("exam" + "_" + exam.getCourse().getCourseName() +".doc"));
		int i=0;
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		int j=1;
		for (Question question : exam.getQuestions()) {
			// create Paragraph
			
			run.setText(j + ") " + question.getQuestion() );
			run.addBreak();
			for (i = 0; i < 4; i++) {
				run.setText((i+1) + ". " + question.getAnswers().get(i).getAnswer() );
				run.addBreak();
			}
			run.addBreak();
			run.addBreak();
			j++;
		}
		document.write(out);
		// Close document
		out.close();
	}
}