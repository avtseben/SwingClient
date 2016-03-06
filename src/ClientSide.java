import com.github.rjeschke.txtmark.Run;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by avtseben on 06.03.2016.
 */
public class ClientSide implements Runnable{

    private JTextArea jta;
    private static PrintWriter out;

    public ClientSide(JTextArea _jta) { jta = _jta;}//когда запускаем клиентскую часть мы даем ссылу на TextArea в наше окне
                                                    //Дабы он выводил туда сообщения от сервера

    public void run(){

        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress("localhost", 8189));
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();

            Scanner in = new Scanner(inStream);
            out = new PrintWriter(outStream, true);

            new Thread(new Runnable() {//Чтения с сервера
                @Override
                public void run() {
                    while (true) {
                        String line = "";
                        if (in.hasNextLine()) line = in.nextLine();
                        //jta.setText(line);//Если что то пришло то мы это выбрасываем в текстовыю область на окне
                        jta.append(line);//И лучше если это appent(добавить)
                    }
                }
            }).start();

            while (true){
                String line = "";
                if(in.hasNextLine()) line = in.nextLine();
                jta.append(line + "\n");
            }


        } catch (IOException e) {
            System.out.println("ClientSide");

        }
    }
    public static void  sendMsg (String str){
        out.print(str);
    }
}

