package I2L.databaseActions;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

//En cas de probleme de connexion changer la ligne du fichier config.inc.php
// ===>    $cfg['Servers'][$i]['host'] = '127.0.0.1';
// et mettre l'addresse IP ou nom d'hote

public class DatabaseActions {
	
	String url = "jdbc:mysql://frcs1658:3306/";
	String dbName = "gpi";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root"; 
	String password = "sim62890";

	public DatabaseActions(String userName, String password)
	{
		this.userName = userName; 
		this.password = password;

	}
	
	public void selectQuery(String query) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//Méthode servant d'exemple
		Class.forName(driver).newInstance();
		Connection conn = (Connection) DriverManager.getConnection(url+dbName,userName,password);
		Statement st = (Statement) conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM  event");
		while (res.next()) {
			int id = res.getInt("id");
			String msg = res.getString("msg");
			System.out.println(id + "\t" + msg);
		}
		conn.close();
	}

	public static boolean insertQuery(String query)
	{
		//Méthode statique pour faire une requete d'insertion
		
		//Adresse de la base
		String url = "jdbc:mysql://FRCS1658:3306/";
		//nom de la base
		String dbName = "gpi";
		String driver = "com.mysql.jdbc.Driver";
		//identifiants
		String userName = "root"; 
		String password = "sim62890";
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url+dbName,userName,password);
			Statement st = (Statement) conn.createStatement();
			
			//Execution de la requete
			int val = st.executeUpdate(query);
			if(val==1)
				//Si la requete a ete correctement effectuée
				System.out.print("Successfully inserted value"); conn.close(); return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isThereAlreadyALine(String machineName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//Méthode statique servant a detecter si il existe deja une ligne dans la base
		// pour la machine passée en parametre
		
		String url = "jdbc:mysql://FRCS1658:3306/";
		String dbName = "gpi";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "sim62890";
		
		Class.forName(driver).newInstance();
		Connection conn = (Connection) DriverManager.getConnection(url+dbName,userName,password);
		Statement st = (Statement) conn.createStatement();
		
		//Requete de selection pour la machine en parametre
		String query = "SELECT * FROM  ordinateur WHERE nom_ordinateur = '" + machineName + "';";
		ResultSet res = st.executeQuery(query);
		System.out.println("Requete : \t" + query);
		
		//Variable pour compter les resultats
		int nb_resultats = 0;
		while (res.next()) {
			int id = res.getInt("id_ordinateur");
			String msg = res.getString("nom_ordinateur");
			System.out.println(id + "\t" + msg);
			nb_resultats++;
		}
		conn.close();
		
		if(nb_resultats > 0) return true;
		return false;
	}

}



