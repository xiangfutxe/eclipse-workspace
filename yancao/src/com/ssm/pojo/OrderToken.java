package com.ssm.pojo;

public class OrderToken {
	private static final long serialVersionUID = 1L;
	private String IsSuccess;
	private String ReturnData;
	private String ErrorNo;
	private String ErrorMessage;
	private String ReturnData2;
	private String ReturnDataList;
	private int TotalRow;
	private String OtherDefined;
	public String getIsSuccess() {
		return IsSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		IsSuccess = isSuccess;
	}
	public String getReturnData() {
		return ReturnData;
	}
	public void setReturnData(String returnData) {
		ReturnData = returnData;
	}
	public String getErrorNo() {
		return ErrorNo;
	}
	public void setErrorNo(String errorNo) {
		ErrorNo = errorNo;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public String getReturnData2() {
		return ReturnData2;
	}
	public void setReturnData2(String returnData2) {
		ReturnData2 = returnData2;
	}
	public String getReturnDataList() {
		return ReturnDataList;
	}
	public void setReturnDataList(String returnDataList) {
		ReturnDataList = returnDataList;
	}
	public int getTotalRow() {
		return TotalRow;
	}
	public void setTotalRow(int totalRow) {
		TotalRow = totalRow;
	}
	public String getOtherDefined() {
		return OtherDefined;
	}
	public void setOtherDefined(String otherDefined) {
		OtherDefined = otherDefined;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
