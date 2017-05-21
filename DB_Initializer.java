import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DB_Initializer {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String  url="jdbc:mysql://URL" ; 
		Connection conn = null;
		Statement stmt;
		String username = "NAME";
		String password = "PW";
		ResultSet resSet;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception exc){
			System.out.println ( exc.getMessage() );
		}
		
		try{
			conn = DriverManager.getConnection(url,username,password);
		}
		catch(Exception exc)
		{
			System.out.println ( exc.getMessage() ); 
		}
		try{
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS pass_makes");
			stmt.executeUpdate("DROP TABLE IF EXISTS res_has");
			stmt.executeUpdate("DROP TABLE IF EXISTS trip_has");
			stmt.executeUpdate("DROP TABLE IF EXISTS has_driver" );
			stmt.executeUpdate("DROP TABLE IF EXISTS checks");
			stmt.executeUpdate("DROP TABLE IF EXISTS pass_complaints");
			stmt.executeUpdate("DROP TABLE IF EXISTS trip" );
			stmt.executeUpdate("DROP TABLE IF EXISTS reviews" );
			stmt.executeUpdate("DROP TABLE IF EXISTS wallet");
			stmt.executeUpdate("DROP TABLE IF EXISTS transaction");
			stmt.executeUpdate("DROP TABLE IF EXISTS reservations" );
			stmt.executeUpdate("DROP TABLE IF EXISTS checkpoint");
			stmt.executeUpdate("DROP TABLE IF EXISTS route" );
			stmt.executeUpdate("DROP TABLE IF EXISTS vehicle");
			stmt.executeUpdate("DROP TABLE IF EXISTS driver");
			stmt.executeUpdate("DROP TABLE IF EXISTS passenger" );
			stmt.executeUpdate("DROP TABLE IF EXISTS user");
			stmt.executeUpdate("DROP TABLE IF EXISTS customer_service");
			stmt.executeUpdate("DROP TABLE IF EXISTS user_login" );
			
			System.out.println("Existing tables dropped!");
			
			
			//user_login
			stmt.executeUpdate("CREATE TABLE user_login"
					+ "(user_id 			int PRIMARY KEY,"
					+ " username 		VARCHAR(20) NOT NULL UNIQUE,"
					+ " email 			VARCHAR(20) NOT NULL,"
					+ " password 		VARCHAR(20) NOT NULL )ENGINE=INNODB");
			
					
			//  USER	
			stmt.executeUpdate("CREATE TABLE user" + 
					"(user_id 				int PRIMARY KEY,"
					+ " name 				VARCHAR(20) NOT NULL,"
					+ " surname 			VARCHAR(20) NOT NULL,"
					+ " age 				int,"
					+ " gender 				VARCHAR(10),"
					+ " phone_number 		int,"
					+ " birth_year 			int,"
					+ "FOREIGN KEY(user_id) references user_login(user_id) ON DELETE CASCADE)ENGINE=INNODB");
			
			//	CUSTOMER SERVICE	
			stmt.executeUpdate("CREATE TABLE customer_service("
					+ " user_id 			int PRIMARY KEY,"
					+ " FOREIGN KEY(user_id) REFERENCES user_login(user_id) ON DELETE CASCADE)ENGINE=INNODB");
			
			//	PASSENGER	
			stmt.executeUpdate("CREATE TABLE passenger"+ 
					"(user_id 				int PRIMARY KEY,"
					+ " FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE)ENGINE=INNODB");
			//	DRIVER	
			stmt.executeUpdate("CREATE TABLE driver"+ 
					"(user_id int PRIMARY KEY,"
					+ " driver_license_number 				int NOT NULL UNIQUE,"
					+ " driver_license_type 				VARCHAR(20),"
					+ " driver_license_expiration_y			int,"
					+ " driver_license_expiration_m			int,"
					+ " FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE)ENGINE=INNODB");
			//	VEHICLE	
			stmt.executeUpdate("CREATE TABLE vehicle"
					+ "(v_id 					int,"
					+ "	manufacturer			varchar(20),"
					+ " model 					varchar(20), "
					+ "	color					varchar(20),"
					+ "	plate_number			varchar(20),"
					+ "	primary key(v_id) ) ENGINE=INNODB");
			//	WALLET
			stmt.executeUpdate("Create table wallet("
					+ "	user_id			int,"
					+ "	balance			int,"
					+ "	primary key(user_id),"
					+ "	foreign key(user_id) references user(user_id) ON DELETE CASCADE)ENGINE=INNODB");
			//	TRANSACTION	
			stmt.executeUpdate("Create table transaction("
					+ "	transaction_id		int,"
					+ "	sender_id			int,"
					+ "	receiver_id			int,"
					+ "	amount				int,"
					+ "	status				varchar(10),"
					+ "	date_of_submission 	varchar(10),"	//Can make it date
					+ "	primary key(transaction_id),"
					+ "	foreign key(sender_id) references passenger(user_id) ON DELETE CASCADE,"
					+ "	foreign key(receiver_id) references driver(user_id) ON DELETE CASCADE)ENGINE=INNODB");

			//	ROUTE
			stmt.executeUpdate("Create table route("
					+ "	r_id		int,"
					+ "	primary key(r_id) )");

			
			//	CHECKPOINT	
			stmt.executeUpdate("Create table checkpoint("
					+ "	cp_id			int,"
					+ "r_id				int, "
					+ "location_name	varchar(20), "
					+ "location_lat		int, "
					+ "lacation_lon		int, "
					+ "Price			int,"
					+ "ETA_hour			int, "
					+ "ETA_min			int,"
					+ "Primary key(cp_id),"
					+ "Foreign key(r_id) references route(r_id) ON DELETE CASCADE)ENGINE=INNODB");
			
			//	RESERVATIONS	
			stmt.executeUpdate("Create table reservations("
					+ "	reservation_id 		int,"
					+ "	user_id				int,"
					+ "	enter_checkpoint	int, "
					+ " exit_checkpoint		int,"
					+ " Cost				int,"
					+ " status				varchar(10),"
					+ "	primary key(reservation_id ),"
					+ " foreign key(user_id) references passenger(user_id) ON DELETE CASCADE,"
					+ "	foreign key(enter_checkpoint) references checkpoint(cp_id) ON DELETE CASCADE,"
					+ "	foreign key(exit_checkpoint) references checkpoint(cp_id) ON DELETE CASCADE)ENGINE=INNODB");
			//	REVIEWS	
			stmt.executeUpdate("Create table reviews("
					+ "	review_id		int,"
					+ "	user_id			int, "
					+ " rating			int, "
					+ " primary key(review_id ),"
					+ "	foreign key(user_id) references passenger(user_id))ENGINE=INNODB");	//made some changes to initial report
			//	TRIP	
			stmt.executeUpdate("Create table trip("
					+ "	trip_id					int,"
					+ "	time_of_departure_h		int, "
					+ " time_of_departure_m		int, "
					+ " status 					varchar(20), "
					+ " primary key(trip_id ))");
						
			
			//	PASS_COMPLAINTS	
			stmt.executeUpdate("Create table pass_complaints("
					+ "	pass_id			int, "
					+ " service_id				int, "
					+ " subject 		varchar(10), "
					+ " message      	varchar(20), "
					+ " time			int, "
					+ "	primary key( pass_id, service_id ),"
					+ " FOREIGN KEY(pass_id) REFERENCES passenger(user_id),"
					+ " FOREIGN KEY(service_id) REFERENCES customer_service(user_id) )ENGINE=INNODB");

			
			//	CHECKS	
			stmt.executeUpdate("Create table checks("
					+ "	reservation_id		int, "
					+ " transaction_id		int, "
					+ "	primary key(reservation_id,transaction_id),"
					+ " FOREIGN KEY(reservation_id) REFERENCES reservations(reservation_id),"
					+ " FOREIGN KEY(transaction_id) REFERENCES transaction(transaction_id))ENGINE=INNODB");
			//	RES_HAS	
			stmt.executeUpdate("Create table res_has("
					+ "	reservation_id				int, "
					+ " review_id					int, "
					+ "	primary key(reservation_id, review_id),"
					+ "	foreign key(reservation_id) references reservations(reservation_id),"
					+ " foreign key(review_id) 		references reviews(review_id))ENGINE=INNODB");
			//	TRIP_HAS	
			stmt.executeUpdate("Create table trip_has("
					+ "	r_id				int, "
					+ " trip_id 			int, "
					+ "	primary key(r_id, trip_id ),"
					+ "	foreign key(r_id)      references route(r_id),"
					+ " foreign key( trip_id ) references trip(trip_id))ENGINE=INNODB");
			//	HAS_DRIVER
			stmt.executeUpdate("Create table has_driver("
					+ "	user_id				int, "
					+ " trip_id 			int, "
					+ "	primary key(user_id, trip_id ),"
					+ "	foreign key(user_id) references driver(user_id),"
					+ " foreign key(trip_id) references trip(trip_id))ENGINE=INNODB");
			
			//	PASS_MAKES
			stmt.executeUpdate("CREATE TABLE pass_makes("
					+ "reservation_id		int,"
					+ "user_id				int,"
					+ "trip_id				int,"
					+ "PRIMARY KEY(reservation_id, user_id, trip_id),"
					+ "FOREIGN KEY(reservation_id) REFERENCES reservations(reservation_id),"
					+ "FOREIGN KEY(user_id) REFERENCES passenger(user_id),"
					+ "FOREIGN KEY(trip_id) REFERENCES trip(trip_id))ENGINE=INNODB");		
			
			//stmt.executeUpdate("INSERT INTO account " +
			//	      "VALUES ('A0000007', 'umitkoy', 6000.00, '2017-01-01')" );		
		}
		catch(Exception exc){	 
			System.out.println ( exc.getMessage() );
		}
				
		System.out.println("Done Initializing!");		
		/*try{
			stmt = conn.createStatement();
			resSet = stmt.executeQuery("select * from customer");
			System.out.println("Customer:");
			while(resSet.next()){
				System.out.println(resSet.getString("cid") + ", " + resSet.getString("name") 
								+ ", " + resSet.getString("bdate") + ", " + resSet.getString("address")
								+ ", " + resSet.getString("city") + ", " + resSet.getString("nationality"));
			}
			System.out.println ("\nAccount:");
			resSet = stmt.executeQuery("select * from account");
			while(resSet.next()){	
				System.out.println(resSet.getString("aid") + ", " + resSet.getString("branch")
						+ ", " + resSet.getFloat("balance")+ ", " + resSet.getString("openDate"));
			}
			System.out.println ("\nOwns:");
			resSet = stmt.executeQuery("select * from owns");
			while(resSet.next()){	
				System.out.println(resSet.getString("cid") + ", " + resSet.getString("aid"));
			}
			System.out.println ();
		}catch(Exception exc){
			 System.out.println ( exc.getMessage() ); 
		}*/
	}

}
