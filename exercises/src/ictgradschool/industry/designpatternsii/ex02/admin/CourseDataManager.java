package ictgradschool.industry.designpatternsii.ex02.admin;

import ictgradschool.industry.designpatternsii.ex02.model.Percentage;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Program which creates a set of StudentResult objects and writes them to
 * disk.
 * 
 */
public class CourseDataManager {
	/*
	 * Name of data file storing StudentResult data.
	 */
	private static String DATA_FILE_NAME = "results.dat";
	
	public static void main(String[] args) {
		generateData();
	}

	/**
	 * Attempts to generate and write a List of StudentResult objects to file.
	 */
	public static void generateData() {
		StudentResult[] results = {
				new StudentResult(63514392, "Diamond", "Christopher",
						new Percentage(5), new Percentage(39), new Percentage(
								50), new Percentage(17)),
				new StudentResult(96076418, "Baxter", "Andrew", new Percentage(
						8), new Percentage(45), new Percentage(59),
						new Percentage(21)),
				new StudentResult(96356612, "Yu", "Jia", new Percentage(8),
						new Percentage(38), new Percentage(54), new Percentage(
								20)),
				new StudentResult(55474209, "Lloyd", "Nathan", new Percentage(
						12), new Percentage(56), new Percentage(85),
						new Percentage(31)),
				new StudentResult(48900686, "Chester", "Victoria",
						new Percentage(23), new Percentage(46), new Percentage(
								65), new Percentage(33)),
				new StudentResult(85605513, "Wade", "Steven",
						new Percentage(23), new Percentage(8), new Percentage(
								40), new Percentage(24)),
				new StudentResult(21174811, "Williams", "Robin",
						new Percentage(25), new Percentage(69), new Percentage(
								40), new Percentage(32)),
				new StudentResult(11743537, "Miller", "James", new Percentage(
						32), new Percentage(78), new Percentage(53),
						new Percentage(40)),
				new StudentResult(84120919, "Steele", "Edward", new Percentage(
						32), new Percentage(65), new Percentage(44),
						new Percentage(37)),
				new StudentResult(47658970, "Briggs", "Matthew",
						new Percentage(33), new Percentage(47), new Percentage(
								44), new Percentage(36)),
				new StudentResult(75342173, "Hannay", "Edward", new Percentage(
						33), new Percentage(98), new Percentage(44),
						new Percentage(41)),
				new StudentResult(52407323, "Meyers", "Daniel", new Percentage(
						33), new Percentage(44), new Percentage(45),
						new Percentage(36)),
				new StudentResult(51381806, "Walkden", "Paul", new Percentage(
						33), new Percentage(55), new Percentage(44),
						new Percentage(37)),
				new StudentResult(47888669, "Halpin", "James", new Percentage(
						34), new Percentage(33), new Percentage(45),
						new Percentage(36)),
				new StudentResult(89746645, "Porter", "Barry", new Percentage(
						34), new Percentage(56), new Percentage(46),
						new Percentage(38)),
				new StudentResult(57103455, "Balpakakis", "Stefanos",
						new Percentage(38), new Percentage(43), new Percentage(
								46), new Percentage(40)),
				new StudentResult(46345131, "Daniels", "Scott", new Percentage(
						38), new Percentage(97), new Percentage(46),
						new Percentage(45)),
				new StudentResult(69276305, "Carter", "Daniel", new Percentage(
						43), new Percentage(23), new Percentage(65),
						new Percentage(45)),
				new StudentResult(66465025, "Bolger", "Oliver", new Percentage(
						44), new Percentage(45), new Percentage(47),
						new Percentage(44)),
				new StudentResult(81564137, "Tsirkas", "Christos",
						new Percentage(44), new Percentage(38), new Percentage(
								98), new Percentage(54)),
				new StudentResult(85882987, "Wrigley", "Michael",
						new Percentage(44), new Percentage(7), new Percentage(
								44), new Percentage(40)),
				new StudentResult(68311797, "Askham", "Dennis", new Percentage(
						45), new Percentage(65), new Percentage(55),
						new Percentage(49)),
				new StudentResult(47637944, "Cornthwaite", "James",
						new Percentage(45), new Percentage(46), new Percentage(
								43), new Percentage(44)),
				new StudentResult(29700038, "Griffiths", "James",
						new Percentage(45), new Percentage(87), new Percentage(
								56), new Percentage(51)),
				new StudentResult(29700038, "Bateman", "Ryan", new Percentage(
						46), new Percentage(88), new Percentage(43),
						new Percentage(49)),
				new StudentResult(96576333, "Faulkner", "Matthew",
						new Percentage(46), new Percentage(66), new Percentage(
								97), new Percentage(58)),
				new StudentResult(59862735, "Lyon", "Stephen", new Percentage(
						46), new Percentage(78), new Percentage(43),
						new Percentage(48)),
				new StudentResult(44492295, "Blakelock", "William",
						new Percentage(47), new Percentage(65), new Percentage(
								45), new Percentage(48)),
				new StudentResult(36660575, "Lu", "Yu", new Percentage(49),
						new Percentage(54), new Percentage(46), new Percentage(
								48)),
				new StudentResult(94562464, "Tipper", "Paul",
						new Percentage(49), new Percentage(67), new Percentage(
								78), new Percentage(56)),
				new StudentResult(95919218, "White", "Jonathan",
						new Percentage(49), new Percentage(88), new Percentage(
								65), new Percentage(56)),
				new StudentResult(57352509, "Konylis", "Georgios",
						new Percentage(50), new Percentage(88), new Percentage(
								54), new Percentage(54)),
				new StudentResult(77838932, "Gatherar", "Paul", new Percentage(
						50), new Percentage(96), new Percentage(67),
						new Percentage(58)),
				new StudentResult(97624276, "Brampton", "Andrew",
						new Percentage(50), new Percentage(56), new Percentage(
								88), new Percentage(58)),
				new StudentResult(18462265, "Hooper", "Robert", new Percentage(
						50), new Percentage(45), new Percentage(88),
						new Percentage(57)),
				new StudentResult(10429920, "Li", "Chao", new Percentage(50),
						new Percentage(34), new Percentage(96), new Percentage(
								57)),
				new StudentResult(89066708, "Mannerings", "Barnaby",
						new Percentage(50), new Percentage(33), new Percentage(
								56), new Percentage(49)),
				new StudentResult(44945516, "Bamber", "Jeff",
						new Percentage(50), new Percentage(76), new Percentage(
								78), new Percentage(56)),
				new StudentResult(42626184, "Brunton", "Roy",
						new Percentage(51), new Percentage(55), new Percentage(
								45), new Percentage(51)),
				new StudentResult(68171577, "Greenall", "Peter",
						new Percentage(51), new Percentage(65), new Percentage(
								65), new Percentage(55)),
				new StudentResult(22728239, "Smoth", "John",
						new Percentage(51), new Percentage(69), new Percentage(
								54), new Percentage(53)),
				new StudentResult(78161860, "Williams", "Adam", new Percentage(
						52), new Percentage(63), new Percentage(67),
						new Percentage(56)),
				new StudentResult(78665838, "Mohla", "Avdhesh", new Percentage(
						53), new Percentage(56), new Percentage(88),
						new Percentage(60)),
				new StudentResult(72319187, "Nawaz", "Ahmed",
						new Percentage(54), new Percentage(98), new Percentage(
								88), new Percentage(65)),
				new StudentResult(57990651, "Jolley", "Paul",
						new Percentage(54), new Percentage(66), new Percentage(
								96), new Percentage(63)),
				new StudentResult(40793583, "Akavani", "Reza", new Percentage(
						54), new Percentage(53), new Percentage(56),
						new Percentage(54)),
				new StudentResult(93605132, "Bentham", "Carl", new Percentage(
						54), new Percentage(55), new Percentage(45),
						new Percentage(52)),
				new StudentResult(77602938, "Faihurst", "James",
						new Percentage(55), new Percentage(98), new Percentage(
								87), new Percentage(65)),
				new StudentResult(68242037, "Gao", "Qiao", new Percentage(56),
						new Percentage(12), new Percentage(43), new Percentage(
								49)),
				new StudentResult(51176659, "Horgan", "Damian", new Percentage(
						56), new Percentage(49), new Percentage(57),
						new Percentage(55)),
				new StudentResult(38116918, "Taylor", "Neil",
						new Percentage(56), new Percentage(46), new Percentage(
								57), new Percentage(55)),
				new StudentResult(42711733, "Welsh", "Chris",
						new Percentage(58), new Percentage(76), new Percentage(
								83), new Percentage(64)),
				new StudentResult(23508376, "Wiseman", "Iain", new Percentage(
						59), new Percentage(55), new Percentage(58),
						new Percentage(58)),
				new StudentResult(68953933, "Fisher", "David", new Percentage(
						59), new Percentage(95), new Percentage(97),
						new Percentage(70)),
				new StudentResult(89229761, "Kerry", "Graeme", new Percentage(
						59), new Percentage(77), new Percentage(71),
						new Percentage(63)),
				new StudentResult(37512964, "Patterson", "John",
						new Percentage(60), new Percentage(56), new Percentage(
								59), new Percentage(59)),
				new StudentResult(74923800, "Glover", "David", new Percentage(
						60), new Percentage(33), new Percentage(41),
						new Percentage(53)),
				new StudentResult(21398765, "Pennington", "Paul",
						new Percentage(61), new Percentage(76), new Percentage(
								65), new Percentage(63)),
				new StudentResult(21670537, "Wedge", "Andrew", new Percentage(
						61), new Percentage(32), new Percentage(41),
						new Percentage(54)),
				new StudentResult(64901375, "Belcher", "Stuart",
						new Percentage(62), new Percentage(56), new Percentage(
								64), new Percentage(61)),
				new StudentResult(25693531, "Hudson", "David", new Percentage(
						62), new Percentage(88), new Percentage(91),
						new Percentage(70)),
				new StudentResult(17054487, "Simpkins", "Mark", new Percentage(
						63), new Percentage(77), new Percentage(70),
						new Percentage(65)),
				new StudentResult(25436348, "Hartley", "David", new Percentage(
						64), new Percentage(56), new Percentage(60),
						new Percentage(62)),
				new StudentResult(83644794, "MacQuire", "Andrew",
						new Percentage(64), new Percentage(87), new Percentage(
								80), new Percentage(69)),
				new StudentResult(58730702, "Midwinter", "Timothy",
						new Percentage(65), new Percentage(66), new Percentage(
								64), new Percentage(64)),
				new StudentResult(84222613, "Shaw", "David",
						new Percentage(65), new Percentage(67), new Percentage(
								61), new Percentage(64)),
				new StudentResult(71271085, "Tomlinson", "Ricky",
						new Percentage(66), new Percentage(98), new Percentage(
								91), new Percentage(74)),
				new StudentResult(97207833, "Winetrobe", "Mary",
						new Percentage(66), new Percentage(34), new Percentage(
								48), new Percentage(59)),
				new StudentResult(95542902, "McManners", "Andy",
						new Percentage(66), new Percentage(76), new Percentage(
								79), new Percentage(69)),
				new StudentResult(47608366, "Murray", "Ian",
						new Percentage(67), new Percentage(71), new Percentage(
								67), new Percentage(67)),
				new StudentResult(75429138, "Bell", "Dave", new Percentage(67),
						new Percentage(32), new Percentage(40), new Percentage(
								58)),
				new StudentResult(42772883, "Flemming", "David",
						new Percentage(67), new Percentage(65), new Percentage(
								70), new Percentage(67)),
				new StudentResult(80963770, "Zamboglou", "Demetres",
						new Percentage(67), new Percentage(49), new Percentage(
								51), new Percentage(62)),
				new StudentResult(13824749, "Weaver", "Matthew",
						new Percentage(67), new Percentage(76), new Percentage(
								63), new Percentage(67)),
				new StudentResult(30440709, "Finch", "Daniel", new Percentage(
						67), new Percentage(44), new Percentage(49),
						new Percentage(61)),
				new StudentResult(74541089, "Ng", "Alex", new Percentage(68),
						new Percentage(23), new Percentage(40), new Percentage(
								57)),
				new StudentResult(23969504, "York", "James",
						new Percentage(68), new Percentage(33), new Percentage(
								30), new Percentage(56)),
				new StudentResult(50365312, "Firth", "Andrew", new Percentage(
						68), new Percentage(85), new Percentage(90),
						new Percentage(74)),
				new StudentResult(49550222, "Goodwin", "Daaniel",
						new Percentage(68), new Percentage(67), new Percentage(
								61), new Percentage(66)),
				new StudentResult(85884182, "Gosling", "Oliver",
						new Percentage(69), new Percentage(65), new Percentage(
								58), new Percentage(66)),
				new StudentResult(76089700, "Moores", "Christian",
						new Percentage(69), new Percentage(49), new Percentage(
								40), new Percentage(61)),
				new StudentResult(15743725, "Marshall", "Charles",
						new Percentage(69), new Percentage(25), new Percentage(
								44), new Percentage(59)),
				new StudentResult(19081423, "Green", "Benjamin",
						new Percentage(71), new Percentage(76), new Percentage(
								80), new Percentage(73)),
				new StudentResult(56031412, "Casson", "Richard",
						new Percentage(73), new Percentage(65), new Percentage(
								60), new Percentage(69)) };

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(DATA_FILE_NAME));
			oos.writeObject(new ArrayList<StudentResult>(Arrays.asList(results)));
			oos.close();
		} catch (IOException e) {
			System.out.println("Error writing studetn result data.");
		}
	}

	/**
	 * Attempts to deserialise a List of StudentResult objects from disk.
	 */
	public static List<StudentResult> readData() {
		List<StudentResult> results = null;

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					DATA_FILE_NAME));
			results = (ArrayList<StudentResult>) ois.readObject();
			ois.close();
			
			System.out.println("Read " + results.size() + " student records.");
		} catch (Exception e) {
			/*
			 * An IOException will be thrown if an error is encountered when
			 * reading from the data file.
			 */
			System.out.println("Error reading from data file: " + e);
		}
		return results;
	}
}
