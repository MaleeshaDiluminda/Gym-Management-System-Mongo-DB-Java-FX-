
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import org.bson.Document;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGymManager {

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);
        int members_count = 0;
        MongoClient gym_customer = new MongoClient("localhost", 27017);
        MongoDatabase client_database = gym_customer.getDatabase("GYM_MANAGEMENT");


        if (client_database.getCollection("Client") == null) {
            client_database.createCollection("Client");
        }

        while (true) {
            System.out.println("Press 1 to Add Default Member");
            System.out.println("Press 2 to Add Student Member");
            System.out.println("Press 3 to Add Over 60 Member");
            System.out.println("Press 4 to DELETE");
            System.out.println("Press 5 to PRINT");
            System.out.println("Press 6 to SAVE");
            System.out.println("Press 7 to SORT");
            System.out.println("Press 8 to GUI");
            System.out.println("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");

            Scanner scn = new Scanner(System.in);
            String command = scn.next();
            System.out.println(command);

            if (command.equals("1")) {
                System.out.print(" First Name                  : ");
                String client_first_name = scn.next();
                System.out.print(" Last Name                   : ");
                String client_last_name = scn.next();
                System.out.print(" contact                     : ");
                String client_contact = scn.next();
                System.out.print(" Gym Number                  : ");
                String client_index = scn.next();
                System.out.print(" Commencing Date(DD/MM/YEAR) : ");
                String commencing_date = scn.next();

                DefaultMember defaultMember = new DefaultMember();

                defaultMember.setClient_first_name(client_first_name);
                defaultMember.setClient_last_name(client_last_name);
                defaultMember.setClient_contact(client_contact);
                defaultMember.setClient_index(client_index);
                defaultMember.setCommencing_date(commencing_date);

                MongoCollection database = client_database.getCollection("Client");

                Document file_one = new Document();

                file_one.put(" First Name ", client_first_name);
                file_one.put(" Last Name ", client_last_name);
                file_one.put(" Contact ", client_contact);
                file_one.put(" Gym Number ", client_index);
                file_one.put(" Commencing Date(DD/MM/YEAR) : ", commencing_date);

                database.insertOne(file_one);
                members_count = members_count + 1;
                System.out.print(members_count);

            } else if (command.equals("2")) {
                System.out.print(" Student First Name           : ");
                String client_first_name = scn.next();
                System.out.print(" Student Last Name            : ");
                String client_last_name = scn.next();
                System.out.print(" Student contact              : ");
                String client_contact = scn.next();
                System.out.print(" Student Gym Number           : ");
                String client_index = scn.next();
                System.out.print(" Commencing Date(DD/MM/YEAR)  : ");
                String commencing_date = scn.next();
                System.out.print(" School or Institute          : ");
                String school_or_institute = scn.next();

                StudentMember studentMember = new StudentMember();

                studentMember.setClient_first_name(client_first_name);
                studentMember.setClient_last_name(client_last_name);
                studentMember.setClient_contact(client_contact);
                studentMember.setClient_index(client_index);
                studentMember.setCommencing_date(commencing_date);
                studentMember.setSchool_or_instutute(school_or_institute);


                MongoCollection database = client_database.getCollection("Client");

                Document file_one = new Document();

                file_one.put(" First Name ", studentMember.getClient_first_name());
                file_one.put(" Last Name ", studentMember.getClient_last_name());
                file_one.put(" Contact ", studentMember.getClient_contact());
                file_one.put(" Gym Number ", studentMember.getClient_index());
                file_one.put(" Commencing Date(DD/MM/YEAR) : ", studentMember.getCommencing_date());
                file_one.put(" School or Institute ", studentMember.getSchool_or_instutute());

                database.insertOne(file_one);
                members_count = members_count + 1;
                System.out.print(members_count);

            } else if (command.equals("3")) {
                System.out.print(" First Name                   : ");
                String client_first_name = scn.next();
                System.out.print(" Last Name                    : ");
                String client_last_name = scn.next();
                System.out.print(" contact                      : ");
                String client_contact = scn.next();
                System.out.print("  Gym Number           : ");
                String client_index = scn.next();
                System.out.print(" Commencing Date(DD/MM/YEAR)  : ");
                String commencing_date = scn.next();
                System.out.print(" Age                          : ");
                String member_age = scn.next();

                Over60Member over60Member = new Over60Member();

                over60Member.setClient_first_name(client_first_name);
                over60Member.setClient_last_name(client_last_name);
                over60Member.setClient_contact(client_contact);
                over60Member.setClient_index(client_index);
                over60Member.setCommencing_date(commencing_date);
                over60Member.setMember_age(member_age);


                MongoCollection database = client_database.getCollection("Client");

                Document file_one = new Document();

                file_one.put(" First Name ", over60Member.getClient_first_name());
                file_one.put(" Last Name ", over60Member.getClient_last_name());
                file_one.put(" Contact ", over60Member.getClient_contact() );
                file_one.put(" Gym Number ", over60Member.getClient_index());
                file_one.put(" Commencing Date(DD/MM/YEAR) : ",over60Member.getCommencing_date());
                file_one.put(" School or Institute ", over60Member.getMember_age());

                database.insertOne(file_one);
                members_count = members_count+1;
                System.out.print(members_count);


            } else if (command.equals("4")) {
                System.out.print(" Gym Number you want to delete : ");
                String delete_gym_number = scn.next();

                MongoCollection database = client_database.getCollection("Client");

                Document file_delete = new Document();
                file_delete.put(" Gym Number ", delete_gym_number);

                database.deleteOne(file_delete);
                members_count = members_count-1;
                System.out.print(members_count);

            } else if (command.equals("5")) {
                MongoCollection database = client_database.getCollection("Client");
                FindIterable<Document> findIter = database.find();
                for (Document document:findIter) {
                    System.out.println(document);
                    System.out.println("_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");

                }

            } else if (command.equals("6")) {
                  try {
                      MongoCollection database = client_database.getCollection("Client");
                      FileWriter fileWriter = new FileWriter("Documents:\\GYMFile\\database.txt");
                      FindIterable<Document> findIterable_one = database.find();
                      fileWriter.write("GYM MANAGEMENT SYSTEM\n");
                      fileWriter.write("\n");
                      fileWriter.write("   First Name                 :\n");
                      fileWriter.write("   Last Name                  :\n");
                      fileWriter.write("   Gym Number                 :\n");
                      fileWriter.write("   Commencing Date            :\n");
                      fileWriter.write("   School or Institute        :\n");
                      fileWriter.write("   Age                        :\n");
                      fileWriter.close();
                      System.out.println("Successfully Saved");

                   }
                      catch  (Exception e){
                          {
                              System.out.println("ERROR");
                          }

              }

            } else if (command.equals("7")){
                MongoCollection database = client_database.getCollection("Client");
                FindIterable<Document> findIter = database.find().sort(new BasicDBObject(" Gym Index ", 1));
                MongoCursor<Document> mongoCursor2 = findIter.iterator();
                while (mongoCursor2.hasNext()){
                    System.out.println(mongoCursor2.next());
                }
            } else if (command.equals("8")){

                try {
                    Application.launch(GUI.class);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}




