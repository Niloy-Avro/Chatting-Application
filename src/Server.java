import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;

public class Server implements ActionListener {
    JButton sendButton;
    JTextField text;
    static JPanel textPanel;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dos;

    Server() {
        //! Heading Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(7, 94,84));
        panel.setBounds(0, 0, 450, 70);
        panel.setLayout(null);
        f.add(panel);
        

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image image101 = image1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon image201 = new ImageIcon(image101);
        JLabel backLabel = new JLabel(image201);
        backLabel.setBounds(5, 20, 25, 25);
        panel.add(backLabel);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(69);
            }
        });

        ImageIcon image2 = new ImageIcon(ClassLoader.getSystemResource("icons/server.png"));
        Image image102 = image2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon image202 = new ImageIcon(image102);
        JLabel profileLabel = new JLabel(image202);
        profileLabel.setBounds(40, 10, 50, 50);
        panel.add(profileLabel);

        ImageIcon image3 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image image103 = image3.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image203 = new ImageIcon(image103);
        JLabel videoLabel = new JLabel(image203);
        videoLabel.setBounds(300, 20, 30, 30);
        panel.add(videoLabel);
        videoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(f, "Not Functional");
            }
        });

        ImageIcon image4 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image image104 = image4.getImage().getScaledInstance(30, 32, Image.SCALE_DEFAULT);
        ImageIcon image204 = new ImageIcon(image104);
        JLabel phoneLabel = new JLabel(image204);
        phoneLabel.setBounds(355, 20, 30, 32);
        panel.add(phoneLabel);
        phoneLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(f, "Not Functional");
            }
        });

        ImageIcon image5 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image image105 = image5.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon image205 = new ImageIcon(image105);
        JLabel moreLabel = new JLabel(image205);
        moreLabel.setBounds(410, 20, 10, 30);
        panel.add(moreLabel);
        moreLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(f, "Not Functional");
            }
        });

        JLabel nameLabel = new JLabel("Server");
        nameLabel.setBounds(100, 20, 100, 18);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel);

        JLabel statusLabel = new JLabel("Online");
        statusLabel.setBounds(100, 38, 100, 18);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setForeground(Color.WHITE);
        panel.add(statusLabel);

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(14, 22, 33));
        JScrollPane scrollPane = new JScrollPane(textPanel);
        scrollPane.setBounds(5, 75, 440, 570);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        f.add(scrollPane);

        text = new JTextField();
        text.setBounds(5, 655, 320, 40);
        text.setFont(new Font("Tahoma", Font.PLAIN,16));
        text.addActionListener(this);
        f.add(text);

        sendButton = new JButton("SEND");
        sendButton.setBounds(330,655,115,40);
        sendButton.setBackground(new Color(7, 94,84));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("Tahoma",Font.PLAIN,14));
        sendButton.addActionListener(this);
        f.add(sendButton);

        //!main Frame
        f.setSize(450,700);
        f.setUndecorated(true);
        f.setLocation(200, 50);
        f.setLayout(null);
        //getContentPane().setBackground(new Color(178, 178, 178));
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendButton || e.getSource() == text) {
            try {
                String tempText = text.getText();
                if(tempText.trim().isEmpty()) return;
                text.setText("");

                JPanel tempTextPanel = formatLabel(tempText);

                JPanel chatRightSide = new JPanel(new BorderLayout());
                chatRightSide.add(tempTextPanel, BorderLayout.LINE_END);
                chatRightSide.setOpaque(false);

                vertical.add(chatRightSide);
                vertical.add(Box.createVerticalStrut(15));

                dos.writeUTF(tempText);

                textPanel.add(vertical, BorderLayout.PAGE_START);
                textPanel.revalidate();
                textPanel.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static JPanel formatLabel(String tempText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel msgLabel = new JLabel("<html><p style = \"width: 150px\">"+tempText+"</html>");
        msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        msgLabel.setBackground(new Color(37, 211, 102));
        msgLabel.setOpaque(true);
        msgLabel.setBorder(new EmptyBorder(13,15,13,50));
        panel.add(msgLabel);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        JLabel timeLabel = new JLabel("12:00");
        timeLabel.setText(dateFormat.format(calendar.getTime()));
        timeLabel.setForeground(Color.WHITE);
        panel.add(timeLabel);
        panel.setOpaque(false);

        return panel;
    }

    public static void main(String[] args) {
        new Server();

        try {
            ServerSocket serverSocket = new ServerSocket(6969);
            while(true) {
                Socket s = serverSocket.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());

                while(true) {
                    String msg = dis.readUTF();
                    JPanel panel = formatLabel(msg);

                    JPanel leftSide = new JPanel(new BorderLayout());
                    leftSide.add(panel, BorderLayout.LINE_START);
                    leftSide.setOpaque(false);

                    vertical.add(leftSide);
                    vertical.add(Box.createVerticalStrut(15));

                    textPanel.add(vertical, BorderLayout.PAGE_START);

                    f.validate();
                    f.repaint();
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
