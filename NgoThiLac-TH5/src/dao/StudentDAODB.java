package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.Student;
import utility.DBUtility;

public class StudentDAODB {

	Connection con;
	ResultSet rs;
	ArrayList <Student> list=new ArrayList<Student>();
	
	DBUtility dbu=new DBUtility();
	
	public void readAll() {
		list=new ArrayList<Student>();
		con=dbu.openConnection();
	
		try {
			rs=con.createStatement().executeQuery("SELECT *FROM Student");
			//chung nào resultset van con ket qua
			while(rs.next()) {
				// doc tư resultset vao  1 doi tuong student
				Student s= new Student();
				s.setCode(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setGender(rs.getInt(3));
				
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbu.closeConnection();
	}

		public void update(Student s) {
		con=dbu.openConnection();
		//cho phep dùng cau lenh sql co truyen tham so
		PreparedStatement pst= null;
		try {
			pst=con.prepareStatement("UPDATE Student SET Name=?,Gender=? WHERE Code=?");
			pst.setString(1, s.getName());
			pst.setInt(2, s.getGender());
			pst.setInt(3, s.getCode());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbu.closeConnection();
	}
		public void insert(Student s) {
			con=dbu.openConnection();
			PreparedStatement pst= null;
			try {
				pst=con.prepareStatement("INSERT into Student (Code,Name,Gender) values(?,?,?)");
				pst.setString(1, s.getName());
				pst.setInt(2, s.getGender());
				pst.setInt(3, s.getCode());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbu.closeConnection();
		}
		public void delete(Student s) {
//			System.out.println(code);
//			for (int i = 0; i < list.size(); i++) {
//				if (list.get(i).getCode() == code) {
//					list.remove(i);
					con=dbu.openConnection();
					PreparedStatement pst= null;
					try {
						pst=con.prepareStatement("DELETE FROM Student WHERE Code=?");
						pst.setInt(3, s.getCode());
						pst.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					dbu.closeConnection();
				}
			
		
			
		
	public static void main(String[] args) {
		StudentDAODB dao= new StudentDAODB();
		//Student s1=new Student(2, "Nguyễn Thị D",1);
	//dao.update(s1);
		//dao.readAll();
		ArrayList <Student> listStudent=dao.getList();
		for (Student s: listStudent) {
			System.out.println(s.getCode()+"\t" +s.getName()+"\t"+s.getGender()+"\t");
		}
		//Student s=new Student(2, "Nguyễn Thị D",1);
	}
	public ArrayList<Student> getList() {
		return list;
	}
	public void setList(ArrayList<Student> list) {
		this.list = list;
	}
}

