import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;

class Handler implements URLHandler {

    LinkedList<String> items = new LinkedList<String>();
    
    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                items.add(parameters[1]);
                return String.format("Added!");
            }
            return "Error!";
        } else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                String key = parameters[1];
                String ret = "";
                for (int i = 0; i < items.size(); i++) {
                    Boolean check = items.get(i).contains(key);
                    if(check == true){
                        ret += items.get(i) + " ";
                    }
                }
                return String.format(ret);
            }
            return "Error!";
        } else {
            return "404 Not Found!";
        }
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
