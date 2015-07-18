package Chart;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageProcess extends Frame{
	Image im, tmp;
	int iw, ih;
	int[] pixels;
	boolean flag_load = false;
	boolean flag_grey = false;
	String fileopen = null, filename = null;// ���ڴ�Ŵ��ļ���ַ ���ļ���
	
	public ImageProcess(){
		this.setTitle("ֱ��ͼ���⻯");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		);
	   
	    setLayout(new FlowLayout());
	    Button load = new Button("��ͼ��");
	    Button grey = new Button("�Ҷ�ͼ��");
	    Button run = new Button("���⻯");
	    
	    
	    setLayout(new FlowLayout());
	    add(load);
	    add(grey);
	    add(run);
	    
	  //��ť�Ķ�������  ��ͼ��
    	load.addActionListener(new ActionListener(){    
    		public void actionPerformed(ActionEvent e){
    			try {
					jLoad_ActionPerformed(e);
				} catch (IOException e1) {	
					e1.printStackTrace();
				}
    		}
    	});
    	//��ť�Ķ�������  �ҶȻ�
    	grey.addActionListener(new ActionListener(){    
    		public void actionPerformed(ActionEvent e){
    			try {
					jGrey_ActionPerformed(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    		}
    	});
    	//��ť�Ķ������� ֱ��ͼ���⻯
    	run.addActionListener(new ActionListener(){    
    		public void actionPerformed(ActionEvent e){
    			jRun_ActionPerformed(e);
    		}
    	});   
	}
	
	
	//��ť������ʵ��  ��ͼ��
    public void jLoad_ActionPerformed(ActionEvent e) throws IOException{    
    	FileDialog filedialog_open;
		
		filedialog_open = new FileDialog(this, "���ļ��Ի���", FileDialog.LOAD);
		filedialog_open.setFile("*.jpg");
		filedialog_open.setVisible(true);

		fileopen = filedialog_open.getDirectory();// �����ļ��Ի�������ʾ���ļ�������Ŀ¼
		filename = filedialog_open.getFile();// ���ص�ǰ�ļ��Ի�������ʾ���ļ������ַ�����

    	File inputFile = new File(fileopen+filename);
        BufferedImage input = ImageIO.read(inputFile);
        
        im = input;
		tmp = input;
        flag_load = true;
        repaint();
    }

   
    //��ť������ʵ��  �ҶȻ�
    public void jGrey_ActionPerformed(ActionEvent e) throws IOException{
		if(flag_load){
		    File inputFile = new File(fileopen+filename);
  			BufferedImage input = ImageIO.read(inputFile);//ImageIO�����һЩ�������� ImageReader �� ImageWriter �Լ�ִ�м򵥱���ͽ���ľ�̬��ݷ�����
			
		  	iw = input.getWidth(this);
  		    ih = input.getHeight(this);
  		    pixels = new int[iw*ih];
  		    
			BufferedImage grayImage = new BufferedImage(iw, ih, BufferedImage.TYPE_BYTE_GRAY);
			for(int i=0; i<iw; i++){
  				for(int j=0; j<ih; j++){
  					int rgb = input.getRGB(i, j);  
  					int grey = (int) (0.3*((rgb&0xff0000 )>>16)+0.59*((rgb&0xff00 )>>8)+0.11*((rgb&0xff))); 
  					rgb = 255<<24|grey<<16|grey<<8|grey;
  					grayImage.setRGB(i, j, rgb);
  				}
  			}
  			tmp = grayImage;
  			try{
    			PixelGrabber pg = new PixelGrabber(tmp,0,0,iw,ih,pixels,0,iw);
    			pg.grabPixels();
    		}catch(InterruptedException e3){
    			e3.printStackTrace();
    	}
  			flag_grey = true;
  			repaint();
			} else{
				JOptionPane.showMessageDialog(null, "�ȵ������ͼ�񡱣�","��ʾ��",
						JOptionPane.WARNING_MESSAGE);
			}
	}

    
    
  //��ť������ʵ�� ֱ��ͼ���⻯ 
    public void jRun_ActionPerformed(ActionEvent e){
    	if(flag_load&&flag_grey){
    		try{
    			PixelGrabber pg = new PixelGrabber(tmp,0,0,iw,ih,pixels,0,iw);
    			pg.grabPixels();
    		}catch(InterruptedException e3){
    			e3.printStackTrace();
    	}
    	BufferedImage greyImage = new BufferedImage(iw, ih, 
					BufferedImage.TYPE_BYTE_GRAY);
    	//��ȡͼ���ֱ��ͼ
    	int[] histogram = new int[256];
    	for(int i=0; i<ih-1; i++){
    		for(int j=0; j<iw-1; j++){
    			int grey = pixels[i*iw+j]&0xff;
    			histogram[grey]++;
    		}
    	}
    	//ֱ��ͼ���⻯
    	double a = (double)255/(iw*ih);
    	double[] c = new double [256];
    	c[0] = (a*histogram[0]);
    	for(int i=1; i<256; i++){
    		c[i] = c[i-1]+(int)(a*histogram[i]);
    	}
    	for(int i=0; i<ih; i++){
    		for(int j=0; j<iw; j++){
    			int grey = pixels[i*iw+j]&0x0000ff;
    			int hist = (int)(c[grey]);
    			pixels[i*iw+j] = 255<<24|hist<<16|hist<<8|hist;
    			greyImage.setRGB(j, i, pixels[i*iw+j]);
    		}
    	}
    	tmp = greyImage;
  		flag_load = true;
  		repaint();
    	}else{
			JOptionPane.showMessageDialog(null, "�ȵ������ͼ�񡱣�","��ʾ��",
					JOptionPane.WARNING_MESSAGE);
		}
    }


    //��ͼ����
  	public void paint(Graphics g){

  			g.drawImage(tmp,50,50,this);
  	}
}