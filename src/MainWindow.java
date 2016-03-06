import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by avtseben on 05.03.2016.
 */
public class MainWindow extends JFrame{

    public void load() {
        setSize(600,300);
        setLocation(600,400);
        setTitle("Java Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Остановить програмы при закрытии окна
        setLayout(new BorderLayout());

        JPanel jp = new JPanel();//панель в окне
        jp.setLayout(new BorderLayout());
        add(jp, BorderLayout.SOUTH);

        JTextArea jta = new JTextArea(5,20);//Отображение текста
        add(jta, BorderLayout.CENTER);
        ClientSide clientSide = new ClientSide(jta);
        new Thread(clientSide).start();

        JTextField jtf = new JTextField(20);//Ввод текста
        jp.add(jtf, BorderLayout.CENTER);
        jtf.setText("Some text");

        JButton jbt = new JButton("Send");//Кнопка
        jp.add(jbt, BorderLayout.EAST);
        jbt.addActionListener(new ActionListener() {//Обработка событий нажатой кнопки
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = jtf.getText();
                jtf.setText("");
                clientSide.sendMsg(s);
            }
        });
        setVisible(true);//Желательно чтобы форма стала видимой только послк того как на ней будут отображены все элементы
    }
}
