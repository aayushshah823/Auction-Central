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
				today.minus(368, ChronoUnit.DAYS), noon, 
				noon.plus(4, ChronoUnit.HOURS));
		auction1.setAuctionName("Chicago-Men's-Club-Annual-Auction");
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
				today.minus(149, ChronoUnit.DAYS), noon, 
				noon.plus(2, ChronoUnit.HOURS));
		auction2.setAuctionName("Project-Alert-Auction");
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
				today.plus(10, ChronoUnit.DAYS), noon, 
				noon.plus(6, ChronoUnit.HOURS));
		auction3.setAuctionName("The-Going-Green-Emporium");
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
		auction3.addItem(new Item("Paper Clips", 27, "Office Depotï¿½ Brand "
				+ "Binder Clips, Small, 3/4\" Wide, 3/8\" Capacity, Black, "
				+ "Pack Of 36", 3000));
		auction3.addItem(new Item("Wampa Plush", 42, "Star Wars Celebration V"
				+ " Exclusive Wampa 18 Inch Plush With Detachable Arm", 1));
		Auction auction4 = new Auction(today.plus(50, ChronoUnit.DAYS), 
				today.plus(51, ChronoUnit.DAYS), noon, 
				noon.plus(11, ChronoUnit.HOURS));
		auction4.setAuctionName("NMF2018");
		auction4.addItem(new Item("Mystery Skin Care Gift Box", 42, "Charlene"
				+ " New York Mystery Gift Box For Mother's Day, Moms, Bridal"
				+ " Showers, Teens Birthdays, Handmade With Moisturizing Skin"
				+ " Care", 1));
		Auction auction5 = new Auction(today.minus(769, ChronoUnit.DAYS),
				today.minus(767, ChronoUnit.DAYS), noon, 
				noon.plus(7, ChronoUnit.HOURS));
		auction5.setAuctionName("NMF2016Auction");
		Auction auction6 = new Auction(today.minus(367, ChronoUnit.DAYS),
				today.minus(367, ChronoUnit.DAYS), noon,
				noon.plus(8, ChronoUnit.HOURS));
		auction6.setAuctionName("NMF2017Auction");
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
				today.minus(1499, ChronoUnit.DAYS), noon, 
				noon.plus(10, ChronoUnit.HOURS));
		auction7.setAuctionName("Nursing-Mothers-Foundation-Auction");
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
				today.plus(46, ChronoUnit.DAYS), noon,
				noon.plus(7, ChronoUnit.HOURS));
		auction8.setAuctionName("YoBoisFundraisingEvent");
		Auction auction9 = new Auction(today.plus(1, ChronoUnit.DAYS), 
				today.plus(1, ChronoUnit.DAYS), noon.minus(3, 
				ChronoUnit.HOURS), noon.plus(6, ChronoUnit.HOURS));
		auction9.setAuctionName("2018-DreamAlive");
		auction9.addItem(new Item("Bluetooth Headphones", 12.49, "SENSO "
				+ "Bluetooth Headphones, Best Wireless Sports Earphones w/Mic "
				+ "IPX7 Waterproof HD Stereo Sweatproof Earbuds for Gym "
				+ "Running Workout 8 Hour Battery Noise Cancelling "
				+ "Headsets", 1));
		Auction auction10 = new Auction(today, today, noon.minus(2, 
				ChronoUnit.HOURS), noon.plus(7, ChronoUnit.HOURS));
		auction10.setAuctionName("BuyMyStuffPlease");
		auction10.addItem(new Item("Aromatherapy Diffuser", 19.99, 
				"Aromatherapy Essential Oil Diffuser, URPOWER 300ml Wood Grain"
				+ " Ultrasonic Cool Mist Whisper-Quiet Humidifier with Color "
				+ "LED Lights Changing & 4 Timer Settings, Waterless Auto "
				+ "Shut-Off for Spa Baby", 1));
		auction10.addItem(new Item("Makeup Brushes", 7.99, "BS-MALL(TM) Makeup"
				+ " Brushes Premium Makeup Brush Set Synthetic Kabuki "
				+ "Cosmetics Foundation Blending Blush Eyeliner Face Powder "
				+ "Brush Makeup Brush Kit", 10));
		Auction auction11 = new Auction(today.plus(13, ChronoUnit.DAYS), 
				today.plus(13, ChronoUnit.DAYS), noon.plus(1, 
						ChronoUnit.HOURS), noon.plus(5, ChronoUnit.HOURS));
		auction11.setAuctionName("Semi-AnnualPro-LifeFoundationGiveaway");
		auction11.addItem(new Item("Pressure Cooker", 50.95, "Instant Pot Duo "
				+ "Mini 3 Qt 7-in-1 Multi- Use Programmable Pressure Cooker, "
				+ "Slow Cooker, Rice Cooker, Steamer, Sauté, Yogurt Maker and "
				+ "Warmer", 1));
		Auction auction12 = new Auction(today.plus(12, ChronoUnit.DAYS), 
				today.plus(13, ChronoUnit.DAYS), noon.minus(8, 
						ChronoUnit.HOURS), noon.plus(1, ChronoUnit.HOURS));
		auction12.setAuctionName("MC-Ride'sAlbumAuction");
		auction12.addItem(new Item("Facial Spray", 6.00, "Mario Badescu Facial"
				+ " Spray with Aloe Herbs and Rosewater", 2));
		auction12.addItem(new Item("Plasma Ball Lamp Light", 20, "RioRand "
				+ "RR-USB-6IN Plasma Ball Lamp Light - USB or Battery Powered"
				, 1));
		auction12.addItem(new Item("Gravy Mix", 300.00, "McCormik Brown Gravy "
				+ "Mix 0.87 oz", 3));
		
		AuctionCentral auctionCentral = new AuctionCentral();
		
		NonProfit nonProfit1 = new NonProfit("ProYouthFoundation",
				"Billy Pilgrim");
		NonProfit nonProfit2 = new NonProfit("ChicagoMen'sClub",
				"John Doe");
		NonProfit nonProfit3 = new NonProfit("ProjectAlert",
				"Jane Doe");
		NonProfit nonProfit4 = new NonProfit("GoingGreenFoundation",
				"Donald");
		NonProfit nonProfit5 = new NonProfit("NursingMothersFoundation",
				"Naomi");	
		NonProfit nonProfit6 = new NonProfit("YoBois",
				"Paul Lazaro");
		NonProfit nonProfit7 = new NonProfit("DreamAliveCenter",
				"Satan");
		NonProfit nonProfit8 = new NonProfit("GracefulAgingClub",
				"Son Goku");
		NonProfit nonProfit9 = new NonProfit("Pro–LifeFoundation",
				"Catherine of Aragon");
		NonProfit nonProfit10 = new NonProfit("Exmilitary",
				"MC Ride");
		
		Bidder bidder1 = new Bidder("bidderguy31", "Julius Caesar");
		Bidder bidder2 = new Bidder("kungfuKenny", "Augustus Caesar");
		Bidder bidder3 = new Bidder("theDonald", "Donald Trump");
		Bidder bidder4 = new Bidder("Kelly", "Maud'Dib");
		Bidder bidder5 = new Bidder("username", "Kelly");
		Bidder bidder6 = new Bidder("Franks814", "Charlemagne The Great");
		Bidder bidder7 = new Bidder("Transcontinental", "Dagny Taggart");
		
		auctionCentral.addNewUser(bidder1); // No bids
		auctionCentral.addNewUser(bidder2); // 1 bid in previous auction
		auctionCentral.addNewUser(bidder3); // 1 bid in current auction
		auctionCentral.addNewUser(bidder4); // 4 bids in current auction
		auctionCentral.addNewUser(bidder5); // 10 bids in all previous auctions
		auctionCentral.addNewUser(bidder6); // 10 bids in all current auctions
		auctionCentral.addNewUser(bidder7); // 6 bids prev / 6 bids current
		auctionCentral.addNewUser(nonProfit1);
		auctionCentral.addNewUser(nonProfit2);
		auctionCentral.addNewUser(nonProfit3);
		auctionCentral.addNewUser(nonProfit4);
		auctionCentral.addNewUser(nonProfit5);
		auctionCentral.addNewUser(nonProfit6);
		auctionCentral.addNewUser(nonProfit7);
		auctionCentral.addNewUser(nonProfit8);
		auctionCentral.addNewUser(nonProfit9);
		auctionCentral.addNewUser(nonProfit10);
		auctionCentral.addNonprofit(nonProfit1); // No previous auction
		auctionCentral.addNonprofit(nonProfit2); // Previous auction > year ago
		auctionCentral.addNonprofit(nonProfit3); // Previous auction < year ago
		auctionCentral.addNonprofit(nonProfit4); // scheduled auction
		auctionCentral.addNonprofit(nonProfit5); // Previous + scheduled
		auctionCentral.addNonprofit(nonProfit6); // scheduled empty auction
		auctionCentral.addNonprofit(nonProfit7); // auction tomorrow
		auctionCentral.addNonprofit(nonProfit8); // auction today
		auctionCentral.addNonprofit(nonProfit9); // auction on same day
		auctionCentral.addNonprofit(nonProfit10); // auction on same day
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
		
		// Adds bids for past auctions
		ArrayList<Item> bidder1Auction1Items = new ArrayList<Item>();
		bidder1Auction1Items.add(new Item("White Gold Diamond Necklace", 
				8000.35, "20.57ct 18k White Gold Diamond Necklace.", 1));
		ArrayList<Item> bidder5Auction1Items = new ArrayList<Item>();
		bidder5Auction1Items.add(new Item("White Gold Diamond Necklace", 
				8000.35, "20.57ct 18k White Gold Diamond Necklace.", 1));
		bidder5Auction1Items.add(new Item("24\" Computer Monitor", 5.00, "Dell"
				+ " 24\" Monitor: P2418D", 2));
		bidder5Auction1Items.add(new Item("iPhone Screen Protectors", 21.99, 
				"Premium Real Screen Protector Tempered Glass Film For iPhone "
				+ "6 6s 7 Plus", 150));
		bidder5Auction1Items.add(new Item("Children's building blocks", 10, 
				"120 Piece Magnetic Tiles magnetic Building Blocks Toys for "
				+ "Kids", 1));
		ArrayList<Item> bidder7Auction2Items = new ArrayList<Item>();
		bidder7Auction2Items.add(new Item("Destroy All Humans - Xbox", 4.20, 
				"Destroy All Humans Videogame for the Original Xbox", 1));
		bidder7Auction2Items.add(new Item("Razor Hoverboard", 300.00, "Razor "
				+ "Hovertrax 2.0 Hoverboard Self-Balancing Smart Scooter", 1));
		bidder7Auction2Items.add(new Item("Penny", 0.007, "1996 Copper Penny",
				1));
		ArrayList<Item> bidder7Auction6Items = new ArrayList<Item>();
		bidder7Auction6Items.add(new Item("Corn Desilker", 0.020252, "Amco "
				+ "Corn Desilker", 5));
		bidder7Auction6Items.add(new Item("Cartoon Striped Socks", 0.98, 
				"Toraway 3D Animals Cartoon Striped Socks Cat Footprints "
				+ "Cotton Socks (Pink)", 9));
		bidder7Auction6Items.add(new Item("Storage Bag Hanger", 1, "16-pocket "
				+ "Green Storage Bag Hanger", 1));
		ArrayList<Item> bidder5Auction6Items = new ArrayList<Item>();
		bidder5Auction6Items.add(new Item("Nail Stencil Sticker", 0.01, 
				"TOPBeauty Nail Stencil Sticker", 1));
		bidder5Auction6Items.add(new Item("Nail Stencil Sticker Set", 40.0215,
				"TOPBeauty Nail Stencil Sticker Set (60 Pieces)", 30));
		bidder5Auction6Items.add(new Item("Cartoon Striped Socks", 0.98, 
				"Toraway 3D Animals Cartoon Striped Socks Cat Footprints "
				+ "Cotton Socks (Pink)", 9));
		ArrayList<Item> bidder5Auction7Items = new ArrayList<Item>();
		bidder5Auction7Items.add(new Item("Vacuum Bags", 1.00, "SINMA "
				+ "Practical Handheld Vacuum Bags", 23));
		bidder5Auction7Items.add(new Item("Unicorn Meat", 3.00, "ThinkGeek "
				+ "Easy-Open Canned Unicorn Meat, 5.5 Ounce, Stuffed Plush "
				+ "Toy", 1));
		bidder5Auction7Items.add(new Item("Rechargeable Light up Glasses", 
				17.00, "Satu Brown Rechargeable Slotted Shutter Light up LED"
				+ " Flash Glasses, Black", 1));
		bidder2.addAuction(auction1, bidder1Auction1Items);
		bidder5.addAuction(auction1, bidder5Auction1Items);
		bidder5.addAuction(auction6,  bidder5Auction6Items);
		bidder7.addAuction(auction2, bidder7Auction2Items);
		bidder7.addAuction(auction6, bidder7Auction6Items);
		
		// Adds bids for future auctions
		bidder3.makeBid(auction3.getItems().get(1).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder4.makeBid(auction3.getItems().get(1).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder4.makeBid(auction3.getItems().get(2).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder4.makeBid(auction3.getItems().get(3).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder4.makeBid(auction3.getItems().get(4).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction4.getItems().get(0).getItemName(), 
				auction4.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction9.getItems().get(0).getItemName(), 
				auction9.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction11.getItems().get(0).getItemName(), 
				auction11.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction12.getItems().get(0).getItemName(), 
				auction12.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction12.getItems().get(1).getItemName(), 
				auction12.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction12.getItems().get(2).getItemName(), 
				auction12.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction3.getItems().get(1).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction3.getItems().get(2).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction3.getItems().get(3).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder6.makeBid(auction4.getItems().get(0).getItemName(), 
				auction4.getAuctionName(), 30000, auctionCentral);
		bidder7.makeBid(auction3.getItems().get(2).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder7.makeBid(auction3.getItems().get(3).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder7.makeBid(auction3.getItems().get(4).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder7.makeBid(auction3.getItems().get(5).getItemName(), 
				auction3.getAuctionName(), 30000, auctionCentral);
		bidder7.makeBid(auction12.getItems().get(1).getItemName(), 
				auction12.getAuctionName(), 30000, auctionCentral);
		bidder7.makeBid(auction12.getItems().get(2).getItemName(), 
				auction12.getAuctionName(), 30000, auctionCentral);
		
		serialize("auctionCentralDefault", auctionCentral);	
		
		// 25 future auctions
		auctionCentral = new AuctionCentral();
		for (int i = 1; i <= 25; i++) {
			NonProfit nonProfit = new NonProfit("nonProfit" + i, 
					"contactPerson" + i);
			auctionCentral.addNonprofit(nonProfit);
			Auction auction = new Auction(today.plus(2*i, ChronoUnit.DAYS), 
					today.plus(2*i, ChronoUnit.DAYS), noon, 
					noon.plus(7, ChronoUnit.HOURS));
			auction.setAuctionName("auction"+i);
			auctionCentral.addAuction(nonProfit, auction);
		}
		serialize("auctionCentralFull", auctionCentral);
		// empty auctionCentral
		serialize("auctionCentralEmpty", new AuctionCentral());
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

