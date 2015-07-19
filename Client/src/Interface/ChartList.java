package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import Chart.*;
public class ChartList extends JFrame implements ActionListener{
	private static final long serialVersionUID = 8813576290868649260L;
	//����˵�����,���ڳ���ҪҪ�õ��Ĳ˵������������
    private JMenuBar mb=new JMenuBar();//�˵���
    private JMenu file=new JMenu("�ļ�");//�ļ��˵�
    private JMenuItem feeling1=new JMenuItem("�øж�");//���ļ����˵��µġ��½����˵���
    private JMenuItem Weight=new JMenuItem("���ѱ���");
    private JMenuItem APPERATIME=new JMenuItem("���ѳ�û");
    
    private JMenu edit=new JMenu("�༭");
    private JRadioButtonMenuItem editLeft=new JRadioButtonMenuItem("�����");
    private JRadioButtonMenuItem editRight=new JRadioButtonMenuItem("�Ҷ���");
    
    private JCheckBoxMenuItem editBold=new JCheckBoxMenuItem("�Ӵ�");
    private JCheckBoxMenuItem editItalic=new JCheckBoxMenuItem("б��");
    
    private JMenu help=new JMenu("����");
    private JMenuItem showHelp=new JMenuItem("˵��");
    //����һ��JPanel����pCenter�������м䲿�֣�����
    private JPanel pCenter=new JPanel();
    //�±��ǵ����˵�
    private JPopupMenu jpm=new JPopupMenu("�����˵�");
    private JMenuItem popItem1=new JMenuItem("�����˵���һ");
    private JMenuItem popItem2=new JMenuItem("�����˵����");
    private void init(){
    	//���˵����뵽�����ϣ�һ��ѳ�ʼ�����ڵĴ���ŵ��Զ����init�����У��ڹ���
    	//�����н�����init������
    	this.setTitle("���ƺ�����Ϣͼ��");
    	mb.add(file);
    	file.add(feeling1);file.add(Weight);
    	file.addSeparator();file.add(APPERATIME);
    	
    	//*Ϊ��״ͼ��Ӽ���
    	feeling1.addActionListener(this);
    	feeling1.setActionCommand("bartchart");
    	//*��״ͼ
    	Weight.addActionListener(this);
    	Weight.setActionCommand("piechart");
    	//*����һ����״ͼ
    	APPERATIME.addActionListener(this);
    	APPERATIME.setActionCommand("bartchart1");
    	
    	
    	mb.add(edit);edit.add(editLeft);edit.add(editRight);
    	ButtonGroup bg=new ButtonGroup();
    	bg.add(editLeft);bg.add(editRight);
    	edit.addSeparator();//���һ���˵��ָ��������ڽ��˵������ָܷ�����Ч���ÿ�
    	edit.add(editBold);edit.add(editItalic);
    	
     	mb.add(help);help.add(showHelp);
    	this.add(mb,BorderLayout.NORTH);
    	//����Ѳ˵����Ĳ˵������尲װ���
    	//����ѵ����˵�����ӵ������˵�jpm��
    	jpm.add(popItem1);
		jpm.add(popItem2);
    	//���ô��ڵĹرհ�ť����
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	
//    	this.add(pCenter,BorderLayout.CENTER);//����ǽ��������������õģ���������
    	//�±��������Ҽ��¼���Ŀ����ʾ�����˵�
    	this.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent me){
    			if(me.getButton()==MouseEvent.BUTTON3){
    				//����굥�����������굥��������λ����ʾ�����˵�
    				jpm.show(me.getComponent(), me.getX(), me.getY());
    				//JOptionPane.showMessageDialog(null,"���λ����("+me.getX()+","+me.getY()+")","",JOptionPane.OK_OPTION);
    			}
    		}
    	});
    	//���ô��ڴ�С����ʾ״̬
    	this.setSize(500,400);
    	this.setVisible(true);
    }
    public ChartList(){
    	init();
    }
    
    public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("bartchart")){
		    System.out.println("I'M in chartlist listener bartchart 103");
			if(e.getActionCommand().equals("bartchart")){
			    System.out.println("I'M in chart listener 106");
				JFrame frame = new BarChart1().getChartFrame();
				frame.setVisible(true);
				frame.setSize(300, 300);
			}
		}
		if(e.getActionCommand().equals("piechart")){
			 System.out.println("I'M in chart listener 110");
				JFrame frame = new PieChart().getChartFrame();
				frame.setVisible(true);
				frame.setSize(300, 300);
		}
		if(e.getActionCommand().equals("bartchart1")){
			 System.out.println("I'M in chart listener 120");
				JFrame frame = new BarChart().getChartFrame();
				frame.setVisible(true);
				frame.setSize(300, 300);
		}
	}

}
