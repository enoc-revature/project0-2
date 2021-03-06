/*
 * JDBC - Java Database Connectivity 
 * 
 * Important Interfaces:
 * - Connection -> allows us to connect to our db
 * - Statement -> raw SQL query
 * - Prepared Statement -> precompiles the SQL string without parameters,
 * once parameters are added, they are only treated as values, never
 * keywords
 * - Callable Statement -> same idea as ps, also prevents SQL
 * injection but is typically used for stored procedures (PL/SQL)
 */
package com.revature.jdbc;

import com.revature.project0_2.dao.InterfaceDAO;

public class SqlDao<T> implements InterfaceDAO<T>{



	// There should be system variables once connected to AWS:RDS
	// Note: STS will not have access to new environment variables until
	// you restart it
	private static String url = "jdbc:postgresql://localhost:5432/postgres";
	// jdbc:postgresql://host:port/database_name
	private static String username = "postgres";
	private static String password = "postgres";

	@Override
	public void writeSerial(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T readSerial(String id, char type) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	@Override
	public void insertPet(Pet p) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			// putting in a native sql query utilizing a prepared statement
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Pets VALUES(?,?)");
			ps.setString(1, p.getName());
			// we are setting the first question mark to be the name that belongs
			// to our pet object
			ps.setString(2, p.getType());
			// we are setting the second question mark to be the type that belongs
			// to our pet object
			ps.execute();
			// allows us to execute a query without a result

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/

	/*
	@Override
	public Pet selectPetByName(String name) {
		Pet pet = null;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pets WHERE name=?");
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a ResultSet type (object)
			while(rs.next()) {
				pet = new Pet(rs.getString("name"), rs.getString("type"));
				//we are iterating through our result set and populating our
				//pet object with the values that are coming from the
				//table's columns
				//"name" and "type" on line 67 are column names
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pet;
	}
	*/

	/*
	@Override
	public List<Pet> selectAllPets() {
		List<Pet> pets = new ArrayList<Pet>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pets");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pets.add(new Pet(rs.getString(1), rs.getString(2)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pets;
	}
	*/

	/*
	@Override
	public void updatePet(Pet p) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

		PreparedStatement ps = conn.prepareStatement("UPDATE Pets SET type=? WHERE name=?");
		ps.setString(1, p.getType());
		ps.setString(2, p.getName());
		ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	*/

	/*
	@Override
	public void deletePet(Pet p) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Pets WHERE name=?");
			ps.setString(1, p.getName());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}

