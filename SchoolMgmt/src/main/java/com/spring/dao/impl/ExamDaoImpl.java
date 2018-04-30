package com.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.dao.ExamDao;
import com.spring.dao.OperationDao;
import com.spring.model.ExamModel;
import com.spring.model.ExamSummaryReportModel;
import com.spring.model.StudentModel;
import com.spring.model.Subjects;

public class ExamDaoImpl implements ExamDao {
	@Autowired
	OperationDao operationDao;

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	public boolean addMarks(ExamModel exam, String studentid, int i) {
		String tablename = "exam_marks_tbl";
		String columns = "(studentid,examid,subjectid,prmarks,thmarks,totalgrade,remarks,inputDate, fullmarks, passmarks,totalmarks, fullmarks_pr, passmarks_pr)";
		String value = studentid + "','" + exam.getExamid() + "','" + exam.getSubjects().getSubjectidlist().get(i)
				+ "','" + exam.getSubjects().getPrmarkslist().get(i) + "','"
				+ exam.getSubjects().getThmarkslist().get(i) + "','" + exam.getSubjects().getTotalgradelist().get(i)
				+ "','" + exam.getSubjects().getRemarkslist().get(i) + "',NOW()," + "'"
				+ exam.getSubjects().getFullmarkslist().get(i) + "','" + exam.getSubjects().getPassmarkslist().get(i)
				+ "','" + exam.getSubjects().getTotalmarks() + "','" + exam.getSubjects().getFullmarks_prlist().get(i)
				+ "','" + exam.getSubjects().getPassmarks_prlist().get(i);
		System.out.println(value);
		return insertTableDetail(tablename, columns, value);
	}

	public List<Subjects> getClassSubjects(String gradeid) {
		String query = "select subjectlist.* from coursetbl join subjectlist on coursetbl.subjectid=subjectlist.subjectid where coursetbl.gradeid='"
				+ gradeid + "'";
		return jdbcTemplate.query(query, new ClassSubjects());
	}

	public static final class ClassSubjects implements RowMapper<Subjects> {

		@Override
		public Subjects mapRow(ResultSet rs, int rowNum) throws SQLException {
			Subjects s = new Subjects();
			s.setSubjectid(rs.getString("subjectid"));
			s.setSubjectname(rs.getString("subjectname"));
			s.setSubjecttype(rs.getString("subjecttype"));
			s.setSubjectcode(rs.getString("subjectCode"));
			s.setFullmarks(rs.getString("fullmarks"));
			s.setPassmarks(rs.getString("passmarks"));
			s.setFullmarks_pr(rs.getString("fullmarks_pr"));
			s.setPassmarks_pr(rs.getString("passmarks_pr"));

			return s;
		}
	}

	public boolean insertTableDetail(String tablename, String columns, String value) {
		boolean status = false;
		String sql = "INSERT INTO " + tablename + " " + columns + "  VALUES ('" + value + "')";
		System.out.println(sql);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			status = true;
		}
		return status;
	}

	public List<ExamModel> specificStudentMarksReport(ExamModel exam, String studentid) {
		String query = "select exam_marks_tbl.*, subjectlist.subjectname, subjectlist.subjecttype, subjectlist.subjectCode from exam_marks_tbl left join subjectlist using(subjectid) where exam_marks_tbl.studentid = '"
				+ studentid + "' and exam_marks_tbl.examid = '" + exam.getExamid() + "'";
		return jdbcTemplate.query(query, new ExamReport());
	}

	public static final class ExamReport implements RowMapper<ExamModel> {

		@Override
		public ExamModel mapRow(ResultSet rs, int rowNum) throws SQLException {

			ExamModel em = new ExamModel();
			Subjects s = new Subjects();
			s.setPrmarks(rs.getString("prmarks"));
			s.setThmarks(rs.getString("thmarks"));
			s.setTotalmarks(rs.getString("totalmarks"));
			s.setTotalgrade(rs.getString("totalgrade"));
			s.setRemarks(rs.getString("remarks"));
			s.setFullmarks(rs.getString("fullmarks"));
			s.setPassmarks(rs.getString("passmarks"));
			s.setFullmarks_pr(rs.getString("fullmarks_pr"));
			s.setPassmarks_pr(rs.getString("passmarks_pr"));
			s.setSubjectname(rs.getString("subjectname"));
			s.setSubjecttype(rs.getString("subjecttype"));
			s.setSubjectcode(rs.getString("subjectCode"));
			em.setSubjects(s);

			return em;
		}
	}

	public ExamSummaryReportModel specificStudentExamSummary(ExamModel exam, String studentid) {
		
		String query = "select * from exam_summary where examid = '"+exam.getExamid()+"' and studentid = '"+studentid+"'";
		return jdbcTemplate.queryForObject(query, new ExamSummary());
	}

	public static final class ExamSummary implements RowMapper<ExamSummaryReportModel> {

		@Override
		public ExamSummaryReportModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExamSummaryReportModel esm = new ExamSummaryReportModel();
			
			esm.setTotal(rs.getString("sum(fullmarks)"));
			esm.setTotal_obtained(rs.getString("sum(totalmarks)"));
			esm.setPercentage(rs.getString("percentage"));
			esm.setCurdate(rs.getString("curdate"));
			
			return esm;
		}
	}
}