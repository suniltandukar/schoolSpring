package com.spring.model;

public class AccountModel {
private String  accountNumber,alternativeAccountId, accountName, limitRef, inputter , previousAccountNumber;


public String getPreviousAccountNumber() {
	return previousAccountNumber;
}
public void setPreviousAccountNumber(String previousAccountNumber) {
	this.previousAccountNumber = previousAccountNumber;
}
public String getInputter() {
	return inputter;
}
public void setInputter(String inputter) {
	this.inputter = inputter;
}
public StudentModel getStudentModel() {
	return studentModel;
}
public void setStudentModel(StudentModel studentModel) {
	this.studentModel = studentModel;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
public String getAlternativeAccountId() {
	return alternativeAccountId;
}
public void setAlternativeAccountId(String alternativeAccountId) {
	this.alternativeAccountId = alternativeAccountId;
}
public String getAccountName() {
	return accountName;
}
public void setAccountName(String accountName) {
	this.accountName = accountName;
}
public String getLimitRef() {
	return limitRef;
}
public void setLimitRef(String limitRef) {
	this.limitRef = limitRef;
}
public CategoryModel getCategoryModel() {
	return categoryModel;
}
public void setCategoryModel(CategoryModel categoryModel) {
	this.categoryModel = categoryModel;
}
public AccountTypeModel getAccountTypeModel() {
	return accountTypeModel;
}
public void setAccountTypeModel(AccountTypeModel accountTypeModel) {
	this.accountTypeModel = accountTypeModel;
}

private CategoryModel categoryModel;
private AccountTypeModel accountTypeModel;
private StudentModel studentModel;


@Override
public String toString() {
	return "AccountModel [accountNumber=" + accountNumber + ", alternativeAccountId=" + alternativeAccountId
			+ ", accountName=" + accountName + ", limitRef=" + limitRef + ", categoryModel=" + categoryModel
			+ ", accountTypeModel=" + accountTypeModel + ", studentModel=" + studentModel + "]";
}

}