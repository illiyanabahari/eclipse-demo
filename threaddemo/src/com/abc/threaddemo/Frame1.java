package com.abc.threaddemo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
public class Frame1 extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text1;
	private JButton btn2;
	private JTextField text2;
	public static void main(String[] args)
	{
		Frame1 frame = new Frame1();
		frame.setVisible(true);
	}
	public Frame1()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn1 = new JButton("start");
		btn1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					@Override
					public void run() {
						for(int i=0;i<100000;i++)
						{
							final int value = i;
							SwingUtilities.invokeLater(
									() -> text1.setText(String.valueOf(value)));
							try
							{
						        Thread.sleep(1);
						    } catch(Exception ex){}
						}						
					}
				}).start();
			}
		});
		btn1.setBounds(76, 66, 84, 20);
		contentPane.add(btn1);
		
		text1 = new JTextField();
		text1.setBounds(197, 67, 177, 18);
		contentPane.add(text1);
		text1.setColumns(10);
		
		btn2 = new JButton("start");
		btn2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					@Override
					public void run() {
						for(int i=0;i<100000;i++)
						{
							final int value = i;
							SwingUtilities.invokeLater(
									() -> text2.setText(String.valueOf(value)));
							try {
						        Thread.sleep(1);
						    } catch(Exception ex){}
						}		
					}
				}).start();
			}
		});
		btn2.setBounds(76, 123, 84, 20);
		contentPane.add(btn2);
		
		text2 = new JTextField();
		text2.setColumns(10);
		text2.setBounds(197, 124, 177, 18);
		contentPane.add(text2);
	}
}
