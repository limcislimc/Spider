package edu.csuft.zhangan.spider;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * ����
 * @author limc
 *
 */
public class Spider{
	/**
	 * �洢����ӰƬ���б�
	 */
	List<Film> filmList = new ArrayList<>();  //CopyOnWriteArrayList
	
	
	String url = "https://movie.douban.com/top250?start=";
	
    int i=0;
	
	
	/**
	 * ����Ĺ��췽��
	 */
	public Spider() {
		// TODO Auto-generated constructor stub
		
	}
	public void runs() {
			
		/**
		 * �̳߳�
		 */
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		
		for(int i=0; i<10; i++) {
			 pool.submit(new MyThread(url  + 25*i));	 
		}
		
		pool.shutdown();
		
		
		//�ȴ��߳��������
		while(true) {
			if(pool.isTerminated()) {
				break;
			}else {
				
			}
		}
		
		//����
		Collections.sort(filmList);	
		
		//����ͼƬ
		
		
	
		// �洢�����ݿ�
		
					try {
						// ��������
						SqlSessionFactory factory = 
								new SqlSessionFactoryBuilder().build(
										new FileReader("config.xml"));
						
						SqlSession session = factory.openSession();
						
						// ����˽ӿڵľ���ʵ�֣����䣩
						FilmMapper mapper = session.getMapper(FilmMapper.class);
						
						for (Film f : filmList) {
							mapper.insert(f);
						}
						session.commit();
						session.close();
						System.out.println("�洢�ɹ�");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.err.println("�������ݿ�ʧ��");
					}
		
		
	}
	


	/**
	 * ���List
	 */
	public void print() {
		for(Film f : filmList)
			System.out.println(f);
			
	}
	
	/**
	 * �߳���
	 * @author limc
	 *
	 */
	class MyThread extends Thread{
		String url;
		public MyThread(String url) {
			this.url = url;
		}
		public void run() {
			//ץȡ����
			Document doc;
			try {
				doc = Jsoup.connect(url).timeout(10000).get();
				
//				System.out.println(doc.title());
//				System.out.println(doc.text());
//				System.out.println(doc.html());
				
				Elements items	 = doc.select(".grid_view .item");
				
				//id + title + director + actors + year + area + style + star + rating + poster + quote
				for(Element item : items) {
					Film film = new Film();
					
				
					
						film.title = item.select(".info .hd .title").get(0).text();
					
					
				
						film.poster = item.select("img").get(0).attr("src");

							film.quote = item.select(".info .bd .inq").text();//1
							//��������
							String rating = item.select(".star span").last().text();
							int rend = rating.indexOf("��");
							film.rating = Integer.parseInt(rating.substring(0,rend));
							
							
							String star	= item.select(".star span").get(1).text();
							String id = item.select("em").text();
							film.star = Double.parseDouble(star);
							film.id =Integer.parseInt(id);

						
					
						String info = item.select(".info .bd p").get(0).text();
						Matcher matcher = Pattern.compile("\\d\\d\\d\\d").matcher(info);
						matcher.find();
						
						int yearindex = matcher.start();
						
						
						int dirend;
						
							dirend = info.indexOf("����");
							if(dirend != -1) {
								film.director = info.substring(3,dirend);
								film.actors = info.substring(dirend+3,yearindex);
							}
							else if(info.indexOf("��") != -1) {
								dirend = info.indexOf("��");
								film.director = info.substring(3,dirend);
								film.actors = "������Ϣ";
							}
							else {
								film.director = info.substring(3,yearindex-1);
								film.actors = "������Ϣ";
							}
						
						
					
						
						film.year = Integer.parseInt(info.substring(matcher.start(), matcher.start()+4));
						
						String a = info.substring( matcher.start()+6); //�����������������
						film.area = (a.split("/"))[0];
						film.style = (a.split("/"))[1].substring(1);
				
					//��info�ֶν��зָ���ַ�������
					
					
					filmList.add(film);
					
					
					
					//����ͼƬ������
					
			        String path = "E:/keshe1/pics/"+film.title+".jpg";
			        try {
			            URL u = new URL(film.poster);
			            DataInputStream dataInputStream = new DataInputStream(u.openStream());
			 
			            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
			            ByteArrayOutputStream output = new ByteArrayOutputStream();
			 
			            byte[] buffer = new byte[1024];
			            int length;
			 
			            while ((length = dataInputStream.read(buffer)) > 0) {
			                output.write(buffer, 0, length);
			            }
			            fileOutputStream.write(output.toByteArray());
			            dataInputStream.close();
			            fileOutputStream.close();
			        } catch (MalformedURLException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				
				
//					System.out.println(film);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("???");
			}
		}
	}

}
