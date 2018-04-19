package com.spring.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dao.OperationDao;
import com.spring.dao.StudentDao;
import com.spring.model.FormDetails;
import com.spring.model.StudentModel;

@Controller
public class NavigationController {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private OperationDao operationDao;

	@RequestMapping(value = "/studentAdmission")
	public String studentForm(Model model) {
		System.out.println(studentDao.getAdmissionClass());
		model.addAttribute("interest", studentDao.SpecialInterest());
		model.addAttribute("housegroup", studentDao.HouseGroup());
		model.addAttribute("section", studentDao.getSection());
		model.addAttribute("classlist", studentDao.getAdmissionClass());
		model.addAttribute("dislist", studentDao.getDistricts());
		model.addAttribute("disabledlist", studentDao.getDisabledType());

		return "student/studentRegistration";
	}

	@RequestMapping(value = "/createExam")
	public String createExam(Model model) {
		model.addAttribute("examlist",operationDao.getExamList());
		return "exam/createExam";
	}

	@RequestMapping(value = "/viewMuncipality")
	public String viewMuncipality() {
		return "onselectpages/viewMuncipality";
	}

	@RequestMapping(value = "/viewWardNo")
	public String viewWardNo() {
		return "onselectpages/viewWardNo";
	}

	@RequestMapping(value = "/listStudents")
	public String listStudents(Model model) {
		List<StudentModel> list = studentDao.getAllStudents();
		model.addAttribute("slist", list);
		return "student/registeredstudents";
	}

	@RequestMapping(value = "/generalSettings")
	public String generalSettings() {

		return "settings/generalSettings";
	}

	@RequestMapping(value = "/profileSettings")
	public String profileSettings() {
		return "settings/userprofile";
	}
	
	@RequestMapping(value="/initialDetails")
	public String initialDetails(Model model){
		model.addAttribute("language",studentDao.getLanguages());
		model.addAttribute("section",studentDao.getSection());
		model.addAttribute("housegroup",studentDao.HouseGroup());
		model.addAttribute("caste",studentDao.getCaste());
		model.addAttribute("specialinterest",studentDao.SpecialInterest());
		model.addAttribute("adclass",studentDao.getAdmissionClass());
		model.addAttribute("examtype",studentDao.getExamType());
		return "initialdetail/initialdetails";
	}
	
	@RequestMapping(value="/subjects")
	public String subjects(Model model, @ModelAttribute("msg") String msg){
		model.addAttribute("subject",operationDao.getSubjectList());
		return "academics/subjects/subjects";
	}
	@RequestMapping(value="/assignSubjects")
	public String assignSubjects(Model model){
		List<FormDetails>  classlist,subjectlist;
		classlist=studentDao.getAdmissionClass();
		subjectlist=operationDao.getSubjectList();
		
		model.addAttribute("classlist",classlist);
		model.addAttribute("subjectlist",subjectlist);
		
		return "academics/subjects/assignsubjects";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(){
		return "dashboard";
	}
	@RequestMapping("/createMarksReport")
	public String createMarksReport(){
		return "exam/createStudentReport";
	}
	@RequestMapping("/marksReportSearch")
	public String marksReportSearch(){
		return "exam/marksSearchBox";
	}
}

