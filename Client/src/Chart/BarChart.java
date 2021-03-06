package Chart;

import java.awt.Font;

import javax.swing.JFrame;

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

public class BarChart extends JFrame{
	ChartFrame frame1;
	public  BarChart(){
		CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
       		                 "出没时间攻略", // 图表标题
                            "好友名字", // 目录轴的显示标签
                            "出没次数", // 数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,           // 是否显示图例(对于简单的柱状图必须是false)
                            false,          // 是否生成工具
                            false           // 是否生成URL链接
                            );
        
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
         domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
         domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
         ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
         rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
          chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
          chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
          
          //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
          
         frame1=new ChartFrame("好感度",chart);        //这里也可以用chartFrame,可以直接生成一个独立的Frame
         
	}
	   private static CategoryDataset getDataSet() {
           DefaultCategoryDataset dataset = new DefaultCategoryDataset();
           dataset.addValue(100, "8~12点", "小明");
           dataset.addValue(100, "12~16点", "小明");
           dataset.addValue(100, "16~20点", "小明");
           dataset.addValue(200, "20~24点", "小明");
           dataset.addValue(200, "8~12点", "小红");
           dataset.addValue(200, "12~16点", "小红");
           dataset.addValue(300, "16~20点", "小红");
           dataset.addValue(300, "20~24点", "小红");
           dataset.addValue(300, "8~12点", "小刚");
           dataset.addValue(400, "12~16点", "小刚");
           dataset.addValue(400, "16~20点", "小刚");
           dataset.addValue(400, "20~24点", "小刚");
           dataset.addValue(500, "8~12点", "小绿");
           dataset.addValue(500, "12~16点", "小绿");
           dataset.addValue(500, "16~20点", "小绿");
           dataset.addValue(400, "20~24点", "小绿");
           dataset.addValue(400, "8~12点", "小白");
           dataset.addValue(400, "12~16点", "小白");
           dataset.addValue(500, "16~20点", "小白");
           dataset.addValue(500, "20~24点", "小白");
           return dataset;
}
public ChartFrame getChartFrame(){
	return frame1;
	
}
}

