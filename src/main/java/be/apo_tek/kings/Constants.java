package be.apo_tek.kings;

/*
This class, which I'm not sure of the real utility, consists of static methods returning code constants. Those are
public and so accessible everywhere (apart from non-static context which may cause conflicts).
 */

public class Constants {

    public static int GENERIC_ATTACK_SPEED() {
        return 24;}

    public static String PLAYERS_COMMAND_IDENTIFIER(){
        return "players";}

    public static boolean COPY_DEFAULT(){
        return true;}

    public static String ENABLE_MESSAGE(){
        return "§cEnabled";}

    public static String DISABLE_MESSAGE(){
        return "§cDisabled";}

}
