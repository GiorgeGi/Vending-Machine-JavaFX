/* 
Μερική εργασία εξαμήνου πάνω στην Java και στο framework JavaFX
Εργάστικα ατομικά

I efarmogi ftiaxtike meso tou https://replit.com JavaFX compiler
Den exei dokimastei se alla sistimata pou xrisimopoioun JavaFX gia na ksero pithana bugs i an oi texnotropies
  CSS pou xrisimopoiisa topothetoun ta antikeimena sto UI allokota se alles mixanes
Mesa stis klaseis iparxoun methodoi, getters kai setters pou mporei na min xrisimopoiountai
  kata tin ektelesi tou programmatos. afethikan ekei gia debug kai mellontikes prosthikes 
  stin efarmogi
O logos pou i ergasia einai se ena terastio arxeio kai i kathe klassi den einai se diko tis ksexoristo arxeio
  einai epeidi den eimoun sigouros an meso replit mporousa na to kano auto
Iparxei ena exception pou den mporesa na piaso kata tin ektelesi tis efarmogis, otan ginete 
  update i etiketa me to sinoliko poso ton eisaxthenton kermaton stin mixani. xrisimopoiisa kapoies
  texnikes alla paramenei. Ostoso pera apo spam stin konsola den prokallei kanena problima oute
  kathisterisis kata tin ektelesi tis efarmogis
I efarmogi ftiaxtike na min xriazete tin consola gia na einai leitourgiki. Osa prints iparxoun einai
  mono gia elenxous kai aposfalamtosi
*/

// ola ta imports apo to java
import java.util.HashMap;
import java.util.Map;


// ola ta imports apo to javafx
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Pair;


// klassi me to proion
class Product {
    // ola ta xaraktiristika (attributes) enos proiontos
    private String name;
    private String description;
    private String brand;
    private double dimensionsHeight;
    private double dimensionsWidth;
    private double dimensionsLength;
    private double price;
    private String expirationDate;
    private boolean isLiquid;
    private String weightOrLiters;

  // o constructor tou proiodos gia na arxikopoiisei ta attributes
    public Product(String name, String description, String brand, double dimensionsHeight, double dimensionsWidth,
            double dimensionsLength, double price, String expirationDate, boolean isLiquid, String weightOrLiters) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.dimensionsHeight = dimensionsHeight;
        this.dimensionsWidth = dimensionsWidth;
        this.dimensionsLength = dimensionsLength;
        this.price = price;
        this.expirationDate = expirationDate;
        this.isLiquid = isLiquid;
        this.weightOrLiters = weightOrLiters;
    }

  // kapoia getters gia mellontiki xrisi sto programma
    
  // epistrefei to attribute onoma proiontos
  public String getName() {
        return name;
    }

  // epistrefei timi proiontos
    public double getPrice() {
        return price;
    }

    // edo mporoun na epistrafoun kialla getters an xreiastoun

}



// klasi gia to rafi
// os shelf orizete to kathe rafi
// os position i kathe thesi (i slot) pou exei ena rafi
class Shelf {
    // attributes
    private int shelfNumber;
    private int maxPositions;
    private int slotID;                             // gia na xrisimopoiithei meta me ena numpad
                                                    // apo to opoio tha aograzei o xristis to
                                                    // to periexomeno sto simio tis mixanis
    
    private Map<Integer, Product> products;         // xrisi Hash map gia to proion me kleidei tin
                                                    // thesi Integer kai timi value to Product gia tin
                                                    // eisagogi kai eksagogi prointon sto rafi basi
                                                    // tin thesi tous
    
    private Map<Integer, Integer> productCounts;    // omoios


    // o constructor
    public Shelf(int shelfNumber, int maxPositions) {
        this.shelfNumber = shelfNumber;
        this.maxPositions = maxPositions;
        this.slotID = slotID;
        this.products = new HashMap<>();
        this.productCounts = new HashMap<>();
    }

    // methodos gia na bazo proion sto rafi
    public void addProduct(int position, Product product, int count) {
        // elenxos ean i thesi sto rafi iparxei kai einai diathesimi
        if (position > 0 && position <= maxPositions) {
            // xrisi ton Hash Maps gia eisagogi proionton
            products.put(position, product);
            productCounts.put(position, count);
        } else {
            // ean i thesi sto rafi den iparxei, einai akatallili i gemati
            System.out.println("Invalid position for product.");
        }
    }

    // methodos getter pou epistrefei to plithos enos proion se thesi sto rafi
    public int getProductCount(int position) {
      // xrisi GetOrDeafult gia epistrofi thesis, an den epitixei epistrefei default 0
      return productCounts.getOrDefault(position, 0);
    }

    // getter gia na epistrefei an einai adeio ena rafi
    public boolean isEmpty(int position) {
        // an ena proion den periexei to kleidi thesi epistrefei true, allios false
        return !products.containsKey(position);
    }

    
    // getter gia oles tis thesis se ola ta rafia
    public int getMaxPositions() {
        return maxPositions;
    }

    // getter pou epistrefei apo ena proion tin thesi
    public Product getProduct(int position) {
        return products.get(position);
    }

    // methodos gia tin aferesei enos proiontos
    public void removeProduct(int position) {
        // elenxos gia sosti thesi
        if (products.containsKey(position)) {
            // i thesi epistrefete apo to Hash map me getOrDefault allios 0
            int count = productCounts.getOrDefault(position, 0);
            if (count > 0) {
                // to plithos proionton mionete kata ena
                count--;
                // enimeronete to has map me ta nea dedomena 
                productCounts.put(position, count);
            }
        }
    }

    // xoros gia alla mellontika getters

}

// i klassi tis mixanis
class VendingMachine {
    // attributes
    private Map<Integer, Shelf> shelves; // bazei ta rafia stin mixani
    private double totalCoins;           // ta sinolika kermata pou exei balei o xristis
    private double change;               // resta apo kapoia agora
    private double cashInside;           // stathero poso pou exei i mixani apo prin gia na dinei
                                         // resta
    
    // 4 idiotites gia na fainontai oi allages se kapoia labels pano sto Grafiko periballon UI
    private StringProperty totalCoinsProperty;      // gia ta xrimata pou bazei o xristis
    private StringProperty changeProperty;          // gia resta
    private StringProperty coinStatusProperty;      // gia to an to kerma mpike stin mixani
    private StringProperty purchaseStatusProperty;  // gia extra stoixeia kata tin agora

    // constructor
    public VendingMachine() {
        // arxikopoiisei ton attributes
        this.shelves = new HashMap<>();
        this.totalCoins = 0.00;
        this.change = 0.00;
        this.cashInside = 100.0; // i mixani exei mesa tis 100 euro apo prin gia resta
        this.totalCoinsProperty = new SimpleStringProperty("Total Coins: 0.00");
        this.changeProperty = new SimpleStringProperty("Change: 0.00");
        this.coinStatusProperty = new SimpleStringProperty(""); // to arxiko label gia auto
                                                                // to property exei dilothei parakato
                                                                // giati einai stin main sinartisi
        this.purchaseStatusProperty = new SimpleStringProperty("Purchase & Cancel Status");
    }

    // methodoi gia prosbasi kai epeksergasia ton String properties  
    public StringProperty totalCoinsProperty() {
        return totalCoinsProperty;
    }

    public StringProperty changeProperty() {
        return changeProperty;
    }

    public StringProperty coinStatusProperty() {
        return coinStatusProperty;
    }

    public StringProperty purchaseStatusProperty() {
        return purchaseStatusProperty;
    }
    
    public void setCoinStatus(String coinStatus) {
        coinStatusProperty.set(coinStatus);
    }

    // methodos gia tin eisagogi sirtarion
    public void addShelf(int shelfNumber, int maxPositions) {
        // elenxos an to rafi iparxei eidi
        if (!shelves.containsKey(shelfNumber)) {
            // dimiourgeia neou sirtariou kai prosthiki autou sto xarti
            Shelf newShelf = new Shelf(shelfNumber, maxPositions);
            shelves.put(shelfNumber, newShelf);
        } else {
            // debug statement
            System.out.println("Shelf already exists.");
        }
    }

    // methodos gia eisagogi proionton se sigkekrimeno simio stin mixani
    // epikalite tin addProduct apo tin klassi shelf
    public void addProductToShelf(int shelfNumber, int position, Product product, int count) {
        // pairnei to sigkekrimeno rafi apo ton xarti
        Shelf shelf = shelves.get(shelfNumber);
        // elenxos an to rafi pou dilothike iparxei / einai katallilo
        if (shelf != null) {
            // analoga posa proionta theloume na baloume sto rafi ginete me loopa
            for (int i = 0; i < count; i++) {
                // dexete orismata thesi, onoma proion kai plithos proionton
                shelf.addProduct(position, product, count);
            }
        } else {
            // debug statement
            System.out.println("Invalid shelf number.");
        }
    }

    // epistrefei ta rafia apo tin mixani
    public Map<Integer, Shelf> getShelves() {
        return shelves;
    }

    // epistrefei thesi apo ena proion apo kapoia thesi se ena rafi
    public Product getProductFromShelf(int shelfNumber, int position) {
        // pairnei to rafi apo ton xarti
        Shelf shelf = shelves.get(shelfNumber);
        // elenxos an to rafi iparxei
        if (shelf != null) {
            // epistrofi thesis
            return shelf.getProduct(position);
        } else {
            // debug statement
            System.out.println("Invalid shelf number.");
            // epistrefei oti i thesi einai adeia
            return null;
        }
    }

    // methodos gia tin eisagogi kermatos stin mixani 
    public void insertCoin(double coinValue) {
        // dilosi tis pithanotitas 20% i mixani na min dextei to kerma
        double probability = Math.random();
        if (probability <= 0.2) {
            // debug statement
            System.out.println("Coin rejected. Please try again.");
            // ginete update sto antistoixo label sto UI me to parakato sxolio gia na to blepei o
            // xristis
            coinStatusProperty.set("Coin Rejected. Please try again.");
        } else {
            // to kerma mpike me epitixia
            // enimeronontai ta sinolika kermata tou xristi athroizontas ta me to poso pou eisixthei
            totalCoins += coinValue;
            // enimeronete to grafiko periballon me tin nea timi
            totalCoinsProperty.set("Total Coins: " + String.format("%.2f", totalCoins));
            // enimeronei to mixanima to xristi sto graffiko periballon oti to kerma mpike
            coinStatusProperty.set("Coin Accepted");
        }
    }

      // methodos gia tin akirosi mias agoras
      public void cancelPurchase() {
          // enimeronete to GUI oti ginete epistrofi ton xrimaton pou exei balei o xristis gia agores
          purchaseStatusProperty.set("Purchase cancelled, returning: " + String.format("%.2f", totalCoins) + "€");
          // debug statement
          System.out.println("Purchase cancelled, returning: " + totalCoins + "€");
          // ta sniolika xrimata pou ebale o xristis mesa einai pleon 0
          totalCoins = 0.0;
          // ginete update to GUI gia na fainete oti den exei o xristis sto mixanima xrimata
          // gia na kanei agores
          totalCoinsProperty.set("Total Coins: " + String.format("%.2f", totalCoins));
      }

      // methodos pou pragmatopoiei mia agora
      public void purchaseProduct(int shelfNumber, int position) {
          // pairnei to rafi
          Shelf shelf = shelves.get(shelfNumber);
          // an to rafi iparxei
          if (shelf != null) {
              // pairnei to proion
              Product product = shelf.getProduct(position);
              // ean to rafi exei proionta 
              if (product != null) {
                  // ipologismos tou Fi-Pi-A me xrisi ternary operator, san if else gia na oristei 
                  // xamiloteros foros sto Nero
                  double vatRate = product.getName().equals("Νερό") ? 0.13 : 0.24;
                  // sinoliki timi mazi me Fi-Pi-A
                  double totalPrice = product.getPrice() * (1 + vatRate); 
                  // ean ta kermata pou exoun balei o xristis einai parapano i isa me tin timi tou
                  // proiontos
                  if (totalCoins >= totalPrice) {
                      // ipologismos gia ta resta
                      double change = totalCoins - totalPrice;
                      // enimerosi tou GUI
                      purchaseStatusProperty.set("Product purchased: " + product.getName());
                      // debug statements
                      System.out.println("Product purchased: " + product.getName());
                      System.out.println("Change returned: " + change);
                      // i mixani pairnei ta lefta tou xristi so iparxon poso tis kai dinei resta
                      cashInside = cashInside + totalPrice;
                      cashInside = cashInside - change;
                      // debug staement
                      System.out.println("Debug for cashInside " + cashInside);
                      // epeidi stin mixani oi agores kathe fora ginontai me osa lefta exei eidi
                      // topothetisei o xristis, me to telos mias agoras ta sinolika xrimata pou
                      // exei balei einai ta resta tou
                      totalCoins = change;
                      // enimerosi tou GUI
                      totalCoinsProperty.set("Total Coins: " + String.format("%.2f", totalCoins));
                      changeProperty.set("Change: " + String.format("%.2f", change));
                      // afairesi enos proiontos apo tin sigkekrimeni topothesia tis mixanis
                      shelf.removeProduct(position);
                  } else {
                      // an den exei balei o xristis arketa xrimata
                      purchaseStatusProperty.set("Insufficient funds. Please insert more coins");
                      System.out.println("Insufficient funds. Please insert more coins.");
                  }
              } else {
                  // an o xristis paei na agorasei apo simio xoris proion
                  purchaseStatusProperty.set("Invalid product position");
                  System.out.println("Invalid product position.");
              }
          } else {
              // an to rafi den iparxei
              purchaseStatusProperty.set("Invalid shelf number");
              System.out.println("Invalid shelf number.");
          }
      }


      // i palia sinartisi agoras
      /*public void purchaseItem(double itemPrice) {
        if (totalCoins >= itemPrice) {
            change = totalCoins - itemPrice;
            totalCoins = 0.00;
        }
      }*/

    // getter gia ta sinolika kermata
    public double getTotalCoins() {
        return totalCoins;
    }

    // getter gia ta resta
    public double getChange() {
        return change;
    }

    // palies sinartisis gia ta kermata
    /*public void acceptCoin(double coinValue) {
        totalCoins += coinValue;
        totalCoinsProperty.set(String.format("Total Coins: %.2f", totalCoins));
        setCoinStatus("Coin Accepted");
    }
    
    public void rejectCoin(double coinValue) {
        change += coinValue;
        changeProperty.set(String.format("Change: %.2f", change));
        setCoinStatus("Coin Rejected");
    }*/
  

    // getter gia ta kermata tis mixanis
    public double getCashInside() {
        return cashInside;
    }

    // setter gia tin topothetisi xrimaton stin mixani apo prin gia resta
    public void setCashInside(double cashInside) {
        this.cashInside = cashInside;
    }
    
  // mellontikes methodoi, getters kai setters

}

// i main
// tha klironomisei stoixeia apo tin Application klassi tou paketou JavaFX
public class Main extends Application {
    // labels gia to GUI, dexontai enimeroseis kata tin diarkeia pou ekteleite i efarmogi
    private Label infoLabel;
    private Label displayLabel;
    private Label totalCoinsLabel;
    private Label changeLabel;
    private Label coinStatusLabel;
    private Label slotStatusLabel;
    private Label purchaseStatusLabel;
    private VendingMachine vendingMachine;
    // auto to orisma einai gia tin tampela stin opoia grafete o monadikos 2psifios kodikos kathe
    // slot tis mixanis, kai einai keni giati dexete times kata tin diarkeia tis ektelesis
    private String enteredCode = "";
  

    // string property gia ton 2psifio kodiko gia ta slots tis mixanis
    private StringProperty enteredCodeProperty() {
    return new SimpleStringProperty(enteredCode);
    }


    // i main methodos einai to simio enarksis tou programmatos
    // i methodos launch lansari tin methodo start() mesa sto Application class tis JavaFX
    public static void main(String[] args) {
        launch(args);
    }


  // i child class main me tin methodo start() kanei override stixeia tis base class Application
  // i start kaleite otan i Application ksekina
  @Override
  public void start(Stage primaryStage) {
    // arxikopoiisei tis mixanis
    vendingMachine = new VendingMachine();

    // ksekina i diadiaksia gemismatos tis mixanis me rafia kai ton rafion me proionta
    // prostithente 5 rafia me 3 thesis to kathena
    vendingMachine.addShelf(1, 3);
    vendingMachine.addShelf(2, 3);
    vendingMachine.addShelf(3, 3);
    vendingMachine.addShelf(4, 3);
    vendingMachine.addShelf(5, 3);


    // dilosi ton proionton me ta monadika tous stoixeia
    Product water = new Product("Νερό", "Μικρό μπουκάλι", "Ζαγόρι", 10, 5, 5, 0.5, "26/8/2045", true, "500ml");
    
    Product cokeClassic = new Product("Coca Cola", "Μικρό τενεκεδάκι", "Coca Cola", 15, 8, 8, 1, "1/6/2024", true, "500ml");
    
    Product cokeLight = new Product("Coca Cola Light", "Μικρό τενεκεδάκι", "Coca Cola", 15, 8, 8, 1, "1/6/2024", true, "500ml");
    
    Product cokeZero = new Product("Coca Cola Zero", "Μικρό τενεκεδάκι", "Coca Cola", 15, 8, 8, 1, "1/6/2024", true, "500ml");
    
    Product chipsSalt = new Product("Πατατάκια Αλάτι", "Μικρή συσκευασία", "Lays", 20, 12, 12, 1.5, "6/7/2023", false, "40gr");
    
    Product chipsOregano = new Product("Πατατάκια Ρίγανη", "Μικρή συσκευασία", "Lays", 20, 12, 12, 1.5, "6/7/2023", false, "40gr");
    
    Product chipsKetchup = new Product("Πατατάκια Κέτσαπ", "Μικρή συσκευασία", "Lays", 20, 12, 12, 1.5, "6/7/2023", false, "40gr");
    
    Product liptonPeach = new Product("Lipton Ροδάκινο", "Μεσαίο μπουκάλι", "Lipton", 17, 8, 8, 1.3, "11/3/2024", true, "600ml");

    Product liptonLemon = new Product("Lipton Λεμόνι", "Μεσαίο μπουκάλι", "Lipton", 17, 8, 8, 1.3, "11/3/2024", true, "600ml");

    Product liptonGreen = new Product("Lipton Πράσινο Τσάι", "Μεσαίο μπουκάλι", "Lipton", 17, 8, 8, 1.3, "11/3/2024", true, "600ml");

    Product lacta = new Product("Σοκολάτα Λάκτα", "Μικρή", "Lacta", 2, 17, 6, 2, "6/7/2023", false, "100gr");

    Product chocoBreak = new Product("Σοκολάτα Break", "Μικρή", "Break", 2, 9, 9, 1.3, "18/4/2024", false, "90gr");
  
    
    // topothetisi proionton se kathe thesi sirtariou se oti posotites epilexthoun
    // exoun afethei epitides dio thesis tis mixanis adeies gia na fainete i leitourgikotita tis mixanis
    vendingMachine.addProductToShelf(1, 1, water, 12);
    vendingMachine.addProductToShelf(1, 2, water, 6);
    vendingMachine.addProductToShelf(2, 1, cokeClassic, 4);
    vendingMachine.addProductToShelf(2, 2, cokeLight, 2);
    vendingMachine.addProductToShelf(2, 3, cokeZero, 7);
    vendingMachine.addProductToShelf(3, 1, chipsSalt, 8);
    vendingMachine.addProductToShelf(3, 2, chipsOregano, 2);
    vendingMachine.addProductToShelf(3, 3, chipsKetchup, 5);
    vendingMachine.addProductToShelf(4, 1, liptonPeach, 3);
    vendingMachine.addProductToShelf(4, 2, liptonLemon, 6);
    vendingMachine.addProductToShelf(4, 3, liptonGreen, 1);
    vendingMachine.addProductToShelf(5, 1, lacta, 3);
    vendingMachine.addProductToShelf(5, 3, chocoBreak, 9);

    // dimiourgeia tou GUI
    GridPane gridPane = createGridPane();
    addShelvesToGrid(gridPane);

    VBox mainVBox = new VBox();
    mainVBox.setSpacing(20);
    mainVBox.setPadding(new Insets(20));

    // sintaksi olon ton labels
    // gia pio eksatomikeumeni topothetisi tous sto GUI xrisimopoiithikan kai texnotropies CSS
    // gia megethos grammaton, topothetisi ston xoro kai poso makri mporei na einai ena string
    // prin paei se apo kato grammi
    infoLabel = new Label("Press the products to see the quantity and the slot code");
    infoLabel.setStyle("-fx-font-size: 20px; -fx-alignment: center; -fx-translate-x: 200px;");
    infoLabel.setWrapText(true);
    
    displayLabel = new Label("Shelf ID: ");
    displayLabel.setStyle("-fx-font-size: 18px; -fx-alignment: center; -fx-translate-x: 100px; -fx-translate-y: 20px;");
    displayLabel.setMaxWidth(200);
    displayLabel.setWrapText(true);

    totalCoinsLabel = new Label("Total Coins: 0.00");
    totalCoinsLabel.setStyle("-fx-font-size: 18px; -fx-alignment: center; -fx-translate-x: 10px; -fx-translate-y: 10px;");
    totalCoinsLabel.setMaxWidth(200);
    totalCoinsLabel.setWrapText(true);
    
    changeLabel = new Label("Change: 0.00");
    changeLabel.setStyle("-fx-font-size: 18px; -fx-alignment: center; -fx-translate-x: 10px; -fx-translate-y: 25px;");
    changeLabel.setMaxWidth(200);
    changeLabel.setWrapText(true);

    coinStatusLabel = new Label("Coin Acceptance Status");
    coinStatusLabel.setStyle("-fx-font-size: 18px; -fx-translate-x: 70px; -fx-translate-y: 5px;");
    coinStatusLabel.setMaxWidth(300);
    coinStatusLabel.setWrapText(true);

    slotStatusLabel = new Label("Slot Status");
    slotStatusLabel.setStyle("-fx-font-size: 18px; -fx-translate-x: 70px; -fx-translate-y: 20px;");
    slotStatusLabel.setMaxWidth(250);
    slotStatusLabel.setWrapText(true);

    purchaseStatusLabel = new Label("Purchase & Cancel Status");
    purchaseStatusLabel.setStyle("-fx-font-size: 18px; -fx-translate-x: 280px; -fx-translate-y: 0px;");
    purchaseStatusLabel.setMaxWidth(300);
    purchaseStatusLabel.setWrapText(true);
    


  
    // texnikes gia na apofigo exceptions me tin boitheia tou stack overflow
    if (!totalCoinsLabel.textProperty().isBound()) {
      Platform.runLater(() -> {
            // https://stackoverflow.com/questions/33496537/javafx-bound-value-cannot-be-set-exception 
        totalCoinsLabel.textProperty().bind(vendingMachine.totalCoinsProperty());
    });
        totalCoinsLabel.textProperty().unbind();
    }


    Platform.runLater(() -> {
      changeLabel.textProperty().bind(vendingMachine.changeProperty());
    });

    coinStatusLabel.textProperty().bind(vendingMachine.coinStatusProperty());
    // dilosi tou onomatos tis etiketas edo, logo ton exceptions eixa thema me tin arxikopoiisi tis
    // stin arxi tis klassis opos tis alles
    // etroge tin etiketa allios
    vendingMachine.setCoinStatus("Coin Acceptance Status");


    Platform.runLater(() -> {
      purchaseStatusLabel.textProperty().bind(vendingMachine.purchaseStatusProperty());
    });


    // orthogonio sxima gia na mpoun ta label mesa
    // den emfanizotan pote stin efarmogi gia kapoio logo
    /*
    // dimiourgeia orthogoniou sximatos
    Rectangle rectangle = new Rectangle(200, 200);

    // texnotropies CSS
    rectangle.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2px;");
    */


    
    // xromata kai stackpane gia na to krataei stin efarmogi
    StackPane stackPane = new StackPane(/*rectangle*/);
    stackPane.setStyle("-fx-background-color: lightgray;");  

    // dimiourgeia tis skinis tis efarmogis
    Scene scene = new Scene(stackPane, 400, 400);

    // kapoia xaraktiristika gia tin skini
    primaryStage.setTitle("Rectangular Frame");
    primaryStage.setScene(scene);
    primaryStage.show();

    
    
    // dimiourgeia ton numpads
    // gia tin epilogi slot tis mixanis gia agora
    HBox numpadHBox = createNumpad();
    // gia tin eisagogi kermaton
    HBox coinNumpad = createCoinNumpad();

    
    // bazo ta 2 numpads se ena koino GridPane oste na mporo na ta topothetiso
    // omorfa kai simetrika sto UI apofeugontas ateleiotes seires me CSS
    GridPane numpadsGridPane = new GridPane();
    // rithmizo tin thesi tou GridPane stin skini, to keno anamesa sta antikeimena
    // pou exei mesa, kai liges sintetagmenes gia kaliteri topothetisi auton
    numpadsGridPane.setStyle("-fx-translate-x: 80px; -fx-translate-y: 20px;");
    numpadsGridPane.setHgap(90);
    numpadsGridPane.add(coinNumpad, 0, 0);
    numpadsGridPane.add(numpadHBox, 1, 0);
    numpadsGridPane.add(purchaseStatusLabel, 1, 0);

    // ena akoma GridPane gia ta labels
    GridPane labelsGridPane = new GridPane();
    // omoios texnikes gia omorfia
    labelsGridPane.setStyle("-fx-translate-x: 80px; -fx-translate-y: 20px;");
    labelsGridPane.setHgap(90);
    labelsGridPane.add(displayLabel, 1, 0);
    labelsGridPane.add(totalCoinsLabel, 0, 0);
    labelsGridPane.add(changeLabel, 0, 1);
    labelsGridPane.add(coinStatusLabel, 3, 0);
    labelsGridPane.add(slotStatusLabel, 3, 1);
      

    // bazo ola ta gridPanes kai labels sto kirio VBox
    // apo aristera pros ta deksia ta orismata sto UI topothetounte apo pano pros ta kato
    mainVBox.getChildren().addAll(infoLabel, gridPane, numpadsGridPane, labelsGridPane);

    // arxikopoiisi kapoion stoixeion kai enarksi tou GUI
    BorderPane rootPane = new BorderPane();
    rootPane.setCenter(mainVBox);

    // megethos parathirou efarmogis
    Scene mainScene = new Scene(rootPane, 1000, 900);

    // onoma parathirou
    primaryStage.setTitle("Vending Machine");
    primaryStage.setScene(mainScene);
    primaryStage.show();
}


// methodos pou antidra otan patiountai ta koumpia tou Numpad
// anaferete sto Numpad pou asxoleite me ta Slots tis mixanis kai tis agores
private void handleNumpadButtonClick(String buttonLabel) {
    // ean patithei to cancel koumpi tou numpad akironete i agora, epistrofi xrimaton
    if (buttonLabel.equals("Cancel")) {
        vendingMachine.cancelPurchase();
        System.out.println("Debug cash returned");
      // ena patithei to OK
    } else if (buttonLabel.equals("OK")) {
        // dixaeirisi tis entolis
        String enteredId = displayLabel.getText().trim(); // pairnei to monadiko ID tou slot
                                                          // to ID pos dinetai perigrafete parakato
                                                          // i methodos trim() aferei ta kena gia
                                                          // apofigi sfalmaton (ta kena einai strings)
                                                          // eno ta ID integers
        
        // oste kathe fora pou patiete OK i mixani na pairnei mono orisma Integer gia to ID kai oxi
        // kai to string minima tou label mazi, agonei to string kai pairnei oti einai meta autou
        enteredId = enteredId.replace("Shelf ID: ", "");
        // sistima try-catch gia tin diaxeirisi tou ID kathe slot tis mixanis
        if (!enteredId.isEmpty()) {
            try {
                // metatrepei to dosmeno apo ton xristi ID se grammi kai stili (slot) pou einai
                // to proion pou eipthimei na agorasei
                int shelfNumber = Integer.parseInt(enteredId.substring(0, 1));
                int position = Integer.parseInt(enteredId.substring(1));
                
                // agorazete to proion stin monadiki tou thesi sti mixani
                vendingMachine.purchaseProduct(shelfNumber, position);
                
                displayLabel.setText("Shelf ID: "); // sbinete i tampela gia na mpei neo ID gia nea
                                                    // agora
            } catch (NumberFormatException e) {
                // diaxeirisi tis periptosis pou to ID den einai sosti eisodos
                // ginontai enimeroseis stis tampeles tou UI
                slotStatusLabel.setText("Invalid input. Please enter a valid unique ID");
                System.out.println("Invalid input. Please enter a valid unique ID.");
                displayLabel.setText("Shelf ID: ");
            }
        }
    } else {
        // gia na ginoun oi parakato diadikasies prepei na iparxei ena ID mesa sto label
        // kathe patima koumpiou pairnaei ena integer stin tampela mexri na patithei OK
        displayLabel.setText(displayLabel.getText() + buttonLabel); 
    }
}




    // arxikopiite to GridPane pou dilothike pio pano
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

// topothetisi ton iparxonton rafion sto GUI
private void addShelvesToGrid(GridPane gridPane) {
    // pairnei ta rafia apo to antikeimeno vending machine kai ta bazei se xarti
    Map<Integer, Shelf> shelves = vendingMachine.getShelves();
    int row = 0;

   // orizete ena Vbox, oti mpei mesa tou mpainei katheta to ena kato apo to allo  
    VBox shelvesVBox = new VBox();
   // 20 pixel keno to ena dipla apo to allo
    shelvesVBox.setSpacing(20);

    // mia loopa pou pairnaei apo kathe rafi ston xarti ton rafio
    for (int shelfNumber : shelves.keySet()) {
        Shelf shelf = shelves.get(shelfNumber); // ipologizei posa rafia exei o xartis
        int maxPositions = shelf.getMaxPositions(); // ipologizei poses thesis exei to kathe rafi

        // orizete ena HBox, oti mpei mesa tou mpainei orizontia to ena dipla apo to allo
        HBox shelfHBox = new HBox();
        // 20 pixel keno anamesa sta pragmata puo exei mesa
        shelfHBox.setSpacing(20);

        // mexri na teleiosoun o megistos arithmos theseon gia proionta i loopa
        // ftiaxnei koumpia gia kathe proion 
        for (int position = 1; position <= maxPositions; position++) {
            Product product = shelf.getProduct(position);
            // me mia methodo pou graftike parakato perniete monadikos kodikos ID gia kathe thesi
            int slotID = generateSlotID(shelfNumber, position);
            Button productButton = createProductButton(shelfNumber, position ,product);
            shelfHBox.getChildren().add(productButton);
        }

        // to Hbox topothetite os child sto Vbox
        shelvesVBox.getChildren().add(shelfHBox);
    }

    // topothetite to Vbox sto gridPane
    gridPane.add(shelvesVBox, 0, row);
}

    // methodos gia monadikous kodikous ID gia kathe thesi stin mixani
    private int generateSlotID(int shelfNumber, int position) {
      // dimiourgeite airthmos gia kathe thesi me tin morfi mathimatikou pinaka matrix
      // 2 psifia to proto perigrafi tin grammi kai to deutero tin stili
      // epilektika kanena rafi den tha exei 2psifio arithmo pou periexei 0 giati sto numpad to 0
      // exei oristei os string eno ta alla noumera os integers
      // tha analithei kiallo auto parakato
      return (shelfNumber * 10) + position;
    }



    // dimourgeia koumpion me to onoma ton proionton
    private Button createProductButton(int shelfNumber, int position, Product product) { 
        Button productButton = new Button();


        // ena iparxei proion se mia thesi
        if (product != null) {
            // ipologizontai ksana i teliki timi gia na fainete sto koumpi
            double vatRate = product.getName().equals("Νερό") ? 0.13 : 0.24;
            double totalPrice = product.getPrice() * (1 + vatRate); // Total price with VAT  
            // sto kathe koumpi fainete to onoma kai i pragmatiki timi
            productButton.setText(product.getName() + " - " + String.format("%.2f", totalPrice) + "€");
            // orizoume os event to e, kai to event einai otan patithei to koumpi epistrefei
            // tis sintetagmenes tou proiontos stin mixani os arguments gia na xrisimopoiithoun meta
            // apo ta labels
            productButton.setOnAction(e -> handleProductButtonClick(shelfNumber, position));
        } else {
            // an to rafi den exei proionta
            productButton.setText("Empty");
            productButton.setDisable(true);
        }

        // xaraktiristika ton koumpion se diastaseis
        productButton.setPrefWidth(200);
        productButton.setPrefHeight(20);

        return productButton;
    }


  // dimiourgeia tou numpad pou asxoleite me ta koumpia gia ta monadika ID kai tin agora proionton
  private HBox createNumpad() {
    HBox numpadHBox = new HBox();
    numpadHBox.setSpacing(10);

    // ta noumera pou xreiazontai gia ta IDs ton simion stin mixani orizontai os int
    // ta koumpia auta otan patountai epistrefoun sto display.Label int
    int[] numberButtons = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    // ta 0, cancel kai ok os string
    String[] controlButtons = {"Cancel", "0", "OK"};

    /* 
       Na simiothei oti apo epilogi den eginan ola ta koumpia na epistrefoun string giati parousiazontan
       polla sfalmata me tin metatropi tou ID apo string se int gia na perastei gia epeksergasia gia tin
       agora enos proiontos.
       Efoson o algorithmos pou dinei monadika ID den epistrefei pote ID pou periexei 0 den xreiaotan na
       ginei to 0 int
       O logos pou to 0 einai string episi einai epeidi ta koumpia me tis times tous ftiaxnontai ston 
       kodika me loopa, opote me tin seira pou exoun tora tha ginotan kai to Cancel koumpi integer kai
       tha genniountan nea problimata
       Praktika to koumpi 0 den exei kamia xrisi sto programma pera apo omorfia
    */

    GridPane numpadGridPane = new GridPane();
    numpadGridPane.setHgap(5);
    numpadGridPane.setVgap(5);

    // arxikopiisi grammon kai stilon sto numpad gia na ksekinisei i diadikasia onomatodosias
    // ton koumpion
    int buttonIndex = 0;
    int col = 0;
    int row = 0;

    // i onomatodosia ton koumpion arithmon ginete me loopa
    for (int number : numberButtons) {
        Button button = new Button(String.valueOf(number));
        button.setPrefWidth(75);
        button.setPrefHeight(75);
        // event e einai otan patiountai ta int koumpia na epistrefoun tous arithmous os int sto label
        button.setOnAction(e -> handleNumpadButtonClick(String.valueOf(number)));

        numpadGridPane.add(button, col, row);

        // ta koumpia topothetountai 3 - 3 an seira mexri na ksemeinoun
        buttonIndex++;
        col++;
        if (buttonIndex % 3 == 0) {
            row++;
            col = 0;
        }
    }

    // omoios gia tin onomatodosia koumpion control
    for (String buttonLabel : controlButtons) {
        Button button = new Button(buttonLabel);
        button.setPrefWidth(75);
        button.setPrefHeight(75);
        // epistrefoun string
        button.setOnAction(e -> handleNumpadButtonClick(buttonLabel));

        numpadGridPane.add(button, col, row);

        col++;
        if (col % 3 == 0) {
            row++;
            col = 0;
        }
    }

    // gia kentrarisma ton numpads
    VBox numpadVBox = new VBox();
    numpadVBox.setAlignment(Pos.CENTER);
    numpadVBox.getChildren().add(numpadGridPane);

    // gia kentrarisma tou frame
    HBox frame = new HBox();
    frame.setAlignment(Pos.CENTER);
    frame.getChildren().add(numpadVBox);

    return frame;
}



  // to numpad pou asxoleite me ta kermata pou bazei o xristis stin mixani
  // i diadikasia onomatodosias, dimiourgias kai topothetisis einai paromoia me to proigoumeno numpad
  // tha sxoliastoun mono kapoies diafores
  private HBox createCoinNumpad() {
    HBox numpadHBox = new HBox();
    numpadHBox.setSpacing(10);

    String[] coinValues = {"0.01", "0.02", "0.05", "0.1", "0.2", "0.5", "1", "2"};

    GridPane numpadGridPane = new GridPane();
    numpadGridPane.setHgap(5);
    numpadGridPane.setVgap(5);

    int buttonIndex = 0;
    int col = 0;
    int row = 0;

    for (String coinValue : coinValues) {
        Button button = new Button(coinValue + "€");
        button.setPrefWidth(60);
        button.setPrefHeight(60);
        // ta kermata theorountai os double
        button.setOnAction(e -> handleCoinButtonClick(Double.parseDouble(coinValue)));

        numpadGridPane.add(button, col, row);

        buttonIndex++;
        col++;
        if (buttonIndex % 3 == 0) {
            row++;
            col = 0;
        }
    }

    VBox numpadVBox = new VBox();
    numpadVBox.setAlignment(Pos.CENTER); 
    numpadVBox.getChildren().add(numpadGridPane);

    HBox frame = new HBox();
    frame.setAlignment(Pos.CENTER); 
    frame.getChildren().add(numpadVBox);

    return frame;
}

    // methodos gia to pos antidroun ta koumpia me ta proionta otan patithoun
    private void handleProductButtonClick(int shelfNumber, int position) {
      //vendingMachine.purchaseProduct(shelfNumber, position);
      // xartis gia ta rafia
      Map<Integer, Shelf> shelves = vendingMachine.getShelves();
      // pairnei ta rafia
      Shelf shelf = shelves.get(shelfNumber);
        // an to rafi iparxei
        if (shelf != null) {
            // dimiourgeite ena ID gia to rafi kai tiponete oste o xristis na mporei na to
            // eisagei sto numpad otan thelei na agorasei kati
            int uniqueId = generateSlotID(shelfNumber, position);
            slotStatusLabel.setText("Unique ID: " + uniqueId);
            System.out.println("Unique ID: " + uniqueId);

        // tiponete episis to plithos ton proionton sto rafi
        int itemCount = shelf.getProductCount(position);
        slotStatusLabel.setText("Item count at slot " + shelfNumber + position + ": " + itemCount);
        System.out.println("Item count at shelf " + shelfNumber + ", position " + position + ": " + itemCount);
        } else {
            // debug statements
            slotStatusLabel.setText("Invalid shelf number");
            System.out.println("Invalid shelf number.");
        }
    }

    // methodos gia to pos antidroun ta koumpia pou exoun ta kermata otan patithoun
    private void handleCoinButtonClick(double coinValue) {
      // kalite i sinartisi pou bazei kermata stin mixani
      // ginete i diadikasia me tin 205 pithanotita na aporiftei to kerma
      vendingMachine.insertCoin(coinValue);
      // ipologizontai to plithos ton kermaton pou ebale o xristis kai ta resta otan erthei i stigmi na
      // iparksoun
      double totalCoins = vendingMachine.getTotalCoins();
      double change = vendingMachine.getChange();
      // ginontai antistoixes enimeroseis stis tampeles otan o xristis bazei kialla kermata, to mixanima
      // pairnei ta xrimata gia na oloklirosei mia agora
      // stin mixani i otan i mixani epistrefei resta meta tin agora
      totalCoinsLabel.setText("Total Coins: " + String.format("%.2f", totalCoins));
      changeLabel.setText("Change: " + String.format("%.2f", change));
    }

    
}
