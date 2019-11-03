import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class tt extends Thread {
    List<String> identity = new ArrayList<>();
    HashMap<String,Integer> tier = new HashMap<>();
    HashMap<String,String> title = new HashMap<>();
    HashMap<String, List<String>> keyword = new HashMap<>();
    public String gettitle(String s) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '"') {
                counter++;
                continue;
            }
            if (counter == 7) {
                sb.append(s.charAt(i));
            }
            if (counter == 8) break;
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
    public String getid(String s) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '"') {
                counter++;
                continue;
            }
            if (counter == 3) {
                sb.append(s.charAt(i));
            }
            if (counter == 4) break;
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
    public int gettier(String s) {

        String c = "";
        for (int i = 20 ; i < s.length() ; i++) {
            if (s.charAt(i) == 'n' && s.charAt(i-1) =='o' && s.charAt(i-2) =='i' && s.charAt(i-3) =='t' && s.charAt(i-4) =='a' && s.charAt(i-5) =='t'
                    && s.charAt(i-6) =='i' && s.charAt(i-7) =='c' && s.charAt(i-8) =='_') {
                c = s.substring(i+4,i+5);
            }
        }
        //System.out.println(Integer.parseInt(c)+1);
        return Integer.parseInt(c)+1;
    }
    public List<String> getkeyword(String s) {
        List<String> ans = new ArrayList<>();
        String c = "";
        for (int i = 20 ; i < s.length() ; i++) {
            if (s.charAt(i) == 'e' && s.charAt(i-1) =='m' && s.charAt(i-2) =='a' && s.charAt(i-3) =='n') {
                int count = 0;
                StringBuilder sb = new StringBuilder();
                for (int j = i ; j < s.length() ; j++) {
                    if (s.charAt(j) =='"') {
                        count++;
                        continue;
                    }
                    if (count == 3) {
                        ans.add(sb.toString());
                        //System.out.println(sb.toString());
                        break;
                    }
                    if (count == 2) {
                        sb.append(s.charAt(j));
                    }

                }
                continue;
            }
        }
        return ans;
    }
    public void readline(String infile) {
        String thisLine = null;

        try {
            // open input stream test.txt for reading purpose.
            File file = new File(infile);

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                String id = getid(st);
                String tit = gettitle(st);
                int tie = gettier(st);
                List<String> temp = getkeyword(st);
                identity.add(id);
                title.put(id,tit);
                tier.put(id,tie);
                keyword.put(id,temp);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void returnid(){
        for (String s : identity) {
            System.out.print(s + " , ");
        }
        System.out.println();
    }
    public void returntitle(String id) {
        System.out.println(title.getOrDefault(id,"DNE"));
    }
    public void returnkeyword(String id) {
        if (keyword.containsKey(id)) {
            for (String s : keyword.get(id)) {
                System.out.print(s + ",");
            }
        }
        else {
            System.out.print("NO ID exist");
        }
        System.out.println();
    }
    public void returntier(String s) {
        System.out.println(tier.getOrDefault(s,null));
    }
    // run() method contains the code that is executed by the thread.
    @Override
    public void run() {
        System.out.println("Inside : " + Thread.currentThread().getName());
        String filename = "read";
        readline(filename);
        System.out.println("Here is all the ID in database");
        returnid();
        System.out.println("to get tiltle use  returntitle(id)");
        System.out.println("to get keyword use  returnkeyword(id)");
        System.out.println("to get tier use  returntier(id)");
        //returnkeyword("100001334");
        //System.out.println("Inside : " + Thread.currentThread().getName());
        for (String s : identity) {
            System.out.println("ID : = " + s);
            System.out.println("Title is :");
            returntitle(s);
            System.out.println("Keywaord are :");
            returnkeyword(s);
            System.out.println("Tier is : (null = DNE)");
            returntier(s);
        }
    }




    public static void main(String[] args) {
        tt m = new tt();

        System.out.println("Creating thread...");
        Thread thread = new Thread();

        System.out.println("Starting thread...");
        thread.start();
    }
}