import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MushroomClassification {

    public static void main(String args[]){
        readData();
    }

    public static char emptyLineCheck(String line){
        if(line.equals("")){
            return ' ';
        }
        else{
            return line.charAt(0);
        }
    }

    public static ArrayList<Mushroom> readData(){
        //Read data and create Mushroom objects for each mushroom
        //and put them into an arraylist
        String csvFile = "secondary_data.csv";
        String line = "";
        String delimiter = ";";
        ArrayList<Mushroom> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the header line and discard it
            br.readLine();

            // Read the remaining lines and store the data in a List of HashMaps
            while ((line = br.readLine()) != null) {
                //Make a mushroom object for each line
                
                String[] row = line.split(delimiter);

                

                double capDiameter = Double.parseDouble(row[1]);
                char capShape = emptyLineCheck(row[2]);
                char capSurface = emptyLineCheck(row[3]);
                char capColor = emptyLineCheck(row[4]);
                boolean bruiseOrBleeds = row[5].equals("t");
                char gillAttachment = emptyLineCheck(row[6]);
                boolean gillColor = row[8].equals("t");
                double stemHeight = Double.parseDouble(row[9]);
                double stemWidth = Double.parseDouble(row[10]);
                char stemRoot = emptyLineCheck(row[11]);
                boolean stemSurface = row[12].equals("t");
                char stemColor = emptyLineCheck(row[13]);
                boolean veilType = row[14].equals("t");
                boolean veilColor = row[15].equals("t");
                boolean ringNumber = row[16].equals("t");
                char ringType = emptyLineCheck(row[17]);
                char sporePrintColor = emptyLineCheck(row[18]);
                char habitat = emptyLineCheck(row[19]);
                char season = emptyLineCheck(row[20]);
                Mushroom mushroom = new Mushroom(capDiameter, capShape, capSurface, capColor, bruiseOrBleeds, 
                gillAttachment, gillColor, stemHeight, stemWidth, stemRoot, stemSurface, stemColor, veilType, 
                veilColor, ringNumber, ringType, sporePrintColor, habitat, season);
                data.add(mushroom);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the data
        for (Mushroom mushroom : data) {
            System.out.println(mushroom);
        }
        return data;
    }

}
