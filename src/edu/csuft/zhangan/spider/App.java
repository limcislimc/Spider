package edu.csuft.zhangan.spider;




/**
 * �����������
 * @author limc
 *
 */
public class App {
	public static void main(String[] args) {
		Spider spider = new Spider();
		spider.runs();
		spider.print();
		System.out.println(spider.filmList.size());
	}
}
