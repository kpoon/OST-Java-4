package greenDB;
                
import javax.swing.JFrame;
                
public class SimplePhoneBook {
    public static void main(String args[]) {   // Instantiate the phone book frame window and display it.
        PhoneBookFrame pbFrame = new PhoneBookFrame();
        pbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pbFrame.setVisible(true);
    }
}  // End SimplePhoneBook class

