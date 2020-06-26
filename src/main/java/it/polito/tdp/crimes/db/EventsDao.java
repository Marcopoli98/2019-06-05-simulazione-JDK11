package it.polito.tdp.crimes.db;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.crimes.model.Event;
import it.polito.tdp.crimes.model.Info;

public class EventsDao {

	public List<Event> listAllEvents() {
		String sql = "SELECT * FROM events";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<Event> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"), res.getInt("offense_code"),
							res.getInt("offense_code_extension"), res.getString("offense_type_id"),
							res.getString("offense_category_id"), res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"), res.getDouble("geo_lon"), res.getDouble("geo_lat"),
							res.getInt("district_id"), res.getInt("precinct_id"), res.getString("neighborhood_id"),
							res.getInt("is_crime"), res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getAnni() {
		String sql = "SELECT distinct(YEAR(e.reported_date)) AS anno " + "FROM `events` e " + "ORDER BY anno ASC ";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<Integer> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(res.getInt("anno"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<Integer> getVertici() {
		String sql = "SELECT DISTINCT(e.district_id) " + "FROM `events` e " + "ORDER BY district_id ASC ";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<Integer> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(res.getInt("district_id"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public Map<Integer,Info> getArchi(int anno){
		String sql = "SELECT e1.district_id AS id1, AVG(e1.geo_lat) AS latitudine, AVG(e1.geo_lon) AS longitudine " + 
				"FROM `events` e1 " + 
				"WHERE YEAR(e1.reported_date) = ? " + 
				"GROUP BY e1.district_id";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			Map<Integer,Info> list = new HashMap<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					LatLng i = new LatLng(res.getDouble("latitudine"), res.getDouble("longitudine"));
					
					list.put(res.getInt("id1"), new Info(res.getInt("id1"), i));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<Integer> getMese (int anno){
		String sql = "SELECT distinct(MONTH(e.reported_date)) AS mese " + 
				"FROM `events` e " + 
				"WHERE YEAR(e.reported_date) = ? " + 
				"ORDER BY mese ASC ";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			List<Integer> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(res.getInt("mese"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Integer> getGiorno (int anno){
		String sql = "SELECT distinct(DAY(e.reported_date)) AS giorno " + 
				"FROM `events` e " + 
				"WHERE YEAR(e.reported_date) = ? " + 
				"ORDER BY giorno ASC ";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			List<Integer> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(res.getInt("giorno"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}
