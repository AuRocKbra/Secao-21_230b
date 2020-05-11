package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Programa {

	public static void main(String[] args) {
		Connection conexao =null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conexao=DB.getConection();
			st = conexao.createStatement();
			rs = st.executeQuery("select seller.*, department.name from seller inner join department on seller.DepartmentId=department.Id;");
			while(rs.next()) {
				System.out.println("["+rs.getInt("ID")+","+rs.getString("Name")+","+rs.getString("Email")+","+rs.getDate("BirthDate")+","+String.format("%.2f",rs.getDouble("BaseSalary"))+","+rs.getInt("DepartmentId")+","+rs.getString("department.name")+"]");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
