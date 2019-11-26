package comp311;

import java.util.Date;
import java.util.List;

public class UserProgram {
	private Date myDate;		//record's attributes
	private String Merchant_Name;
	private int id;
	private String Category;
	private int Currency;
	
	private int SelectionNum;
	
	private Date StartDate;		//report's attributes
	private Date EndDate;
	//private int TotalExpense;
	
	public void CreateOperation(int SelectionNum) {
		switch(SelectionNum) {
		case 0:		//create record
			InputRecordInfo();
			RecordController rc1=new RecordController();
			rc1.CreateANewRecord(myDate,Merchant_Name,Category,Currency);
			break;
		case 1:
			RecordController rc2=new RecordController();
			//rc2.EraseRecord(SelectARecord());
			break;
		case 2:
			RecordController rc3=new RecordController();
			//rc3.EditRecord(SelectARecord());		//undefined so far
			break;
		case 3:		//generate and display report
			InputDateInterval();
			ReportController reportc=new ReportController();
			reportc.InvokeGenerateReport(StartDate, EndDate).DisplayReport(); 
			//还有一个choose monthly/daily/yearly	
			break;
		}
		
	}
	
	public void InputRecordInfo() {
		//输入创建record时的记录信息
	}
	
	/*public int SelectARecord() {
		//返回选中的record的id
	}*/
	
	public void InputDateInterval() {
		//modify the local variable startdate & enddate
		
	}
	
	public void DisplayRecordList() {
		//retrieve whole list from database
		ReportController reportc=new ReportController();
		reportc.DisplayRecordList(reportc.list());		//reportc.list returns a list contains all records in DB
	}
	
	public void DisplayEmptyForm() {
		Record record = new Record();		//no attributes are initiated
		record.DisplayInfoForm();
	}
}
