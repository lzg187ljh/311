package comp311;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ReportController {
	
	//get all record from database, then store into a list
	public List<Record> list() {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				String Category = rs.getString("Category");
				int Currency = rs.getInt("Currency");

				String Merchant_Name = rs.getString("Merchant_Name");
				Date date = rs.getDate("myDate");

				record.setMerchant_Name(Merchant_Name);
				record.setCategory(Category);
				record.setCurrency(Currency);
				record.setMyDate(date);
				record.setId(id);
				records.add(record);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return records;
	}
	
	public void DisplayRecordList(List<Record> records) {
		for (Record record : records) {
           //printf each of record in the list
			System.out.println(record.getCategory() + "    ");
		}
	}
	
	public List<Record> list(Date start, Date end) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where myDate >=? and myDate <= ?";
		try (Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDate(1, DateUtil.util2sql(start));
			ps.setDate(2, DateUtil.util2sql(end));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				String Category = rs.getString("Category");
				int Currency = rs.getInt("Currency");

				String Merchant_Name = rs.getString("Merchant_Name");
				Date date = rs.getDate("myDate");

				record.setMerchant_Name(Merchant_Name);
				record.setCategory(Category);
				record.setCurrency(Currency);
				record.setMyDate(date);
				record.setId(id);
				records.add(record);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> listThisMonth() {
		return list(DateUtil.getMonthBegin(), DateUtil.getMonthEnd());
	}
	
	public int getSpendByRecordList(List<Record> records) {
		
		int Spend = 0;
        for (Record record : records) {
                Spend+=record.getCurrency();
            
        }
        return Spend;
	}
	
	public Report InvokeGenerateReport(Date StartDate, Date EndDate) {		//生成一个report并返回, report 不会再存在report list里了
		List<Record> rList= list(StartDate, EndDate);	// list the records from start date to end date
		int TotalExpense = getSpendByRecordList(rList);
		Report report = new Report(TotalExpense,StartDate,EndDate);
		return report;
	}
}
