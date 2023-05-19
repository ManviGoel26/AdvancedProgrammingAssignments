import java.util.ArrayList;
import java.util.Scanner;


// Assumptions.
// 1. The program terminates when all patients are filled. 
// 2. If we delete the hospital using query 3, we cannot access its details or patients via query 6 and 9.
// 3. If we delete the patient using query 2, they can be accessed via query 6,7, and 9 if the hospitals are either OPEN or query 3 is not used yet.
// 4. If we write the wrong name of the hospital or wrong ID, the query will not give any result.
// 5. The outputs are a little different than the sample case but the inputs are same.
// 6. In query 7, if the patient is not yet admitted than the output is simply the details given by the user.
// 7. Body temperature and oxygen level are considered independent variables, with preference to oxygen level.


//Patient Class
class Patient{
	private String patient_name;
	int age;
	private int Oxygen_Level;
	private float temperature;
	private int recover_days;
	private int ID;
	private int status = 0; // if admitted then change to 1, not admitted then 0.
	Hospital hospital_admitted;
	static int counter = 0;
	
	Patient(String p, float t, int o, int a){
		patient_name = p;
		age = a;
		Oxygen_Level = o;
		temperature = t;
		counter++;
		ID = counter;
		hospital_admitted = null;
	}
	
//	getter functions
	public int getOxygen_level() {
		return this.Oxygen_Level;
	}
	
	public float getBodyTemperature() {
		return this.temperature;
	}
	
	public int get_status() {
		return this.status;
	}
	
	public String getName() {
		return this.patient_name;
	}
	
	public int getDays() {
		return this.recover_days;
	}
	
	public int getID() {
		return this.ID;
	}
	
	
//	setter functions
	public void setRecoveryDays(int d) {
		this.recover_days = d;
	}
	
	public void setHospital(Hospital H) {
		this.hospital_admitted = H;
	}
	
	public void setStatus() {
		this.status = 1;
	}
	
	
//	 static functions
	public static void patient_details(Patient patient) {
//		function number 7
		System.out.println("The name of the patient is " + patient.patient_name+ " . The Body Temperature is " + patient.temperature + " and the oxygen level is " + patient.Oxygen_Level + " . ");
		if (patient.hospital_admitted != null) {
			System.out.println("The patient is already admitted and the name of the health care institute they are admitted to is " + patient.hospital_admitted.getname());
		}
	}
	
	
	
	public static void display_details(Patient patient) {
//		function number 8
		System.out.println("Patient : "+ patient.patient_name + " Patient ID : "+ patient.ID);
		}
	
}




//class hospital
class Hospital{
	private String hospital_name;
	private int number_of_beds;
	private float max_temperature;
	private int min_oxygen_level;
	private int admission_status;
	private ArrayList<Patient> AdmittedPatients = new ArrayList<Patient>();
	
	Hospital(String n, float t, int o, int b){
		hospital_name = n;
		max_temperature = t;
		number_of_beds = b;
		min_oxygen_level = o;
		admission_status = 0;
		
	}
	
//	getter functions
	
	public void addPatient(Patient p) {
		this.AdmittedPatients.add(p);
	}
	public int getBeds() {
		return this.number_of_beds;
	}
	
	public int getOxygenLevel() {
		return this.min_oxygen_level;
	}
	
	public float getTemperature() {
		return this.max_temperature;
	}
	public String getname() {
		return this.hospital_name;
	}
	
	public int getAdmissionStatus() {
		return this.admission_status;
	}
	
	
//	setter functions
	public void setBeds() {
		this.number_of_beds--;
	}
	
	public void setAdmissionStatus() {
		this.admission_status = 1;
	}
	
//	static functions
	
	public static void display_details(Hospital hospital) {
//		function number 6
		System.out.println("The name of the health care institute is " + hospital.hospital_name);
		System.out.println("The minimum oxygen level can be " + hospital.min_oxygen_level);
		System.out.println("The maximum body temperature can be " + hospital.max_temperature);
		System.out.println("The number of vacant beds are " + hospital.number_of_beds);
		if (hospital.getAdmissionStatus() == 0) {
			System.out.println("The Admission Status is OPEN");
		}
		else {
			System.out.println("The Admission Status is CLOSED" );
		}
	}
	
	public static void patient_details(Hospital hospital) {
//		function number 9
		if (hospital.AdmittedPatients.size() != 0) {
			System.out.println("The patients admitted to the health care institute are : ");
			for (int i = 0; i < hospital.AdmittedPatients.size(); i++) {
				System.out.println("Patient : "+ hospital.AdmittedPatients.get(i).getName()+ " Patient ID : "+ hospital.AdmittedPatients.get(i).getID()+ " Days to Recovery : "+ hospital.AdmittedPatients.get(i).getDays());
			}
		}
		else {
			System.out.println("No patients admitted");
		}
	}
	
}



public class L1T1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of patients : ");
		int number_of_patients = sc.nextInt();
		
		
		ArrayList<Patient> list_of_patients = new ArrayList<Patient>();
		ArrayList<Hospital> list_of_hospital = new ArrayList<Hospital>();
		
		
		for (int i = 0; i < number_of_patients; i++) {			
			Patient P1 = new Patient(sc.next(), sc.nextFloat(), sc.nextInt(),sc.nextInt());
			list_of_patients.add(P1);
		}
		
		
		while (number_of_patients > 0) {
			
			System.out.println("Enter the query : ");
			int query = sc.nextInt();
			
			
			if (query == 1) {

				System.out.println("Health Care Institute Information");
				System.out.print("Health Care Institute name : ");
				String H_name = sc.next();
				System.out.print("The maximum body temerature allowed : ");
				float H_temp = sc.nextFloat();
				System.out.print("The minimum oxygen level allowed : ");
				int H_oxy = sc.nextInt();
				System.out.print("Number of Beds available : ");
				int H_bed = sc.nextInt();
				
				
				Hospital H = new Hospital(H_name, H_temp, H_oxy, H_bed);
				
				list_of_hospital.add(H);
				Hospital.display_details(H);
				
//				inserting patients
//				based on oxygen level
				int c = 0;
				while (number_of_patients > 0 && H.getBeds() > 0 && c < list_of_patients.size()) {
					if (list_of_patients.get(c).getOxygen_level() >= H.getOxygenLevel() && list_of_patients.get(c).get_status() == 0) {
						H.addPatient(list_of_patients.get(c));
						System.out.print("Recovery Days for admitted Patient " + list_of_patients.get(c).getID() + " ");
						list_of_patients.get(c).setRecoveryDays(sc.nextInt()); 
						list_of_patients.get(c).setHospital(H); 
						list_of_patients.get(c).setStatus();
						number_of_patients--;
						H.setBeds();
						
					}
					c++;
				}
				
//				based on body temperature
				c = 0;
				while (number_of_patients > 0 && H.getBeds() > 0 && c < list_of_patients.size()) {
					if (list_of_patients.get(c).getBodyTemperature() <= H.getTemperature() && list_of_patients.get(c).get_status() == 0) {
						H.addPatient(list_of_patients.get(c));
						System.out.print("Recovery Days for admitted Patient " + list_of_patients.get(c).getID() + " ");
						list_of_patients.get(c).setRecoveryDays(sc.nextInt());
						list_of_patients.get(c).setHospital(H); 
						list_of_patients.get(c).setStatus();
						number_of_patients--;
						H.setBeds();
						
					}
					c++;
				}
				
				if (H.getBeds() == 0) {
					H.setAdmissionStatus();
				}
			}
					
			
			else if (query == 2) {
				
				System.out.println("The patients admitted are : ");
				for (int i = list_of_patients.size()-1; i >= 0 ; i--) {
					if (list_of_patients.get(i).get_status() != 0) {
						System.out.println("Patient ID : " + list_of_patients.get(i).getID());
						list_of_patients.remove(i);
						
					}
				}
			}
			
			
			else if (query == 3) {
			
				System.out.println("The health care centers with Admission status CLOSED are : ");
				for (int i = list_of_hospital.size()-1; i >= 0 ; i--) {
					if (list_of_hospital.get(i).getAdmissionStatus() != 0) {
						System.out.println(list_of_hospital.get(i).getname());
						list_of_hospital.remove(i);
					}
				}
				
			}

			
			
			else if (query == 4) {
				System.out.println("The number of patients in camp are : " + number_of_patients);
			}

			
			
			else if (query == 5) {
				int count = 0;
				
				for (int i = 0; i < list_of_hospital.size(); i++) {
					if (list_of_hospital.get(i).getAdmissionStatus() == 0) {
						count++;
					}
				}
				
				System.out.println("The number of health care institutes accepting patients are : " + count);
			}

			
			else if (query == 6) {
			
				System.out.println("Please input the name of the health care center : ");
				String name = sc.next();
				
				for (int i = 0; i < list_of_hospital.size(); i++) {
					if (name.equalsIgnoreCase(list_of_hospital.get(i).getname())) {
						Hospital.display_details(list_of_hospital.get(i));
						break;
					}
				}
			}

			
			else if (query == 7) {
			
				System.out.println("Please input the ID of the patient : ");
				int ID = sc.nextInt();
				
				for (int i = 0; i < list_of_patients.size(); i++) {
					if (ID == list_of_patients.get(i).getID()){
						Patient.patient_details(list_of_patients.get(i));
						break;
					}
				}
				
			}

			
			else if (query == 8) {
				
				System.out.println("The patients currently in the application are: ");
				
				for (int i = 0; i < list_of_patients.size(); i++) {
					Patient.display_details(list_of_patients.get(i));
				}
				
			}
			
			
			else if (query == 9) {
			
				System.out.println("Please input the name of the health care center : ");
				String name = sc.next();
				
				for (int i = 0; i < list_of_hospital.size(); i++) {
					if (name.equalsIgnoreCase(list_of_hospital.get(i).getname())) {
						Hospital.patient_details(list_of_hospital.get(i)); 
						break;
					}
				}
				
				
			}

			
		}
		sc.close();
		System.out.println("Program Terminated.");
	}

}
