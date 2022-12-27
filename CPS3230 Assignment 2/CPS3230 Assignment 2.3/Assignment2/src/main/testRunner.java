package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;


public class testRunner {
	Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public Random rand = new Random();
	int code;

	public void invalidLogin(Account account) {
		System.out.println("Bad login at: " + System.currentTimeMillis());
	}
	
	public void validLogin(Account account) {
		
		
	//	this.setEventLogType(5);
		System.out.println("Good login at: " + System.currentTimeMillis());
		
	}
	
	public void viewAlerts(Account account) throws IOException{
		System.out.println("Viewed alerts at: "+ System.currentTimeMillis());
		URL url = new URL("https://api.marketalertum.com/EventsLog/ff557502-1ba4-4578-b094-2efdd4375b1d"); 
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();             
	    connection.setRequestMethod("GET");
	    connection.setRequestProperty("Content-Type", "application/json");

	    InputStream inputStream = connection.getInputStream();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	    LineNumberReader rdr = new LineNumberReader(new InputStreamReader(inputStream)); 
	    StringBuilder result = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	    	if(rdr.getLineNumber()<3){
	        result.append(line+"\n");
	    	}
	    }

	    System.out.println("Content: "+result.toString());

	    reader.close();
	    inputStream.close();
	    connection.disconnect();

		//this.setEventLogType(7);

	}
	
	public void logout(Account account){
		account.setEventLogType(6);
		account.setLogged(false);
		System.out.println("User logged out at: "+ System.currentTimeMillis());
	}
	
	public void createAlert(Account account) throws IOException{
		final int randomNumber2 = rand.nextInt(10);
			
			
				account.setEmpty(false);
				URL url = new URL("https://api.marketalertum.com/Alert");
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setDoOutput(true);
				String jsonInputString = "{\n" +
		                "\"alertType\": 1,\n" +
		                "\"heading\": \"Xiaomi Mi E27 LED Essential RGB Smart Bulb \",\n" +
		                "\"description\": \"LIGHT-L26843\",\n" +
		                "\"url\": \"https://www.scanmalta.com/shop/xiaomi-mi-e27-led-essential-rgb-smart-bulb.html\",\n" +
		                "\"imageUrl\" : \"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/p/r/product-600259-main.jpg\",\n" +
		                "\"postedBy\": \"ff557502-1ba4-4578-b094-2efdd4375b1d\",\n" +
		                "\"priceInCents\":1995\n" +
		                "}";
				try(OutputStream os = connection.getOutputStream()) {
				    byte[] input = jsonInputString.getBytes("utf-8");
				    os.write(input, 0, input.length);			
				}
				code = connection.getResponseCode();
				System.out.println("Alert created at: "+ System.currentTimeMillis());
				System.out.println("Code:"+code);

			
		//	this.setEventLogType(0);
			
	}

	public void deleteAlerts(Account account) throws IOException{
		System.out.println("Alerts deleted at: "+ System.currentTimeMillis());
		URL url = new URL("https://api.marketalertum.com/Alert?userId=ff557502-1ba4-4578-b094-2efdd4375b1d"); 
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
	    connection.setDoOutput(true); 
	    connection.setInstanceFollowRedirects(false); 
	    connection.setRequestMethod("GET"); 
	    connection.setRequestProperty("Content-Type", "text/plain"); 
	    connection.setRequestProperty("charset", "utf-8");
	    connection.connect();
	    System.out.println("Code: "+ connection.getResponseCode());
	//	this.setEventLogType(1);

	}


	/*public void run(final Account account) throws IOException {
		//while(true){
		//	final int randomNumber = rand.nextInt(10);
			
		//	if (randomNumber < 7){
			//	this.badLogin();
				
			//		account.setLogged(false);
					
				
		//	} else {
			//	account.setEventLogType(5);
				
				account.validLogin();
				account.setLogged(true);
				
				
				account.createAlert();
				account.viewAlerts();

				account.deleteAlerts();
				
				account.logout();
			//} 
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	*/
	
	public static int read()
	{
		try{
		return Integer.parseInt(br.readLine());
		}
		catch(Exception ex)
		{ex.printStackTrace();}
		return -1;
	}
	
	public static void write(String text)
	{
		System.out.println(text);
	}
	
	public void menu(final Account account) throws IOException
	{
		boolean run = true;
		while (run)
		{
			System.out.println("****MAIN MENU****");
			if(account.isLoggedIn() == false){
			System.out.println("1. Login");
			}
			else{
			System.out.println("User Logged In.");	
			System.out.println("2. View Alerts");
			System.out.println("3. Logout");
			System.out.println("4. Create Alert");
			System.out.println("5. Delete Alerts");
			System.out.println("6. exit");
			}
			switch(read())
			{
			case 1:write("Id:     (ID is 1234 for this demo only)");
				
			String id = sc.next();
			if(id.equals("1234")){
				account.setEventLogType(5);
				account.setEmpty(true);
				account.setLogged(true);
				validLogin(account);				
			}
			else{ invalidLogin(account); };
			break;
			case 2:
				account.setEventLogType(7);
				viewAlerts(account);
			break;
			case 3:
				account.setEventLogType(6);
				account.setLogged(false);
				logout(account);
			break;
			case 4:
				final int randomNumber2 = rand.nextInt(10);
				if(randomNumber2 < 5){
					account.setStatus(400);
					System.out.println("Bad upload");
					createAlert(account);
				}
				else{
					account.setEventLogType(0);
					account.setEmpty(false);
					account.setStatus(201);
					createAlert(account);
				}
			
			break;
			case 5:
				account.setEventLogType(1);
				account.setStatus(200);
				account.setEmpty(true);
				deleteAlerts(account);
			break;
			case 6:run = false;break;
			}
		}
	}
	

// put Account parameter for each method and remove them from the account class. Then in menu call for example: case 1: account.validLogin();
	
	
	public static void main(String[] args) throws IOException {
		final testRunner m = new testRunner();
		final Account account = m.new Account(false,true,"1234",9,0);
		//m.run(account);
		m.menu(account);
	}
	
	public class Account{
		private boolean loggedIn;
		private String id;
		private int eventLogType;
		private boolean empty;
		private int status;
		int noOfAlerts = 0;
		
		public Account(final boolean loggedIn,final boolean empty, final String id, final int eventLogType, final int status) {
			super();
			this.loggedIn = loggedIn;
			this.id = id;
			this.eventLogType = eventLogType;
			this.empty = empty;
			this.status = status;
		}

		public boolean isLoggedIn() {
			return loggedIn;
		}

		public boolean isEmpty(){
			return empty;
		}
		
		public void setEmpty(boolean empty){
			this.empty = empty;
		}
		
		public void setLogged(boolean loggedIn) {
			
			this.loggedIn = loggedIn;
		}
		
		public void setStatus(int status){
			this.status = status;
		}
		
		public void setEventLogType(int eventLogType){
			this.eventLogType = eventLogType;			
		}
		
		public int getEventLogType(){
			return eventLogType;
		}

		public int getStatus(){
			return status;
		}
		
		public String getID() {
			return id;
		}

		public void setID(String id) {
			this.id = id;
		}
		
		public void badLogin() {
			System.out.println("Bad login at: " + System.currentTimeMillis());
		}
		
		public void validLogin() {
			
			this.setLogged(true);
		//	this.setEventLogType(5);
			System.out.println("Good login at: " + System.currentTimeMillis());
			
		}
		
		
		
	}
}
