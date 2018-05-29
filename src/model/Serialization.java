package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Generates an initial serialization file for a "default" AuctionCentral,
 * populated with various sample data, as well for an empty AuctionCentral,
 * and a completely full AuctionCentral.
 * 
 * @author Jake Yang, Allen Whitemarsh, Raisa
 * @version 5/7/2018
 */
public class Serialization {
	
	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalTime noon = LocalTime.NOON;
		
		Auction auction1 = new Auction(today.minus(370, ChronoUnit.DAYS), 
				noon, noon.plus(4, ChronoUnit.HOURS), "CMC AA");
		auction1.addItem(new Item("A Random Walk Down Wall Street: The "
				+ "Time-tested Strategy for Successful Investing", 5.00, 
				"Reassuring, authoritative, and perennially best-selling "
				+ "guide to investing.", 2));
		auction1.addItem(new Item("Children's building blocks", 10, "120 "
				+ "Piece Magnetic Tiles magnetic Building Blocks Toys for "
				+ "Kids", 1));
		auction1.addItem(new Item("iPhone Screen Protectors", 21.99, "Premium "
				+ "Real Screen Protector Tempered Glass Film For iPhone 6 6s 7"
				+ " Plus", 150));
		auction1.addItem(new Item("24\" Computer Monitor", 5.00, "Dell 24\" "
				+ "Monitor: P2418D", 2));
		auction1.addItem(new Item("White Gold Diamond Necklace", 8000.35, 
				"20.57ct 18k White Gold Diamond Necklace.", 1));
		Auction auction2 = new Auction(today.minus(150, ChronoUnit.DAYS),
				noon, noon.plus(2, ChronoUnit.HOURS), "Project Alert Auction");
		auction2.addItem(new Item("Toddler's Soft Sneaker Shoes", 1.00, 
				"Urberry Fashion Toddlers Prewalker Shoes Infant Baby Boys "
				+ "Girls Soft Sneaker Shoes", 23));
		auction2.addItem(new Item("2\" Locking Olympic Size Barbell Clamp "
				+ "Collars", 3.00, "Quick Release Pair of Locking 2\" Olympic"
				+ " Size Barbell Clamp Collar Great for Pro Training by Clout"
				+ " Fitness", 1));
		auction2.addItem(new Item("Amazon Fire Cover Case", 17.00, "Cover Case"
				+ " for All Amazon Fire HD 10 Tablet(7th Generation, 2017 "
				+ "Release)Premium PU Leather Slim Fit Smart Stand Cover for "
				+ "Fire HD 10.1\" Tablet with Auto Wake&Sleep (bird)", 1));
		auction2.addItem(new Item("Unisex Cloth Slippers", 1, "Waffle Open Toe"
				+ " Adult Slippers Cloth Spa Hotel Unisex Slippers for Women "
				+ "and Men", 1));
		auction2.addItem(new Item("Moana Play Set", 20, "Moana 27 Piece Play "
				+ "Set Featuring Moana and Friends Characters and Tropical "
				+ "Themed Accessories", 1));
		auction2.addItem(new Item("Razor Hoverboard", 300.00, "Razor Hovertrax"
				+ " 2.0 Hoverboard Self-Balancing Smart Scooter", 1));
		auction2.addItem(new Item("Destroy All Humans - Xbox", 4.20, "Destroy "
				+ "All Humans Videogame for the Original Xbox", 1));
		auction2.addItem(new Item("Waterproof Lip Gloss", 1.88, "MISS ROSE "
				+ "Natural Moisturizing Lipstick, 12 Colors Long Lasting "
				+ "Creamy Waterproof Lip Gloss, All-Day Smudge Proof Makeup "
				+ "with Non-Toxic Formula, Great Gift for Wife, Girlfriend "
				+ "(40# - Inked Heart)", 8));
		auction2.addItem(new Item("Engagement Rings", 200000, "Tiffany & Co.'s"
				+ " \"Tiffany Setting\" one-carat engagement ring", 15));
		auction2.addItem(new Item("Penny", 0.007, "1996 Copper Penny", 1));
		Auction auction3 = new Auction(today.plus(10, ChronoUnit.DAYS), 
				noon, noon.plus(6, ChronoUnit.HOURS), "The Going Green "
						+ "Emporium");
		auction3.addItem(new Item("Mesh Bag", 1.00, "Mesh Bag ,IEason "
				+ "Clearance Sale! Underwear Aid Socks Lingerie Laundry "
				+ "Washing Machine Mesh Bag", 1));
		auction3.addItem(new Item("Cat Eye Sunglasses", 2.00, "ZHANGVIP "
				+ "Clearance Sale 2018 New Women Vintage Cat Eye Sunglasses"
				+ " Retro Eyewear Fashion Ladies", 1));
		auction3.addItem(new Item("Calorie Counter Watch", 3.00, "Shinericed"
				+ " Unisex Digital LCD Pedometer Run Step Walking Distance "
				+ "Calorie Counter Watch Bracelet Wristband Sports Fitness "
				+ "Tracker (Black)", 1));
		auction3.addItem(new Item("Bubble Gum Lip Balm", 4.00, "Lip Balm "
				+ "Bubble Gum .15oz by Crazy Rumors", 5));
		auction3.addItem(new Item("Metal Watch", 6.00, "IEason, Women Metal"
				+ " Strap Watch (White)", 2));
		auction3.addItem(new Item("Thermal Gloves", 5.00, "Gloves us Knitted "
				+ "Touch Screen Winter Extra Thick Knit Gloves Soft Warm Thick"
				+ " Wool Windproof Cold Proof Thermal Mittens for Women "
				+ "girl", 1));
		auction3.addItem(new Item("Pet Comb", 13, "Andis Pet 7-1/2-Inch Steel"
				+ " Comb", 1));
		auction3.addItem(new Item("Paper Clips", 27, "Office Depot� Brand "
				+ "Binder Clips, Small, 3/4\" Wide, 3/8\" Capacity, Black, "
				+ "Pack Of 36", 3000));
		auction3.addItem(new Item("Wampa Plush", 42, "Star Wars Celebration V"
				+ " Exclusive Wampa 18 Inch Plush With Detachable Arm", 1));
		auction3.addItem(new Item("1 Month PlayStation Plus Membership", 5, 
				"PS3/ PS4/ PS Vita [Digital Code]", 1));
		Auction auction4 = new Auction(today.plus(50, ChronoUnit.DAYS), 
				noon, noon.plus(11, ChronoUnit.HOURS), "NMF 2018");
		auction4.addItem(new Item("SkincareGiftbox", 42, "Charlene"
				+ " New York Mystery Gift Box For Mother's Day, Moms, Bridal"
				+ " Showers, Teens Birthdays, Handmade With Moisturizing Skin"
				+ " Care", 1));
		Auction auction5 = new Auction(today.minus(769, ChronoUnit.DAYS),
				noon, noon.plus(7, ChronoUnit.HOURS), "NMF 2016 Auction");
		Auction auction6 = new Auction(today.minus(367, ChronoUnit.DAYS),
				noon, noon.plus(8, ChronoUnit.HOURS), "NMF 2017 Auction");
		auction6.addItem(new Item("Nail Stencil Sticker", 0.01, "TOPBeauty "
				+ "Nail Stencil Sticker", 1));
		auction6.addItem(new Item("Lego Piece", 0.0003158, "2x4 Red Lego "
				+ "Piece", 1));
		auction6.addItem(new Item("Corn Desilker", 0.020252, "Amco Corn "
				+ "Desilker", 5));
		auction6.addItem(new Item("Nail Stencil Sticker Set", 40.0215, 
				"TOPBeauty Nail Stencil Sticker Set (60 Pieces)", 30));
		auction6.addItem(new Item("Cartoon Striped Socks", 0.98, "Toraway 3D "
				+ "Animals Cartoon Striped Socks Cat Footprints Cotton Socks "
				+ "(Pink)", 9));
		auction6.addItem(new Item("Storage Bag Hanger", 1, "16-pocket Green "
				+ "Storage Bag Hanger", 1));
		Auction auction7 = new Auction(today.minus(1500, ChronoUnit.DAYS), 
				noon, noon.plus(10, ChronoUnit.HOURS), "NMF Auction");
		auction7.addItem(new Item("Vacuum Bags", 1.00, "SINMA Practical "
				+ "Handheld Vacuum Bags", 23));
		auction7.addItem(new Item("Unicorn Meat", 3.00, "ThinkGeek Easy-Open"
				+ " Canned Unicorn Meat, 5.5 Ounce, Stuffed Plush Toy", 1));
		auction7.addItem(new Item("Rechargeable Light up Glasses", 17.00, 
				"Satu Brown Rechargeable Slotted Shutter Light up LED Flash "
				+ "Glasses, Black", 1));
		auction7.addItem(new Item("Lint Remover", 1, "Ying Electric Fuzz Off "
				+ "Lint Mini Remover", 1));
		auction7.addItem(new Item("Plasma Ball Lamp Light", 20, "RioRand "
				+ "RR-USB-6IN Plasma Ball Lamp Light - USB or Battery "
				+ "Powered", 1));
		auction7.addItem(new Item("Gravy Mix", 300.00, "McCormik Brown Gravy"
				+ " Mix 0.87 oz", 3));
		Auction auction8 = new Auction(today.plus(45, ChronoUnit.DAYS), 
				noon, noon.plus(7, ChronoUnit.HOURS), "Yo Bois Fundraising "
						+ "Event");
		Auction auction9 = new Auction(today.plus(1, ChronoUnit.DAYS), 
				noon.minus(3, ChronoUnit.HOURS), noon.plus(6, ChronoUnit.HOURS)
				, "2018 Dream Alive");
		auction9.addItem(new Item("Bluetooth Headphones", 12.49, "SENSO "
				+ "Bluetooth Headphones, Best Wireless Sports Earphones w/Mic "
				+ "IPX7 Waterproof HD Stereo Sweatproof Earbuds for Gym "
				+ "Running Workout 8 Hour Battery Noise Cancelling "
				+ "Headsets", 1));
		Auction auction10 = new Auction(today, noon.minus(2, 
				ChronoUnit.HOURS), noon.plus(7, ChronoUnit.HOURS), "Buy My "
						+ "Stuff Please");
		auction10.addItem(new Item("Aromatherapy Diffuser", 19.99, 
				"Aromatherapy Essential Oil Diffuser, URPOWER 300ml Wood Grain"
				+ " Ultrasonic Cool Mist Whisper-Quiet Humidifier with Color "
				+ "LED Lights Changing & 4 Timer Settings, Waterless Auto "
				+ "Shut-Off for Spa Baby", 1));
		auction10.addItem(new Item("Makeup Brushes", 7.99, "BS-MALL(TM) Makeup"
				+ " Brushes Premium Makeup Brush Set Synthetic Kabuki "
				+ "Cosmetics Foundation Blending Blush Eyeliner Face Powder "
				+ "Brush Makeup Brush Kit", 10));
		Auction auction11 = new Auction(today.plus(12, ChronoUnit.DAYS), 
				noon.plus(1, ChronoUnit.HOURS), noon.plus(5, ChronoUnit.HOURS),
				"Semi-Annual Pro-Life Auction");
		auction11.addItem(new Item("Pressure Cooker", 50.95, "Instant Pot Duo "
				+ "Mini 3 Qt 7-in-1 Multi- Use Programmable Pressure Cooker, "
				+ "Slow Cooker, Rice Cooker, Steamer, Saut�, Yogurt Maker and "
				+ "Warmer", 1));
		Auction auction12 = new Auction(today.plus(12, ChronoUnit.DAYS), 
				noon.minus(8, ChronoUnit.HOURS), noon.plus(1, ChronoUnit.HOURS)
				, "MC Ride's Album Auction");
		auction12.addItem(new Item("Facial Spray", 6.00, "Mario Badescu Facial"
				+ " Spray with Aloe Herbs and Rosewater", 2));
		auction12.addItem(new Item("Plasma Ball Lamp Light", 20, "RioRand "
				+ "RR-USB-6IN Plasma Ball Lamp Light - USB or Battery Powered"
				, 1));
		auction12.addItem(new Item("Gravy Mix", 300.00, "McCormik Brown Gravy "
				+ "Mix 0.87 oz", 3));
		
		AuctionCentral auctionCentral = new AuctionCentral();
		NonProfit nonProfit1 = new NonProfit("BillyTheKid", "Pro Youth Foundation",
				"Billy Pilgrim");
		NonProfit nonProfit2 = new NonProfit("johnyBoi", "Chicago Men's Club",
				"John Doe");
		NonProfit nonProfit3 = new NonProfit("janey", "Project Alert",
				"Jane Doe");
		NonProfit nonProfit4 = new NonProfit("noided47", "Going-Green Foundation",
				"Donald");
		NonProfit nonProfit5 = new NonProfit("abcdefg", "Nursing Mothers Foundation",
				"Naomi");	
		NonProfit nonProfit6 = new NonProfit("theBossBaby", "Yo Bois",
				"Paul Lazaro");
		NonProfit nonProfit7 = new NonProfit("numberOfTheBeast", "Dream-Alive Center",
				"Satan");
		NonProfit nonProfit8 = new NonProfit("twoheadedboy", "Graceful Aging Club",
				"Son Goku");
		NonProfit nonProfit9 = new NonProfit("LongLiveTheQueen", "Pro-Life Foundation",
				"Catherine of Aragon");
		NonProfit nonProfit10 = new NonProfit("MCRide", "Exmilitary",
				"Stefan Burnett");
		Bidder bidder1 = new Bidder("bidderguy31", "Julius Caesar");
		Bidder bidder2 = new Bidder("kungfuKenny", "Augustus Caesar");
		Bidder bidder3 = new Bidder("theDonald", "Donald Trump");
		Bidder bidder4 = new Bidder("Kelly", "Maud'Dib");
		Bidder bidder5 = new Bidder("username", "Kelly");
		Bidder bidder6 = new Bidder("Franks814", "Charlemagne The Great");
		Bidder bidder7 = new Bidder("Transcontinental", "Dagny Taggart");
		AuctionCentralEmployee employee1 = new AuctionCentralEmployee("KingOfCarrotFlowers", "Antonio");
		AuctionCentralEmployee employee2 = new AuctionCentralEmployee("QueenOfCarrotFlowers", "Antonia");
		auctionCentral.addNewUser(employee1);
		auctionCentral.addNewUser(employee2);
		auctionCentral.addNewUser(bidder1); // No bids
		auctionCentral.addNewUser(bidder2); // 1 bid in previous auction
		auctionCentral.addNewUser(bidder3); // 1 bid in current auction
		auctionCentral.addNewUser(bidder4); // 4 bids in current auction
		auctionCentral.addNewUser(bidder5); // 10 bids in all previous auctions
		auctionCentral.addNewUser(bidder6); // 10 bids in all current auctions
		auctionCentral.addNewUser(bidder7); // 6 bids prev / 6 bids current
		auctionCentral.addNewUser(nonProfit1); // No previous auction
		auctionCentral.addNewUser(nonProfit2); // Previous auction > year ago
		auctionCentral.addNewUser(nonProfit3); // Previous auction < year ago
		auctionCentral.addNewUser(nonProfit4); // scheduled auction
		auctionCentral.addNewUser(nonProfit5); // Previous + scheduled
		auctionCentral.addNewUser(nonProfit6); // scheduled empty auction
		auctionCentral.addNewUser(nonProfit7); // auction tomorrow
		auctionCentral.addNewUser(nonProfit8); // auction today
		auctionCentral.addNewUser(nonProfit9); // auction on same day
		auctionCentral.addNewUser(nonProfit10); // auction on same day
		auctionCentral.addAuction(nonProfit2, auction1);
		auctionCentral.addAuction(nonProfit3, auction2);
		auctionCentral.addAuction(nonProfit4, auction3);
		auctionCentral.addAuction(nonProfit5, auction7);
		auctionCentral.addAuction(nonProfit5, auction5);
		auctionCentral.addAuction(nonProfit5, auction6);
		auctionCentral.addAuction(nonProfit5, auction4);
		auctionCentral.addAuction(nonProfit6, auction8);
		auctionCentral.addAuction(nonProfit7, auction9);
		auctionCentral.addAuction(nonProfit8, auction10);
		auctionCentral.addAuction(nonProfit9, auction11);
		auctionCentral.addAuction(nonProfit10, auction12);
		
		//past bids
		bidder2.addNewToBiddingHistory(auction1, 
				auction1.getItems().get(4), 8500.0);
		bidder5.addNewToBiddingHistory(auction1, 
				auction1.getItems().get(4), 8200.00);
		bidder5.addNewToBiddingHistory(auction1, 
				auction1.getItems().get(3), 7.00);
		bidder5.addNewToBiddingHistory(auction1, 
				auction1.getItems().get(2), 21.99);
		bidder5.addNewToBiddingHistory(auction1, 
				auction1.getItems().get(1), 11.0);
		bidder7.addNewToBiddingHistory(auction2, 
				auction2.getItems().get(6), 4.20);
		bidder7.addNewToBiddingHistory(auction2, 
				auction2.getItems().get(5), 310.00);
		bidder7.addNewToBiddingHistory(auction2, 
				auction2.getItems().get(9), 0.01);
		bidder7.addNewToBiddingHistory(auction6, 
				auction6.getItems().get(2), 0.10);
		bidder7.addNewToBiddingHistory(auction6, 
				auction6.getItems().get(4), 0.98);
		bidder7.addNewToBiddingHistory(auction6, 
				auction6.getItems().get(5), 2.0);
		bidder5.addNewToBiddingHistory(auction6, 
				auction6.getItems().get(0), 1.15);
		bidder5.addNewToBiddingHistory(auction6, 
				auction6.getItems().get(3), 42.24);
		bidder5.addNewToBiddingHistory(auction6, 
				auction6.getItems().get(4), 2.98);
		bidder5.addNewToBiddingHistory(auction7, 
				auction7.getItems().get(0), 1.00);
		bidder5.addNewToBiddingHistory(auction7, 
				auction7.getItems().get(1), 4.20);
		bidder5.addNewToBiddingHistory(auction7, 
				auction7.getItems().get(2), 18.35);
		bidder5.addNewToBiddingHistory(auction7, 
				auction7.getItems().get(4), 21.00);
		bidder5.addNewToBiddingHistory(auction7, 
				auction7.getItems().get(5), 349.21);
		
		//future bids
		auction3.makeBid(auction3.getItems().get(1), 2.09, bidder3);
		auction3.makeBid(auction3.getItems().get(1), 2.68, bidder4);
		auction3.makeBid(auction3.getItems().get(2), 3.0, bidder4);
		auction3.makeBid(auction3.getItems().get(3), 5.60, bidder4);
		auction3.makeBid(auction3.getItems().get(4), 12.720, bidder4);
		auction3.makeBid(auction3.getItems().get(5), 8.0, bidder4);
		auction3.makeBid(auction3.getItems().get(6), 15.0, bidder4);
		auction3.makeBid(auction3.getItems().get(7), 27.23, bidder4);
		auction3.makeBid(auction3.getItems().get(8), 43.05, bidder4);
		
		auction4.makeBid(auction4.getItems().get(0), 58, bidder6);
		auction9.makeBid(auction9.getItems().get(0), 12.49, bidder6);
		auction11.makeBid(auction11.getItems().get(0), 52.95, bidder6);
		auction12.makeBid(auction12.getItems().get(0), 6.00, bidder6);
		auction12.makeBid(auction12.getItems().get(1), 21.00, bidder6);
		auction12.makeBid(auction12.getItems().get(2), 301.00, bidder6);
		auction3.makeBid(auction3.getItems().get(1), 2.5, bidder6);
		auction3.makeBid(auction3.getItems().get(2), 3.45, bidder6);
		auction3.makeBid(auction3.getItems().get(3), 4.56, bidder6);
		auction3.makeBid(auction3.getItems().get(5), 6.75, bidder6);
		auction3.makeBid(auction3.getItems().get(7), 28.89, bidder6);
		auction3.makeBid(auction3.getItems().get(9), 11, bidder6);
		
		auction3.makeBid(auction3.getItems().get(2), 4.20, bidder7);
		auction3.makeBid(auction3.getItems().get(3), 12.56, bidder7);
		auction3.makeBid(auction3.getItems().get(4), 13.00, bidder7);
		auction3.makeBid(auction3.getItems().get(5), 13.00, bidder7);
		auction12.makeBid(auction12.getItems().get(1), 25.55, bidder7);
		auction12.makeBid(auction12.getItems().get(2), 537.28, bidder7);
		
		serialize("auctionCentralDefault", auctionCentral);	
		
		// 25 future auctions
		auctionCentral = new AuctionCentral();
		auctionCentral.addNewUser(new AuctionCentralEmployee("employee", "employeeName"));
		auctionCentral.addNewUser(new Bidder("bidder", "bidderName"));
		for (int i = 1; i <= 25; i++) {
			NonProfit nonProfit = new NonProfit("nonprofit" + i, "nonprofitName" + i, 
					"contactName" + i);
			auctionCentral.addNewUser(nonProfit);
			Auction auction = new Auction(today.plus(2*i, ChronoUnit.DAYS), 
					noon, noon.plus(7, ChronoUnit.HOURS), "Auction"+i);
			auctionCentral.addAuction(nonProfit, auction);
		}
		serialize("auctionCentralFull", auctionCentral);
		// empty auctionCentral
		auctionCentral = new AuctionCentral();
		auctionCentral.addNewUser(new AuctionCentralEmployee("employee", "employeeName"));
		auctionCentral.addNewUser(new Bidder("bidder", "bidderName"));
		auctionCentral.addNewUser(new NonProfit("nonprofit", "nonprofitName", "contactName"));
		serialize("auctionCentralEmpty", auctionCentral);
	}
	
	// Helper method to serialize a given AuctionCentral as a target filename.
	private static void serialize(String fileName, AuctionCentral ac) {
		try {
			FileOutputStream file = new FileOutputStream(fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(ac);
			out.close();
			file.close();
			System.out.println("Serialized!!!!!");
		} catch (IOException exception) {
			System.out.println("IOException");
		}
	}

}
