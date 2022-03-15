package orz.anzhy.match;


import java.util.regex.Pattern;

public class RegexExample {

    public static void main(String[] args){
        String content = "0:0:0:0:0:0:0:1 - - [23/Dec/2021:21:25:56 +0800] \"POST /apiExec/get_idw_dws_eci_test1 HTTP/1.1\" 200 73 localhost \"-\"   \"http://localhost:8890/swagger-ui.html\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36\" \"JSESSIONID=06557C072BAE08A4381E59389AD61CF7\" \"-\" \"-\" \"-\" \"-\" \"-\" \"-\"";

//        String pattern = "/apiExec/([a-z0-9_]+) HTTP/1.1\" ([0-9]+) ([0-9]+) .+? ([0-9.]+)";
        String pattern = ".*/apiExec/(?<URI>[a-z0-9_]+) HTTP/1.1\" (?<state>[0-9]+) ([0-9]+) .+? (?<rtime>[0-9.]+).*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 子字符串? " + isMatch);
    }



}
