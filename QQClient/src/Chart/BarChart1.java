package Chart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
//��������ͼ����һ��Ч������ʵ����һ����ȶ�ֻ�����ݼ������˱仯�����������仯
public class BarChart1 {
	ChartFrame frame1;
	public  BarChart1(){
		CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
       		                 "�øж�", // ͼ�����
                            "����", // Ŀ¼�����ʾ��ǩ
                            "�ø�", // ��ֵ�����ʾ��ǩ
                            dataset, // ���ݼ�
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                            false,          // �Ƿ����ɹ���
                            false           // �Ƿ�����URL����
                            );
        
        //�����￪ʼ
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
          chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
          
          //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������
          
         frame1=new ChartFrame("�øж�",chart);        //����Ҳ������chartFrame,����ֱ������һ��������Frame
         
	}
	   private static CategoryDataset getDataSet() {
           DefaultCategoryDataset dataset = new DefaultCategoryDataset();
           dataset.addValue(100, "С��", "С��");
           dataset.addValue(200, "С��", "С��");
           dataset.addValue(300, "С��", "С��");
           dataset.addValue(400, "С��", "С��");
           dataset.addValue(500, "����", "����");
           dataset.addValue(600,"��ƥ��", "��ƥ��");
           return dataset;
}

public ChartFrame getChartFrame(){
	return frame1;
	
}
}

