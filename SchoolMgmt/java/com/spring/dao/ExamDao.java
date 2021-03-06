package com.spring.dao;

import java.util.List;

import com.spring.model.ExamModel;
import com.spring.model.ExamSummaryReportModel;
import com.spring.model.StudentModel;
import com.spring.model.Subjects;

public interface ExamDao {
	public List<Subjects> getClassSubjects(String gradeid);
	public List<Subjects> getSpecificClassSubjects(String gradeid);
	public Subjects getSubjectDetail(String gradeid);
	
	public boolean addMarks(ExamModel exam, String studentid, int i);
	
	public boolean addMarks(ExamModel exam, int i);
	 public boolean insertTableDetail(String tablename,String columns, String value);
	 
	public List<ExamModel> specificStudentMarksReport(ExamModel exam, String studentid);
	
	public ExamSummaryReportModel specificStudentExamSummary(ExamModel exam, String studentid);
	public List<StudentModel> getClassStudents(String classname, String sectionname);
	public boolean checkStudentSubAvailability(ExamModel exammodel, int i);
	public ExamModel editExam(String examId);
	public boolean updateExam(ExamModel emodel, String examId);
	public boolean deleteExam(String examId);


}
