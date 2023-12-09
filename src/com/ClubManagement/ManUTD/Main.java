package com.ClubManagement.ManUTD;

import java.util.Scanner;

import static java.lang.System.err;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Enter 1 for admin login page OR 2 for player login page");
            int n1 = in.nextInt();
            if (n1 == 1) {
                System.out.println("=====Welcome to Admin Login Page=====");
            } else System.out.println("=====Welcome to Player Login Page=====");

            System.out.print("Enter Your UserID:");
            String userId = in.next();
            System.out.print("Enter Your Password:");
            String pass = in.next();
            if (n1 == 1) {

                DBCode admin = new DBCode();

                if (admin.login(userId, pass)) {

                    while (true) {

                        System.out.println("=====Welcome Admin Home Page=====");
                        System.out.println("Press 1 To Add new Player");
                        System.out.println("Press 2 To View All players");
                        System.out.println("Press 3 To Update Yesterday's Match performance");
                        System.out.println("Press 4 To Update the Age of the player");
                        System.out.println("Press 5 To Update Position of the player");
                        System.out.println("Press 6 To Delete Player from the team");
                        System.out.println("Press 7 To Add new Admin");
                        System.out.println("Press 8 To Modify your Credentials");
                        System.out.println("Press 9 To Logout");
                        System.out.println("_______________________________________________________");

                        int opt = in.nextInt();
                        switch (opt) {
                            case 1:     // String name,int jerseyNum,String nationality,int age,String position,int appearance,int mins,int goals,float ps_percent,int motm,float rating
                                System.out.print("Enter Player's Name:");
                                String name = in.next();
                                System.out.print("Enter Jersey Number of the Player(it must be unique):");
                                int jerseyNum = in.nextInt();
                                System.out.print("Enter Player's Nationality:");
                                String nationality = in.next();
                                System.out.print("Enter Player's Age:");
                                int age = in.nextInt();
                                System.out.print("Enter Player's Position:");
                                String position = in.next();
                                System.out.print("Enter Player's Number of Appearances:");
                                int appearance = in.nextInt();
                                System.out.print("Enter Player's Duration(in mins) on Ground:");
                                int mins = in.nextInt();
                                System.out.print("Enter Player's Goals:");
                                int goals = in.nextInt();
                                System.out.print("Enter Player's Possession in %:");
                                float ps_percent = in.nextFloat();
                                System.out.print("Enter Player's Total no of Man of the match awards:");
                                int motm = in.nextInt();
                                System.out.print("Enter Player's Rating:");
                                float rating = in.nextFloat();
                                System.out.print("Enter Player's Secret key:");
                                String secretkey = in.next();

                                if (admin.insert(name, jerseyNum, nationality, age, position, appearance, mins, goals, ps_percent, motm, rating, secretkey) > 0) {
                                    System.out.println("New player Added successfully");
                                }
                                System.out.println("------------------------------------------------------");
                                System.out.println("Now Create Credentials For the player");
                                System.out.print("Enter UserId for the player:");
                                String user = in.next();
                                System.out.print("Enter password for the player:");
                                pass = in.next();
                                //System.out.println("Enter jersey number of the player");
                                // jerseyNum = in.nextInt();
                                if (admin.createCredentialsForPlayers(user, pass, jerseyNum,secretkey) > 0) {
                                    System.out.println("Credentials Created For the Player Successfully");
                                }
                                break;

                            case 2:
                                admin.adminView();
                                break;

                            case 3:  //int appearance,int mins,int goals,float ps_percent,int motm,int jerseyNum

                                System.out.println("Enter Player's Appearance:");
                                appearance = in.nextInt();
                                System.out.println("Enter Player's Total duration:");
                                mins = in.nextInt();
                                System.out.print("Enter Player's Goals:");
                                goals = in.nextInt();
                                System.out.print("Enter Player's Possession in %:");
                                ps_percent = in.nextFloat();
                                System.out.print("Enter Player's Total no of Man of the match awards:");
                                motm = in.nextInt();
                                System.out.print("Enter Jersey Number of the Player:");
                                jerseyNum = in.nextInt();
                                if (admin.updateAfterEveryMatch(appearance, mins, goals, ps_percent, motm, jerseyNum) > 0) {
                                    System.out.println("Players Stats Updated");
                                }
                                break;

                            case 4:
                                System.out.print("Enter Player's Age:");
                                age = in.nextInt();
                                System.out.print("Enter Player's Jersey Number:");
                                jerseyNum = in.nextInt();

                                if (admin.updateAge(age, jerseyNum) > 0) {
                                    System.out.println("Player's Age Updated Successfully");
                                }
                                break;

                            case 5:
                                System.out.print("Enter Player's position:");
                                position = in.next();
                                System.out.print("Enter Player's Jersey Number:");
                                jerseyNum = in.nextInt();

                                if (admin.updatePosition(position, jerseyNum) > 0) {
                                    System.out.println("Player's Position Updated Successfully.");
                                }
                                break;

                            case 6:
                                System.out.print("Enter Player's Jersey Number:");
                                int jerseyNum5 = in.nextInt();

                                if (admin.delete(jerseyNum5) > 0) {
                                    System.out.println("Player Details Deleted Successfully.");
                                }
                                break;

                            case 7:
                                System.out.println("Enter userId for the New admin:");
                                userId = in.next();
                                System.out.println("Enter password for the New admin");
                                pass = in.next();

                                if (admin.adminCreatingAnotherAdminAccount(userId, pass) > 0) {
                                    System.out.println("New Admin Account created Successfully");
                                }
                                break;
                            case 8:
                                System.out.print("Enter your Old userId");
                                String oldUserId = in.next();
                                System.out.print("Enter your Old Password");
                                String oldPass = in.next();
                                System.out.print("Enter your new Userid");
                                String newUserId = in.next();
                                System.out.print("Enter Your New Password");
                                String newPass = in.next();
                                if (admin.adminModifyingAdminCredentials(oldUserId, oldPass, newUserId, newPass) > 0) {
                                    System.out.println("Admin Credentials Changed Successfully");
                                }
                            case 9:
                                admin.conClose();
                                break;
                            default:
                                System.out.println("Enter Correct Details please ");
                        }
                        if (opt == 9) {
                            break;
                        }
                    }
                }
            } else {
                DBCode player = new DBCode();

                if (player.playerLogin(userId, pass)) {
                    while (true) {
                        System.out.println("=====Welcome Player Home Page=====");
                        System.out.println("Press 1 To View Your Stats");
                        System.out.println("Press 2 To Change Your Credentials");
                        System.out.println("Press 3 To Change Your Name");
                        System.out.println("Press 4 To Logout");
                        int opt = in.nextInt();

                        if (opt == 1) {
                            System.out.print("Enter Your Jersey Number:");
                            int jerseyNum = in.nextInt();
                            System.out.print("Enter your Secret key:");
                            String secretKey = in.next();
                            player.playerView(jerseyNum, secretKey);
//                            break;
                        } else if (opt == 2) {
                            System.out.print("Enter your Jersey Number:");
                            int jerseyNum = in.nextInt();
                            System.out.print("Enter your secret key:");
                            String secretkey = in.next();
                            System.out.print("Enter your new UserId:");
                            String userid = in.next();
                            System.out.print("Enter your new password:");
                            String password = in.next();

                            if (player.playerModifyingCredentials(userid, password, jerseyNum, secretkey) > 0) {
                                System.out.println("Your Credentials Modified Successfully And Please don't forgot your Credentials.");
                            }
                        } else if (opt == 3) {
                            System.out.print("Enter your Jersey number:");
                            int jerseyNum = in.nextInt();
                            System.out.println("Enter your Secret key");
                            String secretkey = in.next();
                            System.out.print("Enter Your new Name:");
                            String name = in.next();

                            if (player.playerModifyingName(name, jerseyNum, secretkey) > 0) {
                                System.out.println("Your name changed successfully");
                            }

                        } else if (opt == 4) {
                            player.conClose();
                            break;
                        } else System.out.println("Enter Correct Details please ");
                    }
                }
            }
        } catch (Exception e) {
            err.println(e);
        }
    }
}
